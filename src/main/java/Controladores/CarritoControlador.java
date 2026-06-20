/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import DaoClases.CarritoDaoMemoria;
import Logica.Carrito;
import Logica.ItemCarrito;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class CarritoControlador{
    
    private CarritoDaoMemoria carritoDaoMemoria;

    public CarritoControlador(CarritoDaoMemoria carritoDaoMemoria) {
        this.carritoDaoMemoria = carritoDaoMemoria;
    }
    
    
    
    public Carrito retornarCarritoPorCodigo(String codigo){
        if(codigo.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de codigo vacio");
            return null;
        }
        Carrito c = carritoDaoMemoria.retornarCarritoPorCodigo(codigo);
        if(c == null){
            JOptionPane.showMessageDialog(null, "Erro: Codigo de carrito no encontrado");
            return null;
        }
        return c;
    }
    
    public void eliminarCarrito(String codigoPermiso, Carrito c){
        if(c == null){
            JOptionPane.showMessageDialog(null, "Error: busque un carrito valido primero");
            return;
        }
        if(codigoPermiso.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de codigo vacio");
            return;
        }
        carritoDaoMemoria.eliminarCarritoUsandoProducto(c);
        JOptionPane.showMessageDialog(null, "Carrito Eliminado con exito");
        return;
    }
    
    public double calcularSubtotal(ArrayList<ItemCarrito> items){
        double subtotal=0;
        for(ItemCarrito i : items){
            subtotal+= i.calcularSubtotal();
        }
        return subtotal;
    }
    
    public boolean existeCarritoPorCodigo(String codigo){
        return carritoDaoMemoria.existeCarritoPorCodigo(codigo);
    }
    
    public void agregarCarrito(String codigo, ArrayList<ItemCarrito> items){
        carritoDaoMemoria.agregarCarrito(codigo, items);
    }
    
    public void actualizarCarrito(ArrayList<ItemCarrito> itemsNuevos, String codigo){
        Carrito encontrado = carritoDaoMemoria.retornarCarritoPorCodigo(codigo);
        encontrado.setItems(itemsNuevos);
    }
    
}
