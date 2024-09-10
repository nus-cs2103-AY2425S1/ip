package lama;

import java.util.HashMap;
import java.util.Map;

/**
 * The AliasManager class is responsible for managing user-defined aliases for commands.
 * It provides methods to set aliases, retrieve the original commands from aliases,
 * and display or save the alias mappings.
 */
public class AliasManager {

    private static Map<String, String> aliasMap = new HashMap<>();
    private static Storage storage;


    /**
     * Sets an alias for a given command. The alias and its corresponding command
     * are stored in the alias map and saved to persistent storage.
     *
     * @param alias   The alias to be mapped to the command.
     * @param command The command that the alias refers to.
     * @throws LamaException Thrown if there is an issue saving the alias to storage.
     */
    public static void setAlias(String alias, String command) throws LamaException {
        aliasMap.put(alias, command);
        storage.saveAliases(aliasMap);
    }

    /**
     * Sets the storage instance to be used for saving and loading aliases.
     *
     * @param storageInstance The storage instance for alias persistence.
     */
    public static void setStorage(Storage storageInstance) {
        storage = storageInstance;
    }

    /**
     * Loads aliases from the storage and populates the alias map with the retrieved data.
     *
     * @throws LamaException Thrown if there is an issue loading aliases from storage.
     */
    public static void loadAliases() throws LamaException {
        loadDefaultAlias();
        Map<String, String> userAliases = storage.loadAliases();
        aliasMap.putAll(userAliases);
    }

    private static void loadDefaultAlias() {
        aliasMap.putIfAbsent("t", "todo");
        aliasMap.putIfAbsent("d", "deadline");
        aliasMap.putIfAbsent("e", "event");
        aliasMap.putIfAbsent("l", "list");
        aliasMap.putIfAbsent("m", "mark");
        aliasMap.putIfAbsent("u", "unmark");
        aliasMap.putIfAbsent("del", "delete");
        aliasMap.putIfAbsent("f", "find");
        aliasMap.putIfAbsent("b", "bye");
        aliasMap.putIfAbsent("a", "alias");
        aliasMap.putIfAbsent("c", "command");
        aliasMap.putIfAbsent("da", "deletealias");
    }

    /**
     * Retrieves the command associated with the given alias. If the alias does not exist,
     * it returns the alias itself (indicating it is not an alias).
     *
     * @param alias The alias to resolve.
     * @return The command associated with the alias, or the alias itself if no mapping exists.
     */
    public static String getCommand(String alias) {
        return aliasMap.getOrDefault(alias, alias);
    }

    /**
     * Displays all current aliases and their corresponding commands to the console.
     * If no aliases are set, a message is displayed indicating that no aliases exist.
     */
    public static void showAliases() {
        if (aliasMap.isEmpty()) {
            System.out.println("No aliases have been set.");
        } else {
            aliasMap.forEach((alias, command) -> {
                System.out.println(alias + " -> " + command);
            });
        }
    }

    /**
     * Formats the list of aliases and their corresponding commands into a string.
     * If no aliases are set, a message indicating that no aliases have been set is returned.
     *
     * @return A formatted string representing all aliases and their mapped commands.
     */
    public static String getFormattedAliasList() {
        if (aliasMap.isEmpty()) {
            return "No aliases have been set.";
        }

        StringBuilder output = new StringBuilder("Here are the aliases in your list:\n");
        aliasMap.forEach((alias, command) -> {
            output.append(alias).append(" -> ").append(command).append("\n");
        });
        return output.toString();
    }

    /**
     * Deletes an existing alias from the alias map and updates the storage.
     *
     * @param alias The alias to be deleted.
     * @throws LamaException Thrown if there is an issue saving the updated aliases to storage.
     */
    public static void deleteAlias(String alias) throws LamaException {
        if (aliasMap.containsKey(alias)) {
            aliasMap.remove(alias);
            storage.saveAliases(aliasMap);
        } else {
            throw new LamaException("Alias '" + alias + "' does not exist.");
        }
    }
}
