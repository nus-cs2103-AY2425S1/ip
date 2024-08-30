package Tools;

import Exception.EmptyDescriptionException;
import Exception.MissingDateException;

public class Parser {

    TaskList tasks;

    Storage storage;

    Ui ui;

    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void parse(String input) {
        try {
            if (input.equals("bye")) {
                storage.saveTasks(tasks);
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.startsWith("delete")) {
                tasks.deleteTask(Integer.parseInt(input.substring(7)) - 1);
            } else if (input.startsWith("todo")) {
                tasks.addTodoTask(input);
            } else if (input.startsWith("deadline")) {
                tasks.addDeadlineTask(input);
            } else if (input.startsWith("event")) {
                tasks.addEventTask(input);
            } else if (input.equals("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark")) {
                tasks.markTask(Integer.parseInt(input.substring(5)) - 1);
            } else if (input.startsWith("unmark")) {
                tasks.unmarkTask(Integer.parseInt(input.substring(7)) - 1);
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (EmptyDescriptionException | MissingDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
