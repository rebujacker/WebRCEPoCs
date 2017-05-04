package Some3rdLibrary;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


// Obvious Handler that attacker can use for perform a RCE with uncontrolled deserialization

public class DangerousH implements Runnable,Serializable{

    private final String command;
    public DangerousH (String command){
    
    this.command = command;
    }
    @Override
    public void run() {
        
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(DangerousH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
