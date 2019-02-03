/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Entidades.Producto;
import Validaciones.ProductoVali;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Steven Ortiz
 */
public class VistaProducto extends javax.swing.JFrame {
    
    ProductoVali productoValidaciones = new ProductoVali();
    JFrame anterior;
    private int id;

    /**
     * Creates new form Usuario
     */
    public VistaProducto() {
        initComponents();
        ajustarVentana();
    }
    
    public VistaProducto(JFrame vistaAnterior) {
        initComponents();
        ajustarVentana();
        anterior = vistaAnterior;
    }
    private void ajustarVentana() {
        cargarTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SOPOST-Mantenimiento Producto");
    }
    
    private void cargarTabla() {
        limpiarTabla();
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        LinkedList<Producto> producto = productoValidaciones.buscar("");
        for (int i = 0; i < producto.size(); i++) {
            
            String[] fila = {
                producto.get(i).getId() + "",
                producto.get(i).getCodigo(),
                producto.get(i).getDescripcion(),
                producto.get(i).getPrecio_compra() + "",
                producto.get(i).getPrecio_venta() + "",
                producto.get(i).isImpuestos() + "",
                producto.get(i).getPorcentaje_ganancia() + "",
                producto.get(i).getCantidad() + ""};
            modelo.addRow(fila);
        }
        jtable.setModel(modelo);
    }
    
    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        int filas = modelo.getRowCount();
        
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        jtable.setModel(modelo);
    }
    
    private void limpiar() {
        cargarTabla();
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtPorcentaje.setText("");
        txtBuscar.setText("");
        txtCantidad.setText("");
    }
    
    private Producto crearProducto() {
        //Se crea producto
        Producto NuevoProducto = new Producto();

        //Se obtienen los datos de cada campo del formulario y se le asignan al objeto producto
        NuevoProducto.setCodigo(txtCodigo.getText().toUpperCase().trim());
        NuevoProducto.setDescripcion(txtDescripcion.getText().toUpperCase().trim());
        if (txtPrecioCompra.getText().equals("")) {
            txtPrecioCompra.setText("0.0");
        }
        if (txtPorcentaje.getText().equals("")) {
            txtPorcentaje.setText("0");
        }
        try {
            NuevoProducto.setPrecio_compra(Double.parseDouble(txtPrecioCompra.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inserte precio compra: " + e.getMessage());
        }
        try {
            NuevoProducto.setPrecio_venta(Double.parseDouble(txtPrecioVenta.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inserte precio venta: " + e.getMessage());
        }
        try {
            NuevoProducto.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inserte la cantidad: " + e.getMessage());
        }
        try {
            NuevoProducto.setPorcentaje_ganancia(Double.parseDouble(txtPorcentaje.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inserte el porcentaje: " + e.getMessage());
        }
        NuevoProducto.setImpuestos(cbImpuestos.isSelected());
        return NuevoProducto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblPrecioCompra = new javax.swing.JLabel();
        lblPrecioVenta = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        lblPorcentaje = new javax.swing.JLabel();
        cbImpuestos = new javax.swing.JCheckBox();
        lblValor = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JFormattedTextField();
        txtPrecioVenta = new javax.swing.JFormattedTextField();
        txtPorcentaje = new javax.swing.JFormattedTextField();
        txtCantidad = new javax.swing.JFormattedTextField();
        lblCalcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 500));

        lblCodigo.setText("Codigo:");

        lblDescripcion.setText("Descripcion:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        lblPrecioCompra.setText("Precio Compra:");

        lblPrecioVenta.setText("Precio Venta:");

        lblTitulo.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblTitulo.setText("Mantenimiento De Productos");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Descripcion", "Precio Compra", "Precio Venta", "Impuestos", "Porcentaje", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtable);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblPorcentaje.setText("Porcentaje:");

        cbImpuestos.setText("Impuestos");

        lblValor.setText("Valor a Buscar:");

        lblCantidad.setText("Cantidad:");

        txtPrecioCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCompraActionPerformed(evt);
            }
        });

        txtPrecioVenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtPorcentaje.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblCalcular.setText("Calcular");
        lblCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(btnVolver)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPrecioCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lblPrecioVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcentaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCantidad)
                                .addGap(4, 4, 4)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbImpuestos)))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCalcular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCrear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver))
                    .addComponent(lblTitulo))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioCompra)
                    .addComponent(lblPrecioVenta)
                    .addComponent(lblPorcentaje)
                    .addComponent(lblCantidad)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorcentaje)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbImpuestos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear)
                    .addComponent(btnModificar)
                    .addComponent(lblValor)
                    .addComponent(lblCalcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtBuscar.getAccessibleContext().setAccessibleName("");
        txtBuscar.getAccessibleContext().setAccessibleDescription("");
        txtCantidad.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     *
     * @param evt
     */
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        Producto nuevo = crearProducto();
        try {
            int respuesta = JOptionPane.showConfirmDialog(null, nuevo.detalles(), "Verificación de datos", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (respuesta == 0) {
                try {
                    if (productoValidaciones.crear(nuevo)) {
                        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar operacion");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        limpiar();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        limpiarTabla();
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        LinkedList<Producto> producto = productoValidaciones.buscar("");
        String nombreBuscar = txtBuscar.getText().toUpperCase().trim();
        for (int i = 0; i < producto.size(); i++) {
            String descripcion = " " + producto.get(i).getDescripcion();
            if (descripcion.indexOf(nombreBuscar) > 0) {
                String[] fila = {
                    producto.get(i).getId() + "",
                    producto.get(i).getCodigo(),
                    producto.get(i).getDescripcion(),
                    producto.get(i).getPrecio_compra() + "",
                    producto.get(i).getPrecio_venta() + "",
                    producto.get(i).isImpuestos() + "",
                    producto.get(i).getPorcentaje_ganancia() + "",
                    producto.get(i).getCantidad() + ""};
                modelo.addRow(fila);
            }
        }
        jtable.setModel(modelo);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableMouseClicked
        // TODO add your handling code here:
        int fila = jtable.getSelectedRow();
        id = Integer.parseInt(jtable.getValueAt(fila, 0).toString());
        txtCodigo.setText(jtable.getValueAt(fila, 1).toString());
        txtDescripcion.setText(jtable.getValueAt(fila, 2).toString());
        txtPrecioCompra.setText(jtable.getValueAt(fila, 3).toString());
        txtPrecioVenta.setText(jtable.getValueAt(fila, 4).toString());
        cbImpuestos.setSelected(Boolean.parseBoolean(jtable.getValueAt(fila, 5).toString()));
        txtPorcentaje.setText(jtable.getValueAt(fila, 6).toString());
        txtCantidad.setText(jtable.getValueAt(fila, 7).toString());
    }//GEN-LAST:event_jtableMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        anterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Producto nuevo = crearProducto();
        nuevo.setId(id);
        try {
            int respuesta = JOptionPane.showConfirmDialog(null, nuevo.detalles(), "Verificación de datos", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (respuesta == 0) {
                try {
                    if (productoValidaciones.crear(nuevo)) {
                        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar operacion");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        limpiar();

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Error debe seleccionar un producto");
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (respuesta == 0) {
                try {
                    if (productoValidaciones.eliminar(id)) {
                        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar operacion");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtPrecioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCompraActionPerformed

    }//GEN-LAST:event_txtPrecioCompraActionPerformed

    private void lblCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCalcularActionPerformed
        double compra = 0.0;
        try {
            compra = Double.parseDouble(txtPrecioCompra.getText());
        } catch (Exception e) {
        }
        double venta = 0.0;
        try {
            venta = Double.parseDouble(txtPrecioVenta.getText());
        } catch (Exception e) {
        }
        double porcentaje = 0;
        try {
            porcentaje = Double.parseDouble(txtPorcentaje.getText());
        } catch (Exception e) {
        }
        if (venta > 0 & porcentaje > 0 & compra == 0) {
            porcentaje /= 100;
            compra = venta / (1 + porcentaje);
            BigDecimal bd = new BigDecimal(compra);
            bd = bd.setScale(2, RoundingMode.HALF_UP);	// Ponemos 2 decimales
            txtPrecioCompra.setText(bd.doubleValue() + "");
        } else if (venta > 0 & compra > 0 & porcentaje == 0) {
            if (venta > compra) {
                double ganancia = venta - compra;
            porcentaje = (ganancia / compra * 100);
            BigDecimal bd = new BigDecimal(porcentaje);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            txtPorcentaje.setText(bd.doubleValue() + "");
            } else {
                JOptionPane.showMessageDialog(null, "Error! \n El precio de venta debe ser mayor al de compra");
            }
            
        } else if (compra > 0 & porcentaje > 0 & venta == 0) {
            venta = compra * (1 + (porcentaje / 100));
            BigDecimal bd = new BigDecimal(venta);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            txtPrecioVenta.setText(bd.doubleValue() + "");
        } else if (venta > 0 & compra > 0 & porcentaje > 0) {
            JOptionPane.showMessageDialog(null, "Error! \n Debe faltar al menos uno de los siguientes valores: \n *Precio Compra \n *Precio Venta \n *Porcentaje Gancia");
        } else {
            JOptionPane.showMessageDialog(null, "Error! \n Debe insertar al menos dos de los siguientes valores: \n *Precio Compra \n *Precio Venta \n *Porcentaje Gancia");
        }
    }//GEN-LAST:event_lblCalcularActionPerformed

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
            java.util.logging.Logger.getLogger(Producto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox cbImpuestos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    private javax.swing.JButton lblCalcular;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblPrecioCompra;
    private javax.swing.JLabel lblPrecioVenta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtPorcentaje;
    private javax.swing.JFormattedTextField txtPrecioCompra;
    private javax.swing.JFormattedTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables
}