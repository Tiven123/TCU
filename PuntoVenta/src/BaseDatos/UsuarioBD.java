/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Steven Ortiz
 */
public class UsuarioBD {

    public UsuarioBD() {
    }
    
    public boolean crear(Usuario nuevoUsuario) throws Exception {
        try (Connection con = Conexion.iniciar()) {
            String sql = "insert into desarrollo.usuario(nombre, apellidos, "
                    + "usuario, contrasena, rol) values(?,?,?,?,?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, nuevoUsuario.getNombre());
            smt.setString(2, nuevoUsuario.getApellidos());
            smt.setString(3, nuevoUsuario.getUsuario());
            smt.setString(4, nuevoUsuario.getContrasena());
            smt.setString(5, nuevoUsuario.getRol());
            return smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw ex;
        }
    }
    public LinkedList<Usuario> buscarTodos() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try (Connection con = Conexion.iniciar()) {
            String sql = "select * from desarrollo.usuario";
            PreparedStatement smt = con.prepareStatement(sql);
            ResultSet resultadoBD = smt.executeQuery();
            while (resultadoBD.next()) {
                Usuario NuevoUsuario = new Usuario();
                NuevoUsuario.setId(resultadoBD.getInt("id"));
                NuevoUsuario.setNombre(resultadoBD.getString("nombre"));
                NuevoUsuario.setApellidos(resultadoBD.getString("apellidos"));
                NuevoUsuario.setUsuario(resultadoBD.getString("usuario"));
                NuevoUsuario.setContrasena(resultadoBD.getString("contrasena"));
                NuevoUsuario.setRol(resultadoBD.getString("rol"));
                usuarios.add(NuevoUsuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    }
    public Usuario buscarNombre(String nombre){
        return null;
    }
    public boolean modificar(Usuario usuarioN) throws SQLException, Exception {
        try (Connection con = Conexion.iniciar()) {
            String sql = "update desarrollo.usuario set nombre = ?, apellidos = ?, "
                    + "usuario = ?, contrasena = ?, rol = ? where id = ? ";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, usuarioN.getNombre());
            smt.setString(2, usuarioN.getApellidos());
            smt.setString(3, usuarioN.getUsuario());
            smt.setString(4, usuarioN.getContrasena());
            smt.setString(5, usuarioN.getRol());
            smt.setInt(6, usuarioN.getId());
           return smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
    public boolean eliminar(int id){
        try (Connection con = Conexion.iniciar()) {
            String sql = "delete from desarrollo.usuario where id = ? ";
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
