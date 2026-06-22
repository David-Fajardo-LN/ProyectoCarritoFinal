/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Dto.ProductoDTO;
import Resultado.ResultadoOperacion;
import Servicios.ProductoServicio;
import Ventanas.Producto.VAgregarProductoView;
import Ventanas.Producto.VBuscarProductoView;
import Ventanas.Producto.VEliminarProductoView;
import Ventanas.Producto.VListarProductosView;
import Ventanas.Producto.VModificarProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Producto;

/**
 *
 * @author User
 */
public class ControladorProducto {
    
    //El controlador conoce todas las ventanas de la seccion y recive como parametro una clase logica de servicio, no la instancia
    //ya que no es su responsabilidad instanciar servicios, solamente comunicar a servicios con los eventos existentes y mostrar respuestas
    
    private ProductoServicio servicioProducto;
    
    private VAgregarProductoView agregarProductoView;
    private VBuscarProductoView buscarProductoView;
    private VEliminarProductoView eliminarProductoView;
    private VListarProductosView listarProductosView;
    private VModificarProductoView modificarProductoView;
    
    private ProductoDTO productoDTOAuxiliar;
    private Runnable regresarMenu;
    
    public ControladorProducto(ProductoServicio servicioProducto) {
        
        this.servicioProducto= servicioProducto;
        
        this.agregarProductoView = new VAgregarProductoView();
        this.buscarProductoView = new VBuscarProductoView();
        this.eliminarProductoView = new VEliminarProductoView();
        this.listarProductosView = new VListarProductosView();
        this.modificarProductoView = new VModificarProductoView();
        this.productoDTOAuxiliar=null;
        
        configurarEventos();
        
    }

    public void setRegresarMenu(Runnable regresarMenu) {
        this.regresarMenu = regresarMenu;
    }
    
    
    
    private void configurarEventos(){

        eventoAgregarProducto();
        eventoBuscarProducto();
        eventoBuscarProductoAEliminar();
        eventoEliminarProducto();
        eventoBuscarProductoAModificar();
        eventoModificarProducto();
        eventoListarProductos();

        regresarDeVentanaAgregar();
        regresarDeVentanaEliminar();
        regresarDeVentanaModificar();
        regresarDeVentanaBuscar();
        regresarDeVentanaListar();
    }
    
    
    // METODOS DE LA VENTANA AGREGAR PRODUCTO
    
    private void agregarProducto(){
        
        // El metodo extrae la informacion ingresada por el usuario usando los getter de la ventana
        
        String nuevoCodigoProducto = agregarProductoView.getTxtCodigoProducto();
        String nuevoNombreProducto = agregarProductoView.getTxtNombreProducto();
        String nuevoPrecioProducto = agregarProductoView.getTxtPrecioProducto();
        
        // servicio retorna un resultado, que contiene un booleano que indica si se completo o no, un mensaje sobre el proceso y un dato
        // Por ejemplo resultado = (false,"Error: codigo invalido", null); Esto ocupa el controlador para mostrar el error especifico o
        // el proceso exitoso.
        
        ResultadoOperacion resultado = servicioProducto.agregarProducto(nuevoCodigoProducto, nuevoNombreProducto, nuevoPrecioProducto);
        
        // Si el resultado no fue exitoso, entonces muestra en mensaje de error extraido de resultad y termina el metodo.
        
        if(!resultado.fueExitoso()){
            JOptionPane.showMessageDialog(agregarProductoView, resultado.getMensaje());
            return;
        }
        
        // Si el proceso fue exitoso, mostramos el mensaje de exito del resultado y ademas, limpiamos los datos para que sea mas comodo
        //ingresar otro producto si asi lo requiere el usuario.
        
        JOptionPane.showMessageDialog(agregarProductoView, resultado.getMensaje());
        agregarProductoView.setTxtCodigoProducto("");
        agregarProductoView.setTxtNombreProducto("");
        agregarProductoView.setTxtPrecioProducto(""); 
    }
    
    // METODOS DE LA VENTANA BUSCAR PRODUCTO
    
