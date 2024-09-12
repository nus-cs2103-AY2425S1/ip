package command;

import java.util.Arrays;
import java.util.List;

import exception.InvalidArgumentException;
import exception.InvalidCommandException;
import exception.LevelHundredException;
import exception.MissingArgumentException;
import task.Deadline;
import task.Event;
import task.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;
import utility.Parser;

/**
 * The AddCommand class adds a new task into the task list and storage file
 */
public class AddCommand extends UserCommand {
    private Todo createTodo(String[] words) throws LevelHundredException {
        String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        if (taskDescription.equals("")) {
            throw new MissingArgumentException("todo", "description");
        }
        assert (!taskDescription.isEmpty()) : "Task description cannot be empty";

        return new Todo(taskDescription);
    }

    private Deadline createDeadline(String[] words) throws LevelHundredException {
        List<String> tmp = Arrays.asList(words);
        int byIdx = tmp.indexOf("/by");
        if (byIdx == -1) {
            throw new MissingArgumentException("deadline", "by");
        }
        assert (Arrays.stream(words).anyMatch("/by"::equals)) : "Deadlines must have a /by";

        String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, byIdx));
        if (taskDescription.equals("")) {
            throw new MissingArgumentException("deadline", "description");
        }
        assert (!taskDescription.isEmpty()) : "Task description cannot be empty";

        String by = String.join(" ", Arrays.copyOfRange(words, byIdx + 1, words.length));
        if (by.equals("")) {
            throw new MissingArgumentException("deadline", "by");
        }
        try {
            by = Parser.parseInputDate(by);
            return new Deadline(taskDescription, by);
        } catch (RuntimeException e) {
            throw new InvalidArgumentException("deadline", "by");
        }
    }

    private Event createEvent(String[] words) throws LevelHundredException {
        List<String> tmp = Arrays.asList(words);
        int fromIdx = tmp.indexOf("/from");
        int toIdx = tmp.indexOf("/to");
        if (fromIdx == -1) {
            throw new MissingArgumentException("event", "from");
        }
        if (toIdx == -1) {
            throw new MissingArgumentException("event", "to");
        }
        assert (Arrays.stream(words).anyMatch("/from"::equals)) : "Events must have a /from";
        assert (Arrays.stream(words).anyMatch("/to"::equals)) : "Events must have a /to";

        // Get task description
        String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, fromIdx));
        if (taskDescription.equals("")) {
            throw new MissingArgumentException("event", "description");
        }
        assert (!taskDescription.isEmpty()) : "Task description cannot be empty";

        // Get from date
        String from = String.join(" ", Arrays.copyOfRange(words, fromIdx + 1, toIdx));
        if (from.equals("")) {
            throw new MissingArgumentException("event", "from");
        }
        try {
            from = Parser.parseInputDate(from);
        } catch (RuntimeException e) {
            throw new InvalidArgumentException("event", "from");
        }

        // Get to date
        String to = String.join(" ", Arrays.copyOfRange(words, toIdx + 1, words.length));
        if (to.equals("")) {
            throw new MissingArgumentException("event", "to");
        }
        try {
            to = Parser.parseInputDate(to);
        } catch (RuntimeException e) {
            throw new InvalidArgumentException("event", "to");
        }

        return new Event(taskDescription, from, to);
    }
    private Task createTask(String[] words, String command) throws LevelHundredException {
        if (command.equals("todo")) {
            return createTodo(words);
        } else if (command.equals("deadline")) {
            return createDeadline(words);
        } else if (command.equals("event")) {
            return createEvent(words);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Adds new task into taskList and storage
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        try {
            String[] words = userInput.split(" ");
            String command = words[0];

            Task newTask = createTask(words, command);
            taskList.addTask(newTask);
            storage.update(taskList.getTaskList());

            StringBuilder res = new StringBuilder("Got it. I've added this task:\n");
            res.append(newTask + "\n");
            res.append("Now you have " + taskList.size() + " tasks in the list.");
            this.setResponse(res.toString());

            ui.printAddTask(newTask, taskList.size());
        } catch (LevelHundredException e) {
            ui.printException(e);
            this.setResponse(e.toString());
        }
    }
}