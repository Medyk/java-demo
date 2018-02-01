/**
 * History:
 *
 * 1 - Initial release.
 */
package pl.waw.medynski.demo.plugins;




/**
 * Imports.
 */
import pl.waw.medynski.demo.IPlugin;




/**
 * PluginOne class.
 *
 * @author Maciej Medyński
 * @version 1
 */
public class PluginOne implements IPlugin
{
    /**
     * Single work unit tick.
     */
    @Override
    public void tick()
    {
        System.out.println("PluginOne");
    }
}
