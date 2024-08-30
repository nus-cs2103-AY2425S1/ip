import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConverSage {
    public static void main(String[] args) {
        String logo = "  ____                          ____                   \n" +
                " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _` |/ _` |/ _ \\\n" +
                "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                "                                            |___/      ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(logo);
        System.out.println("Greetings, I'm your ConverSage.");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String input;

        List<Task> taskList = loadTasks();

        while (true) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);

            try {
                if (input.equalsIgnoreCase("list")) {
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i-1));
                    }
                    System.out.println(horizontalLine);
                } else if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Goodbye. We shall meet again soon.");
                    System.out.println(horizontalLine);
                    break;
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new ConverSageException("Task Number is invalid...");
                    }
                    Task currTask = taskList.get(taskIndex);

                    try {
                        String doneTask = currTask.markAsDone();
                        System.out.println("Good job. I've marked this task as done.");
                        System.out.println(doneTask);
                    } catch (ConverSageException e) {
                        System.out.println("ERR " + e.getMessage());
                    }

                    saveTasks(taskList);
                    System.out.println(horizontalLine);


                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new ConverSageException("Task Number is invalid...");
                    }

                    Task currTask = taskList.get(taskIndex);
                    try {
                        String undoneTask = currTask.markAsUndone();
                        System.out.println("I've marked this task as not done yet, get to it quickly.");
                        System.out.println(undoneTask);
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    saveTasks(taskList);
                    System.out.println(horizontalLine);

                } else if (input.startsWith("todo")) {
                    try {
                        if (input.length() <= 5) {
                            throw new ConverSageException("The description of a todo cannot be empty.");
                        }
                        String taskDesc = input.substring(5);
                        Task newTask = new ToDo(taskDesc);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (Exception e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    saveTasks(taskList);
                    System.out.println(horizontalLine);

                } else if (input.startsWith("deadline")) {
                    try {
                        if (input.length() <= 9 || !input.contains(" /by ")) {
                            throw new ConverSageException("The deadline task requires a description and a deadline.");
                        }
                        String[] deadlineTaskParts = input.substring(9).split(" /by ");
                        String taskDesc = deadlineTaskParts[0];
                        String deadline = deadlineTaskParts[1];
                        Task newTask = new Deadline(taskDesc, deadline);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    saveTasks(taskList);
                    System.out.println(horizontalLine);
                } else if (input.startsWith("event")) {
                    try {
                        if (input.length() <= 6) {
                            throw new ConverSageException("The event task requires a description.");
                        }
                        if (!input.contains(" /from ") || !input.contains(" /to ")) {
                            throw new ConverSageException("The event task requires a start time and an end time");
                        }
                        String[] eventTaskParts = input.substring(6).split(" /from | /to ");
                        String taskDesc = eventTaskParts[0];
                        String from = eventTaskParts[1];
                        String to = eventTaskParts[2];
                        Task newTask = new Event(taskDesc, from, to);
                        taskList.add(newTask);

                        System.out.println("Understood, I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("You have " + taskList.size() + " tasks in your list" );
                    } catch (ConverSageException e) {
                        System.out.println("ERR: " + e.getMessage());
                    }

                    saveTasks(taskList);
                    System.out.println(horizontalLine);
                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new ConverSageException("Task Number is invalid...");
                    }

                    Task currTask = taskList.get(taskIndex);
                    String deletedTask = currTask.toString();
                    taskList.remove(taskIndex);
                    System.out.println("Ok, I've deleted the following task: ");
                    System.out.println("  " + deletedTask);
                    System.out.println("You have " + taskList.size() + " tasks in your list" );

                    saveTasks(taskList);
                    System.out.println(horizontalLine);
                } else {
                    throw new ConverSageException("Invalid command, please try again");
                }
            } catch (ConverSageException e) {
                System.out.println("ERR: " + e.getMessage());
                System.out.println(horizontalLine);
            }

        }
    }

    /*
        method to save a list of tasks to a file myTasks.txt inside a folder called data
     */
    private static void saveTasks(List<Task> taskList) {
        // File object representing the dir
        // Note: ./ means directory is relative to wd
        File dataDir = new File("./data");

        // If dir doesn't exist, create!
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Next, create the myTasks file inside this directory!
        File myTasksFile = new File("./data/myTasks.txt");

        // Now, we need to try to write to the file, create a FileWriter object using myTasksFile
        try (FileWriter writer = new FileWriter(myTasksFile)) {
            for (Task currTask : taskList) {
                // write the currTask into file!
                writer.write(currTask.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("ERR: Failed to save tasks to file.");
        }

    }

    /*
        This method is to load all the tasks from the file whenever application starts
     */
    private static List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File myTasksFile = new File("./data/myTasks.txt");

        // if the above file doesn't exist, no tasks so return empty list
        if (!myTasksFile.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(myTasksFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    taskList.add(Task.fromFileFormat(line));
                } catch (ConverSageException e) {
                    System.out.println("ERR: Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("ERR: Failed to load tasks from file!");
        }



        return taskList;
    }
}
