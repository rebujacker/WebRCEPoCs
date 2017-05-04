package controllers;

import Some3rdLibrary.InoffensiveRunnable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


//This is the main object that is being serialized. The functions inside have the purpose to server a PoC but have not logic use in a real situation.

public class Session implements Serializable{

    
    private Runnable initHook;
    private Comparator comp;
    private final String secret;
    private final String username;

    
    
    
    public Session(Runnable inithook,String Username, String Secret,Comparator comp){
        
        this.initHook = inithook;
        this.secret = Secret;
        this.username = Username;
        this.comp = comp;
        
    }
    

    
    public Session readObject(ByteArrayInputStream ois) throws IOException{
    
       return null;
   
    
    }
    
    
    public ByteArrayOutputStream writeObject(Session ob) throws IOException{
    
       return null;
    }
    
        
    public String getSecret() {

       return null;
    }

    public String getUsername() {
        return null;
    }
    
    public Comparator getComparator() {
        return null;
    }
      
    public Runnable getRunnable() {
        return null;
    }
}
