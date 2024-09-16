package Bwead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles input commands from the system and parses it using Parsers,
 * then provides outputs back to the user.
 */
public class Ui {

    private Scanner scanner;
    private TaskList taskList;
    private History history;

    /**
     * sets a reference to History and TaskList.
     *
     * @param taskList taskList with all the current tasks.
     * @param history handles saving the tasks locally.
     */
    public void set(History history, TaskList taskList) {
        this.history = history;
        this.taskList = taskList;
    }

    /**
     * Prints the error message when loading.
     *
     * @param msg error message.
     */
    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    /**
     * Handles the command inputs by parsing it using a Parser.
     *
     * @param input string command.
     * @return String output to be returned.
     * @throws IOException when a failure occurs while performing scanning or write operations.
     */
    public String handleCommands(String input) throws IOException {

        Parser parser = new Parser(input);

        try {
            if (parser.isOneWord()) {
                if (input.equals("todo")) {
                    throw new BweadException("OOPS!!! The description of a todo cannot be empty.");
                } else if (input.equals("deadline")) {
                    throw new BweadException("OOPS!!! The description of a deadline cannot be empty.");
                } else if (input.equals("event")) {
                    throw new BweadException("OOPS!!! The description of an event cannot be empty.");
                } else if (!input.equals("bye") && !input.equals("list")) {
                    throw new BweadException("i don't know what that means :(");
                }
            } else {
                if (parser.isInvalidMultiWord()) {
                    throw new BweadException("i don't know what that means");
                }
            }
        } catch (BweadException e) {
            return e.getMessage();
        }

        try {
            if (input.equals("bye")) {
                return ("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                return taskList.printlist();
            } else if (input.startsWith("mark")) {
                int toadd = parser.getTaskToMark();
                Task task = taskList.getCurrentList().get(toadd - 1);
                task.setDone(true);
                history.updateFile(taskList.getCurrentList());
                return ("Nice! I've marked this task as done: " + task.getText());
            } else if (input.startsWith("unmark")) {
                int toadd = parser.getTaskToMark();
                Task task = taskList.getCurrentList().get(toadd - 1);
                task.setDone(false);
                history.updateFile(taskList.getCurrentList());
                return ("OK, I've marked this task as not done yet: " + task.getText());
            } else if (input.startsWith("todo ")) {
                String todoName = parser.getTodoName();
                Todo task = new Todo(todoName);
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                assert taskList.getCurrentList().size() != 0;
                return ("Got it. I've added this task: " + task.toString() + "\n" + "Now you have "
                        + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                Deadline task = new Deadline(parser.getDeadlineName(), parser.getDeadlineDate(),
                        parser.getDeadlineTime());
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                assert taskList.getCurrentList().size() != 0;
                return ("Got it. I've added this task: " + task.toString() + "\n" + "Now you have "
                        + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                Event task = new Event(parser.getEventName(), parser.getEventStart(), parser.getEventEnd(),
                        parser.getEventStartTime(), parser.getEventEndTime());
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                assert taskList.getCurrentList().size() != 0;
                return ("Got it. I've added this task: " + task.toString() + "\n" + "Now you have "
                        + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("delete")) {
                assert taskList.getCurrentList().size() > 0;
                Task toremove = taskList.getCurrentList().get(parser.getDeleteIndex() - 1);
                taskList.getCurrentList().remove(toremove);
                history.updateFile(taskList.getCurrentList());
                return ("Noted. I've removed this task: " + toremove.toString() + "\n" + "Now you have "
                        + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("find")) {
                String keyword = parser.getKeyword();
                ArrayList<String> matches = new ArrayList<>();
                for (int i = 0; i < taskList.getCurrentList().size(); i++) {
                    if (taskList.getCurrentList().get(i).getName().contains(keyword)) {
                        matches.add(taskList.getCurrentList().get(i).toString());
                    }
                }
                if (matches.size() == 0) {
                    throw new BweadException("no matching tasks found");
                }
                String matchingTasksString = "Here are the matching tasks in your list:" + "\n";
                for (int i = 0; i < matches.size(); i++) {
                    matchingTasksString = matchingTasksString + (i + 1) + "." + matches.get(i) + "\n";
                }
                return matchingTasksString;
            }
        } catch (BweadException e) {
            return (e.getMessage());
        }

        return "";
    }
}

