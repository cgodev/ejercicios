/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformador;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 * @author carlo
 */
public class Transformador implements Iterable <Contenedor>{
    private static final Map<String, Method> CONVERTIDOR = new HashMap<String, Method>();
    static {
        //Cargando metodos para convertir
        Method[] methods = Transformador.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 1) {
                CONVERTIDOR.put(method.getParameterTypes()[0].getName() + "_"
                    + method.getReturnType().getName(), method);
            }
        }
    }

    private final List <String> resultado;
    private final List <Contenedor> resultadoObj;

    int pivot;
    public  Transformador(){
        this.resultado = new ArrayList<>();
        this.resultadoObj = new ArrayList<>();
    }
    public  void transformar (List lista){
        Iterator iterador = lista.iterator();
        while(iterador.hasNext()){
            pivot = (int) iterador.next();
            resultado.add(intToTxt(pivot));
        }
        System.out.println(resultado);
    }
    
    public void transformarG(List<Contenedor> lista){
        Iterator iterador = lista.iterator();
        while(iterador.hasNext()){
            Contenedor pivot = (Contenedor) iterador.next();
            resultadoObj.add(new Contenedor(Metamorfosis(pivot.getValor(),pivot.getSalida().getClass()),pivot.getSalida()));
        }
    }
    public static <T> T Metamorfosis (Object claseOriginal, Class<T> conversionA){
         // Si el objeto es nulo retonara nulo.
        if (claseOriginal == null) {
            return null;
        }

        // Si puedo hacer cast que seria un caso trivial.
        if (conversionA.isAssignableFrom(claseOriginal.getClass())) {
            return conversionA.cast(claseOriginal);
        }

        // Aqui miramos si hay un convertidor compatible
        String converterId = claseOriginal.getClass().getName() + "_" + conversionA.getName();
        Method converter = CONVERTIDOR.get(converterId);
        if (converter == null) {
            throw new UnsupportedOperationException("Imposible convertir de " 
                + claseOriginal.getClass().getName() + " a " + conversionA.getName()
                + ". Debe de escribir el metodo en el codigo.");
        }

        // Si lo hay hacemos la conversion.
        try {
            return conversionA.cast(converter.invoke(conversionA, claseOriginal));
        } catch (Exception e) {
            throw new RuntimeException("No fue posible convertir de " 
                + claseOriginal.getClass().getName() + " a " + conversionA.getName()
                + ". Fallo con codigo: " + e.getMessage(), e);
        }
    }
    
    public void showListaObjs(List<Contenedor> objs){
        if(objs == null){
            Map <Object,Object> map = new HashMap<>();
            map = this.resultadoObj.stream().collect(Collectors.toMap(item->item.getValor(), item->item.getClaseSalida()));
            map.forEach((k,v)-> System.out.println(k + " se convirtio a un -> " + v));
        }else{
            Map <Object,Object> map = new HashMap<>();
            map = objs.stream().collect(Collectors.toMap(item->item.getValor(), item->item.getSalida().getClass().getName()));
            map.forEach((k,v)-> System.out.println(k + " convertir a ->" + v));
        }
        
    }
    
    
    
    //Aca declaro la logica para la conversion de los distintos tipos de objetos.
    // AQUI USTED PUEDE IMPLEMENTAR LA LOGICA NECESARIA PARA REALIZAR LA CONVERSION DE UN BJ X A UN OBJ Y.
    
    //BOOLEAN A INTEGER
    public static Integer booleanToInteger(Boolean value) {
        return value.booleanValue() ? Integer.valueOf(1) : Integer.valueOf(0);
    }


    //INTEGER A STRING
    public static String integerToString(Integer value) {
        return value.toString();
    }

    //STRING A INTEGER
    public static Integer stringToInteger(String value) {
        return Integer.valueOf(value);
    }

    //BOOLEAN A STRING
    public static String booleanToString(Boolean value) {
        return value.toString();
    }

    //STRING A BOOLEAN
    public static Boolean stringToBoolean(String value) {
        return Boolean.valueOf(value);
    }
    //INTEGER A BOOLEAN
    public static Boolean integerToBoolean(Integer value) {
        return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    //Auxiliar para convertir int a palabra Ejercicio1:
    private String intToTxt(int entero){
        String palabra;
        palabra = cantidadConLetra(Integer.toString(entero));
        return palabra;
    }
    //Aca esta la logica para convertir un entero en palabra:
    private static String cantidadConLetra(String s) {
        StringBuilder result = new StringBuilder();
        BigDecimal totalBigDecimal = new BigDecimal(s).setScale(2, BigDecimal.ROUND_DOWN);
        long parteEntera = totalBigDecimal.toBigInteger().longValue();
        int triUnidades      = (int)((parteEntera % 1000));
        int triMiles         = (int)((parteEntera / 1000) % 1000);
        int triMillones      = (int)((parteEntera / 1000000) % 1000);
        int triMilMillones   = (int)((parteEntera / 1000000000) % 1000);
 
        if (parteEntera == 0) {
            result.append("Cero ");
            return result.toString();
        }
 
        if (triMilMillones > 0) result.append(triTexto(triMilMillones).toString() + "Mil ");
        if (triMillones > 0)    result.append(triTexto(triMillones).toString());
 
        if (triMilMillones == 0 && triMillones == 1) result.append("Millón ");
        else if (triMilMillones > 0 || triMillones > 0) result.append("Millones ");
 
        if (triMiles > 0)       result.append(triTexto(triMiles).toString() + "Mil ");
        if (triUnidades > 0)    result.append(triTexto(triUnidades).toString());
 
        return result.toString();
    }
    
    private static StringBuilder triTexto(int n) {
        StringBuilder result = new StringBuilder();
        int centenas = n / 100;
        int decenas  = (n % 100) / 10;
        int unidades = (n % 10);
 
        switch (centenas) {
            case 0: break;
            case 1:
                if (decenas == 0 && unidades == 0) {
                    result.append("Cien ");
                    return result;
                }
                else result.append("Ciento ");
                break;
            case 2: result.append("Doscientos "); break;
            case 3: result.append("Trescientos "); break;
            case 4: result.append("Cuatrocientos "); break;
            case 5: result.append("Quinientos "); break;
            case 6: result.append("Seiscientos "); break;
            case 7: result.append("Setecientos "); break;
            case 8: result.append("Ochocientos "); break;
            case 9: result.append("Novecientos "); break;
        }
 
        switch (decenas) {
            case 0: break;
            case 1:
        switch (unidades) {
            case 0:
                result.append("Diez "); return result;
            case 1:
                result.append("Once "); return result;
            case 2:
                result.append("Doce "); return result;
            case 3:
                result.append("Trece "); return result;
            case 4:
                result.append("Catorce "); return result;
            case 5:
                result.append("Quince "); return result;
            default:
                result.append("Dieci");
                break;
        }
                break;
            case 2:
                if (unidades == 0) { result.append("Veinte "); return result; }
                else result.append("Veinti");
                break;
            case 3: result.append("Treinta "); break;
            case 4: result.append("Cuarenta "); break;
            case 5: result.append("Cincuenta "); break;
            case 6: result.append("Sesenta "); break;
            case 7: result.append("Setenta "); break;
            case 8: result.append("Ochenta "); break;
            case 9: result.append("Noventa "); break;
        }
 
        if (decenas > 2 && unidades > 0)
            result.append("y ");
 
        switch (unidades) {
            case 0: break;
            case 1: result.append("Un "); break;
            case 2: result.append("Dos "); break;
            case 3: result.append("Tres "); break;
            case 4: result.append("Cuatro "); break;
            case 5: result.append("Cinco "); break;
            case 6: result.append("Seis "); break;
            case 7: result.append("Siete "); break;
            case 8: result.append("Ocho "); break;
            case 9: result.append("Nueve "); break;
        }
 
        return result;
    }
    
    @Override
    public Iterator<Contenedor> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
