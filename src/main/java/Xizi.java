//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;


public class Xizi {
    private static final String FILE_PATH = "./data/xizi.txt";
    private final Storage storage;
    private TaskList actions;
    private final Ui ui;
    private final Parser parser;

    public Xizi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        actions = new TaskList();
        parser = new Parser();

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Xizi(FILE_PATH).run();
    }

    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String userInput = ui.readUserInput();
            CommandType commandType = CommandType.fromInput(userInput);

            try {
                switch (commandType) {
                case DELETE:
                    handleDelete(actions, storage, userInput);
                    break;
                case MARK:
                    handleMark(actions, storage, userInput);
                    break;
                case UNMARK:
                    handleUnmark(actions, storage, userInput);
                    break;
                case TODO:
                    handleTodo(actions, storage, userInput);
                    break;
                case DEADLINE:
                    handleDeadline(actions, storage, userInput);
                    break;
                case EVENT:
                    handleEvent(actions, storage, userInput);
                    break;
                case LIST:
                    ui.handleList(actions);
                    break;
                case BYE:
                    ui.showGoodbyeMessage();
                    return;
                case LIST_ON:
                    handleListOn(actions, userInput);
                    break;
                case HELP:
                    ui.printHelp();
                    break;
                default:
                    throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");
                }
            } catch (IOException | XiziException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    // Handler methods for each command
    private void handleDelete(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.DELETE.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            Task deleted = actions.deleteTask(taskNumber);
            storage.saveTasks(actions.getItems());
            this.ui.showLine();
            this.ui.printMessage("Noted. I've removed this task:");
            this.ui.printMessage("  " + deleted);
            this.ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
            this.ui.showLine();
        }
    }

    private void handleMark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.MARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            this.ui.showLine();
            this.ui.printMessage("Nice! I've marked this task as done: ");
            this.ui.printMessage(actions.markTask(taskNumber));
            this.ui.showLine();
            storage.saveTasks(actions.getItems());
        }
    }

    private void handleUnmark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.UNMARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            this.ui.showLine();
            this.ui.printMessage("OK, I've marked this task as not done yet:");
            this.ui.printMessage(actions.unmarkTask(taskNumber));
            this.ui.showLine();
            storage.saveTasks(actions.getItems());
        }
    }

    private void handleTodo(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = parser.matchCommand(userInput, CommandType.TODO);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            if (!parser.isValidTaskDescription(taskDescription)) {
                throw new XiziException("The description of a todo cannot be empty. Type help to see the formats required.");
            }
            actions.addTask(new Todo(taskDescription));
            storage.appendTask(new Todo(taskDescription));
            this.ui.showLine();
            this.ui.printMessage("Got it. I've added this task:");
            this.ui.printMessage("  [T][ ] " + taskDescription);
            this.ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
            this.ui.showLine();
        }
    }

    private void handleDeadline(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = parser.matchCommand(userInput, CommandType.DEADLINE);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String deadline = matcher.group(2).trim();
            if (!parser.isValidTaskDescription(taskDescription) || deadline.isEmpty()) {
                throw new XiziException("The description or time of a deadline cannot be empty.Type help to see the formats required.");
            }
            Deadline newTask = new Deadline(taskDescription, parser.parseDateTime(deadline));
            actions.addTask(newTask);
            storage.appendTask(newTask);
            this.ui.showLine();
            this.ui.printMessage("Got it. I've added this task:");
            this.ui.printMessage(newTask.toString());
            this.ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
            this.ui.showLine();
        }
    }

    private void handleEvent(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.EVENT.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String fromTime = matcher.group(2).trim();
            String toTime = matcher.group(3).trim();
            if (!parser.isValidTaskDescription(taskDescription) || fromTime.isEmpty() || toTime.isEmpty()) {
                throw new XiziException("The description, from or to time of an event cannot be empty.Type help to see the formats required.");
            }
            try {
                Event newTask = new Event(taskDescription, parser.parseDateTime(fromTime), parser.parseDateTime(toTime));
                actions.addTask(newTask);
                storage.appendTask(newTask);
                this.ui.showLine();
                this.ui.printMessage("Got it. I've added this task:");
                this.ui.printMessage(newTask.toString());
                this.ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
                this.ui.showLine();
            } catch (DateTimeParseException e){
                this.ui.printErrorMessage("The format of the time keyed in should be of the form 'd/M/yyyy HHmm'.");
            }
        }
    }



    public void handleListOn(TaskList actions, String userInput) {
        Matcher matcher = CommandType.LIST_ON.matcher(userInput);
        boolean tasksFound = false;
        if (matcher.matches()) {
            String dateTimeStr = matcher.group(1);
            try {
                LocalDateTime dateTime = parser.parseDateTime(dateTimeStr);
                this.ui.showLine();
                this.ui.printMessage("Here are the tasks on the particular day in your list:");
                for (Task task : actions.getItems()) {
                    if (task instanceof Event) {
                        Event event = (Event) task;
                        if ((event.from.isBefore(dateTime) || event.from.equals(dateTime))&&
                                (event.to.isAfter(dateTime) || event.to.equals(dateTime))) {
                            this.ui.printMessage(event.toString());
                            tasksFound = true;
                        }
                    } else if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.ddl.equals(dateTime)) {
                            this.ui.printMessage(deadline.toString());
                            tasksFound = true;
                        }
                    }
                }
            } catch (DateTimeParseException e) {
                this.ui.printMessage("Invalid date and time format. Please use 'd/M/yyyy HHmm'.");
            }

        }
        if (!tasksFound) {
            this.ui.printMessage("No tasks found on this date.");
        }
        this.ui.showLine();
    }

    private static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have " + actions.getSize() + " tasks in total.");
        }
    }

}