    private void buscarProducto(){
        String codigoDeProductoABuscar = buscarProductoView.getTxtCodigoBuscar();
        // Como resultaod es una clase generica y sabemos que servicio va a retornar un DTO si lo encuentra, entocnes neseitamos indicar a que
        // clase pertenece el objeto retornado, para poder acceder a sus valores.
        ResultadoOperacion<ProductoDTO> resultado = servicioProducto.buscarProducto(codigoDeProductoABuscar);
        
        if(!resultado.fueExitoso()){
            JOptionPane.showMessageDialog(buscarProductoView, resultado.getMensaje());
            return;
        }

        //El proceso en este punto fue exitoso, entocnes extraemos el DTO que asegura el encapsulamiento y ademas, podemos mostrar sus valores en ventana
        ProductoDTO productoDTO = resultado.getDato();
        
        buscarProductoView.setTxtCodigoProducto(productoDTO.getCodigo());
        buscarProductoView.setTxtNombreProducto(productoDTO.getNombre());
        // Realizamos el casting del double a string para pode rmostrar en la ventana
        buscarProductoView.setTxtPrecioProducto(String.valueOf(productoDTO.getPrecio()));
    }
    
    // METODOS DE LA VENTANA ELIMINAR PRODUCTO
    
    private void buscarProductoAEliminar(){
        String codigoAEliminar = eliminarProductoView.getTxtBuscarCodigoEliminar();
        
        ResultadoOperacion<ProductoDTO> resultado = servicioProducto.buscarProducto(codigoAEliminar);
        
        if(!resultado.fueExitoso()){
            JOptionPane.showMessageDialog(eliminarProductoView, resultado.getMensaje());
            return;
        }
        
        ProductoDTO productoDTO = resultado.getDato();
        this.productoDTOAuxiliar=productoDTO;
        
        eliminarProductoView.setTxtCodigoProducto(productoDTO.getCodigo());
        eliminarProductoView.setTxtNombreProducto(productoDTO.getNombre());
        eliminarProductoView.setTxtPrecioProducto(String.valueOf(productoDTO.getPrecio()));
    }
    
    private void eliminarProducto(){ 
        if(this.productoDTOAuxiliar == null){
            JOptionPane.showMessageDialog(eliminarProductoView, "Error: primero busque un producto");
            return;
        }
        
        String codigoAEliminar = productoDTOAuxiliar.getCodigo();
        ResultadoOperacion resultado = servicioProducto.eliminarProducto(codigoAEliminar);
        JOptionPane.showMessageDialog(eliminarProductoView, resultado.getMensaje());
        
        eliminarProductoView.setTxtBuscarCodigoEliminar("");
        eliminarProductoView.setTxtCodigoProducto("");
        eliminarProductoView.setTxtNombreProducto("");
        eliminarProductoView.setTxtPrecioProducto("");
        this.productoDTOAuxiliar=null;
    }
    
    // METODOS DE LA VENTANA MODIFICAR PRODUCTO
    
    private void buscarProductoAModificar(){
        String codigoAModificar = modificarProductoView.getTxtBuscarCodigo();
        
        ResultadoOperacion<ProductoDTO> resultado = servicioProducto.obtenerProductoPorCodigo(codigoAModificar);
        
        if(!resultado.fueExitoso()){
            JOptionPane.showMessageDialog(eliminarProductoView, resultado.getMensaje());
            return;
        }
        
        ProductoDTO productoDTO = resultado.getDato();
        this.productoDTOAuxiliar=productoDTO;
        
        String precio = String.valueOf(productoDTO.getPrecio());
        modificarProductoView.setTxtNombreProducto(productoDTOAuxiliar.getNombre());
        modificarProductoView.setTxtPrecioProducto(precio);
    }
    
