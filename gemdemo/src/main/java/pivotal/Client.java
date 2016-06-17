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
import java.util.UUID;

/**
 * Created by pivotal on 9/22/15.
 */
@RestController
public class Client {

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
        String myValue = UUID.randomUUID().toString();

		myRegion.put(putNum, myValue);
	
		return "Now putting the key " + putNum + " to " + shortcuts + "\n";
    }
    
}
