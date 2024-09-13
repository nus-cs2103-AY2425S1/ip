package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Task;

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
            case ("unmark"):
                return handleMark(answer[1]);
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
        Task curr = list.get(mark);
        curr.changeStatus();
        storage.refreshStorage(list);
        return ui.changeMark(curr);
    }
    public String handleTodo(String input) {
        try {
            list.addTodo(input);
            storage.refreshStorage(list);
            return "todo task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleDeadline(String input) {
        try {
            list.addDeadline(input);
            storage.refreshStorage(list);
            return "deadline task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleEvent(String input) {
        try {
            list.addEvent(input);
            storage.refreshStorage(list);
            return "event added!";
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
}
