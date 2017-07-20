//This controller has a lot of insecure code, is only a PoC, don't follow this controller for build any web code

package controllers;


import java.io.IOException;
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
        
    return new ModelAndView("index");
  }
  
  @RequestMapping(value = "/mail", method=RequestMethod.POST)
  public ModelAndView loginPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
      
	String emailTo = request.getParameter("emailTo");
        
        String emailFrom = request.getParameter("emailFrom");
        
        String body = request.getParameter("body");
        
        String submit = request.getParameter("submit");


      if ( ("Command Injection".equals(submit)) ){

            //We are passing strings without control to a sh/bash/cmd interpreter ==> Command Injection
            String[] cmd = new String[]{"/bin/bash","-c","/usr/sbin/sendmail -f"+emailFrom};
            Runtime.getRuntime().exec(cmd);

            //Same as Exec
            ProcessBuilder pb = new ProcessBuilder(cmd);
	    pb.start();

        }else if( ("Argument Injection".equals(submit)) ){
  
            //We are invoking an process without calling a sh/bash/cmd interpreter. But Still, thanks to Runtime.java
            //tokenizer, we are able to inject extra arguments to target process.
            String cmd = "/usr/sbin/sendmail -f" + emailFrom;
            Runtime.getRuntime().exec(cmd);
            
        }else{

            String[] cmd = new String[]{"/usr/sbin/sendmail","-f",emailFrom};
            //We are passing directly a crafted array to Runtime.java, it is gonna call ProcessBuilder without
            //tokenization. So add extra arguments to target process is not possible.
            Runtime.getRuntime().exec(cmd);
            //By default, ProcessBuilder is protected against Argument Injection, since we are not able to add extra
            //arguments to called process.
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.start();

            
            
        }
 
  return new ModelAndView("index");
  
    }
  
  
}
