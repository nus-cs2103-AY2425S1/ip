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



public class Parser {

    public static String parseConversation(String command) throws UnknownWordException, IncompleteDescException {
        /*if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";*/
        if(command.equals("list")) {
            return Ui.listDisplay(TaskList.getList());
        } else if(command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            // used the library function .startsWith() to match the prefix to mark/unmark
            // use.split("") to split up the words
            // use.parseInt(num) to extract integer from the string

            // if mark, then extract integer and mark that task of the list as done
            // if unmark then extract integer and unmark that task of the list as not done

            String[] stringList = command.split(" ");
            int taskNum = Integer.parseInt(stringList[1]); //second word is the number
            Task t = TaskList.getList().get(taskNum - 1);

            if (taskNum < 1 || taskNum > TaskList.getList().size()) {
                return "task.Task number is out of range. Please retry.";
            }

            if(stringList[0].equals("mark")) {
                return t.markDone();
            } else if(stringList[0].equals("unmark")){
                return t.markIncomplete();
            } else {
                TaskList.delTask(taskNum);
                return Ui.taskDelDescription(taskNum, t);
            }

        } else if(checkCommandLength(command)) {
            if(command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new IncompleteDescException("OH NO! Description of the task cannot be empty!\n " +
                        "Please retry with a command like this format <task type> <task>");
            } else {
                throw new UnknownWordException("Sorry, I do not know what that means :(\n " +
                        "Please try again with a proper command.");
            }
        } else {
            // according to the first word, create a new specific task
            // split into two, first word is type, and the second phrase is task

            String[] split = command.split(" ", 2);

            String type = split[0];
            String stringTask = split[1];

            if(type.equals(Prince.TaskType.todo.toString())) {
                ToDoTask tsk = new ToDoTask(stringTask);
                TaskList.addTask(tsk);
                return Ui.taskAddDescription(tsk);

            } else if (type.equals(Prince.TaskType.deadline.toString())) {
                // before splitting further in the deadline, need to check if correct format

                try {
                    // split again after by
                    String[] splitAgain = stringTask.split(" /by ", 2);
                    String taskDes = splitAgain[0];
                    String deadline = splitAgain[1];

                    DeadlinesTask tsk = new DeadlinesTask(taskDes, deadline);
                    TaskList.addTask(tsk);
                    return Ui.taskAddDescription(tsk);
                } catch(InvalidDeadlineException e) {
                    return e.getMessage();
                }

            } else {
                // split again after from
                // split again after to
                String[] firstSplit = stringTask.split(" /from ", 2);
                String taskDes = firstSplit[0];
                String second = firstSplit[1];

                String[] secondSplit = second.split(" /to ", 2);
                String from = secondSplit[0];
                String to = secondSplit[1];

                EventTask tsk = new EventTask(taskDes, from, to);
                TaskList.addTask(tsk);
                return Ui.taskAddDescription(tsk);
            }
        }
    }

    public static boolean checkCommandLength(String command) {
        String[] split = command.split(" ");
        return split.length == 1;
    }

}
