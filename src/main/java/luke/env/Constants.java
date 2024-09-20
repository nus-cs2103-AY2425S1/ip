package luke.env;

import java.util.List;

/**
 * The {@code Constants} class stores constants that are reused in different parts of the Luke codebase.
 *
 * <ul>
 *     <li>INDENT is used to indent task lists in Luke dialog boxes.</li>
 *     <li>TASK_TYPES is a string list that stores the names of the different task types.</li>
 *     <li>FILE_PATH is the file path of the save data file which Luke will read from and write to.</li>
 * </ul>
 */
public class Constants {
    public static final String INDENT = "  ";
    public static final String BAD_COMMAND = "hmmmm...the command doesn't really make sense to me. try again?";
    public static final String BYE_MESSAGE = "yeah bye bye to you too human being <3";
    public static final List<String> TASK_TYPES = List.of(new String[]{"todo", "event", "deadline"});
    public static final String FILE_PATH = "./data/Luke.txt";
    public static final int PAUSE = 2000;
}
