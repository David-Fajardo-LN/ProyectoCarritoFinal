/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Logica.Producto;
import Logica.ItemCarrito;
import Logica.ProductoBaseDatos;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ProductoControlador {
    //SOLICITAMOS LA BASE DE DATOS COMO PARAMETRO DE FUNCIONAMIENTO AL CONTROLADOR 
    
    private ProductoBaseDatos productos;
    
    public ProductoControlador(ProductoBaseDatos base){
        this.productos=base;
    }
    
    //METODOS PURAMENTE DE VALIDACIÓN DE INFORMACIÓN
    
    private boolean existeProductoPorCodigo(String codigo){
        for(Producto p : productos.getProductos()){
            if(p.getCodigo().equals(codigo))
                return true;
        }
        return false;
    }
    
    private boolean existeProductoPorNombre(String nombre){
        for(Producto p: productos.getProductos()){
            if(p.getNombre().equals(nombre))
                return true;
        }
        return false;
    }
    
    
    //METODOS ME MODIFICACIÓN DE DATOS -AGREGAR-ELIMINAR-MODIFICAR
    
    private void agregarProducto(String codigo, String nombre, double precio){
        productos.agregarProducto(new Producto(codigo, nombre, precio));
    }
    
    private void eliminarProductoPorCodigo(String codigo){
        productos.eliminarProductoDeLaBase(codigo);
    }
    
    private Producto retornarProductoPorCodigo(String codigo){
        Producto p = productos.retornarProductoPorCodigo(codigo);
        return p;
    }
    
    private Producto retornarProductoPorNombre(String nombre){
        Producto p = productos.retornarProductoPorNombre(nombre);
        return p;
    }
    
    private void modificarProductoBase(Producto p, String nuevoCodigo, String nuevoNombre, double nuevoPrecio){
        if(!p.getCodigo().equals(nuevoCodigo))
            p.setCodigo(nuevoCodigo);
        if(!p.getNombre().equals(nuevoNombre))
            p.setNombre(nuevoNombre);
        if(p.getPrecio() != nuevoPrecio)
            p.setPrecio(nuevoPrecio);
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
        
        if(existeProductoPorCodigo(codigo)){
            JOptionPane.showMessageDialog(null, "Error: El codigo de producto ya existe");
            return false;
        }
        
        // SI TODAS LAS REVISIONES ESTAN CORRECTAS, ENTOCNES SE AGREGA EL PRODUCTO
        
        agregarProducto(codigo, nombre, precio);
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
        if(!existeProductoPorCodigo(codigoEliminar)){
            JOptionPane.showMessageDialog(null, "Error: El codigo de producto a eliminar no existe");
            return false;
        }
        eliminarProductoPorCodigo(codigoEliminar);
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
        
        
        
        Producto buscado = retornarProductoPorCodigo(codigoAModificar);
        
        if(buscado ==null){
            JOptionPane.showMessageDialog(null, "Error: Codigo de producto no existe");
            return false;
        }
        
        Producto productoConEseCodigo = retornarProductoPorCodigo(nuevoCodigo);

        if(productoConEseCodigo != null && productoConEseCodigo != buscado){
            JOptionPane.showMessageDialog(null,"Error: El nuevo codigo ya existe");
            return false;
        }
        modificarProductoBase(buscado, nuevoCodigo, nuevoNombre, nuevoPrecio);
        JOptionPane.showMessageDialog(null,"Producto modificado con éxito");
        return true; 
    }
    
    
    
    public Producto buscarProductoPorCodigo(String codigo){
        codigo = codigo.trim();
        
        if(codigo.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: el campo de codigo esta vacio");
            return null;
        }
        return productos.retornarProductoPorCodigo(codigo);
    }
    
    public Producto buscarProductoPorNombre(String nombre){
        nombre = nombre.trim();
        if(nombre.isBlank()){
            JOptionPane.showMessageDialog(null, "Error: el campo de nombre esta vacio");
            return null;
        }
        return productos.retornarProductoPorNombre(nombre);
    }
}
