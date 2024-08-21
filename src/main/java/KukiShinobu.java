import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    private final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        KukiShinobu kuki = new KukiShinobu();
        kuki.listen();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void listen() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();  // Read user input
            KukiShinobu.printHorizontalLine();
            // break out of while loop if user issues "bye" command
            if (command.equals("bye")) {
                break;
            }

            // otherwise, handle all other commands as appropriate
            switch(command) {
                case "list":
                    this.listTasks();
                    break;
                default:
                    this.addTask(command);
            }
            KukiShinobu.printHorizontalLine();
        }
        this.goodbye();
    }

    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));

        }
    }

    private void addTask(String task) {
        this.tasks.add(task);
        System.out.println("added: " + task);
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
