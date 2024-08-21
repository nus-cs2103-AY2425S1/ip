import java.util.Scanner;

public class PandaBot {
    public static void main(String[] args) {
        String line = "_________________________________________";
        Scanner scanner = new Scanner(System.in);

        // statically storing tasks
        String[] taskList = new String[100];
        int taskCount = 0;

        // Simple greeting to the user by PandaBot
        System.out.println(line);
        System.out.println("Hello! I'm PandaBot");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Echo user input until the user inputs "bye"
        while (true) {
            String input = scanner.nextLine();

            // if user has said "bye", stop echo and exit
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                System.out.println(line);
            } else {
                taskList[taskCount] = input;
                taskCount++;

                // echo user input
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }


        }

        scanner.close();
    }
}
