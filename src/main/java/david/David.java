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
     * Returns a start-up message on launch of the appplication
     * @return Start-up message
     */
    public String getStartUpMessage() {
        return ui.start();
    }


    /**
     * Get response from chatbot
     */
    public String getResponse(String input) {
        inputString = input;

        if (inputString.equals("bye")) {
            return endChatBot();
        }

        try {
            switch (StringParser.parseStringToCommand(inputString)) {
            case "LIST":
                return listTasks();
            case "MARK":
                return markTaskAsDone(inputString);
            case "UNMARK":
                return markTaskAsUnDone(inputString);
            case "TODO":
                return addTodoTask(inputString);
            case "EVENT":
                return addEventTask(inputString);
            case "DEADLINE":
                return addDeadlineTask(inputString);
            case "DELETE":
                return deleteTask(inputString);
            case "FIND":
                return findEvent(inputString);
            case "SORT":
                return sortEvent(inputString);
            default:
                throw new DavidUnknownActionException();
            }
        } catch (DavidException e) {
            //The actual error message thrown depends on the runtime type of the exception thrown. (Polymorphism)
            return ui.displayErrorMessage(e);
        }
    }

    /**
     * Sorts the existing tasks in increasing or decreasing order
     * @param s String representing the sort order
     */
    public String sortEvent(String s) throws DavidInvalidArgumentsException {
        String sortOrder = StringParser.parseStringToArguments(s);
        sortOrder = sortOrder.toUpperCase();

        switch (sortOrder) {
        case "ASC":
            tasks.sortAscending();
            break;
        case "DESC":
            tasks.sortDescending();
            break;
        default:
            System.out.println("printing");
        }

        return ui.displaySuccessfulSortMessage(tasks);
    }

    /**
     * Return all events corresponding to the specified event
     * @param s input string
     * @throws DavidInvalidArgumentsException
     */
    public String findEvent(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);
        return ui.findEvent(event, tasks);
    }


    /**
     * Adds todo task to tasklist
     * @param s string task
     * @throws DavidInvalidArgumentsException
     */
    public String addTodoTask(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);

        Task t = new TodoTask(s, false);
        this.tasks.addTask(t);

        return ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds an event task to the arraylist
     * @param s String event
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidRangeException
     * @throws DavidInvalidDateTimeException
     */
    public String addEventTask(String s) throws DavidInvalidArgumentsException, DavidInvalidRangeException,
            DavidInvalidDateTimeException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /from", 2);
        String eventName = eventSplit[0];

        //Check if "from" field exists
        if (eventSplit.length <= 1) {
            throw new DavidInvalidRangeException();
        }

        assert eventSplit.length > 1 : "from field does not exist";

        //Check if "to" field exists
        String[] eventDetails = eventSplit[1].split(" /to", 2);
        if (eventDetails.length <= 1 || eventDetails[0].trim().equals("") || eventDetails[1].trim().equals("")) {
            throw new DavidInvalidRangeException();
        }

        LocalDateTime fromDate = DateParser.getDate(eventDetails[0]);
        LocalDateTime toDate = DateParser.getDate(eventDetails[1]);

        Task t = new EventTask(eventName, fromDate, toDate, false);
        this.tasks.addTask(t);

        return ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds a deadline task to the arraylist
     * @param s String event
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidDeadlineException
     * @throws DavidInvalidDateTimeException
     */
    public String addDeadlineTask(String s) throws DavidInvalidArgumentsException, DavidInvalidDeadlineException,
            DavidInvalidDateTimeException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /by", 2);

        //Checks if deadline is added to the input string
        if (eventSplit.length <= 1 || eventSplit[1].trim().equals("")) {
            throw new DavidInvalidDeadlineException();
        }

        LocalDateTime byDate = DateParser.getDate(eventSplit[1]);
        Task t = new DeadlineTask(eventSplit[0], byDate, false);
        this.tasks.addTask(t);

        return ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Deletes a task from the arraylist
     * @param s String index to delete
     * @throws DavidInvalidArgumentsException
     * @throws DavidInvalidTaskException
     */
    public String deleteTask(String s) throws DavidInvalidArgumentsException, DavidInvalidTaskException {
        try {
            String index = StringParser.parseStringToArguments(s);
            int i = Integer.parseInt(index) - 1;

            //Throws error when invalid indexed task is provided
            if (i >= tasks.getSize()) {
                throw new DavidInvalidTaskException();
            }

            Task t = tasks.getTask(i);
            tasks.deleteTask(i);

            return ui.displaySuccessfulDeleteMessage(t, this.tasks.getSize());
        } catch (NumberFormatException e) {
            return ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }

    }

    /**
     * Marks a task from the arraylist as done
     * @param s String index to mark
     * @throws DavidInvalidArgumentsException
     */
    public String markTaskAsDone(String s) throws DavidInvalidArgumentsException {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.getTask(Integer.parseInt(index) - 1);
            t.markAsDone();

            return ui.displayMarkAsDoneMessage(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.displayErrorMessage("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            return ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    /**
     * Marks a task from the arraylist as done
     * @param s String index to mark
     * @throws DavidInvalidArgumentsException
     */
    public String markTaskAsUnDone(String s) throws DavidInvalidArgumentsException {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.getTask(Integer.parseInt(index) - 1);
            t.markAsUnDone();

            return ui.displayMarkAsUnDoneMessage(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.displayErrorMessage("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            return ui.displayErrorMessage("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    /**
     * Lists all tasks added
     */
    public String listTasks() {
        return ui.listTasks(this.tasks);
    }

    /**
     * End chatbot
     */
    public String endChatBot() {
        try {
            cache.saveTask(tasks);
            return ui.end();
        } catch (DavidCacheException e) {
            return ui.displayErrorMessage(e);
        }

    }


}
