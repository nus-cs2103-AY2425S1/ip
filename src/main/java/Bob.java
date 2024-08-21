import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int taskCounter = 0;
        Task[] tasks = new Task[100];

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello! I'm Bob!\n\tHow can I help you today?");
        System.out.println("\t------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            // add task to tasks array
            if (!input.equals("bye") && !input.equals("list") && !input.startsWith("mark") && !input.startsWith("unmark")) {
                tasks[taskCounter] = new Task(input); // input as task description
                taskCounter++;

                System.out.println("\t------------------------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t------------------------------------------");
            }


            // print tasks
            if (input.equals("list")) {
                System.out.println("\t------------------------------------------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCounter; i++) {
                    int j = i + 1;
                    System.out.println(j + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("\t------------------------------------------");
            }
            
            // mark
            if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                // split the input into array "mark" "2", convert to int, -1

                if (taskIndex < taskCounter && taskIndex >= 0) {
                    tasks[taskIndex].mark();

                    System.out.println("\t------------------------------------------");
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t\t[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                    System.out.println("\t------------------------------------------");
                } else {
                    System.out.println("Invalid index..");
                }
            }

            // unmark
            if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                if (taskIndex < taskCounter && taskIndex >= 0) {
                    tasks[taskIndex].unmark();

                    System.out.println("\t------------------------------------------");
                    System.out.println("\tOk, I've marked this task as not done yet:");
                    System.out.println("\t\t[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                    System.out.println("\t------------------------------------------");

                } else {
                    System.out.println("Invalid index..");
                }
            }
        }

        System.out.println("\t------------------------------------------");
        System.out.println("\tBye, see you again!");
        System.out.println("\t------------------------------------------");
    }
}


