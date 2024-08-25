public class Ollie {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs an Ollie instance with a new TaskList.
     */
    public Ollie() {
        ui = new Ui();
        try {
            this.storage = new Storage("./data/ollie.txt");
            this.taskList = new TaskList(storage.loadTasks(), storage);
        } catch (OllieException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand  The command entered by the user.
     * @param prefixLength The length of the prefix to trim.
     * @return The parsed task number.
     * @throws OllieException If the task number is invalid.
     */
    private int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
        try {
            return Integer.parseInt(userCommand.substring(prefixLength).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new OllieException("Please enter a valid task number! ☺");
        }
    }

    /**
     * Echoes the user's command and performs the corresponding action.
     *
     * @param userCommand The command entered by the user.
     */
    public void respond(String userCommand) {
        try {
            if (userCommand.equals("hello") || userCommand.equals("hi")) {
                ui.greeting();
                return;
            }
            if (userCommand.equals("bye")) {
                ui.exit();
            } else if (userCommand.equals("list")) {
                taskList.listTasks();
            } else if (userCommand.startsWith("mark ")) {
                int taskNumber = parseTaskNumber(userCommand, 5);
                taskList.markTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("unmark ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                taskList.unmarkTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("delete ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                taskList.deleteTask(taskNumber);
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark") || userCommand.startsWith("delete")) {
                throw new OllieException("Please enter the command in the correct format with a task number! ☺");
            } else {
                try {
                    if (userCommand.startsWith("todo")) {
                        taskList.addTask(Todo.createTask(userCommand));
                    } else if (userCommand.startsWith("deadline")) {
                        taskList.addTask(Deadline.createTask(userCommand));
                    } else if (userCommand.startsWith("event")) {
                        taskList.addTask(Event.createTask(userCommand));
                    } else {
                        throw new UnknownTaskTypeException();
                    }
                } catch (OllieException e) {
                    throw e;
                }
            }
        } catch (OllieException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * The main method that runs the Ollie task manager.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws OllieException {
        Ollie ollie = new Ollie();
        ollie.ui.greeting();
        System.out.println();

        String command;

        do {
            command = ollie.ui.readCommand();
            ollie.respond(command);
        } while (!command.equals("bye"));

        ollie.ui.close();
    }
}