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
     * @return The String representation of the stored ArrayList so far.
     */
    public String parse() {
        switch (command) {
            case LIST:
                return tasks.toString();
            case TODO:
                try {
                    String[] parts1 = input.split(" ", 2);
                    Todo toPush = new Todo(parts1[1], false);
                    tasks.addTodo(toPush);
                    storage.writeFile(toPush);
                    return "I've added this todo:\n " + toPush.toString() + "\n"
                            + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
                } catch (ArrayIndexOutOfBoundsException e) {
                    return "Please enter a valid task name.\n";
                }
            case MARK:
                try {
                    tasks.markTask(Integer.valueOf(words[1]) - 1);
                    storage.editLine(Integer.parseInt(words[1]), "mark");
                    Task toMark = tasks.get(Integer.parseInt(words[1]) - 1);
                    return "Good job! I've marked this item as done:\n" + toMark.toString() + "\n";
                } catch (IndexOutOfBoundsException e) {
                    return "Please enter a valid task number.\n";
                }
            case UNMARK:
                try {
                    tasks.unmarkTask(Integer.valueOf(words[1]) - 1);
                    storage.editLine(Integer.valueOf(words[1]), "unmark");
                    Task toUnmark = tasks.get(Integer.valueOf(words[1]) - 1);
                    return "Get better, I've marked this item as not done:\n" + toUnmark.toString() + "\n";
                } catch (IndexOutOfBoundsException e) {
                    return "Please enter a valid task number.\n";
                }
            case DEADLINE:
                try {
                    String[] parts = input.split("/by", 2);
                    String[] firstPart = parts[0].split(" ", 2);

                    String name = firstPart[1];
                    String deadline = parts[1].trim();

                    Deadline deadlineToPush = new Deadline(name, false, deadline);
                    tasks.addDeadline(deadlineToPush);
                    storage.writeFile(deadlineToPush);
                    return "I've added this deadline:\n" + deadlineToPush.toString() + "\n"
                            + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
                } catch (ArrayIndexOutOfBoundsException e) {
                    return "Please enter a valid task name and deadline.";
                }
            case EVENT:
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
                    return "I've added this event:\n" + eventToPush.toString() + "\n"
                            + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
                } catch (ArrayIndexOutOfBoundsException e) {
                    return "Please enter a valid task name and to & from dates.";
                }
            case DELETE:
                try {
                    int index = Integer.valueOf(words[1]) - 1;
                    // reo.Task toRemove = tasks.get(index);
                    tasks.deleteTask(index);
                    storage.removeLine(index + 1);
                    Task toDelete = tasks.get(index);
                    return "I've deleted this task:\n" + toDelete.toString() + "\n"
                            + "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
                } catch (IndexOutOfBoundsException e) {
                    return "Please enter a valid task number.\n";
                }
            case FIND:
                try {
                    String keyword = words[1];
                    TaskList filtered = new TaskList(tasks.filter(keyword));
                    return "Here are the matching tasks in your list:\n" + filtered.toString() + "\n";
                } catch (IndexOutOfBoundsException e) {
                    return "ERROR: Please enter a valid keyword to search for.";
                }
            case BYE:
                return "Bye!";
            case UNKNOWN:
                return "ERROR: Please enter a valid command.\n";
            default:
                return "ERROR: Please enter a valid command.\n";
            }
    }
}
