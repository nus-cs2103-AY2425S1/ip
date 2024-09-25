import parser.Parser;
import storage.Storage;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import tasklist.TaskList;
import task.Task;
import exceptions.EmptyDescriptionException;
import exceptions.MissingDeadlineException;
import exceptions.TaskNotFoundException;
import exceptions.MissingEventTimeException;

/**
 * Represents the main application for managing tasks.
 * <p>
 * This class handles user interactions, processes commands, and manages tasks in a list. It also loads and saves tasks
 * from/to a specified file.
 * </p>
 */
public class BeeBot {
    private static Storage storage;
    private static ArrayList<Task> taskList;

    /**
     * Constructs a {@code BeeBot} instance with the specified file path.
     * <p>
     * Initializes the storage and attempts to load the task list from the provided file path. If loading fails,
     * initializes an empty task list.
     * </p>
     *
     * @param filePath the path to the file used for loading and saving tasks
     */
    public BeeBot(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        storage = new Storage(filePath);
        try {
            taskList = storage.loadTaskListFromFile();
            System.out.println(taskList);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            taskList = new ArrayList<>();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public static String getResponse(String input) {
        String[] parts = input.split(" ");
        String cmd = parts[0];
        try {
            switch (cmd) {
            case "list":
                int size = taskList.size();
                if (size == 0) return "There is currently nothing on the list!";
                String listStr = "";
                for (int i = 0; i < size; i++) {
                    int num = i + 1;
                    listStr += (num + "." + taskList.get(i).toString());
                }
                return listStr;
            case "mark":
                assert Integer.parseInt(parts[1]) > 0 && Integer.parseInt(parts[1]) <= taskList.size() : "Task number is out of range";
                Task doneTask = Parser.getTask(taskList, Integer.parseInt(parts[1]));
                doneTask.markAsDone();
                storage.saveTaskListToFile(taskList);
                return "🐝-utiful! Honeyboo marked this task as done:\n" + doneTask;
            case "unmark":
                assert Integer.parseInt(parts[1]) > 0 && Integer.parseInt(parts[1]) <= taskList.size() : "Task number is out of range";
                Task undoneTask = Parser.getTask(taskList, Integer.parseInt(parts[1]));
                undoneTask.markAsUndone();
                storage.saveTaskListToFile(taskList);
                return "🐝-utiful! Honeyboo marked this task as not done yet:\n" + undoneTask;
            case "todo":
                if (parts.length == 1) throw new EmptyDescriptionException("Enter a description for the Todo Task.\n");
                String todoName = Parser.concatenate(parts, 1);
                TaskList.createToDo(todoName, taskList);
                storage.saveTaskListToFile(taskList);
                return "Growl... Honeyboo added '" + todoName + "' to the list!";
            case "deadline":
                if (parts.length == 1) throw new EmptyDescriptionException("Enter a description for the Deadline Task.\n");
                String deadlineName = Parser.concatenateUntil(parts, "/by");
                String deadlineDate = Parser.dateConverter(Parser.getFollowingDate(parts, "/by"));
                TaskList.createDeadline(deadlineName, deadlineDate, taskList);
                storage.saveTaskListToFile(taskList);
                return "BZZZZZ... Honeyboo added  '"  + deadlineName + "' to the list!";
            case "event":
                if (parts.length == 1) throw new EmptyDescriptionException("Enter a description for the Event Task.\n");
                String eventName = Parser.concatenateUntil(parts, "/from");
                String startTime = Parser.dateConverter(Parser.getFollowingDate(parts, "/from", "/to"));
                String endTime = Parser.dateConverter(Parser.getFollowingDate(parts, "/to", ""));
                TaskList.createEvent(eventName, startTime, endTime, taskList);
                storage.saveTaskListToFile(taskList);
                return "Grrrr... Honeyboo added '" + eventName + "' to the list!";
            case "delete":
                assert Integer.parseInt(parts[1]) - 1 >= 0 && Integer.parseInt(parts[1]) - 1 < taskList.size() : "Task number is out of range";
                TaskList.deleteEvent(Integer.parseInt(parts[1]) - 1, taskList);
                storage.saveTaskListToFile(taskList);
                return "Yum yum in my tum tum! Task eaten!";
            case "find":
                String taskName = Parser.concatenate(parts, 1);
                ArrayList<Task> searchResults = new ArrayList<>();
                for (Task task: taskList) {
                    if (task.getName().contains(taskName)) {
                        searchResults.add(task);
                    }
                }
                int searchSize = searchResults.size();
                String searchStr = "";
                for (int i = 0; i < searchSize; i++) {
                    int num = i + 1;
                    searchStr += (num + "." + searchResults.get(i).toString());
                }
                return searchStr;
            case "update":
                String updateTo = Parser.getUpdatedName(parts);
                TaskList.updateTask(Integer.parseInt(parts[1]) - 1, updateTo, taskList);
                storage.saveTaskListToFile(taskList);
                return "Task updated!";
            case "bye":
                System.exit(0);
                return "Goodbye! The application will now close.";
            default:
                return """
                        Please enter a valid command for worker bee to follow:
                        1. todo <task name>
                        2. deadline <task name> /by <due date>
                        3. event <task-name> /from <start date> /to <end date>
                        4. mark <index>
                        5. unmark <index>
                        6. list
                        7. find <part of task name>
                        8. update <task number> /to <new name>
                        9. delete <task number>
                        9. bye""";
            }
        } catch (EmptyDescriptionException | MissingDeadlineException
                 | MissingEventTimeException | TaskNotFoundException e) {
           return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid date format. Enter date in YYYY-MM-DD format";
        } catch (NumberFormatException e) {
            return "Please enter a valid task number.\n";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage() + e + "\n";
        }
    }
}