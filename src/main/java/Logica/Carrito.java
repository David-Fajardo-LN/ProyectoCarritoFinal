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
    private String codigo;
    private ArrayList<ItemCarrito> items;

    public Carrito() {
    }

    
    
    public Carrito(String codigo) {
        this.codigo=codigo;
        this.items = new ArrayList<>();
    }
    
    public Carrito(String codigo, ArrayList<ItemCarrito> items){
        this.codigo=codigo;
        this.items = items;
    }


    public ArrayList<ItemCarrito> getItems() {return items;}
    public void agregarItem(ItemCarrito item) {this.items.add(item);}
    public void setItems(ArrayList<ItemCarrito> items){this.items=items;}
    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    
    

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
    
    public double calcularSubtotal(){
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
