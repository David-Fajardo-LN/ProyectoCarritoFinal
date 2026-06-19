/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import DaoClases.ProductoDaoMemoria;
import Logica.Producto;
import Logica.ItemCarrito;
import Logica.ProductoBaseDatos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ProductoControlador {
    
    private ProductoDaoMemoria productoDaoMemoria;

    public ProductoControlador(ProductoDaoMemoria productoDaoMemoria) {
        this.productoDaoMemoria = productoDaoMemoria;
    }
    
    private boolean validarEntradaBase(String nombre, String codigo, String precioString){
        if(codigo.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de codigo vacio");
            return false;
        }
        if(nombre.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de nombre vacio");
            return false;
        }
        if(precioString.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de precio vacio");
            return false;
        }
        return true;
    }

    // METODOS PUBLICOS QUE USAN LA LOGICA INTERNA PARA REALIZAR VALIDACIONES Y DEAMAS
    //////////////////////////////////////////////////////////////////////////////////
    
    public boolean crearNuevoProducto(String codigo, String nombre, String precioString){
        
        //ELIMINAMOS ESPACIOS INESESARIOS AL INICIO Y FINAL, POR EJEMPLO "   MANZANA     " ====> "MANZANA"
        
        codigo = codigo.trim();
        nombre = nombre.trim();
        precioString = precioString.trim();
        
        
        // PRIMERO REVISAMOS SI ALGUNO DE LOS CAMPOS INGRESADOS ESTA VACIO CON EL METODO PRIVADO
        
        if(!validarEntradaBase(nombre, codigo, precioString)){
            return false;
        }
        
        //REVISAMOS QUE PRECIO REALMENTE SEA UN NUMERO Y HACEMOS EL CASTING
        
        double precio;
        
        try{
            precio = Double.parseDouble(precioString);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error: El precio debe ser un número");
            return false;
        }
        
        // REVISAMOS QUE EL PRECIO NO SEA NEGATIVO Y TAMPOCO 0
        
        if(precio <= 0){
            JOptionPane.showMessageDialog(null, "Error: El precio no puede ser negativo ni cero");
            return false;
        }
        
        // REVISAMOS SI EL CODIGO INGRESADO EXISTE, YA QUE NO PUEDEN HABER DOS PRODUCTOS OCN UN MISMO CODIGO
        
        if(productoDaoMemoria.existeProductoPorCodigo(codigo)){
            JOptionPane.showMessageDialog(null, "Error: El codigo de producto ya existe");
            return false;
        }
        
        // SI TODAS LAS REVISIONES ESTAN CORRECTAS, ENTOCNES SE AGREGA EL PRODUCTO
        
        productoDaoMemoria.agregarProducto(codigo, nombre, precio);
        JOptionPane.showMessageDialog(null, "Producto agregado con exito");
        return true;
    }
    
    
    
    public boolean eliminarProducto(String codigoEliminar, String codigoPermiso){

        codigoEliminar = codigoEliminar.trim();
        codigoPermiso = codigoPermiso.trim();
        
        if(codigoPermiso.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: Campo de código de permso vacio");
            return false;
        }
        if(!codigoPermiso.equals("123")){
            JOptionPane.showMessageDialog(null, "Error: Codigo de permiso denegado");
            return false;
        }
        if(codigoEliminar.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: ingrese el codigo del producto a eliminar");
            return false;
        }
        if(!productoDaoMemoria.existeProductoPorCodigo(codigoEliminar)){
            JOptionPane.showMessageDialog(null, "Error: El codigo de producto a eliminar no existe");
            return false;
        }
        productoDaoMemoria.eliminarProductoPorCodigo(codigoEliminar);
        JOptionPane.showMessageDialog(null,"Producto eliminado con exito");
        return true;
    }
    
    
    
    public boolean modificarProducto(String codigoAModificar, String codigoPermiso, String nuevoCodigo, String nuevoNombre, String nuevoPrecioString){
        
        codigoPermiso = codigoPermiso.trim();
        codigoAModificar = codigoAModificar.trim();
        
        nuevoCodigo = nuevoCodigo.trim();
        nuevoNombre = nuevoNombre.trim();
        nuevoPrecioString = nuevoPrecioString.trim();
        
        if(codigoPermiso.isBlank()){
            JOptionPane.showMessageDialog(null,"Error: Campo de permiso esta vacio");
            return false;
        }
        if(!codigoPermiso.equals("123")){
            JOptionPane.showMessageDialog(null,"Error: codigo de permiso denegado");
            return false;
        }
        if(codigoAModificar.isBlank()){
            JOptionPane.showMessageDialog(null,"Error: Ingrese el código del producto");
            return false;
        }
        
        if(!validarEntradaBase(nuevoNombre, nuevoCodigo, nuevoPrecioString)){
            return false;
        }
        
        double nuevoPrecio;
        
        try{
            nuevoPrecio = Double.parseDouble(nuevoPrecioString);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error: El precio debe ser un número");
            return false;
        }
        
        if(nuevoPrecio <= 0){
            JOptionPane.showMessageDialog(null, "Error: El precio debe ser mayor a cero");
            return false;
        }
        
        
        
        Producto buscado = productoDaoMemoria.retornarProductoPorCodigo(codigoAModificar);
        
        if(buscado ==null){
            JOptionPane.showMessageDialog(null, "Error: Codigo de producto no existe");
            return false;
        }
        
        Producto productoConEseCodigo = productoDaoMemoria.retornarProductoPorCodigo(nuevoCodigo);

        if(productoConEseCodigo != null && productoConEseCodigo != buscado){
            JOptionPane.showMessageDialog(null,"Error: El nuevo codigo ya existe");
            return false;
        }
        productoDaoMemoria.modificarProductoBase(buscado, nuevoCodigo, nuevoNombre, nuevoPrecio);
        JOptionPane.showMessageDialog(null,"Producto modificado con éxito");
        return true; 
    }
    
    
    
    public Producto buscarProductoPorCodigo(String codigo){
        codigo = codigo.trim();
        
        if(codigo.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: el campo de codigo esta vacio");
            return null;
        }
        return productoDaoMemoria.retornarProductoPorCodigo(codigo);
    }
    
    public Producto buscarProductoPorNombre(String nombre){
        nombre = nombre.trim();
        if(nombre.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: el campo de nombre esta vacio");
            return null;
        }
        return productoDaoMemoria.retornarProductoPorNombre(nombre);
    }
    
    public ArrayList<Producto> retornarProductos(){
        return productoDaoMemoria.retornarProductos();
    }
}
