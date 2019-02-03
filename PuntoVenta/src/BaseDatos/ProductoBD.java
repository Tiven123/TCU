/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Steven Ortiz
 */
public class ProductoBD {

    public ProductoBD() {
    }

    public boolean crear(Producto nuevo) throws Exception {
        try (Connection con = Conexion.iniciar()) {
            String sql = "insert into desarrollo.producto(codigo, descripcion, "
                    + "precio_compra, precio_venta, impuestos, porcentaje_ganancia, cantidad) values(?,?,?,?,?,?,?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, nuevo.getCodigo());
            smt.setString(2, nuevo.getDescripcion());
            smt.setDouble(3, nuevo.getPrecio_compra());
            smt.setDouble(4, nuevo.getPrecio_venta());
            smt.setBoolean(5, nuevo.isImpuestos());
            smt.setDouble(6, nuevo.getPorcentaje_ganancia());
            smt.setInt(7, nuevo.getCantidad());
            return smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public LinkedList<Producto> buscarTodos() {
        LinkedList<Producto> lista = new LinkedList<>();
        try (Connection con = Conexion.iniciar()) {
            String sql = "select * from desarrollo.producto";
            PreparedStatement smt = con.prepareStatement(sql);
            ResultSet resultadoBD = smt.executeQuery();
            while (resultadoBD.next()) {
                Producto nuevo = new Producto();
                nuevo.setId(resultadoBD.getInt("id"));
                nuevo.setCodigo(resultadoBD.getString("codigo"));
                nuevo.setDescripcion(resultadoBD.getString("descripcion"));
                nuevo.setPrecio_compra(resultadoBD.getDouble("precio_compra"));
                nuevo.setPrecio_venta(resultadoBD.getDouble("precio_venta"));
                nuevo.setImpuestos(resultadoBD.getBoolean("impuestos"));
                nuevo.setPorcentaje_ganancia(resultadoBD.getDouble("porcentaje_ganancia"));
                nuevo.setCantidad(resultadoBD.getInt("cantidad"));

                lista.add(nuevo);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public LinkedList<Producto> buscarNombre(String nombre) {
         LinkedList<Producto> lista = new LinkedList<>();
        try (Connection con = Conexion.iniciar()) {
            String sql = "select * from desarrollo.producto where descripcion like ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, "%"+nombre+"%");
            ResultSet resultadoBD = smt.executeQuery();
            while (resultadoBD.next()) {
                Producto nuevo = new Producto();
                nuevo.setId(resultadoBD.getInt("id"));
                nuevo.setCodigo(resultadoBD.getString("codigo"));
                nuevo.setDescripcion(resultadoBD.getString("descripcion"));
                nuevo.setPrecio_compra(resultadoBD.getDouble("precio_compra"));
                nuevo.setPrecio_venta(resultadoBD.getDouble("precio_venta"));
                nuevo.setImpuestos(resultadoBD.getBoolean("impuestos"));
                nuevo.setPorcentaje_ganancia(resultadoBD.getDouble("porcentaje_ganancia"));
                nuevo.setCantidad(resultadoBD.getInt("cantidad"));

                lista.add(nuevo);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    public LinkedList<Producto> buscarCodigo(String codigo) {
         LinkedList<Producto> lista = new LinkedList<>();
        try (Connection con = Conexion.iniciar()) {
            String sql = "select * from desarrollo.producto where codigo like ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, codigo+"%");
            ResultSet resultadoBD = smt.executeQuery();
            while (resultadoBD.next()) {
                Producto nuevo = new Producto();
                nuevo.setId(resultadoBD.getInt("id"));
                nuevo.setCodigo(resultadoBD.getString("codigo"));
                nuevo.setDescripcion(resultadoBD.getString("descripcion"));
                nuevo.setPrecio_compra(resultadoBD.getDouble("precio_compra"));
                nuevo.setPrecio_venta(resultadoBD.getDouble("precio_venta"));
                nuevo.setImpuestos(resultadoBD.getBoolean("impuestos"));
                nuevo.setPorcentaje_ganancia(resultadoBD.getDouble("porcentaje_ganancia"));
                nuevo.setCantidad(resultadoBD.getInt("cantidad"));

                lista.add(nuevo);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public boolean modificar(Producto nuevo) throws SQLException, Exception {
        try (Connection con = Conexion.iniciar()) {
            String sql = "update desarrollo.producto set codigo = ?, descripcion = ?, "
                    + "precio_compra = ?, precio_venta = ?, impuestos = ?, porcentaje_ganancia = ?, cantidad = ? where id = ? ";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, nuevo.getCodigo());
            smt.setString(2, nuevo.getDescripcion());
            smt.setDouble(3, nuevo.getPrecio_compra());
            smt.setDouble(4, nuevo.getPrecio_venta());
            smt.setBoolean(5, nuevo.isImpuestos());
            smt.setDouble(6, nuevo.getPorcentaje_ganancia());
            smt.setInt(7, nuevo.getCantidad());
            smt.setInt(8, nuevo.getId());
            return smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean eliminar(int id) {
        try (Connection con = Conexion.iniciar()) {
            String sql = "delete from desarrollo.producto where id = ? ";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
