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
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Manifest;




/**
 * PluginLoader class.
 * 
 * @author Maciej Medyński
 */
public class PluginLoader
{
    /**
     * Load plugins from file.
     * 
     * @param jar
     * @param plugins 
     * @since 1
     * @version 1
     */
    public static void load(final File jar, final List<IPlugin> plugins) // maybe some IMainThread mainThread
    {
        System.out.println("Load JAR");
        System.out.println("Jar exists: " + jar.exists());
        if (!jar.exists()) return;

        try
        {
            System.out.println("Construct class loader");
            final URLClassLoader loader = URLClassLoader.newInstance(new URL[] { jar.toURI().toURL() }, PluginLoader.class.getClassLoader());
            
            System.out.println("Load manifest file");
            final Manifest manifest = new Manifest(loader.findResource("META-INF/MANIFEST.MF").openStream());
            
            System.out.println("Manifest: " + manifest.getMainAttributes().entrySet());

            final String[] pluginClassNames = manifest.getMainAttributes().getValue("Plugins").split("\\s+");
            System.out.println("Got " + pluginClassNames.length + " plugins: " + Arrays.toString(pluginClassNames));
            
            for (final String pluginClassName : pluginClassNames)
            {
                try
                {
                    System.out.println("Load class " + pluginClassName.trim());
                    final Class clazz = loader.loadClass(pluginClassName);

                    System.out.println("Local loader: " + clazz.getClassLoader().equals(loader));

                    System.out.println("Plugin new instance");
                    final Constructor<IPlugin> constructor = clazz.getConstructor(); // or clazz.getConstructor(IMainThread.class);
                    final IPlugin instance = constructor.newInstance(); // or constructor.newInstance(mainThread)
                    // default constructor and call some kind of instance.register(mainThread)

                    System.out.println("Instance: " + instance);
                    
                    if (null != instance) plugins.add(instance);
                }
                catch (final ReflectiveOperationException ex)
                {
                    System.err.println("Failed to load plugin: " + ex);
                    // or RuntimeException()
                }
            }
        }
        catch (final IOException | RuntimeException ex)
        {
            System.err.println("Failed to open JAR's manifest: " + ex);
            // or RuntimeException()
        }
    }
}
