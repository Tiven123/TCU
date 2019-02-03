/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import java.util.LinkedList;
import BaseDatos.FacturaBD;
import Entidades.Factura;
import Entidades.DetalleFactura;

/**
 *
 * @author steven
 */
public class FacturaVali {

    public FacturaVali() {
    }

    FacturaBD facturaBD = new FacturaBD();

    public boolean crear(Factura nuevo) throws Exception {

        if (nuevo.getFecha_hora()==null) {
            throw new Exception("Fecha y hora requerida");
        }
        if (nuevo.getNombre_cliente().equals("")) {
            throw new Exception("Cliente requerido");
        }
        if (nuevo.getNombre_cajero().equals("")) {
            throw new Exception("Cajero requerido");
        }
        if (nuevo.getTotal() <= 0) {
            throw new Exception("Total requerido requerido ");
        }
        if (nuevo.getId() > 0) {
            return facturaBD.modificar();
        } else {
            return facturaBD.crear(nuevo);
        }
    }
/*
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

    public boolean eliminar(int id) throws Exception {
        System.out.println(id);
        if (id <= 0) {
            throw new Exception("Seleccione un producto");
        }
        return ProductoBaseDatos.eliminar(id);
    }*/

}
