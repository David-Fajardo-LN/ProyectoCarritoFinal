/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resultado;

/**
 *
 * @author User
 */
public class ResultadoOperacion<T> {
    private boolean fueExitoso;
    private String mensaje;
    private T dato;

    public ResultadoOperacion(boolean fueExitoso, String mensaje, T dato) {
        this.fueExitoso = fueExitoso;
        this.mensaje = mensaje;
        this.dato = dato;
    }
    
    public boolean fueExitoso() {
        return fueExitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDato() {
        return dato;
    }
}
