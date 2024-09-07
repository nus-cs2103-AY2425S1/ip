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
            String[] answer = input.split(" ");
            switch (answer[0]) {
            case ("bye"):
                isRunning = false;
                return ui.goodbye();
            case ("list"):
                return ui.showList(list);
            case ("mark"):
            case ("unmark"):
                int mark = Integer.parseInt(answer[1]) - 1;
                Task curr = list.get(mark);
                curr.changeStatus();
                storage.refreshStorage(list);
                return ui.changeMark(curr);
            case ("todo"):
                list.addTodo(answer);
                storage.refreshStorage(list);
                return "todo task added!";
            case ("deadline"):
                list.addDeadline(answer);
                storage.refreshStorage(list);
                return "deadline task added!";
            case ("event"):
                list.addEvent(answer);
                storage.refreshStorage(list);
                return "event added!";
            case ("delete"):
                int key = Integer.parseInt(answer[1]) - 1;
                Task task = list.get(key);
                list.delete(key);
                storage.refreshStorage(list);
                return ui.delete(task);
            case ("find"):
                return ui.search(input.substring(5));
            default:
                throw new MaxineException("Oh no.. this command is not recognised");
            }
        } catch (MaxineException e) {
            System.out.println(e.getMessage());
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
}
