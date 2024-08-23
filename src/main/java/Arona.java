package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Arona {
    public static ArrayList<Task> list = new ArrayList<>(100);

    public static void main(String[] args) {
        // For user input
        Scanner in = new Scanner(System.in);

        // Greeting when Opened
        print("Hello! I'm Arona.");
        print("What can I do for you?");

        // Process inputs
        while (true) {
            String input = in.nextLine();
            // Bye command
            if (input.equalsIgnoreCase("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            }

            // List command
            else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        Task task = list.get(i);
                        print(i+1 + ". " + task.getStatusIcon() + " " + list.get(i));
                    }
                }
            }

            // Mark command
            else if (input.toLowerCase().startsWith("mark ")) {
                String indexString = input.substring(5);
                // Check if it's a number behind
                if (indexString.matches("^[1-9]\\d*$")) {
                    int index = Integer.parseInt(indexString);
                    // Check if it's a task that exists
                    if (index <= list.size()) {
                        Task task = list.get(index-1);
                        task.setStatus(true);
                        print("Nice! I've marked this task as done:");
                        print("    " + task.getStatusIcon() + " " + task);
                    }
                    else {
                        print("Error! This task does not exist!");
                    }
                }
                else {
                    print("Error! Please input a positive number");
                }
            }

            // Unmark command
            else if (input.toLowerCase().startsWith("unmark ")) {
                String indexString = input.substring(7);
                // Check if it's a number behind
                if (indexString.matches("^[1-9]\\d*$")) {
                    int index = Integer.parseInt(indexString);
                    // Check if it's a task that exists
                    if (index <= list.size()) {
                        Task task = list.get(index-1);
                        task.setStatus(false);
                        print("OK, I've marked this task as not done yet:");
                        print("    " + task.getStatusIcon() + " " + task);
                    }
                    else {
                        print("Error! This task does not exist!");
                    }
                }
                else {
                    print("Error! Please input a positive number");
                }
            }

            // Else, add new task
            else {
                list.add(new Task(input));
                print("added " + input);
            }
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void print(Integer lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }
}
