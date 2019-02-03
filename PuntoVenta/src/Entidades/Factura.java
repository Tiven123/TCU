/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 *
 * @author steven
 */
public class Factura {

    private int id;
    private Timestamp fecha_hora;
    private String nombre_cliente;
    private String nombre_cajero;
    private double subtotal_exento;
    private double subtotal_gravado;
    private double total;
    private double efectivo;
    private double tarjeta;
    private double monto_impuestos;
    private LinkedList<DetalleFactura> lista;

    public Factura() {
    }

    public Factura(int id, Timestamp fecha_hora, String nombre_cliente, String nombre_cajero, double subtotal_exento, double subtotal_gravado, double total, double efectivo, double tarjeta, double monto_impuestos, LinkedList<DetalleFactura> lista) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.nombre_cliente = nombre_cliente;
        this.nombre_cajero = nombre_cajero;
        this.subtotal_exento = subtotal_exento;
        this.subtotal_gravado = subtotal_gravado;
        this.total = total;
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.monto_impuestos = monto_impuestos;
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getNombre_cajero() {
        return nombre_cajero;
    }

    public double getSubtotal_exento() {
        return subtotal_exento;
    }

    public double getSubtotal_gravado() {
        return subtotal_gravado;
    }

    public double getTotal() {
        return total;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public double getTarjeta() {
        return tarjeta;
    }

    public double getMonto_impuestos() {
        return monto_impuestos;
    }

    public LinkedList<DetalleFactura> getLista() {
        return lista;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setNombre_cajero(String nombre_cajero) {
        this.nombre_cajero = nombre_cajero;
    }

    public void setSubtotal_exento(double subtotal_exento) {
        this.subtotal_exento = subtotal_exento;
    }

    public void setSubtotal_gravado(double subtotal_gravado) {
        this.subtotal_gravado = subtotal_gravado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public void setTarjeta(double tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void setMonto_impuestos(double monto_impuestos) {
        this.monto_impuestos = monto_impuestos;
    }

    public void setLista(LinkedList<DetalleFactura> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", fecha_hora=" + fecha_hora + ", nombre_cliente=" + nombre_cliente + ", nombre_cajero=" + nombre_cajero + ", subtotal_exento=" + subtotal_exento + ", subtotal_gravado=" + subtotal_gravado + ", total=" + total + ", efectivo=" + efectivo + ", tarjeta=" + tarjeta + ", monto_impuestos=" + monto_impuestos + ", lista=" + lista + '}';
    }

    
    
}
