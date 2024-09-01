package Cookie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class Cookie {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList;
    private Parser parser = new Parser();

    /**
     * Constructor for Cookie.
     * Loads the saved task list into TaskList.
     *
     */
    public Cookie() {
        loadTaskList();
    }

    private void loadTaskList() {
        try {
            taskList = new TaskList(storage.loadFile(storage.fetchFile()));
        } catch (FileNotFoundException | CookieException e) {
            System.out.println(e.getMessage());
        }
    }

    private String handlePrintTasks() {
        String response = "Here are all tasks in your list!\n";
        response = response + ui.printTasks(taskList.getTaskArrayList());
        return response;
    }

    private String handleDelete(String string) throws CookieException {

        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to delete.\n "
                    + "(Please enter an integer after \"delete\")");
        }

        int deleteIndex = parser.parseInteger(string);

        if (deleteIndex <= 0 || deleteIndex > taskList.getSize()) {
            throw new CookieException("The task you want to delete does not exist");
        }
        String response = ui.printDeleteTask(this.taskList.getTask(deleteIndex));
        this.taskList.delete(deleteIndex);
        response = response + ui.printNoTasksInList(this.taskList.getTaskArrayList());
        return response;
    }

    private String handleMark(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to mark.\n "
                    + "(Please enter an integer after \"mark\")");
        }
        int markIndex = parser.parseInteger(string);
        if (markIndex <= 0 || markIndex > taskList.getSize()) {
            throw new CookieException("The task you want to mark does not exist");
        }
        this.taskList.markDone(markIndex);
        return ui.printMarkTask(this.taskList.getTask(markIndex));
    }

    private String handleUnmark(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to mark.\n "
                    + "(Please enter an integer after \"mark\")");
        }

        int unmarkIndex = parser.parseInteger(string);

        if (unmarkIndex <= 0 || unmarkIndex > taskList.getSize()) {
            throw new CookieException("The task you want to mark does not exist");
        }
        this.taskList.unmarkDone(unmarkIndex);
        return ui.printUnmarkTask(this.taskList.getTask(unmarkIndex));
    }

    private String handleTodo(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Please enter a task for you to do.");
        }
        ToDo newTodoTask = new ToDo(string);
        this.taskList.addTask(newTodoTask);
        String response = ui.printLatestTask(newTodoTask);
        response = response + ui.printNoTasksInList(this.taskList.getTaskArrayList());
        return response;
    }

    private String handleDeadline(String string) throws CookieException {
        String[] deadlineDetails = parser.parseDeadline(string);
        if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
            throw new CookieException("Deadlines must include a task todo and a due date \n"
                    + "[task] /by [deadline]");
        }
        Deadline newDeadlineTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
        taskList.addTask(newDeadlineTask);
        String response = ui.printLatestTask(newDeadlineTask);
        response = response + ui.printNoTasksInList(this.taskList.getTaskArrayList());
        return response;
    }

    private String handleEvent(String string) throws CookieException {
        String[] eventDetails = parser.parseEvent(string);
        if (eventDetails.length < 2 || eventDetails[0].isEmpty()
                || eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
            throw new CookieException("Events must include a task, a start time and an end time \n"
                    + "[task] /from [start] /to [end]");
        }
        Event newEventTask = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
        taskList.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
        String response = ui.printLatestTask(newEventTask);
        response = response + ui.printNoTasksInList(this.taskList.getTaskArrayList());
        return response;
    }

    private String handleFind(String string) {
        ArrayList<Task> arrayMatchKeyword = taskList.findByKeyword(string);

        String response = "Here are matching tasks in your list!\n";
        response = response + ui.printTasks(arrayMatchKeyword);
        return response;
    }

    /**
     * Handles the logic when exiting Cookie chatbot.
     * This will save the current tasks into a .txt file and prints a String.
     *
     * @return A String containing the exit message.
     */
    public String handleQuit() {
        try {
            storage.saveFile(taskList.getTaskArrayList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ui.printQuit();
    }

    /**
     * Returns the greetin message on start up.
     * @return String displaying the commands available.
     */
    public String getGreetingMessage() {
        return ui.printGreet();
    }

    /**
     * Processes the user input and generates a response based on the parsed command and description.
     * @param input The user input string containing the command and optional description.
     * @return A response string based on the parsed command and description,
     *         or an error message if the command is invalid.
     */
    public String getResponse(String input) {
        try {
            String command = parser.parseCommand(input);
            String description = parser.parseDescription(input);

            switch (command) {
            case "bye":
                return handleQuit();
            case "list":
                return handlePrintTasks();
            case "find":
                return handleFind(description);
            case "delete":
                return handleDelete(description);
            case "mark":
                return handleMark(description);
            case "unmark":
                return handleUnmark(description);
            case "todo":
                return handleTodo(description);
            case "deadline":
                return handleDeadline(description);
            case "event":
                return handleEvent(description);
            default:
                throw new CookieException("Cookie does not understand this command. I'm sorry!!");

            }
        } catch (CookieException e) {
            return e.getMessage();
        }
    }
}
