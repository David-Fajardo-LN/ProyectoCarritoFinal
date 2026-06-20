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
public class CarritoBaseDatos {
    
    private ProductoBaseDatos productos;
    private ArrayList<Carrito> carritos;

    public CarritoBaseDatos(ProductoBaseDatos productos) {
        this.productos = productos;
        this.carritos = generarCarritosIniciales();
    }
    
    public ArrayList<Carrito> getCarritos() {
        return carritos;
    }
    
    public void agregarCarrito(Carrito c){
        carritos.add(c);
    }
    
    public Carrito retornarCarritoPorCodigoBase(String codigoBuscado){
        for(Carrito c : carritos){
            if(c.getCodigo().equals(codigoBuscado))
                return c;
        }
        return null;
    }
    
    public void eliminarCarritoPorCodigoBase(String codigoEliminar){
        for(int i = 0; i<carritos.size();i++){
            Carrito c = carritos.get(i);
            if(c.getCodigo().equals(codigoEliminar))
                carritos.remove(c);
            return;
        }
    }
    
    public void eliminarCarritoPorObjeto(Carrito c){
        carritos.remove(c);
    }
    
    public boolean buscarCarritoPorCodigo(String codigoBuscar){
        for(Carrito c : carritos){
            if(c.getCodigo().equals(codigoBuscar))
                return true;
        }
        return false;
    }
    
    public int size() {
        return this.carritos.size();
    } 
    
    private ArrayList<Carrito> generarCarritosIniciales(){

        ArrayList<Carrito> carritos = new ArrayList<>();

        ArrayList<Producto> productosBase = productos.getProductos();

    // ===== 3 carritos con 1 item =====

        Carrito c1 = new Carrito("1");
        c1.agregarItem(new ItemCarrito(productosBase.get(0), 3));
        carritos.add(c1);

        Carrito c2 = new Carrito("2");
        c2.agregarItem(new ItemCarrito(productosBase.get(6), 1));
        carritos.add(c2);

        Carrito c3 = new Carrito("3");
        c3.agregarItem(new ItemCarrito(productosBase.get(11), 2));
        carritos.add(c3);

    // ===== 3 carritos con 2 items =====

        Carrito c4 = new Carrito("4");
        c4.agregarItem(new ItemCarrito(productosBase.get(1), 1));
        c4.agregarItem(new ItemCarrito(productosBase.get(2), 4));
        carritos.add(c4);

        Carrito c5 = new Carrito("5");
        c5.agregarItem(new ItemCarrito(productosBase.get(7), 2));
        c5.agregarItem(new ItemCarrito(productosBase.get(9), 1));
        carritos.add(c5);

        Carrito c6 = new Carrito("6");
        c6.agregarItem(new ItemCarrito(productosBase.get(12), 3));
        c6.agregarItem(new ItemCarrito(productosBase.get(14), 5));
        carritos.add(c6);

    // ===== 4 carritos con 3 o más items =====

        Carrito c7 = new Carrito("7");
        c7.agregarItem(new ItemCarrito(productosBase.get(0), 1));
        c7.agregarItem(new ItemCarrito(productosBase.get(3), 2));
        c7.agregarItem(new ItemCarrito(productosBase.get(5), 1));
        carritos.add(c7);

        Carrito c8 = new Carrito("8");
        c8.agregarItem(new ItemCarrito(productosBase.get(6), 1));
        c8.agregarItem(new ItemCarrito(productosBase.get(8), 1));
        c8.agregarItem(new ItemCarrito(productosBase.get(10), 1));
        carritos.add(c8);

        Carrito c9 = new Carrito("9");
        c9.agregarItem(new ItemCarrito(productosBase.get(11), 2));
        c9.agregarItem(new ItemCarrito(productosBase.get(12), 1));
        c9.agregarItem(new ItemCarrito(productosBase.get(13), 4));
        c9.agregarItem(new ItemCarrito(productosBase.get(14), 2));
        carritos.add(c9);

        Carrito c10 = new Carrito("10");
        c10.agregarItem(new ItemCarrito(productosBase.get(2), 6));
        c10.agregarItem(new ItemCarrito(productosBase.get(4), 3));
        c10.agregarItem(new ItemCarrito(productosBase.get(7), 1));
        c10.agregarItem(new ItemCarrito(productosBase.get(9), 2));
        c10.agregarItem(new ItemCarrito(productosBase.get(13), 2));
        carritos.add(c10);

        return carritos;
    }
}
