/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLaveUsb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Ficheros {
    
    public boolean crearFichero(String directorio,String key){
    try {
            String ruta = directorio;
            String contenido = key;
            File file = new File(ruta+"llave_autenticacion.txt");
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
    }
    
    public String muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            cadena=cadena;
             return cadena;
        }
        b.close();
        
        return cadena;
    }
    
    public boolean CambiarNombreUnidadUSb(String unidad){
        boolean result=false;
    try {
	String [] cmd = {"LABEL",unidad,"HELLO!"}; //Comando de apagado en windows
	Runtime.getRuntime().exec(cmd);
        result=true;
    } catch (IOException ioe) {
            System.out.println (ioe);
            result=false;
    }
    return result;
    }
    
     public boolean obtenerID_usb(String unidad){
        boolean result=false;
    try {
	String [] cmd = {"wmic","logicaldisk","where","drivetype=2","get",""}; //Comando de apagado en windows
	Runtime.getRuntime().exec(cmd);
        result=true;
    } catch (IOException ioe) {
            System.out.println (ioe);
            result=false;
    }
    return result;
    }
    
}
