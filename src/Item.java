
/**
 * This is a class for items in the game. 
 * Each item has name, description and weight.
 *
 * @author Brian Hurst
 * @version 2018.01.21
 * 
 * - Added constructor 
 * - Added getItemName method
 * - Added getItemWeight method
 * - Added getItemDescription method 
 * - Added name field
 */
public class Item 
{
    private String name;
    private String description;
    private int weight;

    /**
     * Create a new item with the given description and weight.
     * @param name
     * @param description
     * @param weight
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * @return name
     */
    public String getItemName() 
    {
        return name;
    }

    /**
     * @return weight
     */
    public int getItemWeight() 
    {
        return weight;
    }

    /**
     * @return description
     */
    public String getItemDescription() 
    {
        return description;
    }
}