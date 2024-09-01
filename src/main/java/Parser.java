import java.io.IOException;

public class Parser {

    public static Task parseCommand(String text, TaskList record, Ui ui, Storage storage) throws OptimusException, IOException {
        if (text.equals("bye")) {
            storage.saveToFile(record.getTasks());
            ui.showGoodbye();
            System.exit(0);
        } else if (text.equals("list")) {
            ui.listTasks(record);
        } else if (text.startsWith("delete ")) {
            int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
            Task taskToDelete = record.getTask(taskNumber);
            record.deleteTask(taskNumber);
            ui.TaskDeleted(taskToDelete, record.size());
        } else if (text.startsWith("mark ")) {
            int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
            Task taskToMark = record.getTask(taskNumber);
            taskToMark.setDone();
            ui.TaskMarked(taskToMark);
        } else if (text.startsWith("unmark ")) {
            int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
            Task taskToUnmark = record.getTask(taskNumber);
            taskToUnmark.setNotDone();
            ui.TaskUnmarked(taskToUnmark);
        } else if (text.startsWith("todo ")) {
            String description = text.substring(5).trim();
            if (description.isEmpty()) {
                throw new OptimusException("Oops! The description of a todo cannot be empty. " +
                        "Please provide a task description.");
            }
            return new ToDos(description);
        } else if (text.startsWith("deadline ")) {
            String[] parts = text.split(" /by ");
            if (parts.length < 2) {
                throw new OptimusException("Oops! The deadline format is incorrect. " +
                        "Please provide a description and a deadline (e.g., deadline return book /by 2024-09-10).");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new OptimusException("Oops! The description of a deadline cannot be empty. " +
                        "Please provide a task description.");
            }
            return new Deadlines(description, by);
        } else if (text.startsWith("event ")) {
            String[] parts = text.split(" /from | /to ");
            if (parts.length < 3) {
                throw new OptimusException("Oops! The event time format is incorrect. " +
                        "Please provide both 'from' and 'to' times (e.g., event meeting /from 2024-09-10 14:00 /to 16:00).");
            }
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty()) {
                throw new OptimusException("Oops! The description of an event cannot be empty. " +
                        "Please provide a task description.");
            }
            return new Events(description, from, to);
        } else {
            throw new OptimusException("Sorry, I don't understand that command. " +
                    "Please try again with a valid command (e.g., todo, deadline, event, delete, list, bye, mark, unmark).");
        }
        return null;
    }
}
