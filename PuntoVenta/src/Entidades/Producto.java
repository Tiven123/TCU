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
    private float precio_compra;
    private float precio_venta;
    private boolean impuestos;
    private int porcentaje_ganancia;

    public Producto() {
    }

    public Producto(int id, String codigo, String descripcion, float precio_compra, float precio_venta, boolean impuestos, int porcentaje_ganancia) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.impuestos = impuestos;
        this.porcentaje_ganancia = porcentaje_ganancia;
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

    public float getPrecio_compra() {
        return precio_compra;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public boolean isImpuestos() {
        return impuestos;
    }

    public int getPorcentaje_ganancia() {
        return porcentaje_ganancia;
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

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setImpuestos(boolean impuestos) {
        this.impuestos = impuestos;
    }

    public void setPorcentaje_ganancia(int porcentaje_ganancia) {
        this.porcentaje_ganancia = porcentaje_ganancia;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + ", impuestos=" + impuestos + ", porcentaje_ganancia=" + porcentaje_ganancia + '}';
    }


}
