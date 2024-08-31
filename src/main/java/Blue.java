import Exceptions.EmptyDescriptionException;
import Exceptions.InputErrorException;
import Exceptions.WrongNumberOfItemException;

import java.util.Scanner;
public class Blue {

    public static void greet() {
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Blue! Woof Woof! Yap Yap!");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------");
    }
    public static void farewell() {
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("_     /)---(\\          /~~~\\");
        System.out.println("\\\\   (/ . . \\)        /  .. \\");
        System.out.println(" \\\\__)-\\(*)/         (_,\\  |_)");
        System.out.println(" \\_       (_         /   \\@/    /^^^\\");
        System.out.println(" (___/-(____) _     /      \\   / . . \\");
        System.out.println("              \\\\   /  `    |   V\\ Y /V");
        System.out.println("               \\\\/  \\   | _\\    / - \\");
        System.out.println("                \\   /__'|| \\\\_  |    \\");
        System.out.println("                 \\_____)|_).\\_).||(__V");
        System.out.println("--------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        // Greet the user
        Blue.greet();
        String input = "";

        while (true) {
            input = scanner.nextLine();

            //if bye is typed, break out of loop
            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            //if list is typed, print out corresponding list of tasks
            if (input.equalsIgnoreCase("list")) {
                taskList.printList();
                continue;
            }

            if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    taskList.mark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'mark <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Can you check the number you input please");
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.unmark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'unmark <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Can you check the number you input please");
                }
                continue;
            }

            if (input.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.delete(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'delete <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Can you check the number you input please");
                }
                continue;
            }



            //print out added... and add the item to the myList field in the Note object
            try {
                taskList.addToList(input);
                String temp = "Now you have " + taskList.getNumberOfTask() + " tasks in the list.";
                System.out.println(temp);
                System.out.println("--------------------------------------------");
            } catch (EmptyDescriptionException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (InputErrorException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                // This block is optional and catches any other exceptions that aren't explicitly caught above
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        }

        // Farewell message
        Blue.farewell();

        // Close the scanner
        scanner.close();
    }
}