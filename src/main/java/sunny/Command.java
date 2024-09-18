package sunny;

import java.util.List;

/**
 * Represents all command classes
 */
public abstract class Command {
    public static final String line = "     ────────────────────";

    /**
     * Builds string, read and write to hard drive, depends on the subclass
     * @param ls pass by reference list of clsses
     */
    public abstract String runCommand(List<Task> ls, String m);

}
