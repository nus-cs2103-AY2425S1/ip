package bobby;

import java.io.IOException;

import bobby.exception.BobbyException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;
import bobby.utils.Command;
import bobby.utils.Parser;
import bobby.utils.Storage;
import bobby.utils.TaskList;
import bobby.utils.Ui;

/**
 * Main class for Bobby chatbot.
 */
public class Bobby {
    private static final String filePath = "data.txt";
    private final Storage storage;
    private TaskList listOfTasks;
    private final Ui ui;

    /**
     * Constructs a Bobby instance
     *
     * @param filePath Path of file where the data is to be stored.
     */
    public Bobby(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    public String getResponse(String input) {
        String output = "";

        this.initializeTaskList();

        try {
            Command command = Parser.parse(input);

            switch (command) {
            case BYE:
                output = ui.showBye();
                break;
            case LIST:
                output = ui.showTaskList(this.listOfTasks.getListOfTasks());
                break;
            case TODO:
                Task t = Todo.createTodo(input);
                listOfTasks.addTask(t);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showTaskCreated(t, listOfTasks.getListOfTasks());
                break;
            case DEADLINE:
                Task d = Deadline.createDeadline(input);
                listOfTasks.addTask(d);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showTaskCreated(d, listOfTasks.getListOfTasks());
                break;
            case EVENT:
                Task e = Event.createEvent(input);
                listOfTasks.addTask(e);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showTaskCreated(e, listOfTasks.getListOfTasks());
                break;
            case MARK:
                int indexMarked = Parser.parseNumber(input, 4);
                listOfTasks.mark(indexMarked);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showMarked();
                break;
            case UNMARK:
                int indexUnmark = Parser.parseNumber(input, 6);
                listOfTasks.unmark(indexUnmark);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showUnmarked();
                break;
            case DELETE:
                int indexDelete = Parser.parseNumber(input, 6);
                Task taskToBeDeleted = listOfTasks.getTask(indexDelete - 1);
                listOfTasks.deleteTask(indexDelete);
                this.storage.saveData(this.listOfTasks.getListOfTasks());
                output = ui.showTaskDeleted(taskToBeDeleted, listOfTasks.getNumberOfTasks());
                break;
            case FIND:
                String keyword = input.split(" ", 2)[1].trim();
                output = ui.showFindTasks(this.listOfTasks.findMatchingTasks(keyword));
                break;
            default:
                output = "Error! Please seek help from the developer!";
            }
        } catch (BobbyException | IOException e) {
            return e.getMessage();
        }
        return output;
    }

    /**
     * Initializes the TaskList in Bobby
     */
    public void initializeTaskList() {
        try {
            this.listOfTasks = new TaskList(this.storage.returnTaskList());
        } catch (IOException | BobbyException e) {
            System.out.println(e.getMessage());
        }
    }
}
