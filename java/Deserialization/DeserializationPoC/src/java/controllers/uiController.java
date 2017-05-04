//This controller has a lot of insecure code, is only a PoC, don't follow this controller for build any web code

package controllers;

import Some3rdLibrary.InoffensiveComparator;
import Some3rdLibrary.InoffensiveRunnable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class uiController {
  @RequestMapping(value = "/*")
  public ModelAndView mainPage(HttpServletRequest request) throws IOException {
      
      
      Cookie[] cookies = request.getCookies();
      
      if(cookies != null){
      
      for (Cookie c:cookies){
          
          if (c.getName().equals("session")){
              
              String value = c.getValue();

              byte[] decodedb64 = Base64.getDecoder().decode(value.getBytes());
              
              ByteArrayInputStream serializedbytes = new ByteArrayInputStream(decodedb64);

              Session session = (Session) new Session(null,null,null,null);
              
              //Use the dangerous method readObject to feed the serialized data from the cookie
              //and create the object Session!
              Session session2 = session.readObject(serializedbytes);
              
              
              if(session2.getUsername().equals("admin") && session2.getSecret().equals("test1234")){
              
              
                     return new ModelAndView("logged");
              
              }
              
              
             
              
          }
          
      }
      
      }
      
    return new ModelAndView("index");
  }
  
  @RequestMapping(value = "/login", method=RequestMethod.POST)
  public ModelAndView loginPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
      
      Cookie[] cookies = request.getCookies();
      
      if(cookies != null){
      
      for (Cookie c:cookies){
          
          if (c.getName().equals("session")){
              
              String value = c.getValue();

              byte[] decodedb64 = Base64.getDecoder().decode(value.getBytes());

              ByteArrayInputStream serializedbytes = new ByteArrayInputStream(decodedb64);

              Session session = (Session) new Session(null,null,null,null);

              Session session2 = session.readObject(serializedbytes);
              
              if(session2.getUsername().equals("admin") && session2.getSecret().equals("test1234")){
              
              
                     return new ModelAndView("logged");
              
              }
              
          }
          
      }
      
      }
      
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      
      
      if (("admin".equals(username)) && ("test1234".equals(password))){
  

          Runnable inoffensiverunnable = new InoffensiveRunnable();
          Comparator inoffensivecomparator = new InoffensiveComparator();
          
          //Create the session cookie using default inoffensive classes
          Session session = new Session(inoffensiverunnable,"admin","test1234",inoffensivecomparator);
          ByteArrayOutputStream serializedbytes = session.writeObject(session);

          String b64secret = new String(Base64.getEncoder().encode(serializedbytes.toByteArray()));

          response.addCookie(new Cookie("session", b64secret));
          
          return new ModelAndView("logged");
  
         }

         
      
  return new ModelAndView("index");
  
    }
  
  
  
  //This function is created to support execesive long payloads like beanshell!
  @RequestMapping(value = "/dangerousfunction", method=RequestMethod.POST)
  public void dangerousfunction(HttpServletRequest request,HttpServletResponse response) throws IOException {

              String value = request.getParameter("session");

              byte[] decodedb64 = Base64.getDecoder().decode(value);

              ByteArrayInputStream serializedbytes = new ByteArrayInputStream(decodedb64);
              
              String test = new String(decodedb64);
              System.out.println(test);

              Session session = (Session) new Session(null,null,null,null);
              Session session2 = session.readObject(serializedbytes);

  }
  
  
}
