/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;
import Entidades.Usuario;
import BaseDatos.UsuarioBD;

/**
 *
 * @author Steven Ortiz
 */
public class UsuarioVali {
    UsuarioBD usuarioBaseDatos = new UsuarioBD();

    public UsuarioVali() {
    }
    
    public boolean  crearUsuario(Usuario nuevoUsuario)throws Exception {
        if (nuevoUsuario.getNombre().isEmpty()) {
            throw new Exception("Nombre requerido");
        }
        if (nuevoUsuario.getId() > 0) {
            return usuarioBaseDatos.modificar(nuevoUsuario);
        } else {
            return usuarioBaseDatos.crear(nuevoUsuario);
        }
    }
}
