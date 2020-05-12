/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Uso de lista que por default ya viene con collections generic implementado.
        //Instancia
        
        List<Integer> listaDeEnteros;
        listaDeEnteros = new ArrayList<>();
        listaDeEnteros.add(0);
        listaDeEnteros.add(15);
        listaDeEnteros.add(30);
        listaDeEnteros.add(98);
        
        System.out.println("Ejercicio 1.");
        System.out.println(listaDeEnteros);
        Transformador t = new Transformador();
        t.transformar(listaDeEnteros);
        System.out.println("Ejercicio 1 terminado.");
        
        //Instancia Lista con objetos de la clase container que trabaja con generics k,v como valores de objeto/destino respectivamente.
        //El parametro k sera el objeto a convertir y se insertara en v un tipo de dato que sea el mismo tipo de dato al que se desea llegar.
        List<Contenedor> listaObjs = new ArrayList<>();
        listaObjs.add(new Contenedor(1,"String"));
        listaObjs.add(new Contenedor(0,false));
        //listaObjs.add(new Contenedor("HI",1)); Si quitan el comentario de esta conversion se lanza la excepcion que lanza por incompatibilidad de datos.
        listaObjs.add(new Contenedor(false,0));
        System.out.println("Ejercicio 2.");
        //Muestro la instancia de objetos que deseo transformar y a que los deseo transformar.
        t.showListaObjs(listaObjs);
        //Se procesan los datos
        t.transformarG(listaObjs);
        System.out.println("Tarea procesada.");
        //Se muestra el resultado
        t.showListaObjs(null);
        
                
        
        /*EJERCICIO
        
        1) Crear una funcion/método "transformar", la cual toma un listado de números enteros y por cada elemento retorna su respectivo valor en string, ejemplo
        transformar(List(1,2,3,4)) ..salida: List('uno', 'dos','tres','cuatro')

        Consideraciones:
        No se permite el uso de For
        Que función conoce que realice la misma funcionalidad de la función "transformar" que ya exista en el mundo Java y que evite reimplementar la rueda?


        2) Evolucione el ejercicio #1 y cree una función "transformarG" que reciba un listado de cualquier tipo de dato y transforme cada elemento en cualquier 
        tipo destino.
        Tips y Consideraciones:
        -Se puede revisa el tema de generics
        -De nuevo, no se permite el uso de For 

        Por último, como resultado entregue la URL de descarga del proyecto, para descargarlo desde el repositorio github, indique lo que considere para ejecutar el 
        proyecto y ver la salida. Para mostrar los resultados, es suficiente con que se muetre por consola el resultado.
        */
        
    }
    
}
