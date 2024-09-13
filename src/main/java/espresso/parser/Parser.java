package espresso.parser;

import espresso.ui.Ui;
import espresso.task.TaskList;
import espresso.task.Task;
import espresso.task.TodoTask;
import espresso.task.DeadlineTask;
import espresso.task.EventTask;
import espresso.storage.Storage;
import espresso.parser.Parser;
import espresso.command.InvalidCommandException;
import java.text.ParseException;
import java.io.IOException;

public class Parser {
    public static void parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {

        if (input.equals("list")) {
            ui.printTasks(taskList);
        } else if (checkInput(input, "mark ")) {
            int i = Integer.parseInt(input.substring(5)) - 1;

            taskList.getTask(i).mark();
            ui.printTaskMarked(taskList.getTask(i));
        } else if (checkInput(input, "unmark ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            taskList.getTask(i).unmark();
            ui.printTaskUnmarked(taskList.getTask(i));
        } else if (checkInput(input, "todo ")) {
            ui.printTaskAdded(taskList.addTask(new TodoTask(input.substring(5))), "todo task");
        } else if (checkInput(input, "deadline ")) {
            String[] split = input.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }

            ui.printTaskAdded(taskList.addTask(new DeadlineTask(split[0], split[1])), "deadline");
        } else if (checkInput(input, "event ")) {
            String[] split = input.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }

            ui.printTaskAdded(taskList.addTask(new EventTask(split[0], split[1], split[2])), "event");
        } else if (checkInput(input, "delete ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            ui.printTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
        } else {
            ui.printError("Command cannot be found");
        }
    }

    private static boolean checkInput(String a, String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}