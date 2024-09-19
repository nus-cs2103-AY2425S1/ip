package com.meow;
import java.util.ArrayList;
import java.util.List;

import com.meow.com.tasks.Task;


/**
 * Acts as a abstract data structure for storing active / unactive tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Public constructor for creating task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * adds task to the tasklist
     * @param task
     * @return void
     */
    public String addTask(Task task) throws Meowception {
        try {
            tasks.add(task);
            return addedNewTaskMessage(tasks.get(tasks.size() - 1));
        } catch (Exception e) {
            throw new Meowception("");
        }
    }

    /**
     * marks task at specified index
     * @param number
     * @return String message to be shown to user
     */
    public String markTask(int number) throws Meowception {
        if (tasks.get(number - 1).isDone()) {
            return "    Meow THIS TASK IS ALREADY MARKED !!! ";
        } else {
            System.out.println("ITS DONE");
            tasks.get(number - 1).setDone(true);
            String icon = "[" + tasks.get(number - 1).getCompletionChar() + "]";
            return "    Meow has marked this task as done:\n        " 
                    + icon + tasks.get(number - 1).getTaskName();
        }
    }

    /**
     * Unmarks task at specified index
     * @param index
     * @return String message to be shown to user
     */
    public String unmarkTask(int index) throws Meowception {
        if (!tasks.get(index - 1).isDone()) {
            return "    Meow THIS TASK IS ALREADY UNMARKED !!! ";
        } else {
            tasks.get(index - 1).setDone(false);
            String icon = "[" + tasks.get(index - 1).getCompletionChar() + "]";
            return "    Meow has unmarked this task as done:\n      " + icon + tasks.get(index - 1).getTaskName();
        }          
    }

    /**
     * displays the list of tasks
     * @return String, List to be printed
     */
    public String displayList() {
        if (tasks.isEmpty()) {
            return "    Meow has no tasks in the list hehe";
        }
        String outputList = "Meow Meow Tasks in the list:\n       ";
        for (int i = 0; i < tasks.size(); i++) {
            outputList += (i + 1) + ". " + tasks.get(i) + "\n       ";
        }
        return outputList;
    }

    /**
     * removes tasks at specified index
     * @param index
     * @return void
     */
    public String deleteTask(int index) throws Meowception {
        if (index < 1 || index > tasks.size()) {
            throw new Meowception("404");
        } else {
            tasks.remove(index - 1);
            return "    Meow has removed this task hehe";
        }
    }

    /**
     * Find tasks with the specified keyword
     * Searches all tasks and returns if contains it
     * @param keyword
     * @return TaskList
     */
    public TaskList findTasks(String keyword) throws Meowception {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the message to be shown when a new task is added
     * @Param task object
     * @return String message to be shown
     */

    private String addedNewTaskMessage(Task task) {
        return "    Meow has added this task hehe:\n            "
                + task.toString() + "\n            Neow you have "
                + tasks.size() + " tasks in the list";
    }

    /**
     * Returns the current size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Grabs the task at the specified index
     * @param index
     * @return
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
