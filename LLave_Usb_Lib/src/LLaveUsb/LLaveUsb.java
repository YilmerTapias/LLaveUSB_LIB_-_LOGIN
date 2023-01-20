
package LLaveUsb;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class LLaveUsb {
    MD5 md5=new MD5();
    Ficheros f=new Ficheros();
    File unidades[] = File.listRoots();
    int UsbActuales=0;
    
    //validarDatos, returna 0=llave no ingresa, 1=reconociendo llave, 2=intento de ingreso no autorizado
    // 3=llave reconocida.
    public int validarDatos(File unidades[]){
        int result=0;
        String autenticacion="";
        String key="";
        try{
            
            for(int i=0;i<File.listRoots().length;i++){
              autenticacion="";
              String unidad = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
                if(unidad.contains("HELLO!")==true){ 
                    autenticacion=f.muestraContenido(unidades[i].toString()+"llave_autenticacion.txt");
                    key=md5.Encriptar("Hello World!",unidades[i].toString().substring(0,2));
                    //System.out.println("aute: "+autenticacion);
                    //System.out.println("key: "+key);
                    if(autenticacion.equals(key)){                     
                     result=3;
                     
                     }else{
                     result=2;
                     }
                }else{
                   unidades=File.listRoots();
                  
                }
            }
            
        }catch(Exception e){
        }

       System.out.println("return: "+result);
       return result; 
    }
    
    public String[] Volumenes_todas_usb(){
     String[] temp=new String[unidades.length];
        for(int i=0;i<unidades.length;i++){
            if(!"C:".equals(unidades[i].toString().substring(0,2))){
            temp[i]=unidades[i].toString().substring(0,2);
            }        
        }
        return temp;
    }
    
    public boolean crearFichero(String VolumenUnidad){
        boolean result=false;
        Ficheros f=new Ficheros();
        MD5 md5=new MD5();
        File unidades[] = File.listRoots();
        
        if(f.CambiarNombreUnidadUSb(VolumenUnidad)==true){         
        for(int i=0;i<unidades.length;i++)
        {   
            String unidad = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
            if(unidad.contains("HELLO!")){
                  String encriptado=md5.Encriptar("Hello World!",VolumenUnidad);
                  //System.out.println("volUnidad:"+VolumenUnidad);
                  //System.out.println("encriptado: "+encriptado);
                  boolean resultado=f.crearFichero(unidades[i].toString(), encriptado);
                  if(resultado!=true){
                      //JOptionPane.showMessageDialog(null, "ERROR: ARCHIVO NO CREADO");
                      result=false;
                  }else{
                      JOptionPane.showMessageDialog(null, "ARCHIVO CREADO!");
                      result=true;
                  }
                  
            }
                  
        }
        }else{
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error verifique que la usb esta conectada e intente nuevamente");
            result=false;
        }
        return result;
    }

    public int validarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
