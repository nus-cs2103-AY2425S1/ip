import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Tick {
    private static final String separator = "____________________________________________________________";
    private ArrayList<Task> checklist = new ArrayList<>();
    private Storage storage;

    public Tick(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.checklist = this.storage.loadData();
        } catch (IOException e) {
            System.out.println("An error occurred while loading data from file.");
            this.checklist = new ArrayList<>();
        }
    }

    public void greet() {
        System.out.println(Tick.separator);
        System.out.println("System starting up...");
        System.out.println("Brrt brrt! I'm Tick!");
        System.out.println("What can I do for you?");
        System.out.println(Tick.separator);
    }

    public void exit() {
        System.out.println(Tick.separator);
        System.out.println("System shutting down...");
        System.out.println("Bye bye, see you next time!");
        System.out.println(Tick.separator);
    }

    public void addTaskToList(String taskType, String details) throws TickException {
        if (taskType.equalsIgnoreCase("todo")) {
            if (details.isEmpty()) {
                throw new TickException("The description of a todo cannot be empty.");
            }
            ToDo task = new ToDo(details);
            this.checklist.add(task);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            String[] parts = details.split(" /by ");
            if (parts.length < 2) {
                throw new TickException("Please specify the deadline task in this format:" +
                        " <description> /by <deadline>.");
            }
            try {
                LocalDate by = LocalDate.parse(parts[1]);
                Deadline task = new Deadline(parts[0], by);
                this.checklist.add(task);
            } catch (DateTimeException e) {
                throw new TickException("Please specify the deadline in this format: yyyy-mm-dd.");
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            String[] parts = details.split(" /from | /to ");
            if (parts.length < 3) {
                throw new TickException("Please specify the event task in this format:" +
                        " <description> /from <start> /to <end>.");
            }
            try {
                LocalDate from = LocalDate.parse(parts[1]);
                LocalDate to = LocalDate.parse(parts[2]);
                Event task = new Event(parts[0], from, to);
                this.checklist.add(task);
            } catch (DateTimeException e) {
                throw new TickException("Please specify the dates in this format: yyyy-mm-dd.");
            }
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(this.checklist.get(this.checklist.size() - 1));
        System.out.printf("Now you have %d tasks in the list.\n", this.checklist.size());
        storage.saveData(this.checklist);
    }

    public void removeTaskFromList(String input) throws TickException {
        try {
            int index = Integer.parseInt(input);
            Task task = this.checklist.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.checklist.size());
            storage.saveData(this.checklist);
        } catch (NumberFormatException e) {
            throw new TickException("Please specify the task number you want to delete!");
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("The task number is out of range!");
        }
    }

    public void displayList() {
        if (this.checklist.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.checklist.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, this.checklist.get(i));
            }
        }
    }

    public void markTaskAsDone(String input) throws TickException {
        try {
            int index = Integer.parseInt(input);
            Task task = this.checklist.get(index - 1);
            task.markAsDone();
            System.out.println("Ding ding! I've marked this task as done:");
            System.out.println(task);
            storage.saveData(this.checklist);
        } catch (NumberFormatException e) {
            throw new TickException("Please specify the task number you want to mark as done!");
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("The task number is out of range!");
        }
    }

    public void markTaskAsUndone(String input) throws TickException {
        try {
            int index = Integer.parseInt(input);
            Task task = this.checklist.get(index - 1);
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
            storage.saveData(this.checklist);
        } catch (NumberFormatException e) {
            throw new TickException("Please specify the task number you want to mark as incomplete!");
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("The task number is out of range!");
        }
    }

    public void runCommand(String input) throws TickException {
        String[] inputParts = input.split(" ", 2);
        try {
            CommandType command = CommandType.valueOf(inputParts[0].toUpperCase());
            switch (command) {
            case LIST:
                this.displayList();
                break;
            case MARK:
                this.markTaskAsDone(inputParts[1]);
                break;
            case UNMARK:
                this.markTaskAsUndone(inputParts[1]);
                break;
            case DELETE:
                this.removeTaskFromList(inputParts[1]);
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                this.addTaskToList(inputParts[0], inputParts[1]);
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new TickException("I don't know what that means!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TickException("Please specify an argument!");
        }
    }

    public static void main(String[] args) {
        Tick bot = new Tick("data/tasks.txt");
        bot.greet();
        Scanner scn = new Scanner(System.in);
        String command = scn.nextLine();
        while (!command.equals("bye")) {
            System.out.println(Tick.separator);
            try {
                bot.runCommand(command);
            } catch (TickException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Tick.separator);
            command = scn.nextLine();
        }
        bot.exit();
    }
}