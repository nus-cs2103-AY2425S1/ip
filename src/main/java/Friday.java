import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        String line = "______________________________________";
        System.out.println(greeting + farewell);
        System.out.println(line);
        String[] tasks = new String[100];

        while (true) {
            String inputString = scanner.nextLine();
            System.out.println(line);
            if (inputString.equals("bye")) {
                System.out.println(farewell);
                System.out.println(line);
                break;
            } else if (inputString.equals("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println(i + 1 + ". " + tasks[i]);
                    }
                }
                System.out.println(line);
            } else {
                System.out.println("added: " + inputString);
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        tasks[i] = inputString;
                        break;
                    }
                }
                System.out.println(line);
            }
        }
    }
}
