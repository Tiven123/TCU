/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Steven Ortiz
 */
public class Usuario {

    /**
     * Atributos del usuario
     */
    private int id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private String rol;
    
    /**
     * Constructor vacio
     */
    public Usuario() {
    }
    
    /**
     * Constructor con todos los atributos
     * @param id
     * @param nombre
     * @param apellidos
     * @param usuario
     * @param contrasena
     * @param rol 
     */
    public Usuario(int id, String nombre, String apellidos, String usuario, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }
       
    
    
    /*  Se crean los Getts de cada uno de los atributos del usuario  */
    /**
     * 
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    //Se crean los setts de cada uno de los atributos del usuario

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    //Se crea un diccionario con cada uno de los atributos del usuario
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + '}';
    }
   

}
