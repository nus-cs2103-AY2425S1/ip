import java.util.Scanner;

public class MrIncredible {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextStorage textStorage = new TextStorage();

        MrIncredible.greet();
        // Start an infinite loop to handle user input
        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit condition
            if (input.equals("bye")) {
                MrIncredible.sayBye();
                break;
            }

            // Handle "list" command
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                textStorage.listTasks();
                System.out.println("    ____________________________________________________________");
            } else { // Handle other inputs as tasks
                textStorage.addTask(input);
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        }

        // Close the scanner
        scanner.close();
    }

    public static void greet() {
        String greeting = "    What can I do for you?";

        // Initial greeting
        System.out.println("    ____________________________________________________________");
        System.out.println("Hello! I'm MrIncredible");
        System.out.println(greeting);
        System.out.println("    ____________________________________________________________");
    }

    public static void sayBye() {
        String byeText = "    Bye. Hope to see you again soon!";

        System.out.println("    ____________________________________________________________");
        System.out.println(byeText);
        System.out.println("    ____________________________________________________________");
    }
}
