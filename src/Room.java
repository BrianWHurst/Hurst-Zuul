import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes & Brian Hurst
 * @version 2018.01.23
 * 
 * - Changed the fields to private
 * - Added getExit accessor method
 * - Added getExitString method so that the exits are now prepared by the Room class
 * - Dulpicated getExit and getExitString methods then commented them out for grading purposes
 * - Imported HashMap library 
 * - Added HashMap for room exits 
 * - Modified the constructor to make use of the HashMap      
 * - Modified setExits method
 * - Imported Set library 
 * - Modified getExitString method 
 * - Added getLongDescription method
 * 
 * - Imported ArrayList
 * - Imported Iterator
 * - Added items Arraylist field 
 * - Created new items ArrayList in constructor
 * - Added addItem method
 * - Added removeItem method
 *      -(this will be used once an inventory system is implemented)
 * - Added printListOfItems method
 * - Modified printListOfItems method
 * - Added getItemByName method 
 *      -(this will be used once an inventory system is implemented)
 * - updated javadoc
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;    // Stores the exits of the room
    private ArrayList<Item> items;         // Stores items in the game

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        this.items = new ArrayList();       // Create the game items
    }

    /**
     * Define the exit from this room
     * @param direction The direction of the exit
     * @param neighbor The room in the given direction
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put (direction, neighbor);
    }

    /**
     * @return The description of this room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Add an item to a room
     */
    public void addItem(Item item) 
    {
        this.items.add(item);
    }

    /**
     * List the items that the room contains
     */
    public void printListOfItems() 
    {
        System.out.println("The room contains:");
        Iterator i = this.items.iterator();

        while(i.hasNext()) {
            Item item = (Item)i.next();
            System.out.println(" -> " + item.getItemName() + ": " 
                + item.getItemDescription() + " [Item Weight = " + item.getItemWeight() + "]");
        }
    }

    /**
     * Get an item by name 
     * @return the referenced item
     */
    public Item getItemByName(String name) 
    {
        Item itemReference = null;
        Iterator i = this.items.iterator();

        while(i.hasNext()) {
            Item item = (Item)i.next();
            if (item.getItemName().equals(name)) {
                itemReference = item;
            }
        }
        return itemReference;
    }

    /**
     * Remove an item from the room
     */
    public void removeItem(Item item) 
    {
        if (item != null) {
            this.items.remove(item);
        }
    }

    /**
     * Return a long description of this room, of the form:
     *      You are in the prisoner intake area.
     *      Exits: east west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n" + getExitString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction." If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return the room in the given direction.
     */
    public Room getExit (String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits,
     * for example, "Exits: north, west".
     * @return A description of available exits.
     */
    public String getExitString()
    {
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            exitString += " " + exit;
        }
        return exitString;
    }
}