package main.java;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Arona {
    public static ArrayList<Task> list = new ArrayList<>(100);

    public static void main(String[] args) {

        // Current data.txt directory
        Path dataDir = Paths.get(".", "\\data.txt");

        // Make data.txt file if it doesn't exist
        try {
            Files.createFile(dataDir);
        } catch (Exception e) {
            if (!e.getMessage().equals(".\\data.txt")) {
                print("Error retrieving data file");
                print(e.getMessage());
            }
        }

        // Read data.txt file
        try {
            // todo fix this yellow line
            Files.lines(dataDir).forEach(Arona::process);
        } catch (Exception e) {
            print("Error processing data");
            print(e.getMessage());
        }

        // For user input
        Scanner in = new Scanner(System.in);

        // Greeting when Opened
        print("Hello! I'm Arona.");
        print("What can I do for you?");

        // Process inputs
        while (true) {
            try {
                String input = in.nextLine();

                // Bye command
                if (input.equalsIgnoreCase("bye")) {

                    // Write data to file
                    // todo simplify code
                    try {
                        Files.write(dataDir, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                        for (Task task : list) {
                            Files.write(dataDir, Collections.singletonList(task.getStatusIcon() + task.getCategory() + " " + task), StandardOpenOption.APPEND);
                        }
                    } catch (Exception e) {
                        print("Error in saving data, your data may not be saved.");
                        print(e.getMessage());
                    }

                    print("Bye. Hope to see you again soon!");
                    break;
                }

                // List command
                else if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) != null) {
                            Task task = list.get(i);
                            print(i + 1 + ". " + task.getStatusIcon() + task.getCategory() + " " + list.get(i));
                        }
                    }
                }

                // Delete
                else if (input.toLowerCase().startsWith("delete")) {

                    // Extract and save task number
                    String[] data = input.split("delete", 2);

                    // Check if number
                    if (!data[1].stripLeading().matches("^[1-9]\\d*$")) {
                        throw new AronaException("Error! Please input a positive number");
                    }

                    // Save index as integer
                    int index = Integer.parseInt(data[1].stripLeading());

                    // Check if exist
                    if ((index > list.size())) {
                        throw new AronaException("Error! This task does not exist!");
                    }

                    // Process and Reply
                    print("Got it. I've removed this task:");
                    Task task = list.get(index-1);
                    print(task.getStatusIcon() + task.getCategory() + ": " + task.toString());
                    list.remove(index-1 );
                    print("Now you have " + list.size() + " tasks in the list.");


                }

                // Mark and unmark command
                else if (input.toLowerCase().startsWith("mark") || input.toLowerCase().startsWith("unmark")) {

                    // Extract and save task number
                    String[] data = input.split("mark", 2);

                    // Extract and save action
                    boolean action = input.toLowerCase().startsWith("mark");

                    // Check if number
                    if (!data[1].stripLeading().matches("^[1-9]\\d*$")) {
                        throw new AronaException("Error! Please input a positive number");
                    }

                    // Save index as integer
                    int index = Integer.parseInt(data[1].stripLeading());

                    // Check if exist
                    if ((index > list.size())) {
                        throw new AronaException("Error! This task does not exist!");
                    }

                    // Process and Reply
                    Task task = list.get(index - 1);
                    task.setStatus(action);
                    if (action) {
                        print("Nice! I've marked this task as done:");
                    } else {
                        print("OK, I've marked this task as not done yet:");
                    }
                    print(task.getStatusIcon() + task.getCategory() + ": " + task.toString());
                }

                // Todos or events or deadline command
                else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("event") || input.toLowerCase().startsWith("deadline")) {
                    // Data from command
                    String[] data;

                    // Extract and save description
                    data = input.split(" ", 2);

                    // Check if empty
                    if (data.length == 1 || data[1].isBlank()) {
                        throw new AronaException("Error! Please input a task description");
                    }

                    // Process
                    switch (data[0]) {
                        case "todo": {
                            list.add(new Todos(data[1]));
                            break;
                        }
                        case "deadline": {
                            String[] taskData = data[1].split(" /by ", 2);
                            if (taskData.length == 2) {
                                list.add(new Deadline(taskData[0], taskData[1]));
                            } else {
                                throw new AronaException("Error! Please specify a by");
                            }
                            break;
                        }
                        case "event": {
                            String[] taskData = data[1].split(" /from | /to ", 3);
                            if (taskData.length == 3) {
                                list.add(new Events(taskData[0], taskData[1], taskData[2]));
                            } else {
                                throw new AronaException("Error! Please specify a from and to");
                            }
                            break;
                        }
                    }

                    // Reply
                    print("Got it. I've added this task:");
                    int listSize = list.size();
                    Task task = list.get(listSize - 1);
                    print(task.getStatusIcon() + task.getCategory() + ": " + task.toString());
                    print("Now you have " + listSize + " tasks in the list.");

                }

                // All other unrecognised commands
                else {
                    throw new AronaException("Error, unrecognised command.");
                }
            }
            catch (Exception e) {
                print(e.getMessage());
            }
            }
    }

    private static void process(String line) {
        String[] data = line.split("]", 3);
        for (String i : data) {
            print(i);
        }

        switch (data[1]) {
            case "[T": {
                list.add(new Todos(data[2].substring(1)));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
            }
            case "[D": {
                String[] taskData = data[2].split(" \\(by: ", 2);
                list.add(new Deadline(taskData[0].substring(1), taskData[1].substring(0, taskData[1].length() - 1)));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
            }
            case "[E": {
                String[] taskData = data[2].split(" \\(from: | to: ", 3);
                list.add(new Events(taskData[0].substring(1), taskData[1], taskData[2].substring(0, taskData[2].length() - 1)));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
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
