package controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;



//This is the main object that is being serialized. The functions inside have the purpose to server a PoC but have not logic use in a real situation.

public class Session implements Serializable{

    //Attacker will control these attributes of the serilized class!!
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
    

    //This is the method that read the user Input malicious data!!
    public Session readObject(ByteArrayInputStream ois) throws IOException{
    
        Session s =  null;
        ObjectInputStream in = new ObjectInputStream(ois);
        
        try {
            
            s = (Session) in.readObject();
            
        } catch (IOException | ClassNotFoundException ex) {

        }
        
        //These method calls of the attributes are the "trampoline" that attacker could use!
        //They doesn't need to be inside readObject, just need to be called from the crafted object!
        int a = s.getComparator().compare(s.getSecret(), s.getUsername());
        
        Runnable ad = s.getRunnable();
        ad.run();
       
        
        return s;
   
    
    }
    
    
    public ByteArrayOutputStream writeObject(Session ob) throws IOException{
    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outStream = new ObjectOutputStream(bos);

        try {
            outStream.writeObject(ob);
            
            } finally {
                try {
                        outStream.close(); 
                    } catch (IOException ex) {
            // ignore close exception
                                     }
                        }
        return bos;
    }
    
        
    public String getSecret() {
        return secret;
    }

    public String getUsername() {
        return username;
    }
    
    public Comparator getComparator() {
        return comp;
    }
      
    public Runnable getRunnable() {
        return initHook;
    }
}
