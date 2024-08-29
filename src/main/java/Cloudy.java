
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cloudy {

    private static final String FILE_PATH = "./data/cloudy.txt";


    public static void main(String[] args) {
        Scanner echo = new Scanner(System.in);
        ArrayList<Task> userList = loadTasksFromFile();

        // Starting prompt
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Cloudy.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Asking for user input
        String userInput = echo.nextLine();

        // main program loop
        while (true) {
            // if user types 'bye', program will end
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            // if user types 'list', user can see the tasks
            } else if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i + 1) + ". " + userList.get(i).printTaskOnList());
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // if user marks task
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToMark = userList.get(taskNumber - 1);
                            taskToMark.markTask();
                            saveTasksToFile(userList);

                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskToMark.printTaskOnList());
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();


            // if user unmarks task
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                        Task taskToMark = userList.get(taskNumber - 1);
                        taskToMark.unmarkTask();

                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskToMark.printTaskOnList());
                        System.out.println("____________________________________________________________");

                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();

            // user adds to do task
            } else if (userInput.startsWith("todo")) {
                System.out.println("____________________________________________________________");
                if (userInput.trim().length() <= 4) {
                    System.out.println("No task detected.");
                } else {
                    String taskDescription = userInput.substring(5).trim();
                    Task newTask = new Todo(taskDescription, false);
                    userList.add(newTask);
                    saveTasksToFile(userList);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.printTaskOnList());
                    System.out.println("Now you have " + userList.size() + " tasks in the list.");
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // user adds Deadline task
            } else if (userInput.startsWith("deadline")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] parts = userInput.split("/by");
                String taskDescription = parts[0].substring(9).trim();
                String deadline = parts.length > 1 ? parts[1].trim() : "";
                Task newTask = new Deadline(taskDescription, deadline, false);
                userList.add(newTask);
                saveTasksToFile(userList);

                System.out.println(newTask.printTaskOnList());
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // user adds Event task
            } else if (userInput.startsWith("event")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] partsFrom = userInput.split("/from");
                String taskDescription = partsFrom[0].substring(6).trim();
                String startTime = "", endTime = "";
                if (partsFrom.length > 1) {
                    String[] partsTo = partsFrom[1].split("/to");
                    startTime = partsTo[0].trim();
                    if (partsTo.length > 1) {
                        endTime = partsTo[1].trim();
                    }

                }
                Task newTask = new Event(taskDescription, startTime, endTime, false);
                userList.add(newTask);
                saveTasksToFile(userList);

                System.out.println(newTask.printTaskOnList());
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToDelete = userList.get(taskNumber - 1);

                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskToDelete.printTaskOnList());
                            userList.remove(taskNumber - 1);
                            saveTasksToFile(userList);
                            System.out.println("Now you have " + userList.size() + " tasks in the list.");
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }
                userInput = echo.nextLine();

            // if user types invalid command
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command, try again.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();
            }
        }
    }

    public static void checkFileExists() {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred when creating the file.");
            e.printStackTrace();
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        checkFileExists();

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Skipping invalid task" + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found. Creating one...");
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks.");
            e.printStackTrace();
        }
        return tasks;
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String typeOfTask = parts[0];
        boolean isMarked = parts[1].equals("1");
        String taskDescription = parts[2];

        switch (typeOfTask) {
            case "T":
                return new Todo(taskDescription, isMarked);
            case "D":
                String deadline = parts[3];
                return new Deadline(taskDescription, deadline, isMarked);
            case "E":
                String startTime = parts[3];
                String endTime = parts[4];
                return new Event(taskDescription, startTime, endTime, isMarked);
            default:
                return null;
        }
    }


}
