//import java.util.Scanner;
//import java.util.ArrayList;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//import Bob.Storage.Storage;
//import Bob.Tasks.Deadline;
//import Bob.Tasks.Event;
//import Bob.Tasks.Task;
//import Bob.Tasks.Todo;
//import Bob.Exception.BobException;
//
//public class Bob {
//    private static ArrayList<Task> tasks;
//    private static Storage storage;
//
//    public static void main(String[] args) throws BobException {
//        storage = new Storage("./data/bob.csv");
//
//        tasks = storage.load();
//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//        String logo = " ____        ____\n"
//                + "| __ )  ___ | __ )\n"
//                + "|  _ \\ / _ \\|  _ \\\n"
//                + "| |_) | (_) | |_) |\n"
//                + "|____/ \\___/|____/\n";
//
//        printLine();
//        System.out.println("Hello! I'm Bob!\nHow can I help you today?");
//        System.out.println(logo);
//        printLine();;
//
//        while (!input.equals("bye")) {
//            input = scanner.nextLine().trim(); // trim to remove any whitespace before input
//            String[] inputParts = input.split(" ", 2); // split input and store into array
//            String command = inputParts[0].toLowerCase(); // uniform lowercase for comparison
//            String taskDescription = (inputParts.length <= 1) ? "" : inputParts[1];
//
//            try {
//                switch (command) {
//                    case "list":
//                        listTasks();
//                        break;
//                    case "mark":
//                        markTask(taskDescription);
//                        break;
//                    case "unmark":
//                        unmarkTask(taskDescription);
//                        break;
//                    case "todo":
//                        addTodoTask(taskDescription);
//                        break;
//                    case "deadline":
//                        addDeadlineTask(taskDescription);
//                        break;
//                    case "event":
//                        addEventTask(taskDescription);
//                        break;
//                    case "delete":
//                        deleteTask(taskDescription);
//                        break;
//                    case "bye":
//                        // exit
//                        printLine();
//                        System.out.println("Bye, see you again :)");
//                        printLine();
//                        scanner.close();
//                        return;
//                    default:
//                        throw new BobException("Bob does not understand that command, sorry :(");
//                }
//            } catch (BobException e) {
//                printLine();
//                System.out.println(e.getMessage());
//                printLine();
//            }
//        }
//    }
//
//    // handle commands
//
//    private static void listTasks() {
//        printLine();
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + ". " + tasks.get(i).toString());
//        }
//        printLine();
//    }
//
//    private static void markTask(String taskDescription) throws BobException {
//        int taskIndexMark = Integer.parseInt(taskDescription) - 1;
//        if (taskIndexMark < tasks.size() && taskIndexMark >= 0) {
//            tasks.get(taskIndexMark).mark();
//            storage.save(tasks);
//            printLine();
//            System.out.println("Yay! I've marked this task as done:");
//            System.out.println(tasks.get(taskIndexMark).toString());
//            printLine();
//        } else {
//            throw new BobException("Invalid index :(");
//        }
//    }
//
//    private static void unmarkTask(String taskDescription) throws BobException {
//        int taskIndexUnmark = Integer.parseInt(taskDescription) - 1;
//        if (taskIndexUnmark < tasks.size() && taskIndexUnmark >= 0) {
//            tasks.get(taskIndexUnmark).unmark();
//            storage.save(tasks);
//            printLine();
//            System.out.println("Alright, I've marked this task as not done yet:");
//            System.out.println(tasks.get(taskIndexUnmark).toString());
//            printLine();
//        } else {
//            throw new BobException("Invalid index :(");
//        }
//    }
//
//    private static void addTodoTask(String taskDescription) throws BobException {
//        if (taskDescription.isEmpty()) {
//            throw new BobException("Description of the todo cannot be empty :(");
//        }
//        tasks.add(new Todo(taskDescription));
//        storage.save(tasks);
//        printLine();
//        System.out.println("Got it. I've added this task:");
//        System.out.println(tasks.get(tasks.size() - 1).toString());
//        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
//        printLine();
//    }
//
//    private static void addDeadlineTask(String taskDescription) throws BobException {
//        if (taskDescription.isEmpty()) {
//            throw new BobException("Missing deadline description :(");
//        }
//        String[] dlParts = taskDescription.split(" /by ");
//        if (dlParts.length < 2) {
//            throw new BobException("Missing details :(\nPlease use this format: 'deadline [description] /by [dd/MM/yyyy HHmm]' ");
//        }
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
//            LocalDateTime deadlineDateTime = LocalDateTime.parse(dlParts[1], formatter);
//
//            tasks.add(new Deadline(dlParts[0], deadlineDateTime));
//            storage.save(tasks);
//            System.out.println("------------------------------------------");
//            System.out.println("Ok! I've added this task:");
//            System.out.println(tasks.get(tasks.size() - 1).toString());
//            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
//            System.out.println("------------------------------------------");
//        } catch (DateTimeParseException e) {
//            throw new BobException("Invalid date format! Please use dd/MM/yyyy HHmm :(");
//        }
//    }
//
//    private static void addEventTask(String taskDescription) throws BobException {
//        if (taskDescription.isEmpty()) {
//            throw new BobException("Description of the event is missing :(");
//        } else if (!taskDescription.contains(" /from ") || !taskDescription.contains(" /to ")) {
//            throw new BobException("Missing details :(\nPlease use this format: event [description] /from [start] /to [end]");
//        }
//        String[] eventParts = taskDescription.split(" /from | /to ");
//        tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
//        storage.save(tasks);
//        printLine();
//        System.out.println("Ok! I've added this task:");
//        System.out.println(tasks.get(tasks.size() - 1).toString());
//        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
//        printLine();
//    }
//
//    private static void deleteTask(String taskDescription) throws BobException {
//        int taskIndexDelete = Integer.parseInt(taskDescription) - 1;
//        if (taskIndexDelete < tasks.size() && taskIndexDelete >= 0) {
//            Task removedTask = tasks.remove(taskIndexDelete);
//            storage.save(tasks);
//            printLine();
//            System.out.println("Noted. I've removed this task:");
//            System.out.println("  " + removedTask.toString());
//            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
//            printLine();
//        } else {
//            throw new BobException("Invalid index :(");
//        }
//    }
//
//    private static void printLine() {
//        System.out.println("------------------------------------------");
//    }
//
//}
//
//
package Bob;

import Bob.Storage.Storage;
import Bob.TaskList.TaskList;
import Bob.Ui.Ui;
import Bob.Parser.Parser;
import Bob.Command.Command;
import Bob.Exception.BobException;

import java.util.Scanner;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine().trim();
            try {
                Command command = parser.parse(input);
                command.execute(tasks.getTasks(), storage, ui);
            } catch (BobException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        new Bob("./data/bob.csv").run();
    }
}
