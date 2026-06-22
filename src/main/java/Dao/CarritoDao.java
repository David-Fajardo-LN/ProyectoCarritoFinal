/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import logica.Carrito;
import logica.CarritoBaseDatos;

/**
 *
 * @author User
 */
public class CarritoDao implements InterfazCarritoDao{
    
    private CarritoBaseDatos carritos;

    @Override
    public Carrito buscar(String codigo) {
         return carritos.buscar(codigo);
    }

    @Override
    public void actualizar(Carrito c) {
        carritos.actualizar(c);
    }

    @Override
    public void agregar(Carrito c) {
        carritos.agregar(c);
    }

    @Override
    public void eliminar(String codigo) {
        carritos.eliminar(codigo);
    }
}