    private void modificarProducto(){
        if(this.productoDTOAuxiliar == null){
            JOptionPane.showMessageDialog(eliminarProductoView, "Error: primero busque un producto");
            return;
        }
        
        String codigoAModificar = productoDTOAuxiliar.getCodigo();
        
        String nuevoNombre = modificarProductoView.getTxtNombreProducto();
        String nuevoPrecio = modificarProductoView.getTxtPrecioProducto();
        
        
        
        ResultadoOperacion resultado = servicioProducto.actualizarProducto(productoDTOAuxiliar.getCodigo(), nuevoNombre, String.valueOf(nuevoPrecio));
        JOptionPane.showMessageDialog(modificarProductoView, resultado.getMensaje());
        
        modificarProductoView.setTxtBuscarCodigo("");
        modificarProductoView.setTxtNombreProducto("");
        modificarProductoView.setTxtPrecioProducto("");
        this.productoDTOAuxiliar=null;
    }
    
    // METODOS DE LA VENTANA MODIFICAR PRODUCTO
    
    private void ListarProductos(){
        ResultadoOperacion resultado = servicioProducto.listarProductos();
        ArrayList<ProductoDTO> lista = (ArrayList<ProductoDTO>) resultado.getDato();
        if(resultado.fueExitoso()){
            listarProductosView.mostrarProductos(lista);
        }
    }
    
    // METODOS QUE INICIALIZAN ESCUCHADORES Y EVENTOS AL OCURRIR UN DISPARADOR
    
    public void eventoAgregarProducto(){
        agregarProductoView.getBtnGuardar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
    }
    
    public void eventoBuscarProducto(){
        buscarProductoView.getBtnBuscarPorCodigo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });
    }
    
    
    
    public void eventoBuscarProductoAEliminar(){
        eliminarProductoView.getBtnBuscarProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoAEliminar();
            }
        });
    }
    
    public void eventoEliminarProducto(){
        eliminarProductoView.getBtnEliminar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }
    
    public void eventoBuscarProductoAModificar(){
        modificarProductoView.getBtnBuscarProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoAModificar();
            }
        });
    }
    
    public void eventoModificarProducto(){
        modificarProductoView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            } 
        });
    }
    
    public void eventoListarProductos(){
        listarProductosView.getBtnListarProductos().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarProductos();
            }
        });
    }
    
    private void limpiarDatosAuxiliares(){
        this.productoDTOAuxiliar = null;
    }
    
    
    public void regresarDeVentanaAgregar(){
        agregarProductoView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatosAuxiliares();
                regresarMenu.run();
                agregarProductoView.dispose();
            }
        });
    }
    
    public void regresarDeVentanaEliminar(){
        eliminarProductoView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatosAuxiliares();
                regresarMenu.run();
                eliminarProductoView.setVisible(false);
            }
        });
    }
    
    public void regresarDeVentanaModificar(){
        modificarProductoView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatosAuxiliares();
                regresarMenu.run();
                modificarProductoView.setVisible(false);
            }
        });
    }
    
    public void regresarDeVentanaBuscar(){
        buscarProductoView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatosAuxiliares();
                buscarProductoView.setTxtCodigoBuscar("");
                buscarProductoView.setTxtCodigoProducto("");
                buscarProductoView.setTxtNombreProducto("");
                buscarProductoView.setTxtPrecioProducto("");
                regresarMenu.run();
                buscarProductoView.setVisible(false);
            }
        });
    }
    
    public void regresarDeVentanaListar(){
        listarProductosView.getBtnRegresarMenu().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatosAuxiliares();
                regresarMenu.run();
                listarProductosView.setVisible(false);
            }
        });
    }
    
    public void mostrarVentanaCrearProducto(){
        this.agregarProductoView.setLocationRelativeTo(null);
        this.agregarProductoView.setVisible(true);
    }
    
    public void mostrarVentanaEliminarProducto(){
        this.eliminarProductoView.setLocationRelativeTo(null);
        this.eliminarProductoView.setVisible(true);
    }
    
    public void mostrarVentanaBuscarProducto(){
        this.buscarProductoView.setLocationRelativeTo(null);
        this.buscarProductoView.setVisible(true);
    }
    
    public void mostrarVentanaModificarProducto(){
        this.modificarProductoView.setLocationRelativeTo(null);
        this.modificarProductoView.setVisible(true);
    }
    
    public void mostrarVentanaListarProducto(){
        this.listarProductosView.setLocationRelativeTo(null);
        this.listarProductosView.setVisible(true);
    }
}
