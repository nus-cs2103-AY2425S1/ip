package com.meow;
import java.util.ArrayList;
import java.util.List;

import com.meow.com.tasks.Deadline;
import com.meow.com.tasks.Event;
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
     * Updates the todo task and replaces it with new name
     * @param oldName
     * @param newName
     * @return msg
     */
    public String updateTodoTask(String oldName, String newName) throws Meowception {
        for (Task task : tasks) {
            if (task.getTaskName().equals(oldName) && task.getType().equals("Todo")) {
                task.setTaskName(newName);
                return "    Meow has updated this task hehe";
            }
        }
        throw new Meowception("404");
    }

    /**
     * Updates the deadline task and replaces it with the new data
     * @param changeType, which indicates what to change
     * @param name, the name of the task
     * @param newData, the new data to replace with
     * @throws Meowception
     * @return msg
     */
    public String updateDeadlineTask(String changeType, String name, String newData) throws Meowception {
        Task taskToUpdate = findSpecificTask(name, "deadline");
        if (taskToUpdate == null) {
            return "    Meow can't find this task uwu";
        }
        // able to type cast here because the type is already checked
        Deadline deadlineTask = (Deadline) taskToUpdate;
        if (changeType.equals("name")) {
            deadlineTask.setTaskName(newData);
            return "    Meow has updated this task hehe";
        } else if (changeType.equals("time")) {
            deadlineTask.setNewTime(newData);
            return "    Meow has updated this task hehe";
        }
        throw new Meowception("404");
    }

    /**
     * Updates the event task and repalces it with the new data
     * @param changeType, which indicates what to change
     * @param name, the name of the task
     * @param newData, the new data to replace with
     * @throws Meowception
     */
    public String updateEventTask(String changeType, String name, String newData) throws Meowception {
        Task taskToUpdate = findSpecificTask(name, "event");
        if (taskToUpdate == null) {
            return "    Meow can't find this task uwu";
        }
        // able to type cast here because the type is already checked
        Event eventTask = (Event) taskToUpdate;
        if (changeType.equals("name")) {
            eventTask.setTaskName(newData);
            return "    Meow has updated this task hehe";
        } else if (changeType.equals("from")) {
            eventTask.setNewFromTime(newData);
            return "    Meow has updated this task hehe";
        } else if (changeType.equals("to")) {
            eventTask.setNewToTime(newData);
            return "    Meow has updated this task hehe";
        }
        throw new Meowception("404");
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

    public Task findSpecificTask(String name, String type) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(name) && task.getType().equals(type)) {
                return task;
            } 
        }
        return null;
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
