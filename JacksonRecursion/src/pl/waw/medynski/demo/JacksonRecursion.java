/**
 * History:
 * 
 * 1 - Initial release.
 */
package pl.waw.medynski.demo;




/**
 * Imports.
 */
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.util.ArrayList;
import java.util.List;




/**
 * JacksonRecursion class.
 * 
 * @author Maciej Medyński
 * @version 1
 */
public class JacksonRecursion
{
    /**
     * Entry point.
     * 
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
        /*
        https://stackoverflow.com/questions/16916458/jsonidentityinfo-custom-reference-serialization
        http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
        https://github.com/jsog/jsog-jackson
        */
        
        User user = new User();
        Item item = new Item();
        
        user.items.add(item);
        item.user = user;
        
        System.out.println("-- Json --");
        ObjectMapper jackson = new ObjectMapper(
                //new SmileFactory()
        );
        byte[] json = jackson.writeValueAsBytes(user);
        System.out.println(json.length);
        System.out.println(new String(json, "UTF-8"));
    }
    
    
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Use object property as identity identifier
    //@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class) // Generate sequence number
    //@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class) // Generate UUID as identity identifier
    @JsonIdentityInfo(generator = JSOGGenerator.class) // Same as IntSequenceGenerator but @ref as object
    public static class User
    {
        public int id;
        public String userName;
        public List<Item> items = new ArrayList<>();
    }
    
    
    @JsonIdentityInfo(generator = JSOGGenerator.class)
    public static class Item
    {
        public int id;
        public String itemName;
        public User user;
    }
}
