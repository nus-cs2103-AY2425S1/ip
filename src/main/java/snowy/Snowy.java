package snowy;

import java.util.Scanner;

public class Snowy {

    private TaskList tasks;

    private Storage storage;

    private boolean isRunning = true;

    private Ui ui;

    private Snowy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try{
            this.tasks = new TaskList(storage.load());
        } catch (SnowyException e) {
            this.tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printGreeting();
        while (isRunning) {
            String input = scanner.nextLine();
            String[] parsedInput = Parser.parse(input);
            String command = parsedInput[0];
            String description = parsedInput[1];

            switch (command) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    ui.printList(this.tasks);
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(description);
                         Task task = tasks.markTask(index);
                         ui.printMarkDone(task);
                    } catch (NumberFormatException | SnowyException e) {
                        ui.printIndexError();
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(description);
                        Task task = tasks.unmarkTask(index);
                        ui.printMarkIncomplete(task);
                    } catch (NumberFormatException | SnowyException e) {
                        ui.printIndexError();
                    }
                    break;

                case "todo":
                    try {
                        tasks.addToDo(description);
                    } catch (SnowyException e) {
                        ui.printTodoFormatError();
                    }
                    break;

                case "deadline":
                    try {
                        tasks.addDeadline(description);
                    } catch (SnowyException e) {
                        ui.printDeadlineFormatError();
                    }
                    break;

                case "event":
                    try {
                        tasks.addEvent(description);
                    } catch (SnowyException e) {
                        ui.printEventFormatError();
                    }
                    break;

                case "delete":
                    try {
                        int index = Integer.parseInt(description);
                        Task task = tasks.deleteTask(index);
                        ui.printDeleteTask(task);
                    } catch (NumberFormatException | SnowyException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                default:
                    ui.printUnknownCommand();
                    break;
            }
            ui.printLine();
        }
        try {
            storage.save(tasks.toSaveString());
        } catch (SnowyException e) {

        }
        ui.printEnding();
    }






    public static void main(String[] args) {
        new Snowy("data/snowy.txt").run();

    }
}
