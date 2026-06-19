/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterfazes;

import Logica.Carrito;
import Logica.ItemCarrito;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface CarritoDao {
    boolean existeCarritoPorCodigo(String codigo);
    Carrito retornarCarritoPorCodigo(String codigo);
    
    void agregarCarrito(String codigo, ArrayList<ItemCarrito> items);
    void eliminarCarritoPorCodigo(String codigo);
    void modificarCarritoBase(String codigoCarritoAModificar, Carrito nuevoCarrito, String nuevoCodigo);
    
}
