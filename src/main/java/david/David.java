package david;

import java.time.LocalDateTime;

import david.data.Storage;
import david.exceptions.DavidCacheException;
import david.exceptions.DavidException;
import david.exceptions.DavidInvalidArgumentsException;
import david.exceptions.DavidInvalidDateTimeException;
import david.exceptions.DavidInvalidDeadlineException;
import david.exceptions.DavidInvalidRangeException;
import david.exceptions.DavidInvalidTaskException;
import david.exceptions.DavidUnknownActionException;
import david.parser.DateParser;
import david.parser.StringParser;
import david.task.DeadlineTask;
import david.task.EventTask;
import david.task.Task;
import david.task.TaskList;
import david.task.TodoTask;
import david.ui.Ui;


/**
 * Main chatbot class
 */
public class David {
    private TaskList tasks;
    private Ui ui;
    private String inputString = "";
    private Storage cache;

    /**
     * Constructor for David.David
     */

    public David(String path) {
        this.ui = new Ui();
        this.cache = new Storage(path);
        this.tasks = cache.loadTasks();
    };

    /**
     * Start the chatbot
     */
    public void activateChatBot() {
        ui.start();
        while (true) {
            inputString = ui.getInput(); //get next input

            if (inputString.equals("bye")) {
                endChatBot(); //end chatbot
                break;
            }

            try {
                switch (StringParser.parseStringToCommand(inputString)) {
                case "LIST":
                    listTasks();
                    break;
                case "MARK":
                    markTaskAsDone(inputString);
                    break;
                case "UNMARK":
                    markTaskAsUnDone(inputString);
                    break;
                case "TODO":
                    addTodoTask(inputString);
                    break;
                case "EVENT":
                    addEventTask(inputString);
                    break;
                case "DEADLINE":
                    addDeadlineTask(inputString);
                    break;
                case "DELETE":
                    deleteTask(inputString);
                    break;
                case "FIND":
                    findEvent(inputString);
                    break;
                default:
                    throw new DavidUnknownActionException();
                }
            } catch (DavidException e) {
                /*
                Catch all exception.
                The actual error message thrown/shown depends on the runtime type of
                the exception thrown. (Polymorphism)
                 */
                ui.displayErrorMessage(e);
            }

        }
    }

    /**
     * Return all events corresponding to the specified event
     * @param s input string
     * @throws DavidInvalidArgumentsException
     */
    public void findEvent(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);
        ui.findEvent(event, tasks);
    }


    /**
     * Adds todo task to tasklist
     * @param s string task
     * @throws DavidInvalidArgumentsException
     */
    public void addTodoTask(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);
        Task t = new TodoTask(s, false);
        this.tasks.addTask(t);
        ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds an event task to the arraylist
     * @param s String event
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidRangeException
     * @throws DavidInvalidDateTimeException
     */
    public void addEventTask(String s) throws DavidInvalidArgumentsException, DavidInvalidRangeException,
            DavidInvalidDateTimeException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /from", 2);
        String eventName = eventSplit[0];
        if (eventSplit.length <= 1) {
            //"from" field does not exist
            throw new DavidInvalidRangeException();
        }

        String[] eventDetails = eventSplit[1].split(" /to", 2);
        if (eventDetails.length <= 1 || eventDetails[0].trim().equals("") || eventDetails[1].trim().equals("")) {
            //only the "from" field exist
            throw new DavidInvalidRangeException();
        }

        LocalDateTime fromDate = DateParser.getDate(eventDetails[0]);
        LocalDateTime toDate = DateParser.getDate(eventDetails[1]);

        Task t = new EventTask(eventName, fromDate, toDate, false);
        this.tasks.addTask(t);
        ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds a deadline task to the arraylist
     * @param s String event
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidDeadlineException
     * @throws DavidInvalidDateTimeException
     */
    public void addDeadlineTask(String s) throws DavidInvalidArgumentsException, DavidInvalidDeadlineException,
            DavidInvalidDateTimeException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /by", 2);

        if (eventSplit.length <= 1 || eventSplit[1].trim().equals("")) {
            //deadline is not added to the input string
            throw new DavidInvalidDeadlineException();
        }

        LocalDateTime byDate = DateParser.getDate(eventSplit[1]);
        Task t = new DeadlineTask(eventSplit[0], byDate, false);
        this.tasks.addTask(t);
        ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Deletes a task from the arraylist
     * @param s String index to delete
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidTaskException
     */
    public void deleteTask(String s) throws DavidInvalidArgumentsException, DavidInvalidTaskException {
        try {
            String index = StringParser.parseStringToArguments(s);
            int i = Integer.parseInt(index) - 1;
            if (i >= tasks.getSize()) {
                throw new DavidInvalidTaskException();
            }
            Task t = tasks.getTask(i);
            tasks.deleteTask(i);
            ui.displaySuccessfulDeleteMessage(t, this.tasks.getSize());
        } catch (NumberFormatException e) {
            ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }

    }

    /**
     * Marks a task from the arraylist as done
     * @param s String index to mark
     * @throws DavidInvalidArgumentsException
     */
    public void markTaskAsDone(String s) throws DavidInvalidArgumentsException {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.getTask(Integer.parseInt(index) - 1);
            t.markAsDone();
            ui.displayMarkAsDoneMessage(t);
        } catch (IndexOutOfBoundsException e) {
            ui.displayErrorMessage("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    /**
     * Marks a task from the arraylist as done
     * @param s String index to mark
     * @throws DavidInvalidArgumentsException
     */
    public void markTaskAsUnDone(String s) throws DavidInvalidArgumentsException {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.getTask(Integer.parseInt(index) - 1);
            t.markAsUnDone();
            ui.displayMarkAsUnDoneMessage(t);
        } catch (IndexOutOfBoundsException e) {
            ui.displayErrorMessage("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    /**
     * Lists all tasks added
     */
    public void listTasks() {
        ui.listTasks(this.tasks);
    }

    /**
     * End chatbot
     */
    public void endChatBot() {
        try {
            cache.saveTask(tasks);
            ui.end();
        } catch (DavidCacheException e) {
            ui.displayErrorMessage(e);
        }

    }

    /**
     * Main method called at the start of the program
     * @param args
     */
    public static void main(String[] args) {
        new David("./src/main/java/David/Data/database.txt").activateChatBot();
    }
}
