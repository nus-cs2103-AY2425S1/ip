package snowy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chatbot with the ability to keep track of tasks.
 */
public class Snowy {

    private TaskList tasks;

    private Storage storage;

    private boolean isRunning = true;

    private Ui ui;

    public Snowy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (SnowyException e) {
            this.tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

//    private void run() {
//        Scanner scanner = new Scanner(System.in);
//        ui.printGreeting();
//        while (isRunning) {
//            String input = scanner.nextLine();
//            String[] parsedInput = Parser.parse(input);
//            String command = parsedInput[0];
//            String description = parsedInput[1];
//
//            switch (command) {
//            case "bye":
//                isRunning = false;
//                break;
//
//            case "list":
//                ui.printList(this.tasks);
//                break;
//
//            case "mark":
//                try {
//                    int index = Integer.parseInt(description);
//                    Task task = tasks.markTask(index);
//                    ui.printMarkDone(task);
//                } catch (NumberFormatException | SnowyException e) {
//                    ui.printIndexError();
//                }
//                break;
//
//            case "unmark":
//                try {
//                    int index = Integer.parseInt(description);
//                    Task task = tasks.unmarkTask(index);
//                    ui.printMarkIncomplete(task);
//                } catch (NumberFormatException | SnowyException e) {
//                    ui.printIndexError();
//                }
//                break;
//
//            case "todo":
//                try {
//                    tasks.addToDo(description);
//                } catch (SnowyException e) {
//                    ui.printTodoFormatError();
//                }
//                break;
//
//            case "deadline":
//                try {
//                    tasks.addDeadline(description);
//                } catch (SnowyException e) {
//                    ui.printDeadlineFormatError();
//                }
//                break;
//
//            case "event":
//                try {
//                    tasks.addEvent(description);
//                } catch (SnowyException e) {
//                    ui.printEventFormatError();
//                }
//                break;
//
//            case "delete":
//                try {
//                    int index = Integer.parseInt(description);
//                    Task task = tasks.deleteTask(index);
//                    ui.printDeleteTask(task);
//                } catch (NumberFormatException | SnowyException e) {
//                    System.out.println("Invalid index format. Please try again");
//                }
//                break;
//
//            case "find":
//
//                ArrayList<Task> foundTasks = tasks.findTask(description);
//                ui.printFoundTask(foundTasks);
//                break;
//
//            default:
//                ui.printUnknownCommand();
//                break;
//
//            }
//            ui.printLine();
//        }
//        try {
//            storage.save(tasks.toSaveString());
//        } catch (SnowyException e) {
//            ui.printUpdateError();
//        }
//        ui.printEnding();
//    }

    public String getResponse(String input) {
        String[] parsedInput = Parser.parse(input);
        assert parsedInput.length == 2: "Parsed length should be 2";
        String command = parsedInput[0];
        String description = parsedInput[1];
        String result = "";
        Task newTask;
        assert command != null : "Command should not be null";
        switch (command) {
        case "bye":
            isRunning = false;
            result = ui.printEnding();
            break;

        case "list":
            result = ui.printList(this.tasks);
            break;

        case "mark":
            try {
                int index = Integer.parseInt(description);
                Task task = tasks.markTask(index);
                assert task != null : "task should not be null";
                result = ui.printMarkDone(task);
            } catch (NumberFormatException | SnowyException e) {
                result = ui.printIndexError();
            }
            break;

        case "unmark":
            try {
                int index = Integer.parseInt(description);
                Task task = tasks.unmarkTask(index);
                assert task != null : "task should not be null";
                result = ui.printMarkIncomplete(task);
            } catch (NumberFormatException | SnowyException e) {
                result = ui.printIndexError();
            }
            break;

        case "todo":
            try {
                newTask = tasks.addToDo(description);
                assert newTask != null : "task should not be null";
                result = "New todo task added:\n" + newTask;
            } catch (SnowyException e) {
                result = ui.printTodoFormatError();
            }
            break;

        case "deadline":
            try {
                newTask = tasks.addDeadline(description);
                assert newTask != null : "task should not be null";
                System.out.println("New Deadline task added:\n" + newTask);
            } catch (SnowyException e) {
                result = ui.printDeadlineFormatError();
            }
            break;

        case "event":
            try {
                newTask = tasks.addEvent(description);
                assert newTask != null : "task should not be null";
                System.out.println("New Event task added:\n " + newTask);
            } catch (SnowyException e) {
                result = ui.printEventFormatError();
            }
            break;

        case "delete":
            try {
                int index = Integer.parseInt(description);
                Task task = tasks.deleteTask(index);
                assert task != null : "task should not be null";
                result = ui.printDeleteTask(task);
            } catch (NumberFormatException | SnowyException e) {
                result = "Invalid index format. Please try again";
            }
            break;

        case "find":

            ArrayList<Task> foundTasks = tasks.findTask(description);
            result = ui.printFoundTask(foundTasks);
            break;

        default:
            result = ui.printUnknownCommand();
            break;
        }

        try {
            storage.save(tasks.toSaveString());
        } catch (SnowyException e) {
            result = ui.printUpdateError();
        }
        return result;
    }
}


