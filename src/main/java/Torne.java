import java.util.*;

/**
 * This is the main class for `Torne`.
 * All the main commands are here.
 */

public class Torne {
    private static final ChatOutput OUTPUT = new ChatOutput();
    private static final TaskHandler TASK_HANDLER = new TaskHandler();
    private static final String[] NO_ARGS = new String[0];
    private static final String[] DEFAULT_ARG = {""};
    private static final Map<String, String[]> COMMANDS = Map.of(
            "bye", NO_ARGS,
            "list", NO_ARGS,
            "mark",DEFAULT_ARG,
            "unmark", DEFAULT_ARG,
            "test", new String[]{"a", "b"}
    );

    /**
     * Parses the string command and arguments that the user entered. The correct operation is then called. If the input
     * **invalid**, e.g. the command does not exist, nothing will be printed and the bot asks for another input.
     *
     * @param input string input by the user
     */
    private void parseCommand(String input) {
        // Split input by spaces
        String[] parts = input.split("\\s+");

        if (parts.length == 0) {
            // invalid - return
            return;
        }

        // get command - first no-space phrase
        String command = parts[0];

        // command exists check
        // Level 2 - check if input is a command, if not, add as task
        if (!COMMANDS.containsKey(command)) {
            // Command is not found in COMMANDS
            // add whole input
            addTask(input);
            return;
        }

        // now get available arguments based on command
        List<String> availableArgs = Arrays.asList(COMMANDS.get(command));

        // now handle arguments, I "love" REGEX
        // args will be a string, string map
        HashMap<String, String> argMap = new HashMap<>();

        // remove command from input
        String argsStr = input.substring(command.length());

        // Split string to get arguments + their argument values
        String[] args = argsStr.split("\s(?=\\\\)|$");

        // add a default arg
        // System.out.println(args.length);
        if (args.length > 0 && !args[0].isEmpty() && args[0].charAt(0) != '\\') {
            // the arg array is always nonEmpty, so, to determine the existence of a default arg,
            // we check if the first arg is nonEmpty does not start with a '\'
            argMap.put("", args[0]);
            System.out.println("Default arg added! - " + args[0]);
        }
        
        // go through the args list and add the other args
        for (int i = 1; i < args.length; i++) {
            String argument = args[i];
            // split on first whitespace
            String[] argParts = args[i].split("\\s+", 2);

            // TODO: what to do when only an argument flag provided - presumably that can be expected behavior...
            // For now, raise an error
            if (argParts.length == 1) {
                OUTPUT.error("No value entered for flag \\" +argParts[0]);
                return;
            }

            if (!availableArgs.contains(argParts[0].substring(1))) {
                // argument flag is invalid for the given command
                OUTPUT.error(String.format("Argument flag \\%s is invalid for command %s", argParts[0], command));
                return;
            }

            // add to argMap
            argMap.put(argParts[0].substring(1), argParts[1].trim());

        }
        // OUTPUT.writeText(String.valueOf(argMap));

        // NOW ON TO ACTUALLY MATCHING THE FUNCS
        switch (command) {
        case "list":
            listTasks();
        default:
        }
    }

    /**
     * Shows a greeting message, to be shown when user initialises Torne.
     */
    private void showGreeting() {
        String greetingText = """
Hello! I am
  _______ ____  _____  _   _ ______\s
 |__   __/ __ \\|  __ \\| \\ | |  ____|
    | | | |  | | |__) |  \\| | |__  \s
    | | | |  | |  _  /| . ` |  __| \s
    | | | |__| | | \\ \\| |\\  | |____\s
    |_|  \\____/|_|  \\_\\_| \\_|______|
                                   \s
short for Torment Nexus™, your F R I E N D L Y neighborhood chatbot :3

How may I help you today?""";
        OUTPUT.writeText(greetingText);
    }

    /**
     * Prints a standard exit message.
     */
    private void showExitMessage() {
        String exitText = """
Aww, bye to you as well :c""";
        OUTPUT.writeText(exitText);
    }

    // ==================== TASK RELATED ============================================

    /**
     * Adds a task and shows a message if the task was successfully added.
     * @param name name of task to be added
     */
    private void addTask(String name) {
        TASK_HANDLER.addTask(new Task(name));
        String message = "added: " + name;
        OUTPUT.writeText(message);
    }

    /**
     * Shows the lists of tasks to the user.
     */
    private void listTasks() {
        OUTPUT.writeText(TASK_HANDLER.getTaskListString());
    }

    // =================== MAIN ===================================
    public static void main(String[] args) {
        Torne torne = new Torne();
        Scanner scanner = new Scanner(System.in);
        String input;

        // greet user
        torne.showGreeting();

        while (true) {
            // Read input from user
            input = scanner.nextLine().trim();

            // first check if it's an exit (`bye`) command
            if (input.equals("bye")) {
                torne.showExitMessage();
                break;
            }

            // else, parse and handle the input :D
            torne.parseCommand(input);
        }

    }
}
