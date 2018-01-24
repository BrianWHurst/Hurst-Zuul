/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes & Brian Hurst
 * @version 2018.01.21
 * 
 * - 01/2018
 * - Modified validCommands method
 * - Modified validCommands method. Added sneak command
 * - Added showAllCmds method
 * - Modified validCommands method. Added unSneak command
 * - Removed showAllCmds method
 * - Added getCommandList method
 */ 

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "sneak", "unsneak"
        };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }
    
    /**
     * Get the valid comands in the game 
     * @return the list of valid commands
     */
    public String getCommandList() 
    {
        String commandList = "";
        for(int i = 0; i < validCommands.length; i++) {
            commandList += validCommands[i] + "  ";
        }
        return commandList;
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}