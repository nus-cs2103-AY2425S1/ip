import java.util.Scanner;

public class Llama {
    public static void displayString(String str) {
        System.out.println("\t" + str);
    }

    public static void displayTask(int num, Task task) {
        System.out.println("\t" + num + ". " + task);
    }

    public static void main(String[] args) {
        String logo = "";
        String hr = "____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);
        // Assume less than 100 tasks
        Task[] taskArray = new Task[100];
        int last = 0;

        // Initializing message
        displayString(hr);
        displayString("Hello! I'm Llama!");
        displayString(logo);
        displayString("What can I do for you?");

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            displayString(hr);
            String input = sc.nextLine();
            displayString(hr);

            String command = input;
            String remaining = "";
            if (input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
                remaining = input.substring(input.indexOf(" ") + 1);
            }


            if (command.equals("bye")) {
                // End program
                shouldContinue = false;
                sc.close();
            } else if (command.equals("list")) {
                // List out tasks
                for (int i = 0; i < last; i++) {
                    int x = i + 1;
                    displayTask(x, taskArray[i]);
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(remaining) - 1;
                taskArray[index].markDone();
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(remaining) - 1;
                taskArray[index].markNotDone();
            } else {
                // Add text into a list
                taskArray[last] = new Task(input);
                last++;
                displayString("Added: " + input);
            }
        }

        // Exit message
        displayString("Baaaaaa byeeee. Come baaaaack soon!");
    }
}
