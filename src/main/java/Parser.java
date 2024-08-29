/*import java.io.IOException;

public class Parser {
    public String parse(String command, TaskList taskList, Ui ui, Storage storage) throws RubyException, IOException {
        String[] words = command.split(" ", 2);
        String keyword = words[0];

        return switch (keyword) {
            case "bye" -> "Bye. Hope to see you again soon!";
            case "list" -> taskList.listTasks();
            case "mark" -> handleMark(taskList, storage, words);
            case "unmark" -> handleUnmark(taskList, storage, words);
            case "todo" -> handleTodo(taskList, storage, words);
            case "deadline" -> handleDeadline(taskList, storage, words);
            case "event" -> handleEvent(taskList, storage, words);
            case "delete" -> handleDelete(taskList, storage, words);
            default -> throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }

    private String handleMark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to mark.");
        }
        int markIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(markIndex).markAsDone();
        storage.save(taskList);
        return "Nice! I've marked this task as done:\n     " + taskList.getTask(markIndex);
    }

    private String handleUnmark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to unmark.");
        }
        int unmarkIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(unmarkIndex).markAsNotDone();
        storage.save(taskList);
        return "OK, I've marked this task as not done yet:\n     " + taskList.getTask(unmarkIndex);
    }

    private String handleTodo(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(words[1].trim());
        taskList.addTask(todo);
        storage.save(taskList);
        return "Got it. I've added this task:\n     " + todo + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    private String handleDeadline(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = words[1].split(" /by ");
        if (deadlineParts.length < 2) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }
        Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        taskList.addTask(deadline);
        storage.save(taskList);
        return "Got it. I've added this task:\n     " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    private String handleEvent(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventParts = words[1].split(" /from ");
        if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }
        String[] times = eventParts[1].split(" /to ");
        Task event = new Event(eventParts[0].trim(), times[0].trim(), times[1].trim());
        taskList.addTask(event);
        storage.save(taskList);
        return "Got it. I've added this task:\n     " + event + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    private String handleDelete(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to delete.");
        }
        int deleteIndex = Integer.parseInt(words[1]) - 1;
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        storage.save(taskList);
        return "Noted. I've removed this task:\n     " + deletedTask + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public boolean isExit(String command) {
        return command.equals("bye");
    }
}*/

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public String parse(String command, TaskList taskList, Ui ui, Storage storage) throws RubyException, IOException {
        String[] words = command.split(" ", 2);
        String keyword = words[0];

        return switch (keyword) {
            case "bye" -> "Bye. Hope to see you again soon!";
            case "list" -> taskList.listTasks();
            case "mark" -> handleMark(taskList, storage, words);
            case "unmark" -> handleUnmark(taskList, storage, words);
            case "todo" -> handleTodo(taskList, storage, words);
            case "deadline" -> handleDeadline(taskList, storage, words);
            case "event" -> handleEvent(taskList, storage, words);
            case "delete" -> handleDelete(taskList, storage, words);
            default -> throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }

    private String handleMark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to mark.");
        }
        int markIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(markIndex).markAsDone();
        storage.save(taskList.getTasks());
        return "Nice! I've marked this task as done:\n     " + taskList.getTask(markIndex);
    }

    private String handleUnmark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to unmark.");
        }
        int unmarkIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(unmarkIndex).markAsNotDone();
        storage.save(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n     " + taskList.getTask(unmarkIndex);
    }

    private String handleTodo(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(words[1].trim());
        taskList.addTask(todo);
        storage.save(taskList.getTasks());
        return "Got it. I've added this task:\n     " + todo + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    private String handleDeadline(TaskList taskList, Storage storage, String[] words) throws RubyException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = words[1].split(" /by ");
        if (deadlineParts.length < 2) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }

        try {
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineParts[1].trim(), DATE_FORMAT);
            Task deadline = new Deadline(deadlineParts[0].trim(), deadlineDate);
            taskList.addTask(deadline);
            storage.save(taskList.getTasks());
            return "Got it. I've added this task:\n     " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (DateTimeParseException | IOException e) {
            throw new RubyException("OOPS!!! The date/time format should be 'yyyy-MM-dd HH:mm'.");
        }
    }

    private String handleEvent(TaskList taskList, Storage storage, String[] words) throws RubyException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventParts = words[1].split(" /from ");
        if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }

        String[] times = eventParts[1].split(" /to ");
        try {
            LocalDateTime startTime = LocalDateTime.parse(times[0].trim(), DATE_FORMAT);
            LocalDateTime endTime = LocalDateTime.parse(times[1].trim(), DATE_FORMAT);

            Task event = new Event(eventParts[0].trim(), startTime, endTime);
            taskList.addTask(event);
            storage.save(taskList.getTasks());
            return "Got it. I've added this task:\n     " + event + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (DateTimeParseException | IOException e) {
            throw new RubyException("OOPS!!! The date/time format should be 'yyyy-MM-dd HH:mm'.");
        }
    }

    private String handleDelete(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        if (words.length < 2) {
            throw new RubyException("Please specify the task number to delete.");
        }
        int deleteIndex = Integer.parseInt(words[1]) - 1;
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        storage.save(taskList.getTasks());
        return "Noted. I've removed this task:\n     " + deletedTask + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public boolean isExit(String command) {
        return command.equals("bye");
    }
}

