package main;

import exception.CommandFoundButInvalidException;
import exception.CommandNotFoundException;
import exception.EmptyStringException;

import java.util.Scanner;

public class Hyperion {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;
    boolean isOver = false;
    // takes in a filepath for the storage
    public Hyperion(String filePath) {
        try {
            this.ui = new Ui();
            this.ui.showWelcome();
            this.storage = new Storage(filePath);
            this.allTasks = new TaskList(storage.load());
            this.ui.showList(this.allTasks.getAllTasks());
        } catch (CommandFoundButInvalidException e ) {
            System.out.print("There is an error" + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String input;

        while(!isOver) {
            System.out.println("> ");
            input = scanner.nextLine();

            try {
                Parser parser = new Parser(input, allTasks, this.storage, this.ui);
                isOver = parser.isOver();
            } catch (EmptyStringException |
                     CommandFoundButInvalidException |
                     CommandNotFoundException e) {
                ui.displayError(e.getMessage());
            }
        }

        scanner.close();
    }
    public static void main(String[] args) {
        new Hyperion("data/tasks.txt");
    }
}
