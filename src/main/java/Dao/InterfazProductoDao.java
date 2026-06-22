/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.ArrayList;
import logica.Producto;

/**
 *
 * @author User
 */
public interface InterfazProductoDao {
    void agregar(Producto p);
    Producto buscar(String codigo);
    void eliminar(String codigo);
    void actualizar(Producto p);
    boolean existeProducto(String codigo);
    ArrayList obtenerListaDeProductos();
}
