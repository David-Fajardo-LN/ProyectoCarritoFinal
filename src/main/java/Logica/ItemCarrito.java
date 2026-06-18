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
public class ItemCarrito {
    
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {return producto;}
    public int getCantidad() {return cantidad;}

    public void setProducto(Producto producto) {this.producto = producto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.producto);
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
        final ItemCarrito other = (ItemCarrito) obj;
        return Objects.equals(this.producto, other.producto);
    }
    
    public double calcularSubtotal(){
        return (this.producto.getPrecio() * cantidad);
    }

    @Override
    public String toString() {
        String mensaje = "Item : ";
        mensaje += "\n\tProducto: "+ producto;
        mensaje += "\n\tCantidad: "+ cantidad;
        return mensaje;
    }
}
