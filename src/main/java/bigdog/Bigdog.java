package bigdog;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Bigdog {

    // Array to store tasks
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bigdog(String file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList(this.storage.load());
        this.ui = new Ui();
    }

    public void run() {

        ui.greet();
        boolean toContinue = true;


        while (toContinue) {
            try {

                    String userInput = ui.readInput();
                    String[] commands = Parser.parse(userInput);
                    switch (commands[0]) {
                        case "bye":
                            toContinue = false;
                            ui.bye();
                            break;
                        case "list":
                            this.tasks.show();
                            break;
                        case "mark":
                            ui.print(this.tasks.mark(Integer.parseInt(commands[1])));
                            break;
                        case "unmark":
                            ui.print(this.tasks.unmark(Integer.parseInt(commands[1])));
                            break;
                        case "delete":
                            ui.print(this.tasks.delete(Integer.parseInt(commands[1])));
                            break;
                        case "todo":
                            ui.print(this.tasks.add(Todo.of(commands[1])));
                            break;
                        case "deadline":
                            ui.print(this.tasks.add(Deadline.of(commands[1])));
                            break;
                        case "event":
                            ui.print(this.tasks.add(Event.of(commands[1])));
                            break;

                }
            } catch (BigdogException |
                     DateTimeParseException |
                     NumberFormatException |
                     IndexOutOfBoundsException e) {
                ui.print(e.getMessage());
            } finally {
                storage.save(this.tasks.get());
            }
        }
    }

    public static void main(String[] args) {

        new Bigdog("./src/main/Bigdog.txt").run();

    }

}
