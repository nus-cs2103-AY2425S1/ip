package chacha;

import chacha.task.Task;

import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * Represents the object that handles user inputs and matches them to the respective action.
 *
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(ChaCha chacha) {
        this.storage = chacha.storage;
        this.tasks = chacha.tasks;
        this.ui = chacha.ui;
    }

    /**
     * Returns the string representation of response to the user input.
     *
     * @return String representation.
     */
    public String parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return this.ui.printExit();

        } else if (userInput.equals("list")) {
            return this.doListCommand();

        } else if (userInput.startsWith("todo ")) {
            return this.doAddToDoCommand(userInput);

        } else if (userInput.startsWith("deadline ")) {
            return this.doAddDeadlineCommand(userInput);

        } else if (userInput.startsWith("event ")) {
            return this.doAddEventCommand(userInput);

        } else if (userInput.startsWith("mark ")) {
            return this.doMarkCommand(userInput);

        } else if (userInput.startsWith("unmark ")) {
            return this.doUnmarkCommand(userInput);

        } else if (userInput.startsWith("delete ")) {
            return this.doDeleteCommand(userInput);

        } else if (userInput.startsWith("find ")) {
            return this.doFindCommand(userInput);
        } else {
            String[] arr = {"Hmmm... I don't seem to understand this. Please input another command! "};
            return this.ui.printStrings(arr);
        }
    }

    /**
     * Returns the string representation of the list of tasks.
     *
     * @return String representation.
     */
    public String doListCommand() {
        return this.tasks.printList(this.ui);
    }

    /**
     * Returns the string representation of response to adding To Do task.
     *
     * @return String representation.
     */
    public String doAddToDoCommand(String userInput) {
        try {
            Task taskAdded = this.tasks.addToDo(userInput, this.ui, this.storage);

            return this.ui.printAdd(taskAdded, this.tasks);

        } catch (ChaChaException e) {
            return e.toString();
        }
    }

    /**
     * Returns the string representation of response to adding Deadline task.
     *
     * @return String representation.
     */
    public String doAddDeadlineCommand(String userInput) {
        try {
            Task taskAdded = this.tasks.addDeadline(userInput, this.ui, this.storage);

            return this.ui.printAdd(taskAdded, this.tasks);

        } catch (ChaChaException e) {
            return e.toString();
        } catch (DateTimeException e) {
            String[] arr = {"Please input the date in the format YYYY-MM-DD. "};
            return this.ui.printStrings(arr);
        }
    }

    /**
     * Returns the string representation of response to adding Event task.
     *
     * @return String representation.
     */
    public String doAddEventCommand(String userInput) {
        try {
            Task taskAdded = this.tasks.addEvent(userInput, this.ui, this.storage);

            return this.ui.printAdd(taskAdded, this.tasks);

        } catch (ChaChaException e) {
            return e.toString();
        } catch (DateTimeException e) {
            String[] arr = {"Please input the date in the format YYYY-MM-DD. "};
            return this.ui.printStrings(arr);
        }
    }

    /**
     * Returns the string representation of response to deleting a Task.
     *
     * @return String representation.
     */
    public String doDeleteCommand(String userInput) {
        try {
            Task taskDeleted = this.tasks.deleteTask(userInput, this.ui, this.storage);

            return this.ui.printDelete(taskDeleted, this.tasks);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a task number... You don't enough tasks!"};
            return this.ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return this.ui.printStrings(arr);
        }
    }

    /**
     * Returns the string representation of response to marking a Task done.
     *
     * @return String representation.
     */
    public String doMarkCommand(String userInput) {
        try {
            Task taskMarked = this.tasks.markDone(userInput, this.ui, this.storage);

            return this.ui.printMark(taskMarked);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a task number... You don't enough tasks!"};
            return this.ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return this.ui.printStrings(arr);
        }
    }

    /**
     * Returns the string representation of response to mark a Task undone.
     *
     * @return String representation.
     */
    public String doUnmarkCommand(String userInput) {
        try {
            Task taskUnmarked = this.tasks.markUndone(userInput, this.ui, this.storage);

            return this.ui.printUnmark(taskUnmarked);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a task number... You don't enough tasks!"};
            return this.ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return this.ui.printStrings(arr);
        }
    }

    public String doFindCommand(String userInput) {
        try {
            ArrayList<Task> results = this.tasks.find(userInput, this.ui);
            return this.ui.printList(results, "Here are the matching tasks in your list: \n");
        } catch (ChaChaException e) {
            return e.toString();
        }
    }
}
