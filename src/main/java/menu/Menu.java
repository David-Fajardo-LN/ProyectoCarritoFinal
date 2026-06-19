/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package menu;

import Controladores.ProductoControlador;
import DaoClases.ProductoDaoMemoria;
import Logica.ProductoBaseDatos;
import Ventanas.VPrincipalView;

/**
 *
 * @author User
 */
public class Menu {

    public static void main(String[] args) {
        
        
    // SE PASA EL CONTROLADOR COMO PARAMETRO A LA VENTANA PRINCIPAL PARA USAR LOS METODOS PUBLICOS
    // SE PASA COMO PARAMETRO AL CONTROLADOR LA BASE DE DATOS, PARA QUE SIEMPR EOPERE EN LA MISMA INSTANCIA DE DATOS
        
    /*
        
    Las ventanas secundarias (agregar, eliminar, modificar, buscar, etc.) 
    reciben como parámetro la instancia de la ventana principal junto con el controlador que esta utiliza.

    Esto se realiza con el objetivo de mantener una única instancia de los objetos durante la ejecución del programa. 
    Si cada ventana creara una nueva instancia de la ventana principal, también se generaría una nueva instancia de la base de datos, 
    provocando que los cambios realizados anteriormente se pierdan.

    Por ejemplo, un producto podría ser eliminado correctamente desde la ventana de eliminación, pero al regresar a la ventana principal
    se cargaría nuevamente una base de datos con los valores iniciales, 
    haciendo que las operaciones de agregar, eliminar, modificar y buscar pierdan su funcionalidad.

    Al compartir la misma instancia, todas las ventanas trabajan sobre la misma fuente de datos, 
    permitiendo que los cambios realizados se mantengan y sean visibles en todo el sistema.
    */
        
        ProductoBaseDatos baseDatosProducto = new ProductoBaseDatos();
        ProductoDaoMemoria productoDaoMemoria = new ProductoDaoMemoria(baseDatosProducto);
        ProductoControlador controladorProducto = new ProductoControlador(productoDaoMemoria);
        
        VPrincipalView principalView = new VPrincipalView(controladorProducto);
        principalView.setLocationRelativeTo(null);
        principalView.setVisible(true);
        
    }
}
