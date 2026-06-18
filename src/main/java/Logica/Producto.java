/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    
    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() {return codigo;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}

    public void setCodigo(String codigo) {this.codigo = codigo;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(double precio) {this.precio = precio;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    
    public boolean esMismoCodigo(String codigo){
        return this.codigo.equals(codigo);
    }

    @Override
    public String toString() {
        String mensaje = "Producto:"; 
        mensaje += "\nCodigo: " + codigo;
        mensaje += "\nNombre: " + nombre; 
        mensaje += "\nPrecio=" + precio;
        return mensaje;
    }
}
