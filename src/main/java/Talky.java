import java.time.LocalDateTime;

public class Talky {
    //private static ArrayList<Task> userTasks = new ArrayList<>();
    private final static String SAVE_PATH = "./data/talky.txt";
    private boolean isRunning;
    private Ui ui;
    private TaskList userTasks;
    private SaveData saveData;
    private Parser parser;

    private Talky() {
        ui = new Ui();
        saveData = new SaveData(SAVE_PATH);
        try {
            userTasks = new TaskList(saveData.loadData());
        } catch (TalkyException err) {
            ui.printSeperator(err.getMessage());
            userTasks = new TaskList();
        }
    }

    public void run(){
        ui.printSeperator("Hello I'm Talky\n" + "How may I help you?");
        boolean isRunning = true;
        while (isRunning) {
            try {
                String newCommand = ui.getCommand();
                String commandType = Parser.commandType(newCommand);
                String[] commandArgs = Parser.commandArgs(newCommand, commandType);
                switch (commandType) {
                case "bye":
                    ui.printSeperator("Bye!!! Do let me know if there's anything else!");
                    isRunning = false;
                    break;
                case "list":
                    ui.printSeperator(TaskList.tolistFormat());
                    break;
                case "mark":
                    mark(commandArgs);
                    break;
                case "unmark":
                    unmark(commandArgs);
                    break;
                case "todo":
                    addTodo(commandArgs);
                    break;
                case "deadline":
                    addDeadline(commandArgs);
                    break;
                case "event":
                    addEvent(commandArgs);
                    break;
                case "delete":
                    delete(commandArgs);
                    break;
                default:
                    throw new TalkyException("Invalid Command");
                }
                saveData.saveData(userTasks);
            } catch (TalkyException err) {
                ui.printSeperator(err.getMessage());
            }
        }
    }

    public static void main(String[] args) throws TalkyException {
        new Talky().run();
    }

    private void mark(String[] commandArgs) throws TalkyException {
        try {
            int indexToChange = Integer.parseInt(commandArgs[0]) - 1;
            String changedTask = userTasks.markTask(indexToChange, true);
            ui.printSeperator("Task Marked Done: " + changedTask);
        } catch (IndexOutOfBoundsException err) {
            throw new TalkyException("Given index out of bounds");
        } catch (NumberFormatException err) {
            throw new TalkyException("Mark must be given a number");
        }
    }

    private void unmark(String[] commandArgs) throws TalkyException {
        try {
            int indexToChange = Integer.parseInt(commandArgs[0]) - 1;
            String changedTask = userTasks.markTask(indexToChange, false);
            ui.printSeperator("Task Marked Done: " + changedTask);
        } catch (IndexOutOfBoundsException err) {
            throw new TalkyException("Given index out of bounds");
        } catch (NumberFormatException err) {
            throw new TalkyException("Unmark must be given a number");
        }
    }

    private void addTodo(String[] commandArgs) throws TalkyException {
        String name = commandArgs[0];
        userTasks.addToDo(name);
        ui.printSeperator("Added ToDo: " + name);
    }

    private void addDeadline(String[] commandArgs) throws TalkyException {
        String name = commandArgs[0];
        String by = commandArgs[1];
        LocalDateTime formattedBy = Parser.parseDate(by).get(0);
        userTasks.addDeadline(name, formattedBy);
        ui.printSeperator("Added Deadline: " + name);
    }

    private void addEvent(String[] commandArgs) throws TalkyException {
        String name = commandArgs[0];
        String from = commandArgs[1];
        String to = commandArgs[2];
        LocalDateTime formattedFrom = Parser.parseDate(from).get(0);
        LocalDateTime formattedTo = Parser.parseDate(to).get(0);
        userTasks.addEvent(name, formattedFrom, formattedTo);
        ui.printSeperator("Added Event: " + name);
    }

    private void delete(String[] commandArgs) throws TalkyException {
        try {
            int indexToChange = Integer.parseInt(commandArgs[0]) - 1;
            String changedTask = userTasks.deleteTask(indexToChange);
            ui.printSeperator("Deleted Task: " + changedTask);
        } catch (IndexOutOfBoundsException err) {
            throw new TalkyException("Given index out of bounds");
        } catch (NumberFormatException err) {
            throw new TalkyException("Delete must be given a number");
        }
    }
}

