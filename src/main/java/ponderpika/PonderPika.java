package ponderpika;

import ponderpika.exception.PonderPikaException;
import ponderpika.exception.SaveFileFormatException;
import ponderpika.exception.UnknownCommandException;
import ponderpika.parser.Parser;
import ponderpika.storage.IoHandler;
import ponderpika.task.Task;
import ponderpika.task.TaskList;
import ponderpika.task.Todo;
import ponderpika.ui.Ui;

/**
 * The {@code ponderpika} class represents a task management system.
 * It allows users to manage tasks including adding, listing, marking, unmarking, deleting tasks, and more.
 */
public class PonderPika {

    private final IoHandler io;
    private TaskList taskList = new TaskList();
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private String commandType;

    /**
     * Constructs a new PonderPika instance.
     * <p>
     * This constructor initializes the PonderPika object by attempting to load the task list from
     * persistent storage using loadData. If loading the data fails due to a PonderPikaException,
     * a new, empty TaskList is created and an error message is printed.
     * </p>
     */
    public PonderPika(String path) {
        ui.greet();
        this.io = new IoHandler(path);
        try {
            this.taskList = io.loadData();
        } catch (PonderPikaException e) {
            System.out.println(e.toString());
        }
    }

    public String getResponse(String userInput) {
        Command command;
        try {
            command = parser.parseCommand(userInput);
            commandType = command.getAction().toString();
            return handleCommand(command);
        } catch (PonderPikaException e) {
            return e.toString();
        }
    }

    /**
     * Returns the commandType for the particular user input
     * @return type of command given by user
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Saves the current task list to a persistent storage.
     * <p>
     * This method attempts to save the task list using the specified I/O operations.
     * If an error occurs during the save process, a SaveFileFormatException is caught
     * </p>
     */
    public void saveFile() {
        try {
            this.io.saveData(this.taskList);
        } catch (SaveFileFormatException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Handles a given Command by performing the appropriate action based on the command's action type.
     * <p>
     * This method processes commands for various actions such as listing tasks, marking tasks, adding
     * different types of tasks (To-do, DEADLINE, EVENT), deleting tasks, finding tasks by a keyword, and
     * handling the termination of the application.
     * </p>
     *
     * @param command the command to be processed, which includes the action type and associated data
     *
     * @throws PonderPikaException if the action type in the command is unknown or not handled
     */
    public String handleCommand(Command command) throws PonderPikaException {
        switch (command.getAction()) {
        case LIST:
            return taskList.printTasks();

        case MARK:
            return taskList.markTask((Integer) command.getData());

        case UNMARK:
            return taskList.unmarkTask((Integer) command.getData());

        case TODO:
            Task todo = new Todo((String) command.getData());
            taskList.addTask(todo);
            return "Pika! I have added your todo: " + command.getData()
                    + "\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list";

        case DEADLINE:
            Task deadline = (Task) command.getData();
            taskList.addTask(deadline);
            return "Pika! I have added a deadline: " + deadline.getDescription()
                    + "\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list";

        case EVENT:
            Task event = (Task) command.getData();
            taskList.addTask(event);
            return "Pika! I have added your event: " + event.getDescription()
                    + "\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list";

        case DELETE:
            taskList.deleteTask((Integer) command.getData());
            return "Your task has been deleted." + "\nPeek-A-Boo! We have "
                    + taskList.getTasks().size() + " tasks in our list";

        case FIND:
            System.out.println("Found these tasks:\n");
            return taskList.findTasks((String) command.getData());

        case PRIORITY:
            String[] taskToBePrioritized = (String[]) command.getData();
            int i = Integer.parseInt(taskToBePrioritized[1].trim()) - 1;
            return taskList.setPriorityLevel(i, taskToBePrioritized[0].trim());

        case BYE:
            saveFile();
            ui.bidBye();
            return "Exited!";

        default:
            throw new UnknownCommandException();
        }
    }
}
