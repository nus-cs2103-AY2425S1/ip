package serenity;

import serenity.SerenityException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task createTask(String input) throws SerenityException {
        Task t;

        if (input.startsWith("todo")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a todo cannot be empty.");
            } else {
                //remove the type of task
                String taskDescription = input.split(" ", 2)[1];
                t = new Todo(taskDescription);
            }
        } else if (input.startsWith("deadline")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a deadline cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/by");
                t = new Deadline(parts[0].strip(), parts[1].strip());
            }
        } else if (input.startsWith("event")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of an event cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/from");
                String[] timings = parts[1].split("/to");
                t = new Event(parts[0].strip(), timings[0].strip(), timings[1].strip());
            }
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }

        return t;

    }

    public String addTask(Task t) {
        tasks.add(t);
        String numOfTasks = tasks.size() == 1 ? "task" : "tasks";
        return ("Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " " + numOfTasks + " in the list.");
    }

    public String deleteTask(String input) throws SerenityException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        Task t = tasks.get(index);
        tasks.remove(index);

        String numOfTasks = tasks.size() == 1 ? "task" : "tasks";
        return ("Noted. I've removed this task:\n" + t
                + "\nNow you have " + tasks.size() + " " + numOfTasks + " in the list.");
    }

    public String changeStatus(String input) throws SerenityException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        String message;

        if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task t = tasks.get(index);
            t.markAsDone();
            message = "Nice! I've marked this task as done:\n" + t;
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task t = tasks.get(index);
            t.markAsNotDone();
            message = "OK, I've marked this task as not done yet:\n" + t;
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }

        return message;

    }

    @Override
    public String toString() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            message += "\n" + index + ". " + tasks.get(i);
        }
        return message;
    }

    public String toDataFormat() {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).formatData() + "\n" ;
        }
        return data;

    }

}
