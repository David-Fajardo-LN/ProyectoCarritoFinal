/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controladores.ControladorProducto;
import Controladores.ControladorVentanaPrincipal;
import Dao.ProductoDao;
import Servicios.ProductoServicio;
import logica.ProductoBaseDatos;

/**
 *
 * @author User
 */
public class Menu {
    public static void main(String[] args) {
        ProductoBaseDatos productoBaseDatos = new ProductoBaseDatos();
        ProductoDao productoDao = new ProductoDao(productoBaseDatos);
        ProductoServicio servicioProducto = new ProductoServicio(productoDao);
    
        ControladorProducto controladorProducto = new ControladorProducto(servicioProducto);
        ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal(controladorProducto);
    }
}
