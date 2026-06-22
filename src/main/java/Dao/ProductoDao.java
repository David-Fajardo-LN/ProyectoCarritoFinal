/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.util.ArrayList;
import logica.Producto;
import logica.ProductoBaseDatos;

/**
 *
 * @author User
 */
public class ProductoDao implements InterfazProductoDao{
    
    private ProductoBaseDatos productos;

    public ProductoDao(ProductoBaseDatos productos) {
        this.productos = productos;
    }
    
    

    @Override
    public Producto buscar(String codigo) {
        return productos.buscar(codigo);
    }

    @Override
    public void actualizar(Producto p) {
        productos.actualizar(p);
    }

    @Override
    public void agregar(Producto p) {
       productos.agregar(p);
    }

    @Override
    public void eliminar(String codigo) {
        productos.eliminar(codigo);
    }

    @Override
    public boolean existeProducto(String codigo) {
        return productos.existeProducto(codigo);
    }

    @Override
    public ArrayList<Producto> obtenerListaDeProductos() {
        return productos.getProductos();
    }
    
    
    
    
}
