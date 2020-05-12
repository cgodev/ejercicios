/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformador;

/**
 *
 * @author carlo
 * @param <k>
 * @param <v>
 * 
 */
public class Contenedor <k,v> {
    k valor;
    v salida;

    public Contenedor(k valor, v salida) {
        this.valor = valor;
        this.salida = salida;
    }
    
    

    public k getValor() {
        return valor;
    }

    public void setValor(k valor) {
        this.valor = valor;
    }

    public v getSalida() {
        return salida;
    }

    public void setSalida(v salida) {
        this.salida = salida;
    }
    public String getClaseSalida(){
        return salida.getClass().getName();
    }
}
