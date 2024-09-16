package milutrock;

import java.util.ArrayList;

import milutrock.exceptions.InvalidTaskFormatException;
import milutrock.exceptions.UnknownCommandException;
import milutrock.tasks.Deadline;
import milutrock.tasks.Event;
import milutrock.tasks.Task;
import milutrock.tasks.ToDo;

/**
 * Parses user input commands to interact with a `TaskList` and `Ui`.
 */
public class Parser {
    private String stdin;
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.stdin = "";
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Returns the value of the `stdin` variable.
     * 
     * @return The `stdin` variable.
     */
    public String getStdin() {
        return this.stdin;
    }

    /**
     * Processes user input to execute different commands.
     * Throws an `UnknownCommandException` for unknown commands.
     * 
     * @param input The command to execute.
     * @return A boolean representing if the program should continue taking input.
     */
    public boolean parseCommand(String input) throws UnknownCommandException {
        this.stdin += input + "\n";

        if (input.equals("bye")) {
            this.handleBye();
            return false;
        }

        String[] words = input.split("\\s+");

        if (input.equals("list")) {
            this.handleList();
        } else if (words.length == 2 && words[0].equals("mark")) {
            this.handleMark(words);
        } else if (words.length == 2 && words[0].equals("unmark")) {
            this.handleUnmark(words);
        } else if (words.length == 2 && words[0].equals("delete")) {
            this.handleDelete(words);
        } else if (words.length > 1 && words[0].equals("find")) {
            this.handleFind(input);
        } else if (
            words[0].equals("todo")
            || words[0].equals("deadline")
            || words[0].equals("event")
        ) {
            this.handleAdd(words, input);
        } else {
            throw new UnknownCommandException(input);
        }

        return true;
    }

    private void handleBye() {
        this.ui.printByeMessage();
    }

    private void handleList() {
        this.ui.printTaskList();
    }

    private void handleMark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        this.taskList.markTaskAsDone(i);
        this.ui.printMarkMessage(i);
    }

    private void handleUnmark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        this.taskList.unmarkTaskAsDone(i);
        this.ui.printUnmarkMessage(i);
    }

    private void handleDelete(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        Task task = this.taskList.removeTask(i);
        this.ui.printDeleteMessage(task);
    }

    private void handleFind(String input) {
        String query = input.substring(5);
        ArrayList<Task> tasks = this.taskList.getTasksFromSearchString(query);
        this.ui.printFindMessage(tasks);
    }

    private void handleAdd(String[] words, String input) {
        try {
            Task task;
            if (words[0].equals("todo")) {
                task = ToDo.getToDoFromInput(input);
            } else if (words[0].equals("deadline")) {
                task = Deadline.getDeadlineFromInput(input);
            } else {
                // words[0] is guaranteed to be "event" here
                task = Event.getEventFromInput(input);
            }
            this.taskList.addTask(task);
            this.ui.printAddMessage();
        } catch (InvalidTaskFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
