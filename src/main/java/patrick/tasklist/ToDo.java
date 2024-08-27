package patrick.tasklist;

import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    public static void toDoTask(String input) throws Parser.PatrickException {
        String taskDescription = input.replace("todo", "");
        if (taskDescription.isEmpty()) {
            throw new Parser.PatrickException("Description of a todo cannot be empty!!");
        } else {
            Task task = new ToDo(taskDescription);
            Storage.addList(task);
            Ui.showUserMsg(task.toString());
            try {
                Storage.appendToFile("\n" + task.toString());
            } catch (IOException e) {
                System.out.println("There is an error: " + e.getMessage());
            }
        }
    }
}
