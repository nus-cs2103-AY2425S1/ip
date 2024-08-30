package chacha;

import chacha.task.Task;
import java.time.DateTimeException;
import java.util.ArrayList;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(ChaCha chacha) {
        this.storage = chacha.storage;
        this.tasks = chacha.tasks;
        this.ui = chacha.ui;
    }

    public String parseCommand(String userInput) {
        if (userInput.startsWith("bye")) {
            return this.ui.printExit();

        } else if (userInput.startsWith("list")) {
            return this.listCommand();

        } else if (userInput.startsWith("todo")) {
            return this.addToDoCommand(userInput);

        } else if (userInput.startsWith("deadline")) {
            return this.addDeadlineCommand(userInput);

        } else if (userInput.startsWith("event")) {
            return this.addEventCommand(userInput);

        } else if (userInput.startsWith("mark")) {
            return this.markCommand(userInput);

        } else if (userInput.startsWith("unmark")) {
            return this.unmarkCommand(userInput);

        } else if (userInput.startsWith("delete")) {
            return this.deleteCommand(userInput);

        } else if (userInput.startsWith("find")) {
            return this.doFindCommand(userInput);
        } else {
            String[] arr = {"Hmmm... I don't seem to understand this. Please input another command! "};
            return this.ui.printStrings(arr);
        }
    }

    public String listCommand() {
        return this.tasks.printList(this.ui);
    }

    public String addToDoCommand(String userInput) {
        try {
            Task taskAdded = this.tasks.addToDo(userInput, this.ui, this.storage);

            return this.ui.printAdd(taskAdded, this.tasks);

        } catch (ChaChaException e) {
            return e.toString();
        }
    }

    public String addDeadlineCommand(String userInput) {
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

    public String addEventCommand(String userInput) {
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

    public String deleteCommand(String userInput) {
        try {
            Task taskDeleted = this.tasks.deleteTask(userInput, this.ui, this.storage);

            return this.ui.printDelete(taskDeleted, this.tasks);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a chacha.task number... You don't enough tasks!"};
            return this.ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return this.ui.printStrings(arr);
        }
    }

    public String markCommand(String userInput) {
        try {
            Task taskMarked = this.tasks.markDone(userInput, this.ui, this.storage);

            return this.ui.printMark(taskMarked);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a chacha.task number... You don't enough tasks!"};
            return this.ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return this.ui.printStrings(arr);
        }
    }

    public String unmarkCommand(String userInput) {
        try {
            Task taskUnmarked = this.tasks.markUndone(userInput, this.ui, this.storage);

            return this.ui.printUnmark(taskUnmarked);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a chacha.task number... You don't enough tasks!"};
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
