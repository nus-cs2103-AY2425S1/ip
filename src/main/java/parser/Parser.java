package parser;
import task.TaskList;
import ui.Ui;

import exception.IncompleteDescException;
import exception.UnknownWordException;
import exception.InvalidDeadlineException;

import task.DeadlinesTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import prince.Prince;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses input commands by user and performs the corresponding actions
 *
 * Class is responsible for interpreting input commands such as adding and deleting tasks,
 * marking tasks as done or undone and providing tasks in a list format when the user demands.
 * Class is also responsible for creating the various types of tasks and thus throws relevant
 * exceptions to ensure that commands are formatted correctly and the right tasks are created
 * according to the tasks.
 *
 */


public class Parser {

    /**
     * Parses the commands given by the user and performs the necessary actions
     * @param command
     * @return a String message in response to the command thrown and creates the corresponding
     * object/ executes the corresponding action
     * @throws UnknownWordException if the command is unknown
     * @throws IncompleteDescException if the command is not fully completed in the right format
     */
    public static String parseConversation(String command) throws UnknownWordException, IncompleteDescException {
        /*if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";*/

        assert command != null : "Command should not be null";

        if (command.equals("list")) {
            return Ui.listDisplay(TaskList.getList());
        } else if (command.trim().startsWith("find")) {

            String[] searchEngine = command.split(" ", 2);
            assert searchEngine.length == 2 : "Object to search must be specified after the 'find' command";

            if (searchEngine.length < 2) {
                return "Please specify a keyword to search after 'find'.";
            }

            String wordToSearch = searchEngine[1];

            ArrayList<Task> taskList = TaskList.getList();
            ArrayList<Task> outputList = new ArrayList<Task>();
            int length = taskList.size();
            for (int i = 0; i < length; i++) {
                Task task = taskList.get(i);
                String taskDesc = task.getDescription();
                if (taskDesc.contains(wordToSearch)) {
                    outputList.add(task);
                }
            }

            return Ui.diffListDisplay("Here are the matching tasks in your list:", outputList);

        } else if (command.startsWith("mark") || command.startsWith("unmark") ||
                command.startsWith("delete")) {
            // used the library function .startsWith() to match the prefix to mark/unmark
            // use.split("") to split up the words
            // use.parseInt(num) to extract integer from the string

            // if mark, then extract integer and mark that task of the list as done
            // if unmark then extract integer and unmark that task of the list as not done

            String[] stringList = command.split(" ");
            int taskNum = Integer.parseInt(stringList[1]); //second word is the number
            assert taskNum > 0 && taskNum < TaskList.getList().size() : "Task number should be within the correct " +
                    "range.";

            Task t = TaskList.getList().get(taskNum - 1);

            if (taskNum < 1 || taskNum > TaskList.getList().size()) {
                return "Task number is out of range. Please retry.";
            }

            if (stringList[0].equals("mark")) {
                return t.markDone();
            } else if (stringList[0].equals("unmark")){
                return t.markIncomplete();
            } else {
                TaskList.delTask(taskNum - 1);
                return Ui.taskDelDescription(taskNum, t);
            }

        } else if (checkCommandLength(command)) {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new IncompleteDescException("OH NO! Description of the task cannot be empty!\n " +
                        "Please retry with a command like this format <task type> <task>");
            } else {
                throw new UnknownWordException("Sorry, I do not know what that means :(\n" +
                        "Please try again with a proper command.");
            }
        } else if (!checkUnknownCommand(command)) {
            System.out.println("Unknown command detected: " + command);
            throw new UnknownWordException("Sorry, I do not know what that means :(\n" +
                    "Please try again with a proper command.");
        } else {
            // according to the first word, create a new specific task
            // split into two, first word is type, and the second phrase is task
            System.out.println("Command passed checkUnknownCommand: " + command);

            String[] split = command.split(" ", 2);

            String type = split[0];
            String stringTask = split[1];

            if (type.equals(Prince.TaskType.todo.toString())) {
                ToDoTask tsk = new ToDoTask(stringTask);
                TaskList.addTask(tsk);
                return Ui.taskAddDescription(tsk);

            } else if (type.equals(Prince.TaskType.deadline.toString())) {
                // before splitting further in the deadline, need to check if correct format

                try {
                    // split again after by
                    String[] splitAgain = stringTask.split(" /by ", 2);
                    assert splitAgain.length == 2 : "Deadline task should be properly formatted with '/by'";

                    String taskDes = splitAgain[0];
                    String deadline = splitAgain[1];

                    DeadlinesTask tsk = new DeadlinesTask(taskDes, deadline);
                    TaskList.addTask(tsk);
                    return Ui.taskAddDescription(tsk);
                } catch (InvalidDeadlineException e) {
                    return e.getMessage();
                }

            } else {
                // split again after from
                // split again after to
                String[] firstSplit = stringTask.split(" /from ", 2);
                String taskDes = firstSplit[0];
                String second = firstSplit[1];

                String[] secondSplit = second.split(" /to ", 2);
                assert secondSplit.length == 2 : "Event task should be properly formatted with '/from' and '/to'";
                String from = secondSplit[0];
                String to = secondSplit[1];

                EventTask tsk = new EventTask(taskDes, from, to);
                TaskList.addTask(tsk);
                return Ui.taskAddDescription(tsk);
            }
        }
    }

    /**
     * Checks if command's length is one word
     * this method is used to check whether the commands are likely incomplete
     *
     * @param cmd
     * @return true if command length is only 1
     */
    public static boolean checkCommandLength(String cmd) {
        assert cmd != null : "Command should not be null.";

        String[] split = cmd.trim().split(" ", 2);
        return split.length == 1;
    }

    /**
     * Checks if command starts with a task type
     * this method is used to check whether the commands are likely unknown commands
     *
     * @param cmd
     * @return true if command length is only 1
     */
    public static boolean checkUnknownCommand(String cmd) {
        assert cmd != null : "Command should not be null.";
        return (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event"));
    }


}
