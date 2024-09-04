package maga;

import maga.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final Parser parser;

    public Ui(TaskList taskList) {
        parser =  new Parser(taskList, scanner);
    }

    public void start() {
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            parser.handleInput(input);
            input = scanner.nextLine();
        }
    }
}