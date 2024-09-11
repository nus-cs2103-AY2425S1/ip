package boss;

import boss.exceptions.BossException;
import boss.exceptions.InvalidInputException;
import boss.tasks.Deadline;
import boss.tasks.Event;
import boss.tasks.Task;
import boss.tasks.Todo;

import java.io.IOException;

/**
 * Represents the class that deals with
 * making sense of the user command
 */
public class Parser {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Creates a Parser object!
     *
     * @param storage deals with loading tasks and saving tasks from file
     * @param tasks contains the task list
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Handles the user command for the GUI.
     *
     * @param task user input that contains the command.
     * @return String containing response from the Chatbot.
     * @throws BossException throws an exception if user input is invalid.
     * @throws IOException throws an exception if writing to data fails.
     */
    public String getResponse(String task) throws BossException, IOException {
        assert !task.isEmpty() : "task cannot be empty";

        if (task.equals("list")) {
            return storage.printTasks();
        } else if (task.equals("Yashvan is the best")) {
            return "THATS RIGHT! HE IS THE GOAT OF GOATS";
        } else if (task.startsWith("mark")) {
            /* The first element contains the newFileData
            and the second element contains the Boss' response. */
            String[] newFileDataWithResponse = tasks.mark(task);
            storage.writeToFile(newFileDataWithResponse[0], false);
            return tasks.printMark(newFileDataWithResponse[1]);
        } else if (task.startsWith("unmark")) {
            /* The first element contains the newFileData
            and the second element contains the Boss' response. */
            String[] newFileDataWithResponse = tasks.unmark(task);
            storage.writeToFile(newFileDataWithResponse[0], false);
            return tasks.printUnmark(newFileDataWithResponse[1]);
        } else if (task.startsWith("delete")) {
            /*The first element contains the newFileData
            and the second element contains the Boss' response. */
            String[] newFileDataWithResponse = tasks.delete(task);
            storage.writeToFile(newFileDataWithResponse[0], false);
            return tasks.printDelete(newFileDataWithResponse[1]);
        } else if (task.startsWith("find")) {
            String description = task.split("find ")[1];
            return tasks.findTask(description);
        } else if (task.startsWith("remind")) {
            return tasks.remind();
        } else if (task.startsWith("bye")) {
            return "Bye! Have a wonderful day legend!";
        } else if (task.startsWith("todo")) {
            Task newTask = todo(task);
            storage.writeToFile(newTask + System.lineSeparator(), true);
            return tasks.printAddTask(newTask);
        } else if (task.startsWith("deadline")) {
            Task newTask = deadline(task);
            storage.writeToFile(newTask + System.lineSeparator(), true);
            return tasks.printAddTask(newTask);
        } else if (task.startsWith("event")) {
            Task newTask = event(task);
            storage.writeToFile(newTask + System.lineSeparator(), true);
            return tasks.printAddTask(newTask);
        } else {
            Task taskItem = new Task(task);
            tasks.addTask(taskItem);
            storage.writeToFile(taskItem + System.lineSeparator(), true);
            return "added: " + task;
        }
    }

    /**
     * Parses the user's input if command is to create a todo task
     *
     * @param s user's input
     * @return Todo Task
     * @throws InvalidInputException is thrown if user's input is invalid
     */
    public Task todo(String s) throws InvalidInputException {
        String[] string = s.split(" ");
        if (string.length == 1) {
            throw new InvalidInputException("todo");
        }
        Task item = new Todo(s);
        tasks.addTask(item);
        return item;
    }

    /**
     * Parses the user's input if command is to create a deadline task
     *
     * @param s user's input
     * @return Deadline Task
     * @throws InvalidInputException is thrown if user's input is invalid
     */
    public Task deadline(String s) throws InvalidInputException {
        String[] str = s.split("/by ");
        if (str.length == 1) {
            throw new InvalidInputException("deadline");
        }
        Task newTask = new Deadline(str[0], false, str[1]);
        tasks.addTask(newTask);
        return newTask;
    }

    /**
     * Parses the user's input if command is to create an event task
     *
     * @param s user's input
     * @return Todo Task
     * @throws InvalidInputException is thrown if user's input is invalid
     */
    public Task event(String s) throws InvalidInputException {
        String[] strArr = s.split("/");
        if (!(strArr.length == 3 && strArr[1].contains("from") && strArr[2].contains("to"))) {
            throw new InvalidInputException("event");
        }

        String[] description = strArr[0].split(" ");
        String[] fromArr = strArr[1].split("from ");
        String[] toArr = strArr[2].split("to ");

        if (description.length <= 1 || fromArr.length <= 1 || toArr.length <= 1
                || fromArr[1].isEmpty() || toArr[1].isEmpty()) {
            throw new InvalidInputException("event");
        }

        String from = fromArr[1];
        String to = toArr[1];

        Task newItem = new Event(strArr[0], from, to, false);

        tasks.addTask(newItem);
        return newItem;
    }
}
