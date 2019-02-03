/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import Entidades.Producto;
import BaseDatos.ProductoBD;
import java.util.LinkedList;

/**
 *
 * @author Steven Ortiz
 */
public class ProductoVali {

    ProductoBD ProductoBaseDatos = new ProductoBD();
    
    public ProductoVali() {
    }
    
    public boolean crear(Producto nuevo) throws Exception {
        
        if (nuevo.getCodigo().isEmpty()) {
            throw new Exception("Codigo requerido");
        }
        if (nuevo.getDescripcion().isEmpty()) {
            throw new Exception("Descripcion requerida");
        }
        if (nuevo.getPrecio_venta() <= 0 ) {
            throw new Exception("Precios Venta requerido ");
        }
        if (nuevo.getPrecio_venta() < nuevo.getPrecio_compra() ) {
           
            throw new Exception("Precio de venta debe ser mayor al de compra");
        }
        if (nuevo.getId() > 0) {
            return ProductoBaseDatos.modificar(nuevo);
        } else {
            return ProductoBaseDatos.crear(nuevo);
        }
    }

    public LinkedList<Producto> buscar(String nombre) {
        if (nombre.isEmpty()) {
            return ProductoBaseDatos.buscarTodos();
        } else {
            LinkedList<Producto> resultado = ProductoBaseDatos.buscarNombre(nombre);
            return resultado;
        }
    }
    public LinkedList<Producto> buscarNombre(String nombre) {
        if (nombre.isEmpty()) {
            return ProductoBaseDatos.buscarTodos();
        } else {
            LinkedList<Producto> resultado = ProductoBaseDatos.buscarNombre(nombre);
            return resultado;
        }
    }
    public LinkedList<Producto> buscarCodigo(String codigo) {
        if (codigo.isEmpty()) {
            return ProductoBaseDatos.buscarTodos();
        } else {
            LinkedList<Producto> resultado = ProductoBaseDatos.buscarCodigo(codigo);
            return resultado;
        }
    }
    public boolean eliminar(int id) throws Exception{
        System.out.println(id);
        if (id <= 0) {
            throw new Exception("Seleccione un producto");
        }
        return ProductoBaseDatos.eliminar(id);
    }
}
