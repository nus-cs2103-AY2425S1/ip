package Parse;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Exception.MissingArg;
import Exception.WrongKeyword;
public class Parse {
    public static String parseTodo(String input) {
        return input.substring(5);
    }
    public static String[] parseDeadline(String input) {
        return input.substring(9).split(" /by ");
    }
    public static String[] parseEvent(String input) {
        return input.substring(6).split(" /from | /to ");
    }
    public static Boolean initialParse(String input, Ui ui, TaskList tasks, Storage storage) {
        if (input.equals("bye")){
            ui.uiBye();
            return false;
        } else if (input.equals("list")) {
            tasks.handleList();
            return true;
        } else if (input.startsWith("mark")) {
            tasks.markDone(input);
            storage.writeFile(tasks.getArray());
            return true;
        } else if (input.startsWith("unmark")) {
            tasks.markUnDone(input);
            storage.writeFile(tasks.getArray());
            return true;
        } else if (input.startsWith("delete")) {
            tasks.delete(input);
            storage.writeFile(tasks.getArray());
            return true;
        }else {
            try {
                tasks.handleTask(input);
                storage.writeFile(tasks.getArray());
                return true;
            } catch (WrongKeyword | MissingArg e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
