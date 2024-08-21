
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
                if(input.length() < 6 || !Character.isDigit(input.charAt(5))) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Command must be followed by a specific task number");
                    System.out.println("\t\t" + "_".repeat(50));
                }
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
                if(input.length() < 6 || !Character.isDigit(input.charAt(5))) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Command must be followed by a specific task number");
                    System.out.println("\t\t" + "_".repeat(50));
                }
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
            } else if(input.startsWith("todo")) {
                if (input.length() == 4) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of a todo cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    String description = input.substring(5);
                    taskList[counter] = new ToDo(description);
                    counter++;
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" + taskList[counter - 1]);
                    System.out.println("\t\t" + "Now you have " + counter + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else if(input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Deadline format should be: deadline DESCRIPTION /by DATE");
                    System.out.println("\t\t" + "_".repeat(50));
                    continue;
                }
                if (input.length() == 8) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of a deadline cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    int index = input.indexOf("/");
                    String temp = input.substring(index + 1);
                    int tempIndex = input.indexOf("y");
                    String deadline = input.substring(tempIndex + 2);
                    String description = input.substring(9, index);
                    taskList[counter] = new Deadline(description, deadline);
                    counter++;
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" + taskList[counter - 1]);
                    System.out.println("\t\t" + "Now you have " + counter + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            }  else if(input.startsWith("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t event format should be: event DESCRIPTION /from DATE/to DATE");
                    System.out.println("\t\t" + "_".repeat(50));
                    continue;
                }
                if (input.length() == 5) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of an event cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    int index = input.indexOf("/");
                    String description = input.substring(6, index);
                    String temp = input.substring(index + 1);
                    int index_2 = temp.indexOf("/");
                    int index_m = temp.indexOf("m");
                    String dateStart = temp.substring(index_m + 1, index_2);
                    String dateEnd = temp.substring(index_2 + 4);
                    taskList[counter] = new Event(description, dateStart, dateEnd);
                    counter++;
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" +taskList[counter - 1]);
                    System.out.println("\t\t" +"Now you have " + counter + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t\t" + "_".repeat(50));
            }
        }
    }
}

