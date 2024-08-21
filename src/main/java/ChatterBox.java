
import java.util.Scanner;
public class ChatterBox {
    public static void main(String[] args) {

        Task[] taskList = new Task[100];
        int counter = 0;

        Scanner sc = new Scanner(System.in);
        //Greeting the user
        System.out.println("\t\t" + "_".repeat(50));
        System.out.println("\t\t" + "Hey there! I'm ChatterBox");
        System.out.println("\t\t" + "What's on your plate today?");
        System.out.println("\t\t" + "_".repeat(50));

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + "Take care! Looking forward to helping you again soon!");
                System.out.println("\t\t" + "_".repeat(50));
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t Here's what you've got on your to-do list so far:");
                for(int i = 0; i < counter; i++) {
                    System.out.println("\t\t\t- " + taskList[i]);
                }
                System.out.println("\t\t" + "_".repeat(50));
            } else if (input.startsWith("mark")) {
                int indexSpace = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
                if (taskIndex >= 0 && taskIndex < counter) {
                    taskList[taskIndex].markAsDone();
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Super! Task marked as done:");
                    System.out.println("\t\t\t" + taskList[taskIndex]);
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Invalid task number.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else if (input.startsWith("unmark")) {
                int indexSpace = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
                if (taskIndex >= 0 && taskIndex < counter) {
                    taskList[taskIndex].markAsNotDone();
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Sure, task has been marked as not done:");
                    System.out.println("\t\t\t" + taskList[taskIndex]);
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Invalid task number.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            }else {
                taskList[counter] = new Task(input);
                counter++;
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + input + " is added to your list");
                System.out.println("\t\t" + "_".repeat(50));
            }
        }
    }
}