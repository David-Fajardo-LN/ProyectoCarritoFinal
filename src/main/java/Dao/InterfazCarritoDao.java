/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import logica.Carrito;

/**
 *
 * @author User
 */
public interface InterfazCarritoDao {
    void agregar(Carrito c);
    Carrito buscar(String codigo);
    void actualizar(Carrito c);
    void eliminar(String codigo);
}
