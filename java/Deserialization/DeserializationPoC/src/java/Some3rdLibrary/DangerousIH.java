
package Some3rdLibrary;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//Obvious Invocation Handler that an attacker could use to RCE through deserialization

public class DangerousIH implements Serializable,InvocationHandler{

    private String command;
       public DangerousIH(String Command){
    
        this.command = Command;
    
    }
       
    //The method invoke can be used to trick the call of another method!
    @Override
    public Object invoke(Object o, Method method, Object[] os) throws IOException{
        
        Runtime.getRuntime().exec(command); 
        return null;
    
    }
   
}
    
    
    
    

