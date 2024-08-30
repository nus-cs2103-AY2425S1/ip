package com.meow;
import java.util.ArrayList;
import java.util.List;

import com.meow.com.tasks.Task;
import com.meow.com.tasks.Todo;
import com.meow.com.tasks.Deadline;

public class TaskList {
    List<Task> tasks;
    
    // Public constructor for TaskList
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /*
     * adds task to the tasklist
     * @param Task task
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

    /*
     * adds todo task to the tasklist
     * @param String taskName
     * @return void
     */
    public String addTodoTask(String command) throws Meowception {
        if (command.trim().isEmpty()) {
            throw new Meowception("100");
            //errorMsg("Your todo task can't be blank silly goose, write something NEOW MEOW");
        }
        else {
            tasks.add(new Todo (command));
            return addedNewTaskMessage(tasks.get(tasks.size() - 1));
        }
    }

    /*
     * adds deadline task to the tasklist
     * @param String taskName
     * @param String deadline
     * @return void
     */
    public String addDeadlineTask(String command) throws Meowception {
        if (command.contains("/by ")) {
            if (command.substring(command.indexOf("/by") + 3 ).trim().isEmpty()) {
                throw new Meowception("200");
                // errorMsg("Meow meow you need to enter a deadline for your task");
                // return;
            } else {
                String taskName = command.substring(0, command.indexOf("/by ") - 1);
                if (taskName.trim().isEmpty()) {
                    throw new Meowception("100");
                    // errorMsg("Meow meow you need to enter a task name for your deadline");
                    // return;
                }
                String by = command.substring(command.indexOf("/by ") + 4);
                
                tasks.add(new Deadline(taskName, by));
                return addedNewTaskMessage(tasks.get(tasks.size() - 1));
            }
            
        } else {
           throw new Meowception("200");
        }
    }

    /*
     * adds event task to the tasklist
     * @param String taskName
     * @param String timeframe
     * @return String message to be shown to user
     */
    public String addEventTask(String command) throws Meowception {
        return "";
    }

    /*
     * marks task at specified index
     * @param int number
     * @return String message to be shown to user
     */
    public String markTask(int number) throws Meowception {
        if (tasks.get(number - 1).isDone()) {
            return "    Meow THIS TASK IS ALREADY MARKED !!! ";
        } else {
            System.out.println("ITS DONE");
            tasks.get(number - 1).setDone(true);
            String icon = "[" + tasks.get(number - 1).getCompetionChar() +"]";
            return "    Meow has marked this task as done:\n        " 
                    + icon +tasks.get(number - 1).getTaskName();
        }
    }

    /*
     * unmarks task at specified index
     * @param int index
     * @return String message to be shown to user
     */
    public String unmarkTask(int index) throws Meowception {
         // Check if the task is already unmarked and stuff...         
        if (!tasks.get(index - 1).isDone()) {
            return "    Meow THIS TASK IS ALREADY UNMARKED !!! ";
        } else {
            tasks.get(index - 1).setDone(false);
            String icon = "[" + tasks.get(index - 1).getCompetionChar() +"]";
            return "    Meow has unmarked this task as done:\n      " + icon +tasks.get(index - 1).getTaskName();
        }          
    }

    /*
     * displays the list of tasks
     * @param None
     * @return String, List to be printed
     */
    public String displayList() {
        if (tasks.isEmpty()) {
            return "    Meow has no tasks in the list hehe";
        }
        String outputList = "Meow Meow Tasks in the list:\n     ";
        for (int i = 0; i < tasks.size(); i++) {
            outputList += (i + 1) + ". " + tasks.get(i) + "\n       ";
        }
        return outputList;
    }

    /*
     * removes tasks at specified index
     * @param int index
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

    // Returns the message to be shown when a new task is added
    private String addedNewTaskMessage(Task task) {
        return "    Meow has added this task hehe:\n            " 
                + task.toString() + "\n            Neow you have " 
                + tasks.size() + " tasks in the list";
    }

    // Returns the size of the task list
    public int getSize() {
        return tasks.size();
    }

    // Returns the task at the specified index
    public Task get(int index) {
        return tasks.get(index);
    }
}
