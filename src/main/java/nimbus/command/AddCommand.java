package nimbus.command;

import nimbus.exception.*;
import nimbus.task.DeadlineTask;
import nimbus.task.EventTask;
import nimbus.task.Task;
import nimbus.task.TodoTask;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.util.ArrayList;

/**
 * AddCommand class is a subclass of Command
 * It is able to create tasks and add them into the task list
 */

public class AddCommand extends Command {

    private ArrayList<Task> tasks;
    private TaskList taskList;
    public String taskDescription;

    /**
     * Creates an AddCommand Object where taskInput is the task description
     *
     * @param userInput user input
     * @param taskList taskList object that is passed in
     */

    public AddCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = userInput;
        this.taskList = taskList;
        this.tasks = taskList.getTaskList();
    }

    /**
     * Adds a task into the taskList in this file,
     * Able to add TodoTask, DeadlineTask and EventTask
     *
     * @throws WrongInputException if task description does not fit\
     * @throws MissingDescriptionException if task is missing description
     * @throws MissingDeadlineException if userInput did not include deadline
     * @throws MissingStartEndTimeException if there is either missing start or end time for events
     * @throws WrongDateTimeFormatException if dateTime format is wrong in userInput
     */

    @Override
    public void execute() {
        try {
            if (!taskDescription.startsWith("todo") &&
                    !taskDescription.startsWith("deadline") &&
                    !taskDescription.startsWith("event")) {
                throw new WrongInputException();
            }
            // here handle the different task types
            if (taskDescription.startsWith("todo")) {
                String temp = taskDescription.substring(4).trim();
                if (temp.isEmpty()) { throw new MissingDescriptionException("todo"); }

                String taskName = taskDescription.substring(5);
                taskList.add(new TodoTask(taskName));
            } else if (taskDescription.startsWith("deadline")) {
                String temp = taskDescription.substring(8).trim();
                if (temp.isEmpty()) { throw new MissingDescriptionException("deadline"); }
                if (!temp.contains("/by")) { throw new MissingDeadlineException(); }


                int byIndex = taskDescription.indexOf("/by");
                String taskName = taskDescription.substring(9, byIndex);
                String deadline = taskDescription.substring(byIndex + 4);

                taskList.add(new DeadlineTask(taskName, deadline));
            } else if (taskDescription.startsWith("event")) {
                String temp = taskDescription.substring(5).trim();
                if (temp.isEmpty()) { throw new MissingDescriptionException("event"); }
                if (!temp.contains("/from")) { throw new MissingStartEndTimeException("/from"); }
                if (!temp.contains("/to")) { throw new MissingStartEndTimeException("/to"); }

                int fromIndex = taskDescription.indexOf("/from");
                int toIndex = taskDescription.indexOf("/to");
                String taskName = taskDescription.substring(6, fromIndex).trim();
                String startTime = taskDescription.substring(fromIndex + 5, toIndex).trim();
                String endTime = taskDescription.substring(toIndex + 3).trim();

                taskList.add(new EventTask(taskName, startTime, endTime));
            } else {
                System.out.println("Please use keywords: todo, deadline or event");
                return;
            }
            System.out.println("Nimbus.Nimbus added this: \n" + tasks.get(tasks.size() - 1).toString() +
                    "\n" + "Nimbus.Nimbus says you have " + tasks.size() + " tasks in your list!" + Ui.horizontalLine);
        } catch (WrongInputException e) {
            System.out.println(e.toString());
        } catch (MissingDescriptionException u) {
            System.out.println(u.toString());
        } catch (MissingDeadlineException d) {
            System.out.println(d.toString());
        } catch (MissingStartEndTimeException a) {
            System.out.println(a.toString());
        } catch (WrongDateTimeFormatException n) {
            System.out.println(n.toString());
        }
    }
}
