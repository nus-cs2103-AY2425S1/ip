package milo.ui;

import milo.tasks.Task;
import milo.lists.TaskList;

/**
 * Milo Ui system, specifically for tasks
 * sets how Milo interacts with user
 * as well as passing on user input to Parser
 */
public class TaskUi extends Ui{
    /**
     * Milo prints all tasks in the array list
     *
     * @param todoList list of tasks
     */
    public String printTaskList(TaskList todoList) {
        assert todoList != null;
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < todoList.getNumberOfTasks(); i++) {
            response.append(i + 1).append(".").append(todoList.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Milo prints feedback message on marking task as done
     *
     * @param curTask object that system marked as done
     */
    public String printMark(Task curTask) {
        assert curTask != null;
        return "Nice! I've marked this task as done:\n" +
                "  " + curTask + "\n";
    }

    /**
     * Milo prints feedback message on marking task as not done
     *
     * @param curTask object that system marked as not done
     */
    public String printUnmark(Task curTask) {
        assert curTask != null;
        return "Ok, I've marked this as not done yet:\n" +
                "  " + curTask + "\n";
    }

    /**
     * Milo prints feedback message on deleting task
     *
     * @param curTask object that system deleted
     * @param numberOfTasks left in the todo list
     */
    public String printDelete(Task curTask, int numberOfTasks) {
        assert curTask != null;
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:\n");
        response.append("  ").append(curTask).append("\n");
        if (numberOfTasks == 1) {
            response.append("Now you have ").append(numberOfTasks).append(" task in the list.\n");
        } else {
            response.append("Now you have ").append(numberOfTasks).append(" tasks in the list.\n");
        }
        return response.toString();
    }

    /**
     * Milo prints task details when added
     *
     * @param curTask object added to the list
     * @param numberOfTasks left in todo list
     */
    public String printAddTask(Task curTask, int numberOfTasks) {
        assert curTask != null;
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("  ").append(curTask).append("\n");
        if (numberOfTasks == 1) {
            response.append("Now you have ").append(numberOfTasks).append(" task in the list.\n");
        } else {
            response.append("Now you have ").append(numberOfTasks).append(" tasks in the list.\n");
        }
        return response.toString();
    }

    /**
     * Milo prints matching tasks to the keyword input
     *
     * @param todoList list of matching tasks
     * @param tasksFound number of matching tasks found
     */
    public String printFoundTask(TaskList todoList, int tasksFound) {
        assert todoList.getNumberOfTasks() > 0;
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksFound; i++) {
            response.append(i + 1).append(".").append(todoList.get(i).toString()).append("\n");
        }
        return response.toString();
    }
}
