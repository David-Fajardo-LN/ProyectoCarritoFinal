/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoClases;

import DaoInterfazes.ProductoDao;
import Logica.Producto;
import Logica.ProductoBaseDatos;

/**
 *
 * @author User
 */
public class ProductoDaoMemoria implements ProductoDao{
    
    private ProductoBaseDatos productos;

    public ProductoDaoMemoria(ProductoBaseDatos productos) {
        this.productos = productos;
    }
    
    @Override
    public void agregarProducto(String codigo, String nombre, double precio) {
        productos.agregarProducto(new Producto(codigo, nombre, precio));
    }

    @Override
    public void eliminarProductoPorCodigo(String codigo) {
        productos.eliminarProductoDeLaBase(codigo);
    }

    @Override
    public boolean existeProductoPorCodigo(String codigo) {
        for(Producto p : productos.getProductos()){
            if(p.getCodigo().equals(codigo))
                return true;
        }
        return false;
    }

    @Override
    public boolean existeProductoPorNombre(String nombre) {
        for(Producto p: productos.getProductos()){
            if(p.getNombre().equals(nombre))
                return true;
        }
        return false;
    }

    @Override
    public void modificarProductoBase(Producto p, String nuevoCodigo, String nuevoNombre, double nuevoPrecio) {
        if(!p.getCodigo().equals(nuevoCodigo))
            p.setCodigo(nuevoCodigo);
        if(!p.getNombre().equals(nuevoNombre))
            p.setNombre(nuevoNombre);
        if(p.getPrecio() != nuevoPrecio)
            p.setPrecio(nuevoPrecio);
    }

    @Override
    public Producto retornarProductoPorCodigo(String codigo) {
        Producto p = productos.retornarProductoPorCodigo(codigo);
        return p;
    }

    @Override
    public Producto retornarProductoPorNombre(String nombre) {
        Producto p = productos.retornarProductoPorNombre(nombre);
        return p;
    }  
}
