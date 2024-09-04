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
                return tasks.deleteTask(Integer.parseInt(input.substring(7)) - 1);
            } else if (input.startsWith("todo")) {
                return tasks.addTodoTask(input);
            } else if (input.startsWith("deadline")) {
                return tasks.addDeadlineTask(input);
            } else if (input.startsWith("event")) {
                return tasks.addEventTask(input);
            } else if (input.equals("list")) {
                return tasks.listTasks();
            } else if (input.startsWith("mark")) {
                return tasks.markTask(Integer.parseInt(input.substring(5)) - 1);
            } else if (input.startsWith("unmark")) {
                return tasks.unmarkTask(Integer.parseInt(input.substring(7)) - 1);
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
