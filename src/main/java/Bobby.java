import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    private final Scanner input;
    private ArrayList<Task> tasks;
    private boolean isRunning;
    private static final String FILE_PATH = "tasks.txt";

    public Bobby() {
        this.input = new Scanner(System.in);
        this.isRunning = true;
        this.tasks = new ArrayList<>();

        try {
            Scanner fileScanner = Bobby.getFile();
            while (fileScanner.hasNextLine()) {
                // I don't really like this nested try block
                try {
                    Task newTask = Bobby.parseTaskString(fileScanner.nextLine());
                    this.tasks.add(newTask);
                } catch (IOException e) {
                    System.out.println("File format corrupted");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }


    public String getInput() {
        return this.input.nextLine();
    }

    public void printAddSuccess(Task newTask) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void printDeleteSuccess(Task task) {
        System.out.println("Got it. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void processInput(String s) throws InputException {
        String[] inputArr = s.split(" ", 2);
        String command = inputArr[0];
        switch (command) {
        case "bye" -> {
            this.isRunning = false;
            System.out.println("Bye. Hope to see you again soon!");
        }
        case "list" -> {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        }
        case "mark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (idx >= this.tasks.size() || idx < 0) {
                throw new InvalidIndexException();
            }
            this.tasks.get(idx).setIsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(this.tasks.get(idx));
        }
        case "unmark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (idx >= this.tasks.size() || idx < 0) {
                throw new InvalidIndexException();
            }
            this.tasks.get(idx).setIsDone(false);
            System.out.println("Ok, I've marked this task as not done yet: ");
            System.out.println(this.tasks.get(idx));
        }
        case "delete" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (idx >= this.tasks.size() || idx < 0) {
                throw new InvalidIndexException();
            }
            Task removedTask = this.tasks.remove(idx);
            this.printDeleteSuccess(removedTask);
        }
        case "todo" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            Task newTask = new Todo(inputArr[1]);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        }
        case "deadline" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/by ")) {
                throw new MissingDeadlineByException();
            }
            String[] args = inputArr[1].split(" /by ", 2);
            if (args.length <= 1) {
                throw new DeadlineArgsException();
            }
            String name = args[0];
            String deadline = args[1];
            if (name.trim().isEmpty() || deadline.trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            Task newTask = new Deadline(name, deadline);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        }
        case "event" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/from ")) {
                throw new MissingEventFromException();
            }
            if (!inputArr[1].contains(" /to")) {
                throw new MissingEventToException();
            }
            String[] args = inputArr[1].split(" /from ", 2);
            if (args.length <= 1) {
                throw new EventArgsException();
            }
            String name = args[0];
            String[] fromTo = args[1].split(" /to ", 2);
            if (fromTo.length <= 1) {
                throw new EventArgsException();
            }
            String from = fromTo[0];
            String to = fromTo[1];
            if (name.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            Task newTask = new Event(name, from, to);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        }
        default -> throw new InvalidCommandException();
        }
    }

    public void sayHi() {
        System.out.println("Hello, I'm Bobby");
        System.out.println("What can I do for you?");
    }

    private static Scanner getFile() throws FileNotFoundException {
        File taskListFile = new File(FILE_PATH);
        try {
            taskListFile.createNewFile();
        } catch (IOException e) {
            System.out.println("IO Error in creating new file: " + e.getMessage());
        }
        return new Scanner(taskListFile);
    }

    private static Task parseTaskString(String taskString) throws IOException {
        // TODO error handling for if data file is corrupted
        String[] tokens = taskString.split("\\|");
        boolean isDone = Integer.parseInt(tokens[1]) == 1;
        String title = tokens[2];
        return switch (tokens[0]) {
            case "T" -> new Todo(title, isDone);
            case "D" -> new Deadline(title, tokens[3], isDone);
            case "E" -> new Event(title, tokens[3], tokens[4], isDone);
            default -> throw new IOException();
        };
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.sayHi();
        while (chatBot.isRunning) {
            try {
                chatBot.processInput(chatBot.getInput());
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
