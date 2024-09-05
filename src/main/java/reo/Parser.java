package reo;

public class Parser {
    enum Command {
        BYE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        UNKNOWN,
    }
    private String input;
    private String[] words;
    private Command command;
    private TaskList tasks;
    private Storage storage;
    public Parser(String input, TaskList tasks, Storage storage) {
        this.input = input;
        this.tasks = tasks;
        this.words = input.split("\\s+");
        this.storage = storage;

        try {
            this.command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            this.command = Command.UNKNOWN;
        }
    }

    /**
     * Interpret user's command, make the necessary file changes,
     * and display the corresponding UI.
     *
     * @return The String representation of the response
     * for the corresponding command.
     */
    public String parse() {
        switch (command) {
            case LIST:
                return handleList();
            case TODO:
                return handleTodo();
            case MARK:
                return handleMark();
            case UNMARK:
                return handleUnmark();
            case DEADLINE:
                return handleDeadline();
            case EVENT:
                return handleEvent();
            case DELETE:
                return handleDelete();
            case FIND:
                return handleFind();
            case BYE:
                return handleBye();
            case UNKNOWN:
                return handleUnknown();
            default:
                return handleUnknown();
            }
    }

    /**
     * Gets the correct UI for the LIST command.
     *
     * @return The String representation of the stored ArrayList so far.
     */
    public String handleList() {
        return tasks.toString();
    }

    /**
     * Gets the correct UI for the TODO command.
     *
     * @return The String representation of the added Task,
     * with a confirmation message.
     */
    public String handleTodo() {
        final String TODO_CONFIRMATION = "I've added this todo:\n ";
        final String TODO_ERROR = "Please enter a valid task name.\n";

        try {
            String[] parts = input.split(" ", 2);
            Todo toPush = new Todo(parts[1], false);
            tasks.addTodo(toPush);
            storage.writeFile(toPush);
            return TODO_CONFIRMATION + toPush.toString() + "\n"
                    + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            return TODO_ERROR;
        }
    }

    /**
     * Gets the correct UI for the MARK command.
     *
     * @return The String representation of the marked Task,
     * with a confirmation message.
     */
    public String handleMark() {
        final String MARK_CONFIRMATION = "Good job! I've marked this item as done:\n";
        final String MARK_ERROR = "Please enter a valid task number.\n";
        try {
            tasks.markTask(Integer.valueOf(words[1]) - 1);
            storage.editLine(Integer.parseInt(words[1]), "mark");
            Task toMark = tasks.get(Integer.parseInt(words[1]) - 1);
            return MARK_CONFIRMATION + toMark.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return MARK_ERROR;
        }
    }

    /**
     * Gets the correct UI for the UNMARK command.
     *
     * @return The String representation of the unmarked Task,
     * with a confirmation message.
     */
    public String handleUnmark() {
        final String UNMARK_CONFIRMATION = "Get better, I've marked this item as not done:\n";
        final String UNMARK_ERROR = "Please enter a valid task number.\n";
        try {
            tasks.unmarkTask(Integer.valueOf(words[1]) - 1);
            storage.editLine(Integer.valueOf(words[1]), "unmark");
            Task toUnmark = tasks.get(Integer.valueOf(words[1]) - 1);
            return UNMARK_CONFIRMATION + toUnmark.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return UNMARK_ERROR;
        }
    }

    /**
     * Gets the correct UI for the DEADLINE command.
     *
     * @return The String representation of the marked Task,
     * with a confirmation message.
     */
    public String handleDeadline() {
        final String DEADLINE_CONFIRMATION = "I've added this deadline:\n";
        final String DEADLINE_ERROR = "Please enter a valid task name and deadline.\n";
        try {
            String[] parts = input.split("/by", 2);
            String[] firstPart = parts[0].split(" ", 2);

            String name = firstPart[1];
            String deadline = parts[1].trim();

            Deadline deadlineToPush = new Deadline(name, false, deadline);
            tasks.addDeadline(deadlineToPush);
            storage.writeFile(deadlineToPush);
            return DEADLINE_CONFIRMATION + deadlineToPush.toString() + "\n"
                    + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            return DEADLINE_ERROR;
        }
    }

    /**
     * Gets the correct UI for the EVENT command.
     *
     * @return The String representation of the added Task,
     * with a confirmation message.
     */
    public String handleEvent() {
        final String EVENT_CONFIRMATION = "I've added this event:\n";
        final String EVENT_ERROR = "Please enter a valid task name and to & from dates.\n";
        try {
            String[] parts = input.split("/from", 2);
            String[] firstPart = parts[0].split(" ", 2);
            String[] secondPart = parts[1].split("/to", 2);

            String name = firstPart[1];
            String from = secondPart[0].trim();
            String to = secondPart[1].trim();

            Event eventToPush = new Event(name, false, to, from);
            tasks.addEvent(eventToPush);
            storage.writeFile(eventToPush);
            return  EVENT_CONFIRMATION + eventToPush.toString() + "\n"
                    + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            return EVENT_ERROR;
        }
    }

    /**
     * Gets the correct UI for the DELETE command.
     *
     * @return The String representation of the deleted Task,
     * with a confirmation message.
     */
    public String handleDelete() {
        final String DELETE_CONFIRMATION = "I've deleted this task:\n";
        final String DELETE_ERROR = "Please enter a valid task number.\n";
        try {
            int index = Integer.valueOf(words[1]) - 1;
            // reo.Task toRemove = tasks.get(index);
            tasks.deleteTask(index);
            storage.removeLine(index + 1);
            Task toDelete = tasks.get(index);
            return DELETE_CONFIRMATION + toDelete.toString() + "\n"
                    + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        } catch (IndexOutOfBoundsException e) {
            return DELETE_ERROR;
        }
    }

    /**
     * Gets the correct UI for the FIND command.
     *
     * @return The String representation of the matching Tasks
     */
    public String handleFind() {
        final String FIND_CONFIRMATION = "Here are the matching tasks in your list:\n";
        final String FIND_ERROR = "ERROR: Please enter a valid keyword to search for.\n";
        try {
            String keyword = words[1];
            TaskList filtered = new TaskList(tasks.filter(keyword));
            return "Here are the matching tasks in your list:\n" + filtered.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return FIND_ERROR;
        }
    }

    /**
     * Gets the correct UI for the BYE command.
     *
     * @return The goodbye message.
     */
    public String handleBye() {
        return "Bye!";
    }

    /**
     * Gets the correct UI for unknown commands.
     *
     * @return The error message.
     */
    public String handleUnknown() {
        return "ERROR: Please enter a valid command.\n";
    }

}
