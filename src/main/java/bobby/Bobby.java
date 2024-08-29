package bobby;

import bobby.exception.BobbyException;

import java.io.IOException;
import java.util.Scanner;

public class Bobby {
    private static String filePath = "data.txt";
    private Storage storage;
    private TaskList listOfTasks;
    private Ui ui;

    /**
     * Constructs a Bobby instance
     *
     * @param filePath Path of file where the data is to be stored.
     */
    public Bobby(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Bobby bobby = new Bobby(filePath);
        bobby.run();
    }

    /**
     * The method that is run when Bobby is launched
     */
    public void run() {
        ui.showGreeting();
        this.initializeTaskList();
        Scanner s = new Scanner(System.in);
        boolean hasExited = false;

        this.ui.showTaskList(this.listOfTasks.getListOfTasks());
        while (!hasExited) {
            String input = s.nextLine();
            try {
                Command command = Parser.parse(input);

                switch (command) {
                case BYE:
                    ui.showBye();
                    hasExited = true;
                    break;
                case LIST:
                    ui.showTaskList(this.listOfTasks.getListOfTasks());
                    break;
                case TODO:
                    Task t = Todo.createTodo(input);
                    listOfTasks.addTask(t);
                    ui.showTaskCreated(t, listOfTasks.getListOfTasks());
                    break;
                case DEADLINE:
                    Task d = Deadline.createDeadline(input);
                    listOfTasks.addTask(d);
                    ui.showTaskCreated(d, listOfTasks.getListOfTasks());
                    break;
                case EVENT:
                    Task e = Event.createEvent(input);
                    listOfTasks.addTask(e);
                    ui.showTaskCreated(e, listOfTasks.getListOfTasks());
                    break;
                case MARK:
                    int indexMarked = Parser.parseNumber(input, 4);
                    listOfTasks.mark(indexMarked);
                    ui.showMarked();
                    break;
                case UNMARK:
                    int indexUnmark = Parser.parseNumber(input, 6);
                    listOfTasks.unmark(indexUnmark);
                    ui.showUnmarked();
                    break;
                case DELETE:
                    int indexDelete = Parser.parseNumber(input, 6);
                    Task taskToBeDeleted = listOfTasks.getTask(indexDelete - 1);
                    listOfTasks.deleteTask(indexDelete);
                    ui.showTaskDeleted(taskToBeDeleted, listOfTasks.getNumberOfTasks());
                    break;
                case FIND:
                    String keyword = input.split(" ", 2)[1].trim();
                    ui.showFindTasks(this.listOfTasks.findMatchingTasks(keyword));
                }
            } catch (BobbyException e) {
                System.out.println(e.getMessage());
            }
        }
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
