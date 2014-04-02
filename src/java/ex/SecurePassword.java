/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex;

import java.security.MessageDigest;

/**
 *
 * @author Keris
 */
public class SecurePassword {
    
    public SecurePassword(){
        
    }
    
    public String md5password(String pass){
         
          String salt = "39494::://ééé;;!!èè@mkljkei'''§§@.";
           String passwordToHash = pass + salt;
          String generatedPassword = null;
           try {
               //create messageDigest instance for MD5
               MessageDigest md = MessageDigest.getInstance("MD5");
               //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[
                            i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
           }catch (Exception e){
               e.printStackTrace();
           }
         return generatedPassword;  
    }
}
