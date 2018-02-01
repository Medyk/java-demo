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
 * PluginThree class.
 *
 * @author Maciej Medyński
 * @version 1
 */
public class PluginThree implements IPlugin
{
    /**
     * Setup plugin.
     */
    @Override
    public void setup()
    {
        tick = (int)(Math.random() * 100);
        System.out.println("PluginThree - initial value: " + tick);
    }
    /**
     * Single work unit tick.
     */
    @Override
    public void tick()
    {
        if (0 == tick % 5)
        {
            System.out.println("PluginThree - " + tick);
        }
        ++tick;
    }
    
    
    /**
     * Tick number.
     */
    private int tick;
}
