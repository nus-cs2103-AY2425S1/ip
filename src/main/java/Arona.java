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
                        print(i+1 + ". " + task.getStatusIcon() + task.getCategory() + " " + list.get(i));
                    }
                }
            }

            // Mark and unmark command
            else if (input.toLowerCase().startsWith("mark ") || input.toLowerCase().startsWith("unmark ")) {
                boolean con = true;

                // Data from command
                int index = -1;
                boolean action;

                // Extract and save action
                action = input.toLowerCase().startsWith("mark ");

                // Extract and save task number
                String[] data = input.split(" ", 2);

                if (data.length == 1) {
                    print("Error! Please input a positive number");
                    con = false;
                }

                // Check if number
               if (con && !data[1].matches("^[1-9]\\d*$")) {
                    print("Error! Please input a positive number");
                    con = false;
                }
               else {
                   index = Integer.parseInt(data[1]);
               }

                // Check if exist
                if (con && (index > list.size())) {
                    print("Error! This task does not exist!");
                    con = false;
                }

                // Process
                if (con) {
                    Task task = list.get(index - 1);
                    task.setStatus(action);
                    if (action) {
                        print("Nice! I've marked this task as done:");
                    } else {
                        print("OK, I've marked this task as not done yet:");
                    }
                    print(task.getStatusIcon() + task.getCategory() + ": " + task.toString());
                }
            }

            // Todos or events or deadline command
            else if (input.toLowerCase().startsWith("todo ") || input.toLowerCase().startsWith("event ") || input.toLowerCase().startsWith("deadline ")) {
                // Data from command
                boolean con = false;
                String[] data;

                // Extract and save description
                data = input.split(" ", 2);

                // Check if empty
                if (data[1].isBlank()) {
                    print("Error! Please input a task description");
                }

                // Process
                switch (data[0]) {
                    case "todo": {
                        list.add(new Todos(data[1]));
                        con = true;
                        break;
                    }
                    case "deadline": {
                        String[] taskData = data[1].split("/by ", 2);
                        if (taskData.length == 2) {
                            list.add(new Deadline(taskData[0], taskData[1]));
                            con = true;
                        }
                        else {
                            print("Error! Please specify a by");
                        }
                        break;
                    }
                    case "event": {
                        String[] taskData = data[1].split("/from |/to ", 3);
                        if (taskData.length == 3) {
                            list.add(new Events(taskData[0], taskData[1], taskData[2]));
                            con = true;
                        }
                        else {
                            print("Error! Please specify a from and to");
                        }
                        break;
                    }
                }
                if (con) {
                    print("Got it. I've added this task:");
                    int listSize = list.size();
                    Task task = list.get(listSize - 1);
                    print(task.getStatusIcon() + task.getCategory() + ": " + task.toString());
                    print("Now you have " + listSize + " tasks in the list.");
                }
            }

            else {
                print("Error, unrecognised command.");
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
