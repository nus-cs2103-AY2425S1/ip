package bob;

import bob.command.Command;
import bob.exception.UnknownCommandException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {
    private static final String ARGUMENT_PREFIX = "/";
    private static HashMap<String, Class<? extends Command>> commandTable;

    public Parser() {
        this.loadCommands();
    }

    private void loadCommands() {
        commandTable = new HashMap<>();
        Set<Class<?>> allClasses = ClassGetter.getAllClasses(Command.class.getPackageName());

        for (Class<?> clazz : allClasses) {
            if (!Command.class.isAssignableFrom(clazz)) {
                continue;
            }

            String command;
            try {
                command = (String) clazz.getDeclaredField("COMMAND").get(null);
            } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
                continue;
            }

            commandTable.put(command, clazz.asSubclass(Command.class));
        }
    }

    private Class<? extends Command> getCommand(String command) {
        Class<? extends Command> clazz = commandTable.get(command);
        if (clazz == null) {
            throw new UnknownCommandException();
        }

        return clazz;
    }

    public Command parse(String string) {
        Map<String, String> tokenizedString = tokenize(string);
        Class<? extends Command> commandClazz = getCommand(tokenizedString.get("CMD"));

        try {
            return commandClazz.getConstructor(Map.class).newInstance(tokenizedString);
        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new UnknownCommandException();
        }
    }

    public static Map<String, String> tokenize(String string) {
        Map<String, String> map = new HashMap<>();
        String[] t = string.split(" ", 2);
        map.put("CMD", t[0]);

        if (t.length == 1) {
            return map;
        }

        String delimiter = String.format("((^| )%s[^\\s]+( |$))", ARGUMENT_PREFIX);
        String[] arguments = t[1].split(String.format("(?<=%1$s)|(?=%1$s)", delimiter));
        for (int i = 0; i < arguments.length; i++) {
            String s = arguments[i].strip();
            if (s.startsWith(ARGUMENT_PREFIX)) {
                String s1 = i == arguments.length - 1 ? "" : arguments[++i].strip();
                map.put(s.substring(1), s1);
            } else {
                map.put("", s);
            }
        }

        return map;
    }
}
