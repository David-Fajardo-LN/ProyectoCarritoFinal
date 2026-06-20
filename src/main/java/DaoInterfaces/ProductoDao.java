/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterfaces;

import Logica.Producto;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface ProductoDao {
    boolean existeProductoPorCodigo(String codigo);
    boolean existeProductoPorNombre(String nombre);
    
    Producto retornarProductoPorCodigo(String codigo);
    Producto retornarProductoPorNombre(String nombre);
    
    void agregarProducto(String codigo, String nombre, double precio);
    void eliminarProductoPorCodigo(String codigo);
    void modificarProductoBase(Producto p, String nuevoCodigo, String nuevoNombre, double nuevoPrecio);
    
    ArrayList<Producto> retornarProductos();
}
