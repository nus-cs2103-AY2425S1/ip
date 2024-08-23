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
            "help", NO_ARGS,
            "mark",DEFAULT_ARG,
            "unmark", DEFAULT_ARG,
            "todo", NO_ARGS,
            "deadline", new String[]{"", "by"},
            "event", new String[]{"", "from", "to"},
            "delete", DEFAULT_ARG
    );

    private String helpMessage;

    /**
     * Parses the string command and arguments that the user entered. The correct operation is then called. If the input
     * is **invalid**, e.g. the command does not exist, nothing will be printed and the bot asks for another input.
     * Invalid inputs have their own handling processes.
     *
     * @param input string input by the user
     */
    private void parseCommand(String input) throws TorneException {
        // empty command - do nothing
        if (input.isBlank()) {
            return;
        }

        // Split input by spaces
        String[] parts = input.split("\\s+");

        if (parts.length == 0) {
            // invalid - return
            return;
        }

        // get command - first no-space phrase
        String command = parts[0];

        // command exists check:
        // Check if input is a command, if not, give error message
        if (!COMMANDS.containsKey(command)) {
            // Command is not found in COMMANDS
            throw new TorneInvalidCommandException(String.format("Command %s is not valid.", command));
        }

        // now get available arguments based on command
        List<String> availableArgs = Arrays.asList(COMMANDS.get(command));

        // now handle arguments, I "love" REGEX
        // args will be a string, string map
        HashMap<String, String> argMap = new HashMap<>();

        // remove command from input
        String argsStr = input.substring(command.length());

        // Split string to get arguments + their argument values
        String[] args = argsStr.split("\s(?=/)|$");

        // add a default arg
        // System.out.println(args.length);
        if (args.length > 0 && !args[0].isEmpty() && args[0].charAt(0) != '/') {
            // the arg array is always nonEmpty, so, to determine the existence of a default arg,
            // we check if the first arg is nonEmpty does not start with a '\'
            argMap.put("", args[0]);
            // System.out.println("Default arg added! - " + args[0]);
        }
        
        // go through the args list and add the other args
        for (int i = 1; i < args.length; i++) {
            String argument = args[i];
            // split on first whitespace
            String[] argParts = args[i].split("\\s+", 2);

            // TODO: what to do when only an argument flag provided - presumably that can be expected behavior...
            // For now, raise an error
            if (argParts.length == 1) {
                throw new TorneInvalidCommandException("No value entered for flag " + argParts[0]);
            }

            if (!availableArgs.contains(argParts[0].substring(1))) {
                // argument flag is invalid for the given command
                throw new TorneInvalidCommandException(
                        String.format("Argument flag %s is invalid for command %s", argParts[0], command));
            }

            // add to argMap
            argMap.put(argParts[0].substring(1), argParts[1].trim());

        }
        // OUTPUT.writeText(String.valueOf(argMap));

        // NOW ON TO ACTUALLY MATCHING THE FUNCS
        String defaultArg = argMap.get("");

        switch (command) {
        case "list":
            listTasks();
            break;
        case "help":
            showHelp();
            break;
        case "mark":
            mark(defaultArg);
            break;
        case "unmark":
            unmark(defaultArg);
            break;
        case "delete":
            deleteTask(defaultArg);
            break;
        case "todo":
            addTaskTodo(defaultArg);
            break;
        case "deadline":
            addTaskDeadline(defaultArg, argMap.get("by"));
            break;
        case "event":
            addTaskEvent(defaultArg, argMap.get("from"), argMap.get("to"));
            break;
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

    /**
     * Shows a help message to the user.
     * The help message lists out all available commands and their options
     */
    private void showHelp() {
        if (helpMessage != null) {
            // has already been generated
            OUTPUT.writeText(helpMessage);
            return;
        }

        StringBuilder helpMessageBuilder = new StringBuilder("This is the list of Torne commands and options:");

        for (var entry : COMMANDS.entrySet()) {
            String cmd = entry.getKey();
            String[] args = entry.getValue();
            helpMessageBuilder.append("\n").append(ChatOutput.INDENT).append(cmd);

            for (String arg : args) {
                if (arg.isEmpty()) {
                    // default arg
                    helpMessageBuilder.append(" [<value>]");
                    continue;
                }
                helpMessageBuilder.append(String.format(" [/%s <value>]", arg));
            }
        }
        // memoize
        helpMessage = helpMessageBuilder.toString();
        OUTPUT.writeText(helpMessage);
    }

    // ==================== TASK RELATED ============================================

    /**
     * Adds a task and shows a message if the task was successfully added.
     *
     * @param task task that is to be added
     */
    private void addTask(Task task) {
        TASK_HANDLER.addTask(task);
        int count = TASK_HANDLER.getTaskCount();

        String message = "Alright, I'll add this task:\n" + task
                + String.format("\nNow you have %d task", count)
                + ((count > 1) ? "s!" : "!");
        OUTPUT.writeText(message);
    }

    /**
     * Creates a new task with no date/time, with type `TaskTodo`.
     *
     * @param name name of task to be added
     */
    private void addTaskTodo(String name) throws TorneException {
        Task toAdd = new TaskTodo(name);
        addTask(toAdd);
    }

    /**
     * Creates a new task with a datetime as the deadline, with type `TaskDeadline`.
     *
     * @param name name of task to be added
     * @param by date/time to do the task by
     */
    private void addTaskDeadline(String name, String by) throws TorneException {
        Task toAdd = new TaskDeadline(name, by);
        addTask(toAdd);
    }

    /**
     * Creates a new task that starts at a datetime and ends at a datetime,
     * with type `TaskEvent`.
     *
     * @param name name of task to be added
     * @param from starting datetime
     * @param to ending datetime
     */
    private void addTaskEvent(String name, String from, String to) throws TorneException {
        Task toAdd = new TaskEvent(name, from, to);
        addTask(toAdd);
    }

    /**
     * Shows the lists of tasks to the user.
     */
    private void listTasks() {
        String message;
        if (TASK_HANDLER.getTaskCount() == 0) {
            message = "You currently have no tasks!\nSo quiet...";
        } else {
            message = String.format("You currently have %d tasks:\n", TASK_HANDLER.getTaskCount())
                    + TASK_HANDLER.getTaskListString();
        }

        OUTPUT.writeText(message);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param indexStr index of task to be marked as complete.
     */
    private void deleteTask(String indexStr) {
        if (indexStr == null) {
            OUTPUT.error("No task index specified.");
            return;
        }

        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;

            if (index < 0 || index >= TASK_HANDLER.getTaskCount()) {
                // TODO I'm guessing task handler should be the one raising this exception! SAME AS THE OTHER TWO
                OUTPUT.error("Invalid task index. Out of range.");
                return;
            }

            Task removed = TASK_HANDLER.removeTask(index);
            int count = TASK_HANDLER.getTaskCount();

            String message = "Alright, I'll delete this task:\n" + removed
                    + String.format("\nNow you have %d task", count)
                    + ((count > 1) ? "s left!" : "left!");
            OUTPUT.writeText(message);

        } catch (NumberFormatException e) {
            OUTPUT.error("Invalid task index. It is not an integer.");
        }
    }

    /**
     * Marks a particular task as complete.
     *
     * @param indexStr index of task to be marked as complete.
     */
    private void mark(String indexStr) {
        if (indexStr == null) {
            OUTPUT.error("No task index specified.");
            return;
        }

        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;

            if (index < 0 || index >= TASK_HANDLER.getTaskCount()) {
                // TODO I'm guessing task handler should be the one raising this exception!
                OUTPUT.error("Invalid task index. Out of range.");
                return;
            }

            if (TASK_HANDLER.getTask(index).getIsDone()) {
                OUTPUT.writeText("This task is already done, stop wasting my time.");
            } else {
                TASK_HANDLER.getTask(index).markAsDone();
                OUTPUT.writeText("Marking this task as done :)\n    " + TASK_HANDLER.getTask(index));
            }

        } catch (NumberFormatException e) {
            OUTPUT.error("Invalid task index. It is not an integer.");
        }

    }

    /**
     * Marks a particular task as incomplete.
     *
     * @param indexStr index of task to be marked as incomplete.
     */
    private void unmark(String indexStr) {
        if (indexStr == null) {
            OUTPUT.error("No task index specified.");
            return;
        }

        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;

            if (index < 0 || index >= TASK_HANDLER.getTaskCount()) {
                // TODO I'm guessing task handler should be the one raising this exception!
                OUTPUT.error("Invalid task index. Out of range");
                return;
            }

            if (!TASK_HANDLER.getTask(index).getIsDone()) {
                OUTPUT.writeText("Excuse me, this task is already not done. I can't make it even less done.");
            } else {
                TASK_HANDLER.getTask(index).markAsNotDone();
                OUTPUT.writeText("Unmarking this task :(\n    " + TASK_HANDLER.getTask(index));
            }

        } catch (NumberFormatException e) {
            OUTPUT.error("Invalid task index. It is not an integer.");
        }

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
            try {
                torne.parseCommand(input);
            } catch (TorneException e) {
                OUTPUT.error(e.toString());
            }

        }

    }
}
