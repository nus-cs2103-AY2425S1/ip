package bob;

import bob.command.Command;
import bob.exception.UnknownCommandException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {
    private static final String ARGUMENT_PREFIX = "/";
    private static HashMap<String, Class<? extends Command>> commandTable;



    public Command getCommand(String command) {
        Class<? extends Command> clazz = commandTable.get(command);
        if (clazz == null) {
            throw new UnknownCommandException();
        }

        try {
            return clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new UnknownCommandException();
        }
    }

    public void loadCommands() {
        commandTable = new HashMap<>();
        Set<Class<?>> allClasses = getAllClasses(Command.class.getPackageName());

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

    // Code from https://www.baeldung.com/java-find-all-classes-in-package
    private static Set<Class<?>> getAllClasses(String packageName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(filename -> filename.endsWith(".class"))
                .map(filename -> filename.substring(0, filename.lastIndexOf('.')))
                .map(className -> getClass(className, packageName))
                .collect(Collectors.toSet());
    }
    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public Command parse(String string) {
        return null;
    }
}
