/**
 * History:
 *
 * 1 - Initial release.
 */
package pl.waw.medynski.demo;




/**
 * Imports.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;




/**
 * Main class.
 *
 * @author Maciej Medyński
 * @version 1
 */
public class Main
{
    /**
     * Entry point.
     * 
     * @param args command line arguments
     * @throws java.lang.Exception
     */
    public static void main(final String[] args) throws Exception
    {
        System.out.println("Prepare");
        final List<IPlugin> list = new ArrayList<>();
        int loop = Integer.parseInt(System.getProperty("loop", "5"));
        
        System.out.println("Load plugins");
        PluginLoader.load(new File("PluginOneTwo.jar"), list);
        PluginLoader.load(new File("PluginThree.jar"), list);
        PluginLoader.load(new File("PluginOneOther.jar"), list);
        
        System.out.println("Setup");
        list.stream().forEach(IPlugin::setup);
        
        System.out.println("Loop");
        for (int i = 1; i <= loop; ++i)
        {
            System.out.println("Tick: " + i);
            list.stream().forEach(IPlugin::tick);
        }
        
        System.out.println("Cleanup");
        list.stream().forEach(IPlugin::cleanup);
    }
}
