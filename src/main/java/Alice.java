import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello! I am Alice! \nWhat can I do for you?");
        System.out.println("------------------------------------------");

        // get commands from the user while response is not "bye"
        String response = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();

            // if user says bye, exit the loop
            if (response.equals("bye")) {
                break;
            }
            // if user wants list, print out all the tasks added
            else if (response.equals("list")) {
                System.out.println("------------------------------------------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i).toString());
                }
                System.out.println("------------------------------------------");
                continue;
            }

            // for marking tasks as done or undone: separate the string from the index
            String[] result = response.split(" ");
            switch (result[0]) {
                case "mark":
                    Task markTask = tasks.get(Integer.parseInt(result[1]) - 1);
                    markTask.markAsDone();
                    System.out.println("------------------------------------------");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(markTask);
                    System.out.println("------------------------------------------");
                    break;
                case "unmark":
                    Task unmarkTask = tasks.get(Integer.parseInt(result[1]) - 1);
                    unmarkTask.markAsUndone();
                    System.out.println("------------------------------------------");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(unmarkTask);
                    System.out.println("------------------------------------------");
                    break;
                default:
                    break;
            }
            if (result[0].equals("mark") || result[0].equals("unmark")) {
                continue;
            }
            
            // all other strings are tasks that should be added
            Task nextTask = new Task(response);
            tasks.add(nextTask);
            System.out.println("------------------------------------------");
            System.out.println("added: " + response);
            System.out.println("------------------------------------------");
        }

        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
