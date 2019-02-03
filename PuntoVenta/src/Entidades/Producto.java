/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author steven
 */
public class Producto {

    private int id;
    private String codigo;
    private String descripcion;
    private double precio_compra;
    private double precio_venta;
    private boolean impuestos;
    private double porcentaje_ganancia;
    private int cantidad;

    public Producto() {
    }

    public Producto(int id, String codigo, String descripcion, double precio_compra, double precio_venta, boolean impuestos, double porcentaje_ganancia, int cantidad) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.impuestos = impuestos;
        this.porcentaje_ganancia = porcentaje_ganancia;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public boolean isImpuestos() {
        return impuestos;
    }

    public double getPorcentaje_ganancia() {
        return porcentaje_ganancia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setImpuestos(boolean impuestos) {
        this.impuestos = impuestos;
    }

    public void setPorcentaje_ganancia(double porcentaje_ganancia) {
        this.porcentaje_ganancia = porcentaje_ganancia;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String detalles() {
        return "Nuevo Producto \n"
                + "Id: " + id + "\n"
                + "Codigo: " + codigo + "\n"
                + "Descripcion: " + descripcion + "\n"
                + "Precio de Compra: " + precio_compra + "\n"
                + "Precio de Venta: " + precio_venta + "\n"
                + "Posee impuestos? " + impuestos + "\n"
                + "Porcentaje de Ganancia: " + porcentaje_ganancia + "% \n"
                + "Cantidad de Existencias: " + cantidad;
    }

    @Override
    public String toString() {
        return descripcion+"     Precio: "+precio_venta+"     Disponible: "+cantidad;
    }

}
