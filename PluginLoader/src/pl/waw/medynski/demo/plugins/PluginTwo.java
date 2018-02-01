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
 * PluginTwo class.
 *
 * @author Maciej Medyński
 * @version 1
 */
public class PluginTwo implements IPlugin
{
    /**
     * Single work unit tick.
     */
    @Override
    public void tick()
    {
        System.out.println("PluginTwo - " + System.currentTimeMillis());
    }
}
