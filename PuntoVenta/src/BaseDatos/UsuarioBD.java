/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;
import Entidades.Usuario;

/**
 *
 * @author Steven Ortiz
 */
public class UsuarioBD {

    public UsuarioBD() {
    }
    
    public boolean crear(Usuario nuevoUsuario) throws Exception{
        throw new Exception("Crear");
    }
    public boolean buscarTodos(Usuario nuevoUsuario){
        return true;
    }
    public boolean buscarNombre(Usuario nuevoUsuario){
        return true;
    }
    public boolean modificar(Usuario nuevoUsuario){
        return true;
    }
    public boolean eliminar(Usuario nuevoUsuario){
        return true;
    }
    
}
