import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    private final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        KukiShinobu shinobu = new KukiShinobu();
        shinobu.listen();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void listen() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();  // Read user input
            KukiShinobu.printHorizontalLine();

            // split user input into commands and argument (if applicable)
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            // break out of while loop if user issues "bye" command
            if (command.equals("bye")) {
                break;
            }

            // otherwise, handle all other commands as appropriate
            switch(command) {
                case "list":
                    this.listTasks();
                    break;
                case "mark":
                    // argument is task index
                    this.markAsDone(Integer.parseInt(argument));
                    break;
                case "unmark":
                    // argument is task index
                    this.unmarkAsDone(Integer.parseInt(argument));
                    break;
                default:
                    this.addTask(input);
            }
            KukiShinobu.printHorizontalLine();
        }
        this.goodbye();
    }

    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    private void markAsDone(int i) {
        this.tasks.get(i - 1).markAsDone();
    }

    private void unmarkAsDone(int i) {
        this.tasks.get(i - 1).unmarkAsDone();
    }

    private void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        this.tasks.add(newTask);
        System.out.println("added: " + taskDescription);
    }

    public void greet() {
        KukiShinobu.printHorizontalLine();
        System.out.println("Hello! I'm " + this.name + "!");
        System.out.println("What can I do for you?");
        KukiShinobu.printHorizontalLine();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        KukiShinobu.printHorizontalLine();
    }

    public void fancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }


}
