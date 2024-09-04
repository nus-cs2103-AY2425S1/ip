package maga;

import maga.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller;

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
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
    }

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

    public void close() {
        System.out.println("Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!");
    }
}