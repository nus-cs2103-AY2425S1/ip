package maga;

import java.util.Scanner;

import maga.task.TaskList;

/**
 * The {@code Ui} class represents the user interface for interacting with the Maga bot.
 * It handles user input, displays messages, and delegates command handling to the {@code Controller}.
 * The class also provides greeting and closing messages in line with the bot's persona.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller;

    /**
     * Constructs a new {@code Ui} instance with the specified {@code TaskList}.
     * The {@code Ui} initializes a {@code Controller} to manage the interaction between
     * the user and the task list.
     *
     * @param taskList The task list that the {@code Controller} will manage.
     */
    public Ui(TaskList taskList) {
        controller = new Controller(scanner, taskList);
    }

    private void printGreeting() {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo + "\nI am THE best chatbot from the one and only"
                + " US of A trust me everyone says I'm the best. How can I help you serve the American people?");
    }

    /**
     * Starts the user interface by displaying a greeting message and
     * waiting for user input in a loop. The input is processed by the {@code Controller}
     * until an exit command is issued.
     */
    public void start() {
        printGreeting();
        String input = scanner.nextLine();
        while (!controller.isExitCommand(input)) {
            String message = controller.handleInput(input);
            if (message != null) {
                System.out.println(message);
            }

            input = scanner.nextLine();
        }

    }

    /**
     * Closes the user interface by printing a farewell message.
     * This method is called when the bot is about to shut down.
     */
    public void close() {
        System.out.println("Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!");
    }
}
