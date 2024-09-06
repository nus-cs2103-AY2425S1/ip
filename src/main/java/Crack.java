import java.io.IOException;

public class Crack {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Crack(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(ui));
        } catch (IOException e) {
            ui.showError("Unable to load tasks from file.");
            tasks = new TaskList();  // Initialize with an empty task list if load fails
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String command = Parser.parseCommand(input);  // Get the command part of the input

            switch (command) {
                case "bye":
                    ui.showGoodbye();
                    ui.close();
                    return;
                case "list":
                    if (tasks.isEmpty()) {
                        ui.showMessage("Your task list is empty.");
                    } else {
                        ui.showMessage("Here are the tasks in your list:\n" + tasks.listTasks());
                    }
                    break;
                case "mark":
                    try {
                        int index = Parser.parseTaskNumber(input);
                        tasks.getTask(index).markAsDone();
                        ui.showMessage("Nice! I've marked this task as done:\n   " + tasks.getTask(index));
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        int index = Parser.parseTaskNumber(input);
                        tasks.getTask(index).unmark();
                        ui.showMessage("OK, I've marked this task as not done yet:\n   " + tasks.getTask(index));
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        String description = Parser.parseTodoDescription(input);
                        Todo newTodo = new Todo(description);
                        tasks.addTask(newTodo);
                        ui.showTaskAdded(newTodo, tasks.getSize());
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineDetails = Parser.parseDeadline(input);
                        Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        tasks.addTask(newDeadline);
                        ui.showTaskAdded(newDeadline, tasks.getSize());
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        String[] eventDetails = Parser.parseEvent(input);
                        Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                        tasks.addTask(newEvent);
                        ui.showTaskAdded(newEvent, tasks.getSize());
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        int index = Parser.parseTaskNumber(input);
                        Task removedTask = tasks.removeTask(index);
                        ui.showMessage("Noted. I've removed this task:\n   " + removedTask);
                        storage.saveTasks(tasks.getTasks(), ui);
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                default:
                    ui.showError("Invalid Command.");
                    break;
            }
        }
    }

    // Main method to initialize Crack and run the application
    public static void main(String[] args) {
        new Crack("./data/crack.txt").run();  // Passes file path to the constructor
    }
}
