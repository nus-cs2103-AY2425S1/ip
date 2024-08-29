package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Commands {
    public static final HashMap<String, CommandTypes> COMMANDS_HASH_MAP;

    static {
        // initialization
        COMMANDS_HASH_MAP = new HashMap<>();
        COMMANDS_HASH_MAP.put("list", CommandTypes.LIST);
        COMMANDS_HASH_MAP.put("mark", CommandTypes.MARK);
        COMMANDS_HASH_MAP.put("unmark", CommandTypes.UNMARK);
        COMMANDS_HASH_MAP.put("delete", CommandTypes.DELETE);
        COMMANDS_HASH_MAP.put("todo", CommandTypes.TODO);
        COMMANDS_HASH_MAP.put("event", CommandTypes.EVENT);
        COMMANDS_HASH_MAP.put("deadline", CommandTypes.DEADLINE);
        COMMANDS_HASH_MAP.put("bye", CommandTypes.EXIT);
    }
}
