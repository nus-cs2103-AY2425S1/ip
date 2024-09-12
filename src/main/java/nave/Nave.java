package nave;

import java.util.Scanner;

/**
 * The {@code Nave} class represents the main chatbot.
 * It interacts with the user, processes commands, manages tasks, and handles
 * persistence of task data.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Nave {
    private final TaskList tasks;
    private final TaskStorage storage;
    private final Ui ui;
    private final Parser parser;
    private Parser.Command commandType;

    Nave() {
        this.tasks = new TaskList();
        this.storage = new TaskStorage("./data/tasks.txt");
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Starts the application and enters the main loop.
     * <p>
     * The method displays a greeting to the user, processes user input in a loop
     * until the user inputs "bye", and performs actions based on the parsed input.
     * It handles various commands such as listing tasks, marking tasks, unmarking tasks,
     * adding tasks, deleting tasks, and providing help messages.
     * </p>
     */
    public void run() {
        //Greet User
        ui.greet();

        //Load tasks from local file
        storage.onStart(tasks);

        Scanner inputReader = new Scanner(System.in);

        //Get user's input
        String userInput = inputReader.nextLine();
        int place; //For commands that delete a certain task at x place
        while (!userInput.equals("bye")) {
            switch(parser.handleInput(userInput)) {
            case LIST:
                ui.showResponse(tasks.listItems());
                break;
            case HELP:
                ui.helpMessage();
                break;
            case MARK:
                place = parser.parseMark(userInput);
                ui.showResponse(tasks.markItem(place));
                break;
            case UNMARK:
                place = parser.parseUnmark(userInput);
                ui.showResponse(tasks.unmarkItem(place));
                break;
            case TASK:
                try {
                    Task curr = parser.parseTask(userInput);
                    tasks.addTask(curr);
                    storage.saveToFile(curr.toFileFormat()); //Add task to local file
                    ui.showResponse(curr.creationResponse() + tasks.countTasks());
                } catch (WrongInputException e) {
                    ui.showResponse(e.getMessage());
                }
                break;
            case DELETE:
                place = parser.parseDelete(userInput);
                storage.deleteFromFile(place); //Delete task from local file
                ui.showResponse(tasks.deleteItem(place));
                break;
            case UNSURE:
                ui.unsureMessage();
                break;
            case FIND:
                String keyword = parser.parseFind(userInput);
                ui.showResponse(tasks.findTasks(keyword));
                break;
            default:
                ui.unsureMessage();
            }
            userInput = inputReader.nextLine();
        }

        //Say goodbye
        ui.sayFarewell();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        String output;
        int place;

        commandType = parser.handleInput(userInput);
        switch(commandType) {
        case LIST:
            output = tasks.listItems();
            break;
        case HELP:
            output = ui.guiHelp();
            break;
        case MARK:
            place = parser.parseMark(userInput);
            output = tasks.markItem(place);
            break;
        case UNMARK:
            place = parser.parseUnmark(userInput);
            output = tasks.unmarkItem(place);
            break;
        case TASK:
            try {
                Task curr = parser.parseTask(userInput);
                tasks.addTask(curr);
                storage.saveToFile(curr.toFileFormat()); //Add task to local file
                output = curr.creationResponse() + tasks.countTasks();
            } catch (WrongInputException e) {
                output = e.getMessage();
            }
            break;
        case DELETE:
            place = parser.parseDelete(userInput);
            storage.deleteFromFile(place); //Delete task from local file
            output = tasks.deleteItem(place);
            break;
        case BYE:
            output = ui.guiFarewell();
            break;
        case FIND:
            String keyword = parser.parseFind(userInput);
            output = tasks.findTasks(keyword);
            break;
        default:
            output = ui.guiUnsure();
        }

        return output;
    }

    /**
     * Generates a greeting for the user's chat message.
     */
    public String getGreeting() {
        return ui.guiGreet();
    }

    /**
     * Loads tasks from storage when starting up Nave on GUI.
     */
    public void guiStart() {
        //Load tasks from local file
        storage.onStart(tasks);
    }

    /**
     * Gets command type of command input by user.
     */
    public Parser.Command getCommandType() {
        return commandType;
    }

    /**
     * The entry point of the application.
     * <p>
     * Creates a new {@code Nave} instance and starts it by calling the {@code run} method.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Nave().run();
    }
}


