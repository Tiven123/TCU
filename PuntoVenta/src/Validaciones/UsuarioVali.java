/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import Entidades.Usuario;
import BaseDatos.UsuarioBD;
import java.util.LinkedList;

/**
 *
 * @author Steven Ortiz
 */
public class UsuarioVali {

    UsuarioBD usuarioBaseDatos = new UsuarioBD();
    
    public UsuarioVali() {
    }
    
    public boolean crearUsuario(Usuario nuevoUsuario) throws Exception {
        if (nuevoUsuario.getNombre().isEmpty()) {
            throw new Exception("Nombre requerido");
        }
        if (nuevoUsuario.getApellidos().isEmpty()) {
            throw new Exception("Apellidos requeridos");
        }
        if (nuevoUsuario.getUsuario().isEmpty()) {
            throw new Exception("Usuario requerido");
        }
        if (nuevoUsuario.getContrasena().isEmpty()) {
            throw new Exception("Contraseña requerida");
        }
        if (nuevoUsuario.getRol().isEmpty()) {
            throw new Exception("Rol requerido");
        }
        if (nuevoUsuario.getId() > 0) {
            return usuarioBaseDatos.modificar(nuevoUsuario);
        } else {
            return usuarioBaseDatos.crear(nuevoUsuario);
        }
    }

    public LinkedList<Usuario> buscar(String nombre) {
        if (nombre.isEmpty()) {
            return usuarioBaseDatos.buscarTodos();
        } else {
            Usuario usuarioEncontrado = usuarioBaseDatos.buscarNombre(nombre);
            LinkedList<Usuario> resultado = new LinkedList<>();
            if (usuarioEncontrado != null) {
                resultado.add(usuarioEncontrado);
            }
            return resultado;
        }
    }
    public boolean eliminar(int id) throws Exception{
        System.out.println(id);
        if (id <= 0) {
            throw new Exception("Seleccione un usuario");
        }
        return usuarioBaseDatos.eliminar(id);
    }
}
