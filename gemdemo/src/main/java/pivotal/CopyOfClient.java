package pivotal;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Created by pivotal on 9/22/15.
 */
@RestController
public class CopyOfClient {

    @Autowired
    ClientCache cache;
    Region myRegion;
    String shortcuts;

    @RequestMapping("/doget")
    public String doGet() {
    	if(myRegion == null){
/*			   		
 * 	Define the client shortcut
 */
    		
	       myRegion = cache.createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("test");
	       shortcuts = "CACHING_PROXY";
// 	       myRegion = cache.createClientRegionFactory(ClientRegionShortcut.PROXY).create("test");
//	       shortcuts = "PROXY";
    	}    	

       Random rand = new Random();
       int getNum = rand.nextInt(10000);
        
        Object val = myRegion.get(getNum);
        		
        return "key is " + getNum + " to " + shortcuts + "\n";
    }    

    @RequestMapping("/doput")
    public String doPut() {

    	if(myRegion == null){
/*			   		
 * 	Define the client shortcut
 */
		       myRegion = cache.createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("test");
		       shortcuts = "CACHING_PROXY";
//		       myRegion = cache.createClientRegionFactory(ClientRegionShortcut.PROXY).create("test");
//		       shortcuts = "PROXY";
    	}    	

		Random rand = new Random();
		int putNum = rand.nextInt(10000);
		BufferedImage image = null;

		try{		
		   File file = new File("ApacheGeodeFullColor.png");
		   image = ImageIO.read(new File("ApacheGeodeFullColor.png"));	   	  	   	   
				   
	     }catch(IOException ioe){
	  	   ioe.printStackTrace();
	     }

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		image.flush();

		try {
			ImageIO.write(image, "png", bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		byte[] bImage = baos.toByteArray();
	
		myRegion.put(putNum, Base64.encode(bImage));
	
		Object val = myRegion.get(putNum);
		return "Now putting the key " + putNum + " to " + shortcuts + "\n";
    }
    
    @RequestMapping("/poolIdleTimeout")
    public Long getClientPoolIdleTimeout() {
        return cache.getDefaultPool().getIdleTimeout();
    }

    @ExceptionHandler()
    public void handleException(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), getStackString(ex));
    }

    private String getStackString(Exception e) {
        StringBuilder s = new StringBuilder(e.getClass().getCanonicalName()).append(e.getMessage());
        for (StackTraceElement ste : e.getStackTrace()) {
            s.append("  at ").append(ste.toString()).append("\n");
        }
        return s.toString();
    }
}
