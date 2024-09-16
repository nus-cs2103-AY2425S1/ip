package milutrock;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

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
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Processes user input to execute different commands.
     * Throws an `UnknownCommandException` for unknown commands.
     * 
     * @param input The command to execute.
     * @return A boolean representing if the program should continue taking input.
     */
    public String parseCommand(String input) throws UnknownCommandException {
        String[] words = input.split("\\s+");

        if (input.equals("list")) {
            return this.handleList();
        } else if (words.length == 2 && words[0].equals("mark")) {
            return this.handleMark(words);
        } else if (words.length == 2 && words[0].equals("unmark")) {
            return this.handleUnmark(words);
        } else if (words.length > 1 && words[0].equals("delete")) {
            return this.handleDelete(input);
        } else if (words.length > 1 && words[0].equals("find")) {
            return this.handleFind(input);
        } else if (
            words[0].equals("todo")
            || words[0].equals("deadline")
            || words[0].equals("event")
        ) {
            return this.handleAdd(words, input);
        } else {
            throw new UnknownCommandException(input);
        }
    }

    private String handleList() {
        return this.ui.printTaskList();
    }

    private String handleMark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        this.taskList.markTaskAsDone(i);

        return this.ui.printMarkMessage(i);
    }

    private String handleUnmark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        this.taskList.unmarkTaskAsDone(i);

        return this.ui.printUnmarkMessage(i);
    }

    private String handleDelete(String input) {
        String query = input.substring(7);
        String[] words = query.split("\\s+");

        Integer[] indexes = new Integer[words.length];
        for (int i = 0; i < words.length; i++) {
            indexes[i] = Integer.parseInt(words[i]) - 1;
        }
        Arrays.sort(indexes, Collections.reverseOrder());

        ArrayList<Task> deletedTasks = new ArrayList<Task>();
        for (Integer i : indexes) {
            Task task = this.taskList.removeTask(i);
            deletedTasks.add(task);
        }

        return this.ui.printDeleteMessage(deletedTasks);
    }

    private String handleFind(String input) {
        String query = input.substring(5);
        ArrayList<Task> tasks = this.taskList.getTasksFromSearchString(query);

        return this.ui.printFindMessage(tasks);
    }

    private String handleAdd(String[] words, String input) {
        try {
            Task task;
            if (words[0].equals("todo")) {
                task = ToDo.getToDoFromInput(input);
            } else if (words[0].equals("deadline")) {
                task = Deadline.getDeadlineFromInput(input);
            } else {
                // words[0] is guaranteed to be "event" here
                assert words[0].equals("event");
                
                task = Event.getEventFromInput(input);
            }
            this.taskList.addTask(task);

            return this.ui.printAddMessage();
        } catch (InvalidTaskFormatException e) {
            return e.getMessage();
        }
    }
}
