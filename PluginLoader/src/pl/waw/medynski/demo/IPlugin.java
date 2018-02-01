/**
 * History:
 *
 * 1 - Initial release.
 */
package pl.waw.medynski.demo;




/**
 * IPlugin interface.
 *
 * @author Maciej Medyński
 * @version 1
 */
public interface IPlugin
{
    /**
     * Setup plugin.
     */
    public default void setup() {};
    
    
    /**
     * Single work unit tick.
     */
    public default void tick() {};
    
    
    /**
     * Cleanup plugin.
     */
    public default void cleanup() {};
}
