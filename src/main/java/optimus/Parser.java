package optimus;
import javafx.application.Platform;

import java.io.IOException;
import java.util.List;

public class Parser {

    public static String parseCommand(String text, TaskList record, Ui ui, Storage storage) throws OptimusException, IOException {
        text = text.trim();
        if (text.equals("bye")) {
            storage.saveToFile(record.getTasks());
            return ui.showGoodbye();  // Return goodbye message to GUI
        } else if (text.startsWith("find ")) {
            String keyword = text.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new OptimusException("The keyword provided is empty");
            }
            return record.findTasks(keyword, ui);  // Return found tasks
        } else if (text.equals("list")) {
            return ui.listTasks(record);  // Return task list
        } else if (text.startsWith("delete")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
            }
            Task taskToDelete = record.getTask(taskNumber);
            record.deleteTask(taskNumber);
            return ui.TaskDeleted(taskToDelete, record.sizeOfRecord());  // Return task deleted response
        } else if (text.startsWith("mark")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
            }
            Task taskToMark = record.getTask(taskNumber);
            taskToMark.setDone();
            return ui.TaskMarked(taskToMark);  // Return task marked response
        } else if (text.startsWith("unmark")) {
            String[] parts = text.split(" ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
                throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
            }
            Task taskToUnmark = record.getTask(taskNumber);
            taskToUnmark.setNotDone();
            return ui.TaskUnmarked(taskToUnmark);  // Return task unmarked response
        } else if (text.startsWith("todo ")) {
            String description = text.substring(5).trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a todo cannot be empty. Please provide a task description.");
            }
            Task newTask = new ToDos(description);
            record.addTask(newTask);
            return ui.taskAdded(newTask, record.sizeOfRecord());  // Return task added response
        } else if (text.startsWith("deadline ")) {
            String[] parts = text.split(" /by ");
            if (parts.length < 2) {
                throw new OptimusException("The deadline format is incorrect. Please provide a description and a deadline.");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a deadline cannot be empty. Please provide a task description.");
            }
            Task newTask = new Deadlines(description, by);
            record.addTask(newTask);
            return ui.taskAdded(newTask, record.sizeOfRecord());  // Return task added response
        } else if (text.startsWith("event ")) {
            String[] parts = text.split(" /from | /to ");
            if (parts.length < 3) {
                throw new OptimusException("The event time format is incorrect. Please provide both 'from' and 'to' times.");
            }
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of an event cannot be empty. Please provide a task description.");
            }
            Task newTask = new Events(description, from, to);
            record.addTask(newTask);
            return ui.taskAdded(newTask, record.sizeOfRecord());  // Return task added response
        } else {
            throw new OptimusException("I don't understand that command. Please try again with a valid command.");
        }
    }

}
