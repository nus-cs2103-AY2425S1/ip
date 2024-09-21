package Tools;

import Exception.EmptyDescriptionException;
import Exception.MissingDateException;

/**
 * abolished Parser for handling and routing commands to manage task operations.
 * this one is used for Chatgpt without ui page
 */
public class Parser {

    TaskList tasks;

    Storage storage;

    Ui ui;

    /**
     * Constructs a new parser with associated task list and storage.
     *
     * @param tasks The task list to be managed and manipulated.
     * @param storage The storage system to save task changes.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses and processes user input commands to perform task operations.
     *
     * @param input The user input command.
     * @throws EmptyDescriptionException if a task description is missing.
     * @throws MissingDateException if a required date for a task is missing.
     */
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
            } else if (input.startsWith("find")) {
                String search = input.substring(input.indexOf(" ") + 1);
                tasks.findTask(search);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (EmptyDescriptionException | MissingDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
