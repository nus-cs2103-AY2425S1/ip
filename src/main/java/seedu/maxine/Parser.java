package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Task;

public class Parser {
    private Ui ui;
    private TaskList list;
    private Storage storage;
    private boolean isRunning;
    
    public Parser() {
        ui = new Ui();
        list = new TaskList();
        this.isRunning = true;
        storage = new Storage("data/maxine.txt");
    }
    
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
            default:
                throw new MaxineException("Oh no.. this command is not recognised");
            }
        } catch (MaxineException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean getStatus() {
        return isRunning;
    }
}
