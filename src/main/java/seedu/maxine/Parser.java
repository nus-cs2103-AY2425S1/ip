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
    public void parse(String input) {
        try {
            String[] answer = input.split(" ");
            switch (answer[0]) {
            case ("bye"):
                ui.goodbye();
                isRunning = false;
                break;
            case ("list"):
                ui.showList(list);
                break;
            case ("mark"):
            case ("unmark"):
                int mark = Integer.parseInt(answer[1]) - 1;
                Task curr = list.get(mark);
                curr.changeStatus();
                ui.changeMark(curr);
                storage.refreshStorage(list);
                break;
            case ("todo"):
                try {
                    list.addTodo(answer);
                    storage.refreshStorage(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: todo [enter maxine.task]");
                }
                break;
            case ("deadline"):
                try {
                    list.addDeadline(answer);
                    storage.refreshStorage(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: deadline [enter maxine.task] /by [enter deadline]");
                }
                break;
            case ("event"):
                try {
                    list.addEvent(answer);
                    storage.refreshStorage(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: event [enter event] /from [start date] /to [end date]");
                }
                break;
            case ("delete"):
                int key = Integer.parseInt(answer[1]) - 1;
                list.delete(key);
                ui.delete(list.get(key));
                storage.refreshStorage(list);
                break;
            case ("find"):
                ui.search(input.substring(5));
                break;
            default:
                throw new MaxineException("Oh no.. this command is not recognised");
            }
        } catch (MaxineException e) {
            System.out.println(e.getMessage());
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
