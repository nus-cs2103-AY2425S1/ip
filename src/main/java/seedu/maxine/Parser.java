package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Ui ui;
    private TaskList list;
    private Storage storage;
    private boolean isRunning;

    /**
     * Constructs new instance of Parser class
     */
    public Parser() {
        ui = new Ui();
        list = new TaskList();
        this.isRunning = true;
        storage = new Storage("data/maxine.txt");
    }

    /**
     * Parses the user's inputs and sends the corresponding response.
     * The parser accepts the commands bye, list, delete, mark, unmark, find,
     * todo, deadline and event.
     * <p>
     * The method handles exceptions internally when users do not follow 
     * the correct format of typing out a task
     * 
     * @param input Input string that is to be parsed for corresponding action
     */
    public String parse(String input) {
        try {
            input = input.toLowerCase();
            String[] answer = input.split(" ");
            switch (answer[0]) {
            case ("bye"):
                return handleBye();
            case ("list"):
                return handleList();
            case ("mark"):
                return handleMark(answer[1]);
            case ("unmark"):
                return handleUnmark(answer[1]);
            case ("todo"):
                return handleTodo(input);
            case ("deadline"):
                return handleDeadline(input);
            case ("event"):
                return handleEvent(input);
            case ("delete"):
                return handleDelete(answer[1]);
            case ("find"):
                return handleFind(input);
            case ("clear"):
                return handleDeleteAll();
            default:
                throw new MaxineException("Oh no.. this command is not recognised");
            }
        } catch (MaxineException e) {
            return "Oh no.. this command is not recognised";
        }
    }

    /**
     * Returns boolean which indicates if the parser is still running.
     * When parser stops running, Maxine also stops running
     * @return The status of the Parser
     */
    public boolean getStatus() {
        return isRunning;
    }

    /**
     * 
     * @return
     */
    public String handleBye() {
        isRunning = false;
        return ui.goodbye();
    }
    public String handleList() {
        return ui.showList(list);
    }
    public String handleMark(String answer) {
        int mark = Integer.parseInt(answer) - 1;
        Task task = list.get(mark);
        task.markDone();
        storage.refreshStorage(list);
        return ui.changeMark(task);
    }
    public String handleUnmark(String answer) {
        int mark = Integer.parseInt(answer) - 1;
        Task task = list.get(mark);
        task.markUndone();
        storage.refreshStorage(list);
        return ui.changeMark(task);
    }
    public String handleTodo(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("todo ");
            String regex = "todo";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 2 || !matcher.find()) {
                throw new MaxineException("Please follow this "
                        + "format: todo [enter maxine.task]");
            }
            String description = answer[1];
            Todo task = new Todo(description);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - todo task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleDeadline(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("deadline | /by ");
            String regex = "deadline.*?/by";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 3 || !matcher.find()) {
                throw new MaxineException("Please follow this format: deadline "
                        + "[enter maxine.task] /by [enter deadline]");
            }
            String description = answer[1];
            String deadline = answer[2];
            Deadline task = new Deadline(description, deadline);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - deadline task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleEvent(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("event | /from | /to ");
            String regex = "event.*?/from.*?/to";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 4 || !matcher.find()) {
                throw new MaxineException("Please follow this format: event [enter event] "
                        + "/from [start date] /to [end date]");
            }
            String description = answer[1];
            String startTime = answer[2];
            String endTime = answer[3];
            Event task = new Event(description, startTime, endTime);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - event added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleDelete(String answer) {
        int key = Integer.parseInt(answer) - 1;
        Task task = list.get(key);
        list.delete(key);
        storage.refreshStorage(list);
        return ui.delete(task);
    }
    public String handleFind(String input) {
        return ui.search(input.substring(5));
    }
    public String handleDeleteAll() {
        list.deleteAll();
        storage.refreshStorage(list);
        return ui.deleteAll();
    }
}
