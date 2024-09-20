package david;

import java.time.LocalDateTime;

import david.data.Storage;
import david.exceptions.DavidCacheException;
import david.exceptions.DavidException;
import david.exceptions.DavidInvalidArgumentsException;
import david.exceptions.DavidInvalidDateTimeException;
import david.exceptions.DavidInvalidDeadlineException;
import david.exceptions.DavidInvalidIndexRangeException;
import david.exceptions.DavidInvalidNoTaskException;
import david.exceptions.DavidInvalidRangeException;
import david.exceptions.DavidInvalidTimeException;
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
    public David() {
        this.ui = new Ui();
        this.cache = new Storage();
        this.tasks = cache.loadTasks();
    };

    /**
     * Returns a start-up message on launch of the appplication
     *
     * @return Start-up message.
     */
    public String getStartUpMessage() {
        return ui.start();
    }


    /**
     * Get response from chatbot
     */
    public String getResponse(String input) {
        String response = "";
        inputString = input;

        if (inputString.equals("bye")) {
            return endChatBot();
        }

        try {
            switch (StringParser.parseStringToCommand(inputString)) {
            case "LIST":
                response = listTasks();
                break;
            case "MARK":
                response = markTaskAsDone(inputString);
                break;
            case "UNMARK":
                response = markTaskAsUnDone(inputString);
                break;
            case "TODO":
                response = addTodoTask(inputString);
                break;
            case "EVENT":
                response = addEventTask(inputString);
                break;
            case "DEADLINE":
                response = addDeadlineTask(inputString);
                break;
            case "DELETE":
                response = deleteTask(inputString);
                break;
            case "FIND":
                response = findEvent(inputString);
                break;
            case "SORT":
                response = sortEvent(inputString);
                break;
            case "HELP":
                response = ui.displayHelp();
                break;
            default:
                throw new DavidUnknownActionException();
            }
        } catch (DavidException e) {
            //The actual error message thrown depends on the runtime type of the exception thrown. (Polymorphism)
            return ui.displayErrorMessage(e);
        }

        return response;
    }

    /**
     * Sorts the existing tasks in increasing or decreasing order
     *
     * @param s String representing the sort order.
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
            throw new DavidInvalidArgumentsException();
        }

        return ui.displaySuccessfulSortMessage(tasks);
    }

    /**
     * Return all events corresponding to the specified event
     *
     * @param s input string.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     */
    public String findEvent(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);
        return ui.findEvent(event.trim(), tasks);
    }


    /**
     * Adds todo task to tasklist
     *
     * @param s string task.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     */
    public String addTodoTask(String s) throws DavidInvalidArgumentsException {
        String event = StringParser.parseStringToArguments(s);

        Task t = new TodoTask(event, false);
        this.tasks.addTask(t);

        return ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds an event task to the arraylist
     *
     * @param s String event.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     * @throws DavidInvalidRangeException if range is invalid.
     * @throws DavidInvalidDateTimeException if time is invalid.
     */
    public String addEventTask(String s) throws DavidInvalidArgumentsException, DavidInvalidRangeException,
            DavidInvalidDateTimeException, DavidInvalidTimeException {
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
        DateParser.validateDateTime(fromDate, toDate);

        Task t = new EventTask(eventName, fromDate, toDate, false);
        this.tasks.addTask(t);

        return ui.displayTaskDetails(t, this.tasks.getSize());
    }

    /**
     * Adds a deadline task to the arraylist
     *
     * @param s String event.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     * @throws DavidInvalidRangeException if range is invalid.
     * @throws DavidInvalidDateTimeException if time is invalid.
     */
    public String addDeadlineTask(String s) throws DavidInvalidArgumentsException, DavidInvalidDeadlineException,
            DavidInvalidDateTimeException, DavidInvalidTimeException {
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
     *
     * @param s String index to delete.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     * @throws DavidInvalidNoTaskException if task is invalid.
     */
    public String deleteTask(String s) throws DavidInvalidArgumentsException, DavidInvalidIndexRangeException,
            DavidInvalidNoTaskException {
        String index = StringParser.parseStringToArguments(s.trim());
        int i = Integer.parseInt(index) - 1;

        //Throws error when invalid indexed task is provided
        if (i < 0 || i > tasks.getSize()) {
            throw new DavidInvalidIndexRangeException();
        }
        if (tasks.getSize() == 0) {
            throw new DavidInvalidNoTaskException();
        }

        Task t = tasks.getTask(i);
        tasks.deleteTask(i);
        return ui.displaySuccessfulDeleteMessage(t, this.tasks.getSize());

    }

    /**
     * Marks a task from the arraylist as done
     *
     * @param s String index to mark.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     */
    public String markTaskAsDone(String s) throws DavidInvalidArgumentsException, DavidInvalidIndexRangeException,
            DavidInvalidNoTaskException {
        String index = StringParser.parseStringToArguments(s.trim());
        int i = Integer.parseInt(index) - 1;

        if (i < 0 || i > tasks.getSize()) {
            throw new DavidInvalidIndexRangeException();
        }

        if (tasks.getSize() == 0) {
            throw new DavidInvalidNoTaskException();
        }

        Task t = tasks.getTask(Integer.parseInt(index) - 1);
        t.markAsDone();
        return ui.displayMarkAsDoneMessage(t);

    }

    /**
     * Marks a task from the arraylist as done
     *
     * @param s String index to mark.
     * @throws DavidInvalidArgumentsException if arguments are invalid.
     */
    public String markTaskAsUnDone(String s) throws DavidInvalidArgumentsException, DavidInvalidIndexRangeException,
            DavidInvalidNoTaskException {
        String index = StringParser.parseStringToArguments(s);
        int i = Integer.parseInt(index) - 1;

        if (i < 0 || i > tasks.getSize()) {
            throw new DavidInvalidIndexRangeException();
        }

        if (tasks.getSize() == 0) {
            throw new DavidInvalidNoTaskException();
        }

        Task t = tasks.getTask(Integer.parseInt(index) - 1);
        t.markAsUnDone();
        return ui.displayMarkAsUnDoneMessage(t);
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
