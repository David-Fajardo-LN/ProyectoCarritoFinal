/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author User
 */


public class ProductoBaseDatos {
    // AQUI SE GUARDAN TODOS LOS PRODUCTOS -- SIMULANDO UNA BASE DE DATOS --
    private ArrayList<Producto> productos;

    public ProductoBaseDatos() {
        productos = generarProductosIniciales();
    }
    

    public ArrayList<Producto> getProductos() {return productos;}
    public int getCantidad(){return productos.size();}
    public void setProducto(Producto p) {this.productos.add(p);}
    
    
    public void agregarProducto(Producto p){
        productos.add(p);
    }
    
    public int buscarProductoPorCodigo(String codigo){
        for(int i = 0; i<productos.size(); i++){
            if(productos.get(i).getCodigo().equals(codigo))
                return i;
        }
        return -1;
    }
    
    public int buscarProductoPorNombre(String nombre){
        for(int i = 0; i<productos.size();i++){
            if(productos.get(i).getNombre().equals(nombre))
                return i;
        }
        return -1;
    }
    
    public Producto retornarProductoPorCodigo(String codigo){
        for(Producto p: productos){
            if(p.getCodigo().equals(codigo))
                return p;
        }
        return null;
    }
    
    public Producto retornarProductoPorNombre(String nombre){
        for(Producto p: productos){
            if(p.getNombre().equals(nombre))
                return p;
        }
        return null;
    }
    
    public void eliminarProductoDeLaBase(String codigo){
        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).getCodigo().equals(codigo)){
                productos.remove(i);
                return;
        }
    }
}
    
    
    private ArrayList<Producto> generarProductosIniciales(){
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        productos.add(new Producto("1", "Arroz Super Extra 5kg", 6.50));
        productos.add(new Producto("ALI-002", "Aceite Girasol 1L", 3.25));
        productos.add(new Producto("ALI-003", "Leche Entera 1L", 1.10));
        productos.add(new Producto("ALI-004", "Atún en aceite", 1.85));
        productos.add(new Producto("ALI-005", "Azúcar Blanca 1kg", 1.20));
        productos.add(new Producto("ALI-006", "Café Soluble 200g", 5.90));
        
        productos.add(new Producto("TEC-001", "Audífonos Bluetooth", 25.00));
        productos.add(new Producto("TEC-002", "Memoria USB 64GB", 12.50));
        productos.add(new Producto("TEC-003", "Mouse Inalámbrico", 15.00));
        productos.add(new Producto("TEC-004", "Cable HDMI 2m", 8.00));
        productos.add(new Producto("TEC-005", "Teclado USB", 18.00));
        
        productos.add(new Producto("HOG-001", "Detergente Líquido 2L", 7.50));
        productos.add(new Producto("HOG-002", "Papel Higiénico 12u", 5.50));
        productos.add(new Producto("HOG-003", "Jabón de Mano", 1.50));
        productos.add(new Producto("HOG-004", "Esponja de cocina", 0.75));
        
        return productos;
    }
}
