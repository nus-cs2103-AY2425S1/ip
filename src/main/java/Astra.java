import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Astra {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Astra(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (AstraException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the index argument of a command.
     *
     * @param text Command text with its arguments.
     * @return Index specified in the command.
     * @throws AstraException If index is invalid.
     */
    private static int getIndex(String text) throws AstraException {
        int index;
        try {
            index = Integer.parseInt(text.split(" ")[1]);
        } catch (Exception e) {
            throw new AstraException("Invalid index.");
        }
        return index;
    }

    /**
     * Returns the key word arguments of a command.
     *
     * @param text Command text with its arguments.
     * @return Map of each key word to its string value.
     */
    private static HashMap<String, String> getArgs(String text) {
        String[] words = text.split(" ");
        HashMap<String, String> args = new HashMap<>();
        String key = "main";
        for (String word : words) {
            if (word.charAt(0) == '/') {
                key = word.substring(1);
            } else {
                if (args.containsKey(key)) {
                    args.put(key, args.get(key) + " " + word);
                } else {
                    args.put(key, word);
                }
            }
        }
        return args;
    }

    public void listItems() {
        ui.display(tasks.toString());
    }

    /**
     * Adds a task to the task list.
     *
     * @param type Type of the task.
     * @param text Command text with its arguments.
     * @throws AstraException If task description is empty.
     */
    public void add(TaskType type, String text) throws AstraException {
        String argText;

        // validate args length
        String typeArg = text.split(" ")[0];
        if (text.length() > typeArg.length() + 1) {
            argText = text.substring(typeArg.length() + 1).strip();
            if (argText.isEmpty()) {
                throw new AstraException("The description of a task cannot be empty.");
            }
        } else {
            throw new AstraException("The description of a task cannot be empty.");
        }

        // build args map
        HashMap<String, String> args = getArgs(argText);

        Task t = null;
        try {
            switch (type) {
            case TODO -> t = new Todo(args.get("main"));
            case DEADLINE -> t = new Deadline(args.get("main"), args.get("by"));
            case EVENT -> t = new Event(args.get("main"), args.get("from"), args.get("to"));
            }
        } catch (DateTimeParseException e) {
            throw new AstraException("Please supply valid dates (yyyy-MM-dd HHmm).");
        } catch (NullPointerException e) {
            throw new AstraException("Please supply dates.");
        }

        tasks.add(t);
        storage.save(tasks.toText());
        String msg = "Got it. I've added this task: \n  " +
                    t + "\n" +
                    "Now you have " + tasks.length() + " tasks in the list. \n";

        ui.display(msg);
    }

    public void delete(int index) throws AstraException {
        Task t = tasks.delete(index);
        storage.save(tasks.toText());
        String msg = " Noted. I've removed this task: \n  " + t + "\n";
        ui.display(msg);
    }

    public void mark(int index) throws AstraException {
        Task t = tasks.markAsDone(index, true);
        storage.save(tasks.toText());
        String msg = " Nice! I've marked this task as done: \n  " + t + "\n";
        ui.display(msg);
    }

    public void unmark(int index) throws AstraException {
        Task t = tasks.markAsDone(index, false);
        storage.save(tasks.toText());
        String msg = " OK, I've marked this task as not done yet: \n  " + t + "\n";
        ui.display(msg);
    }

    public void run() {
        boolean loop = true;
        ui.greet();
        while (loop) {
            String fullCommand = ui.readCommand();
            String command = fullCommand.split(" ")[0];
            try {
                if (command.equals("list")) {
                    listItems();
                } else if (command.equals("bye")) {
                    loop = false;
                } else if (command.equals("delete")) {
                    delete(getIndex(fullCommand));
                } else if (command.equals("mark")) {
                    mark(getIndex(fullCommand));
                } else if (command.equals("unmark")) {
                    unmark(getIndex(fullCommand));
                } else if (command.equals("todo")) {
                    add(TaskType.TODO, fullCommand);
                } else if (command.equals("deadline")) {
                    add(TaskType.DEADLINE, fullCommand);
                } else if (command.equals("event")) {
                    add(TaskType.EVENT, fullCommand);
                } else {
                    throw new AstraException("Unknown command.");
                }
            } catch (AstraException e) {
                ui.showError(e);
            }
        }

        ui.goodbye();
        ui.stop();
    }

    public static void main(String[] args) {
        new Astra("./data").run();
    }
}
