package Tools;

import Exception.EmptyDescriptionException;
import Exception.MissingDateException;

public class NewParser {

    NewTaskList tasks;

    NewStorage storage;


    public NewParser(NewTaskList tasks, NewStorage storage) {
        this.tasks = tasks;
        this.storage = storage;

    }

    public String parse(String input) {
        try {
            if (input.equals("bye")) {
                storage.saveTasks(tasks);
                return "Bye. Hope to see you again soon!";
            } else if (input.startsWith("delete")) {
                String s = tasks.deleteTask(Integer.parseInt(input.substring(7)) - 1);
                storage.saveTasks(tasks);
                return s;
            } else if (input.startsWith("update")) {
                String command_message = tasks.updateTask(input);
                storage.saveTasks(tasks);
                return command_message;
            } else if (input.startsWith("todo")) {
                String s = tasks.addTodoTask(input);
                storage.saveTasks(tasks);
                return s;
            } else if (input.startsWith("deadline")) {
                String s = tasks.addDeadlineTask(input);
                storage.saveTasks(tasks);
                return s;
            } else if (input.startsWith("event")) {
                String s = tasks.addEventTask(input);
                storage.saveTasks(tasks);
                return s;
            } else if (input.equals("list")) {
                String s = tasks.listTasks();
                storage.saveTasks(tasks);
                return s;
            } else if (input.startsWith("mark")) {
                String s = tasks.markTask(Integer.parseInt(input.substring(5)) - 1);
                storage.saveTasks(tasks);
                return s;
            } else if (input.startsWith("unmark")) {
                String s = tasks.unmarkTask(Integer.parseInt(input.substring(5)) - 1);
                storage.saveTasks(tasks);
                return s;
            } else if (input.equalsIgnoreCase("exit")) {
                return"Bye. Hope to see you again soon!";
            } else if (input.startsWith("find")) {
                String search = input.substring(input.indexOf(" ") + 1);
                tasks.findTask(search);
            } else {
                return"OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

        } catch (EmptyDescriptionException | MissingDateException e) {
            return e.getMessage();
        }

        return "can't recognize the command";
    }
}
