package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.UnknownCommandException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Command {
    private static HashMap<String, Class<? extends Command>> commandTable;

    public static void loadCommands() {
        commandTable = new HashMap<>();
        Set<Class<?>> allClasses = getAllClasses();

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

    public static Command of(String command) {
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

    private static Set<Class<?>> getAllClasses() {
        String packageName = Command.class.getPackageName();
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

    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String argument);
}
