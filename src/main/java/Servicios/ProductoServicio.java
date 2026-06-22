/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Dao.ProductoDao;
import Dto.ProductoDTO;
import Resultado.ResultadoOperacion;
import java.util.ArrayList;
import logica.Producto;

/**
 *
 * @author User
 */
public class ProductoServicio {
    
    private ProductoDao productoDao;

    public ProductoServicio(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }
    
    
    public ResultadoOperacion buscarProducto(String codigo){
        if(codigo.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de codigo vacio",null);
        }
        
        Producto buscado = productoDao.buscar(codigo);
        
        if(buscado == null){
            return new ResultadoOperacion(false, "Error: Producto no encontrado", null);
        }
        
        return new ResultadoOperacion(true, "Producto encontrado", new ProductoDTO(buscado.getCodigo(), buscado.getNombre(), buscado.getPrecio()));
    }
    
    public ResultadoOperacion eliminarProducto(String codigo){
        if(codigo.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de codigo vacio", null);
        }
        
        boolean existeProducto = productoDao.existeProducto(codigo);
        if(!existeProducto){
            return new ResultadoOperacion(false, "Error: Producto no encontrado", null);
        }
        productoDao.eliminar(codigo);
        return new ResultadoOperacion(true, "Producto eliminado con exito", null);
    }
    
    public ResultadoOperacion actualizarProducto(String codigo, String nombre, String precioString){
        if(codigo.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de codigo vacio", null);
        }
        if(nombre.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de nombre vacio", null);
        }
        
        double precio;
        
        try{
            precio = Double.parseDouble(precioString);
        }catch(NumberFormatException e){
            return new ResultadoOperacion(false, "Error: Precio debe ser numerico", null);
        }
        
        if(precio <= 0){
            return new ResultadoOperacion(false, "Error: Precio invalido",null);
        }
        
        boolean existeProducto = productoDao.existeProducto(codigo);
        if(!existeProducto){
            return new ResultadoOperacion(false, "Error: Producto no encontrado", null);
        }
        
        productoDao.actualizar(new Producto(codigo, nombre, precio));
        return new ResultadoOperacion(true, "Producto actualizado", null);
    }

    
    public ResultadoOperacion agregarProducto(String codigo, String nombre, String precioString){
        if(codigo.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de codigo vacio", null);
        }
        if(nombre.isBlank()){
            return new ResultadoOperacion(false, "Error: Campo de nombre vacio", null);
        }
        
        boolean existeProducto = productoDao.existeProducto(codigo);
        if(existeProducto){
            return new ResultadoOperacion(false, "Error: El codigo de producto ya existe", null);
        }
        
        double precio;
        
        try{
            precio = Double.parseDouble(precioString);
        }catch(NumberFormatException e){
            return new ResultadoOperacion(false, "Error: Precio invalido", null);
        }
        
        if(precio<= 0){
            return new ResultadoOperacion(false, "Error: Precio invalido", null);
        }
        
        productoDao.agregar(new Producto(codigo, nombre, precio));
        return new ResultadoOperacion(true, "Producto agregado", null);
    }
    
    public ResultadoOperacion obtenerProductoPorCodigo(String codigo){
        Producto encontrado = productoDao.buscar(codigo);
        
        if(encontrado == null){
            return new ResultadoOperacion(false, "Error: Producto no encontrado", null);
        }
        
        return new ResultadoOperacion(true,"Producto encontrado", new ProductoDTO(encontrado.getCodigo(), encontrado.getNombre(), encontrado.getPrecio()));  
    }
    
    public ResultadoOperacion listarProductos(){
        ArrayList<Producto> productos = productoDao.obtenerListaDeProductos();
        
        ArrayList<ProductoDTO> listaCopia = new ArrayList<>();
        
        for(int i = 0; i<productos.size();i++){
            Producto productoOriginal  = productos.get(i);
            listaCopia.add(new ProductoDTO(productoOriginal .getCodigo(), productoOriginal .getNombre(), productoOriginal .getPrecio()));
        }        
        
        return new ResultadoOperacion(true, "Lista extraida con exito", listaCopia);
    }
    
}
