
package LLaveUsb;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.profesorfalken.jpowershell.PowerShell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
//import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class MD5 {
    public String Encriptar(String texto,String volUnidad) {
        Ficheros f=new Ficheros();
        //String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String secretKey="";
        String base64EncryptedString = "";

        try {
            secretKey=Extraer_GUID_USB(volUnidad);
            System.out.println("secret key:"+secretKey);
            if(!"".equals(secretKey)){
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
            }
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
    public String Desencriptar(String textoEncriptado,String volUnidad) throws Exception {

        String secretKey = ""; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            secretKey=Extraer_GUID_USB(volUnidad);
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        
        return base64EncryptedString;
}
    public String Extraer_GUID_USB(String unidad) throws IOException{
             String command = "GWMI -namespace root\\cimv2 -class win32_volume | FL -property DriveLetter, DeviceID";
             String result="";
             String ExtraerInfoDevice="";
             result=PowerShell.executeSingleCommand(command).getCommandOutput();
             result=result.replaceAll(" ", ".");
             //result = result.replace("\n", "").replace("\r", "");
             
             System.out.println(result);
             outerloop:
             for(int i=0;i<result.length();i++){
              String temp=String.valueOf(result.charAt(i)).toString();
              temp+=String.valueOf(result.charAt(i+1)).toString();
                if(temp.equals(unidad)){
                    for(int j=i+29;j<result.length();j++){
                       String temp2=String.valueOf(result.charAt(j)).toString();
                        temp2+=String.valueOf(result.charAt(j+1)).toString();
                        //System.out.println(temp2);
                       if(temp2.equals("}\\")==false){
                           ExtraerInfoDevice+=String.valueOf(result.charAt(j)).toString();
                       }else{
                          ExtraerInfoDevice=ExtraerInfoDevice.replaceAll("-","");
                          System.out.println(ExtraerInfoDevice);
                           break outerloop;
                       }
                      
                    }
             }
             }
            //ExtraerInfoDevice=ExtraerInfoDevice.replaceAll("-","");
            System.out.println("salio");
            
            return ExtraerInfoDevice;
   
            }
}
