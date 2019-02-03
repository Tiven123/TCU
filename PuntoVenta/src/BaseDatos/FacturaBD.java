/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.DetalleFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Entidades.Factura;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author steven
 */
public class FacturaBD {

    public FacturaBD() {
    }

    public boolean crear(Factura factura) throws SQLException, Exception {
        PreparedStatement queryFactura = null;
        PreparedStatement queryDetalle = null;
        String sqlFactura = "INSERT INTO desarrollo.factura (fecha_hora, nombre_cliente, nombre_cajero, subtotal_exento, "
                + "subtotal_gravado, total, efectivo, tarjeta , monto_impuestos) VALUES (?,?,?,?,?,?,?,?,?);";
        String sqlDetalle = "INSERT INTO desarrollo.detalle_factura (id_factura, codigo_producto, cantidad) VALUES (?,?,?);";
        Connection con = Conexion.iniciar();
        try {
            con.setAutoCommit(false);
            //Factura
            queryFactura = con.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            queryFactura.setTimestamp(1, factura.getFecha_hora());
            queryFactura.setString(2, factura.getNombre_cliente());
            queryFactura.setString(3, factura.getNombre_cajero());
            queryFactura.setDouble(4, factura.getSubtotal_exento());
            queryFactura.setDouble(5, factura.getSubtotal_gravado());
            queryFactura.setDouble(6, factura.getTotal());
            queryFactura.setDouble(7, factura.getEfectivo());
            queryFactura.setDouble(8, factura.getTarjeta());
            queryFactura.setDouble(9, factura.getMonto_impuestos());
            int resultadoFacturas = queryFactura.executeUpdate();
            if (resultadoFacturas == 0) {
                throw new SQLException("Error al Generar Factura");
            }
            ResultSet generatedKeys = queryFactura.getGeneratedKeys();
            int idFactura;
            while (generatedKeys.next()) {
                idFactura = generatedKeys.getInt(1);
                factura.setId(idFactura);
                System.out.println("" + idFactura);
            }
            //Detalle de Factura
            queryDetalle = con.prepareStatement(sqlDetalle);
            for (int i = 0; i < factura.getLista().size(); i++) {
                queryDetalle.setInt(1, factura.getId());
                queryDetalle.setString(2, factura.getLista().get(i).getCodigoProducto());
                queryDetalle.setInt(3, factura.getLista().get(i).getCantidad());
                int resultadoDetalle = queryDetalle.executeUpdate();
                if (resultadoDetalle == 0) {
                    throw new SQLException("Error al Guardar Lineas");
                }
            }
            con.commit();
            
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.err.print("La transacción se deshace.");
                    con.rollback();
                    return false;
                } catch (SQLException excep) {
                    excep.printStackTrace();
                    return false;
                }
            }
        } finally {
            if (queryFactura != null) {
                queryFactura.close();
            }
            if (queryDetalle != null) {
                queryDetalle.close();
            }
            con.setAutoCommit(true);
        }
        con.close();
        return true;

    }

    public LinkedList<Factura> buscarTodos() {
        LinkedList<Factura> resultado = new LinkedList<>();
        try (Connection con = Conexion.iniciar()) {
            String sqlFactura = "SELECT * FROM desarrollo.factura";
            String sqlDetalle = "SELECT * FROM desarrollo.detalle_factura WHERE id_factura = ?";
            PreparedStatement queryFactura = con.prepareStatement(sqlFactura);
            ResultSet resultadoFactura = queryFactura.executeQuery();
            LinkedList<DetalleFactura> listaDetalle = new LinkedList<>();
            while (resultadoFactura.next()) {
                Factura factura = new Factura();
                factura.setId(resultadoFactura.getInt("id"));
                factura.setFecha_hora(resultadoFactura.getTimestamp("fecha_hora"));
                factura.setNombre_cliente(resultadoFactura.getString("nombre_cliente"));
                factura.setNombre_cajero(resultadoFactura.getString("nombre_cajero"));
                factura.setSubtotal_exento(resultadoFactura.getDouble("subtotal_exento"));
                factura.setSubtotal_gravado(resultadoFactura.getDouble("subtotal_gravado"));
                factura.setTotal(resultadoFactura.getDouble("total"));
                factura.setEfectivo(resultadoFactura.getDouble("efectivo"));
                factura.setTarjeta(resultadoFactura.getDouble("tarjeta"));
                factura.setMonto_impuestos(resultadoFactura.getDouble("monto_impuestos"));
                //detalle
                PreparedStatement queryDetalle = con.prepareStatement(sqlDetalle);
                queryDetalle.setInt(1, factura.getId());
                ResultSet resultadoDetalle = queryDetalle.executeQuery();
                while (resultadoDetalle.next()) {
                    listaDetalle = new LinkedList<>();
                    DetalleFactura detalleFactura = new DetalleFactura();
                    detalleFactura.setCodigoProducto(resultadoDetalle.getString("codigo_producto"));
                    detalleFactura.setCantidad(resultadoDetalle.getInt("cantidad"));
                    listaDetalle.add(detalleFactura);
                }
                factura.setLista(listaDetalle);
                resultado.add(factura);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;

    }

    public LinkedList<Factura> buscarFecha() {
        LinkedList<Factura> resultado = new LinkedList<>();
        return resultado;
    }

    public LinkedList<Factura> buscarEmpleado() {
        LinkedList<Factura> resultado = new LinkedList<>();
        return resultado;
    }

    public LinkedList<Factura> buscarFechaEmpleado() {
        LinkedList<Factura> resultado = new LinkedList<>();
        return resultado;
    }

    public LinkedList<Factura> buscarFactura() {
        LinkedList<Factura> resultado = new LinkedList<>();
        return resultado;
    }

    public boolean modificar() {
        return true;
    }

    public boolean eliminar() {
        return true;
    }

}
