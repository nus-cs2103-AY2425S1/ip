package optimus;
import java.io.IOException;

public class Parser {

    public static Task parseCommand(String text, TaskList record, Ui ui, Storage storage) throws OptimusException, IOException {
        text = text.trim();
        if (text.equals("bye")) {
            storage.saveToFile(record.getTasks());
            ui.showGoodbye();
            System.exit(0);
        } else if (text.equals("list")) {
            ui.listTasks(record);
        } else if (text.startsWith("delete")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty." +
                        " Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. " +
                        "Please provide a valid task number.");
            }
            Task taskToDelete = record.getTask(taskNumber);
            record.deleteTask(taskNumber);
            ui.TaskDeleted(taskToDelete, record.sizeOfRecord());
        } else if (text.startsWith("mark")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty." +
                        " Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. " +
                        "Please provide a valid task number.");
            }
            Task taskToMark = record.getTask(taskNumber);
            taskToMark.setDone();
            ui.TaskMarked(taskToMark);
        } else if (text.startsWith("unmark")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty." +
                        " Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. " +
                        "Please provide a valid task number.");
            }
            Task taskToUnmark = record.getTask(taskNumber);
            taskToUnmark.setNotDone();
            ui.TaskUnmarked(taskToUnmark);
        } else if (text.startsWith("todo ")) {
            String description = text.substring(5).trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a todo cannot be empty. " +
                        "Please provide a task description.");
            }
            return new ToDos(description);
        } else if (text.startsWith("deadline ")) {
            String[] parts = text.split(" /by ");
            if (parts.length < 2) {
                throw new OptimusException("The deadline format is incorrect. " +
                        "Please provide a description and a deadline (e.g., deadline return book /by 2024-09-10).");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a deadline cannot be empty. " +
                        "Please provide a task description.");
            }
            return new Deadlines(description, by);
        } else if (text.startsWith("event ")) {
            String[] parts = text.split(" /from | /to ");
            if (parts.length < 3) {
                throw new OptimusException("The event time format is incorrect. " +
                        "Please provide both 'from' and 'to' times (e.g., event meeting /from 2024-  " +
                        "2024-09-10 14:00 /to 2024-09-10 16:00).");
            }
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of an event cannot be empty. " +
                        "Please provide a task description.");
            }
            return new Events(description, from, to);
        } else {
            throw new OptimusException("I don't understand that command. " +
                    "Please try again with a valid command (e.g., todo, deadline, event, delete, list, bye, mark, unmark).");
        }
        return null;
    }
}
