/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDatos.Conexion;
import Entidades.DetalleFactura;
import Entidades.Factura;
import Entidades.Producto;
import Reportes.inventarios;
import Validaciones.FacturaVali;

import Validaciones.ProductoVali;
import com.sun.jndi.ldap.Connection;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author steven
 */
public class Principal extends javax.swing.JFrame {

    private final ProductoVali productoVali = new ProductoVali();
    private Factura factura = new Factura();
    private boolean pagoefectivo;
    private boolean pagotarjeta;
    private LinkedList<DetalleFactura> listaDetalle;
    private FacturaVali facturaVali = new FacturaVali();

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        ajustarVentana();
        ajustarTabla();
        fechaHora();
    }

    private void ajustarVentana() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SOPOST-Principal");
        txtCodigo.requestFocus();
        txtCodigo.selectAll();
        cbEfectivo.setSelected(true);
    }

    private void ajustarTabla() {
        tbDetalle.getColumnModel().getColumn(0).setMaxWidth(0);
        tbDetalle.getColumnModel().getColumn(0).setMinWidth(0);
        tbDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tbDetalle.getColumnModel().getColumn(2).setPreferredWidth(400);
    }

    private void agregarLinea() {
        if (cbBuscar.getItemCount() > 0) {
            btnFacturar.setEnabled(true);
            Producto nuevo = (Producto) cbBuscar.getSelectedItem();
            DefaultTableModel modelo = (DefaultTableModel) tbDetalle.getModel();
            boolean encontrado = true;
            for (int i = 0; i < tbDetalle.getRowCount(); i++) {
                String codigo = tbDetalle.getValueAt(i, 0).toString();
                if (codigo.equals(nuevo.getCodigo())) {
                    int cantidad = Integer.parseInt(tbDetalle.getValueAt(i, 1).toString());
                    cantidad += 1;
                    double precio = Double.parseDouble(tbDetalle.getValueAt(i, 3).toString());
                    double total = precio * cantidad;
                    tbDetalle.setValueAt(cantidad + "", i, 1);
                    tbDetalle.setValueAt(total + "", i, 4);
                    encontrado = false;
                }
            }

            if (encontrado) {

                String[] fila = {
                    nuevo.getCodigo() + "",
                    "1",
                    nuevo.getDescripcion() + "",
                    nuevo.getPrecio_venta() + "",
                    nuevo.getPrecio_venta() + "",
                    nuevo.isImpuestos() + ""
                };
                modelo.addRow(fila);
                tbDetalle.setModel(modelo);
            }
            desglose();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados para esta busqueda", "No Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void desglose() {
        listaDetalle = new LinkedList<DetalleFactura>();
        double exento = 0;
        double gravado = 0;
        double impuestos = 0;
        double total = 0;
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            String impuesto = tbDetalle.getValueAt(i, 5).toString();
            if (impuesto.equals("true")) {
                gravado += Double.parseDouble(tbDetalle.getValueAt(i, 4).toString());
            } else {
                exento += Double.parseDouble(tbDetalle.getValueAt(i, 4).toString());
            }
            DetalleFactura detalle = new DetalleFactura();
            detalle.setCodigoProducto(tbDetalle.getValueAt(i, 0).toString());
            detalle.setCantidad(Integer.parseInt(tbDetalle.getValueAt(i, 1).toString()));
            listaDetalle.add(detalle);
        }
        gravado /= 1.13;
        BigDecimal gravado2 = new BigDecimal(gravado);
        gravado2 = gravado2.setScale(2, RoundingMode.HALF_UP);
        impuestos = gravado * 0.13;
        BigDecimal impuestos2 = new BigDecimal(gravado);
        impuestos2 = impuestos2.setScale(2, RoundingMode.HALF_UP);
        total = gravado + impuestos + exento;
        factura.setSubtotal_exento(exento);
        factura.setSubtotal_gravado(gravado2.doubleValue());
        factura.setMonto_impuestos(impuestos2.doubleValue());
        factura.setTotal(total);
        txtExento.setText("" + exento);
        txtGravado.setText("" + gravado2.doubleValue());
        txtImpuestos.setText("" + impuestos2.doubleValue());
        txtTotal.setText("" + total);
        fechaHora();
        factura.setLista(listaDetalle);
    }

    private void limpiar() {
        txtDescripcion.setText("");
        txtCodigo.setText("");
    }

    private void cargarBusqueda(LinkedList<Producto> lista) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        if (lista.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias", "No Resultados", JOptionPane.WARNING_MESSAGE);
        } else {

            for (int i = 0; i < lista.size(); i++) {

                modelo.addElement(lista.get(i));
            }
            cbBuscar.setModel(modelo);
        }
    }

    private void buscar() {
        if (!txtCodigo.getText().equals("") & txtDescripcion.getText().equals("")) {
            String codigo = txtCodigo.getText();
            LinkedList<Producto> lista = productoVali.buscarCodigo(codigo);
            cargarBusqueda(lista);
            agregarLinea();

        } else if (!txtDescripcion.getText().equals("") & txtCodigo.getText().equals("")) {
            String nombre = txtDescripcion.getText().trim().toUpperCase();
            LinkedList<Producto> lista = productoVali.buscarNombre(nombre);
            cargarBusqueda(lista);
        } else {
            JOptionPane.showMessageDialog(null, "Error Solo se puede reazilar la busqueda \npor una de las siguientes opciones: \n"
                    + "Codigo \nDescripcion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
    }

    private void fechaHora() {
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("hh:mm:ss a");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtHora.setText("" + hourFormat.format(date));
        txtFecha.setText("" + dateFormat.format(date));
        Timestamp fechaHora = new Timestamp(System.currentTimeMillis());
        factura.setFecha_hora(fechaHora);
    }

    private void pago() {
        if (cbEfectivo.isSelected() & !cbTarjeta.isSelected()) {
            String recibido = (String) JOptionPane.showInputDialog(null, "Ingrese el Monto Recibido", "Recepcion de Efectivo", JOptionPane.INFORMATION_MESSAGE);
            try {
                double efectivo = Double.parseDouble(recibido);
                if (efectivo >= factura.getTotal()) {
                    double vuelto = efectivo - factura.getTotal();
                    if (vuelto > 0) {
                        JOptionPane.showMessageDialog(null, "El vuelto es de " + vuelto, "Vuelto", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error Inserte un monto mayor al total ", "Error en monto", JOptionPane.ERROR_MESSAGE);
                    pago();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Inserte un monto Ejemplo: 100.25 ", "Error en monto", JOptionPane.ERROR_MESSAGE);
                pago();
            }
            //double efectivo = Double.parseDouble(recibido);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        lblHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        lblCajero = new javax.swing.JLabel();
        txtCajero = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbBuscar = new javax.swing.JComboBox<>();
        btnEliminarLinea = new javax.swing.JButton();
        cbEfectivo = new javax.swing.JCheckBox();
        lblPago = new javax.swing.JLabel();
        cbTarjeta = new javax.swing.JCheckBox();
        btnDescuento = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        lblExcento = new javax.swing.JLabel();
        txtExento = new javax.swing.JTextField();
        lblGravado = new javax.swing.JLabel();
        txtGravado = new javax.swing.JTextField();
        lblImpuestos = new javax.swing.JLabel();
        txtImpuestos = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCliente.setText("Cliente:");

        txtCliente.setText("Cliente Contado");

        lblFecha.setText("Fecha :");

        txtFecha.setEditable(false);
        txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtFecha.setText("12/02/2018");

        lblHora.setText("Hora:");

        txtHora.setEditable(false);
        txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        txtHora.setText("10:23:25 PM");

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cantidad", "Descripcion", "Precio", "Total", "Impuesto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbDetalleKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalle);

        lblCajero.setText("Cajero:");

        txtCajero.setEditable(false);
        txtCajero.setText("Steven");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblDescripcion.setText("Descripcion:");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });

        lblCodigo.setText("Codigo:");

        txtCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCodigo.setDropMode(javax.swing.DropMode.INSERT);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        cbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarActionPerformed(evt);
            }
        });

        btnEliminarLinea.setText("Eliminar Linea");
        btnEliminarLinea.setEnabled(false);

        cbEfectivo.setText("Efectivo");
        cbEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEfectivoActionPerformed(evt);
            }
        });

        lblPago.setText("Forma de Pago:");

        cbTarjeta.setText("Tarjeta");
        cbTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTarjetaActionPerformed(evt);
            }
        });

        btnDescuento.setText("Descuento");
        btnDescuento.setEnabled(false);
        btnDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescuentoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);

        btnFacturar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnFacturar.setText("Facturar");
        btnFacturar.setEnabled(false);
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        lblExcento.setText("Subtotal Excento:");

        txtExento.setEditable(false);

        lblGravado.setText("Subtotal Gravado:");

        txtGravado.setEditable(false);

        lblImpuestos.setText("Impuestos:");

        txtImpuestos.setEditable(false);

        lblTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTotal.setText("Total a Pagar:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jMenu1.setText("Mantenimientos");

        jMenuItem1.setText("Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Productos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Inventario");

        jMenuItem3.setText("Agregar");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reporte");

        jMenuItem4.setText("Inventario");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Cierre");

        jMenuItem5.setText("Caja");
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Dia");
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEfectivo)
                            .addComponent(lblPago)
                            .addComponent(cbTarjeta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarLinea)
                            .addComponent(btnDescuento))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblExcento)
                                    .addComponent(lblGravado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtExento)
                                    .addComponent(txtGravado, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblImpuestos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtImpuestos)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFacturar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1)
                    .addComponent(cbBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFecha))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescripcion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCajero))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCajero)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCajero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPago)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbEfectivo)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEliminarLinea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTarjeta)
                            .addComponent(btnDescuento)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblExcento)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGravado)
                            .addComponent(txtGravado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblImpuestos)
                            .addComponent(txtImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        VistaUsuario vista = new VistaUsuario(this);
        vista.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        VistaProducto vista = new VistaProducto(this);
        vista.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void cbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarActionPerformed
        agregarLinea();
    }//GEN-LAST:event_cbBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleKeyPressed

    }//GEN-LAST:event_tbDetalleKeyPressed

    private void tbDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleKeyTyped
        System.out.println("holi");
    }//GEN-LAST:event_tbDetalleKeyTyped

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void btnDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDescuentoActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        fechaHora();
        factura.setNombre_cliente(txtCliente.getText().toUpperCase().trim());
        factura.setNombre_cajero(txtCajero.getText().toUpperCase().trim());
        pago();
        try {
            if (facturaVali.crear(factura)) {
                JOptionPane.showMessageDialog(null, "Exito");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }


    }//GEN-LAST:event_btnFacturarActionPerformed

    private void cbEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEfectivoActionPerformed
        pagoefectivo = cbEfectivo.isSelected();
    }//GEN-LAST:event_cbEfectivoActionPerformed

    private void cbTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTarjetaActionPerformed
        pagotarjeta = cbTarjeta.isSelected();
    }//GEN-LAST:event_cbTarjetaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        inventarios nuevo= new inventarios();
        nuevo.iniciar();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JButton btnEliminarLinea;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JComboBox<String> cbBuscar;
    private javax.swing.JCheckBox cbEfectivo;
    private javax.swing.JCheckBox cbTarjeta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCajero;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblExcento;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblGravado;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblImpuestos;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtExento;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtGravado;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtImpuestos;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
