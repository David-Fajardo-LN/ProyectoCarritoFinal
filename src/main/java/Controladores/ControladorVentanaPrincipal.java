/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Ventanas.VPrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

/**
 *
 * @author User
 */
public class ControladorVentanaPrincipal {
    
    private VPrincipalView ventanaPrincipal;
    private ControladorProducto controladorProducto;

    public ControladorVentanaPrincipal(ControladorProducto controladorProducto) {
        this.ventanaPrincipal = new VPrincipalView();
        this.controladorProducto = controladorProducto;
        controladorProducto.setRegresarMenu(accionRegresar);
        this.ventanaPrincipal.setVisible(true);
        this.ventanaPrincipal.setLocationRelativeTo(null);
        configurarEventos();
    }
    
    Runnable accionRegresar = new Runnable(){
        @Override
        public void run(){
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setLocationRelativeTo(null);
        }
    };
    
    
    public void configurarEventos(){
        abrirOpcionCrearProducto();
        abrirOpcionBuscarProducto();
        abrirOpcionModificarProducto();
        abrirOpcionListarProductos();
    }
    
    
    public void abrirOpcionCrearProducto(){
        ventanaPrincipal.getOpcionCrearProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                controladorProducto.mostrarVentanaCrearProducto();
                
            }
        });
    }
    
    public void abrirOpcionBuscarProducto(){
        ventanaPrincipal.getOpcionBuscarProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                controladorProducto.mostrarVentanaBuscarProducto();
            }
        });
    }
    
    public void abrirOpcionModificarProducto(){
        ventanaPrincipal.getOpcionModificarProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                controladorProducto.mostrarVentanaModificarProducto();
            }
        });
    }
    
    public void abrirOpcionListarProductos(){
        ventanaPrincipal.getOpcionListarProductos().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                controladorProducto.mostrarVentanaListarProducto();
            }
        });
    }
    
    public void abrirOpcionEliminarProducto(){
        ventanaPrincipal.getOpcionEliminarProducto().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                controladorProducto.mostrarVentanaEliminarProducto();
            }
        });
    }
    
    
}
