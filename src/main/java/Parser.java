import java.io.IOException;

public class Parser {

    public enum TaskTypes {
        TODO,
        EVENT,
        DEADLINE,
        UNDEFINED
    }

    public enum Commands {
        DELETE,
        MARK,
        UNMARK,
        BYE
    }

    /**
     * Determines if a String can be converted into an Integer
     *
     * @param str String to be converted
     * @return True if String is an Integer
     */
    public boolean canBeInteger(String str) {
        if (str == null || str == "") {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts String to Integer if it can be converted.
     *
     * @param str String to be converted
     * @return Integer form of String, otherwise, arbitrary Integer Maximum Value
     */
    public int convertStrToInteger(String str) {
        if (canBeInteger(str)) {
            return Integer.parseInt(str);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Determines if String indicates a Todo task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Todo "
     */
    public boolean startsWithTodo(String str, int length) {
        return (length >= 5 && str.toLowerCase().startsWith("todo "));
    }

    /**
     * Determines if String indicates a Event task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Event "
     */
    public boolean startsWithEvent(String str, int length) {
        return (length >= 6 && str.toLowerCase().startsWith("event "));
    }

    /**
     * Determines if String indicates a Deadline task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Deadline "
     */
    public boolean startsWithDeadline(String str, int length) {
        return (length >= 9 && str.toLowerCase().startsWith("deadline "));
    }

    /**
     * Determines if String indicates a Delete command.
     * @param str String to be analysed.
     * @param length Length of String
     * @return True if String indicates a Delete command.
     */
    public boolean startsWithDelete(String str, int length) {
        return (length >= 7 && str.toLowerCase().startsWith("delete "));
    }

    public DeleteCommand parseInputDelete(String str, int length) {
        String temp = (str.substring(7, length));
        if (canBeInteger(temp)) {
            return new DeleteCommand(str);
        }
    }

        public TaskTypes getTaskType (String str){
            int length = str.length();
            if (startsWithTodo(str, length)) {
                return Shnoop.TaskTypes.TODO;
            } else if (startsWithEvent(str, length)) {
                return Shnoop.TaskTypes.EVENT;
            } else if (startsWithDeadline(str, length)) {
                return Shnoop.TaskTypes.DEADLINE;
            } else {
                return Shnoop.TaskTypes.UNDEFINED;
            }
        }

        /**
         * Returns the relevant string excluding Todo, Event, or Deadline tag.
         *
         * @param str User input to sieve through.
         * @return String excluding task type.
         */
        public String getTaskDetails (String str){
            Parser.TaskTypes taskType = getTaskType(str);
            int length = str.length();

            switch (taskType) {
            case TODO:
                return str.substring(5, length);
            case EVENT:
                return str.substring(6, length);
            case DEADLINE:
                return str.substring(9, length);
            default:
                return str;
            }
        }

        /**
         * Does the required code for a mark or unmark CLI input.
         *
         * @param input Given by the user.
         * @return String indicating the action that was done.
         */
        public String parseInputMark (String input){
            if (input.length() >= 6 && input.substring(0, 5).equals("mark ")) {
                if (canBeInteger(input.substring(5, input.length()))) {
                    boolean result = tasks.get(convertStrToInteger(input.substring(5, input.length())) - 1).markTask();
                    if (result) {
                        System.out.println("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: ");
                    } else {
                        System.out.println("✿ Shnoop ✿: Daisy dukes! This task was already done my love: ");
                    }
                    System.out.println(tasks.get(convertStrToInteger(input.substring(5, input.length())) - 1)
                            .getTaskWithStatus());
                    return "mark_task";
                }
            } else if (input.length() >= 8 && input.substring(0, 7).equals("unmark ")) {
                if (canBeInteger(input.substring(7, input.length()))) {
                    boolean result = tasks.get(convertStrToInteger(input.substring(7, input.length())) - 1).unmarkTask();
                    if (result) {
                        System.out.println("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: ");
                    } else {
                        System.out.println("✿ Shnoop ✿: Daisy dukes! This task was never done my love: ");
                    }
                    System.out.println(tasks.get(convertStrToInteger(input.substring(7, input.length())) - 1)
                            .getTaskWithStatus());
                    return "mark_task";
                }
            }

            return "not_mark_or_unmark";
        }

        /**
         * Returns String representing action done.
         * Performs a series of actions depending on input and mode.
         *
         * @param input Input given by user.
         * @return String action code.
         */
        public String parseInput (String input) throws UndefinedTaskException, IncompleteEventOrDeadlineException,
                EmptyDescriptionException, UnmarkableArrayException, IndexOutOfBoundsException, IOException {
            switch (mode) {

                // For Level-2 Add, List mode.
            case "todo":

                // For mark specific
                try {
                    String parseInputMarkResult = parseInputMark(input);
                    if (parseInputMarkResult != "not_mark_or_unmark") {
                        return parseInputMarkResult; // Exit if it is mark or unmark command
                    } else if (startsWithDelete(input, input.length())) {
                        return parseInputDelete(input, input.length());
                    }
                } catch (NullPointerException | IndexOutOfBoundsException npe) {
                    throw new UnmarkableArrayException();
                }


                // Other than mark or unmark or delete
                switch (input) {

                case "bye":
                    clearFile(path.toString());
                    for (int i = 0; i < tasks.size(); i++) {
                        try {
                            writeToFile(tasks.get(i).toUString() + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
                        }
                    }
                    System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. "
                            + "Don't worry we got it on lock. ♡");
                    completion = true;
                    return "exit";

                case "list":
                    System.out.println("✿ Shnoop ✿: Find, fresh, fierce and ready.");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) == null) {
                            break;
                        }
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    return "list";

                default:
                    String x = getRandomQuote(arrPointer % 3);

                    Shnoop.TaskTypes taskType = getTaskType(input);
                    String taskDesc = getTaskDetails(input);
                    Task newTask;

                    try {
                        switch (taskType) {
                        case TODO:
                            newTask = new Todo(taskDesc);
                            break;
                        case EVENT:
                            newTask = new Event(taskDesc);
                            break;
                        case DEADLINE:
                            newTask = new Deadline(taskDesc);
                            break;
                        default:
                            throw new UndefinedTaskException();
                        }

                        addTask(newTask);

                        return "add_task";
                    } catch (EmptyDescriptionException e) {
                        throw new EmptyDescriptionException();
                    } catch (IncompleteEventOrDeadlineException f) {
                        throw new IncompleteEventOrDeadlineException();
                    } catch (UndefinedTaskException g) {
                        throw new UndefinedTaskException();
                    }

                }

                // If mode is NIL, indicate bug
            default:
                return "empty_input_bug";
            }
        }

    public static Command parse(String input) throws ShnoopException {
        String[] commandParts = input.split(" ");
        String command = commandParts[0].toUpperCase();

        switch (command) {
        case TaskTypes.TODO:
            return new AddCommand(input, TaskTypes.TODO);
        case TaskTypes.DEADLINE:
            return new AddCommand(input, TaskTypes.DEADLINE);
        case TaskTypes.EVENT:
            return new AddCommand(input, TaskTypes.EVENT);
        case Commands.DELETE:
            return new DeleteCommand(input);
        case Commands.MARK:
            return new MarkCommand(input, true);
        case Commands.UNMARK:
            return new MarkCommand(input, false);
        case Commands.BYE:
            return new ExitCommand();
        default:
            throw new ShnoopException("✿ Shnoop ✿: You could travel the world, but "
                    + "nothing comes close to choosing a task type.\n"
                    + "✿ Shnoop ✿: Try typing 'todo', 'event' or 'deadline' followed by stating the task description.");
        }
    }
}
