/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoClases;

import DaoInterfazes.CarritoDao;
import Logica.Carrito;
import Logica.CarritoBaseDatos;
import Logica.ItemCarrito;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CarritoDaoMemoria implements CarritoDao{
    
    private CarritoBaseDatos carritos;

    @Override
    public void agregarCarrito(String codigo, ArrayList<ItemCarrito> items) {
        carritos.agregarCarrito(new Carrito(codigo, items));
        
    }

    @Override
    public void eliminarCarritoPorCodigo(String codigo) {
        carritos.eliminarCarritoPorCodigoBase(codigo);
    }

    @Override
    public boolean existeCarritoPorCodigo(String codigo) {
        for(Carrito c : carritos.getCarritos()){
            if(c.getCodigo().equals(codigo))
                return true;
        }
        return false;
    }

    @Override
    public void modificarCarritoBase(String codigoCarritoAModificar, Carrito nuevoCarrito, String nuevoCodigo) { 
    }

    @Override
    public Carrito retornarCarritoPorCodigo(String codigo) {
        return carritos.retornarCarritoPorCodigoBase(codigo);
    }
}
