/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterfazes;

import Logica.Producto;

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
}
