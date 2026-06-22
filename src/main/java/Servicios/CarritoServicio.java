/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Dao.CarritoDao;
import Dto.ItemCarritoDTO;
import Resultado.ResultadoOperacion;
import java.util.ArrayList;
import logica.Carrito;
import logica.ItemCarrito;

/**
 *
 * @author User
 */
public class CarritoServicio {
    private CarritoDao carritoDao;

    public CarritoServicio(CarritoDao carritoDao) {
        this.carritoDao = carritoDao;
    }
    
    public ResultadoOperacion buscarCarrito(String codigoBuscado){
        Carrito encontrado = carritoDao.buscar(codigoBuscado);
        if(encontrado == null){
            return new ResultadoOperacion(false, "Error: Carrito no encontrado", null);
        }
        ArrayList<ItemCarritoDTO> carrito = new ArrayList();
        for(ItemCarrito i : encontrado.getItems()){
            
            String codigo = i.getProducto().getCodigo();
            String nombre = i.getProducto().getNombre();
            double precio = i.getProducto().getPrecio();
            int cantidad = i.getCantidad();
            
            ItemCarritoDTO item = new ItemCarritoDTO(codigo, nombre, precio, cantidad);
            carrito.add(item);
        }
        return new ResultadoOperacion(true, "Carrito encontrado", carrito);
    }
}
