package mortalreminder.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import mortalreminder.commands.CommandAlternatives;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedPrinting;

/**
 * Handles all long term storage for command alternatives.
 */
public class CommandAlternativesStorage {
    protected static final String STORAGE_ALTERNATIVE_COMMAND_PATH = "src/main/resources/data/alternativeCommands.txt";

    /**
     * Appends a new command alternative to the storage for {@link CommandType}.
     *
     * @param commandAlternative String that we want to represent a command using.
     * @param commandType the commandType we want the string to represent.
     * @throws MortalReminderException if the file cannot be found.
     */
    public static void appendToAlternativeCommandFile(String commandAlternative, CommandType commandType)
            throws MortalReminderException {
        try {
            FileWriter fw = new FileWriter(STORAGE_ALTERNATIVE_COMMAND_PATH, true);
            String commandString = commandType.toString().toLowerCase();
            fw.write(commandAlternative + " " + commandString + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new MortalReminderException("Corrupted storage file! Please refresh using refresh commands.");
        }
    }

    /**
     * Loads all saved command alternatives to the command alternatives hashmap.
     *
     * @return Hashmap of all saved commands
     * @throws MortalReminderException if the file is corrupted and cannot be created
     */
    public static CommandAlternatives loadCommandsFromFile() throws MortalReminderException {
        try {
            File f = new File(STORAGE_ALTERNATIVE_COMMAND_PATH);
            boolean checkFileExists = f.getParentFile().mkdirs() || f.createNewFile() || f.exists();
            Scanner s = new Scanner(f);
            HashMap<String, CommandType> stringCommandTypeHashMap = new HashMap<>();
            while (s.hasNextLine()) {
                String input = s.nextLine();
                String[] inputSplit = input.split(" ");
                String commandString = inputSplit[0];
                CommandType commandType = CommandType.valueOf(inputSplit[1].toUpperCase());
                stringCommandTypeHashMap.put(commandString, commandType);
            }
            s.close();
            return new CommandAlternatives(stringCommandTypeHashMap);
        } catch (RuntimeException | IOException e) {
            throw new MortalReminderException("Corrupted storage file!");
        }
    }

    /**
     * Clears the storage file for all alternatives.
     */
    public static String clearAlternativesFile() throws MortalReminderException {
        try {
            FileWriter fw = new FileWriter(STORAGE_ALTERNATIVE_COMMAND_PATH);
            fw.write("");
            fw.close();
            return FormattedPrinting.alternativesCleared();
        } catch (IOException e) {
            throw new MortalReminderException("File cannot be found!");
        }
    }
}
