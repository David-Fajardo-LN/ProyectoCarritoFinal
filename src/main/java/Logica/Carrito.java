/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Carrito {
    //CONTIENE UNA LISTA DE ITEMS PARA SIMULAR UN CARRITO FISICO
    
    private ArrayList<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }


    public ArrayList<ItemCarrito> getItems() {return items;}
    public void setItem(ItemCarrito item) {this.items.add(item);}
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.items);
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
        final Carrito other = (Carrito) obj;
        return Objects.equals(this.items, other.items);
    }
    
    public double calcularTotal(){
        double total=0;
        for(ItemCarrito i : items)
            total += i.calcularSubtotal();
        return total;
    }
    
    public void listarItemsDeCarrito(){
        for(ItemCarrito i: items){
            System.out.println(i);
        }
    }
}
