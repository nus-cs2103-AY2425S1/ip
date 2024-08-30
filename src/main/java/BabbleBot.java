import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class BabbleBot {
    private static final String TASK_LIST_PATH = "./data/babblebot.txt";
    private static ArrayList<Task> storedTasks = new ArrayList<>();
    private static void saveTasksToFile() throws IOException {
        File f = new File(TASK_LIST_PATH);
        f.getParentFile().mkdirs(); // create directories if they don't exist
        FileWriter fw = new FileWriter(TASK_LIST_PATH);
        for (Task task : storedTasks) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();
    }

    private static void loadTasksFromFile() throws IOException {
        File file = new File(TASK_LIST_PATH);
        if (!file.exists()) {
            return; // No file exists, start with an empty task list
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");
            Task task;
            switch (taskData[0]) {
                case "T":
                    task = new Todo(taskData[2]);
                    break;
                case "D":
                    task = new Deadline(taskData[2], taskData[3]);
                    break;
                case "E":
                    String description = taskData[2];
                    String[] fromTo = taskData[3].split(" to ");
                    String from = fromTo[0];
                    String to = fromTo[1];
                    task = new Event(description, from, to);
                    break;
                default:
                    continue; // Skip unknown task types
            }
            if (taskData[1].equals("1")) {
                task.markAsDone();
            }
            storedTasks.add(task);
        }
        scanner.close();
    }

    private static void printTasksInFile() throws FileNotFoundException {
        File f = new File(TASK_LIST_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }
    public static void main(String[] args) {
        try {
            loadTasksFromFile(); // Load tasks from the file at the start
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        Boolean notBye = true;
        Scanner in = new Scanner(System.in);
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(separator);

        while(notBye && in.hasNextLine()) {
            try {
                String userInp = in.nextLine();
                String[] userInpSplit = userInp.split(" ");
                if (userInpSplit[0].equals("bye")) {
                    notBye = false;
                } else if (userInpSplit[0].equals("list")) {
                    System.out.println(separator);
                    int tempCount = 1;
                    for (Task t : storedTasks) {
                        System.out.println(tempCount + ". " + t.toString());
                        tempCount++;
                    }
                    System.out.println(separator);
                } else if (userInpSplit[0].equals("todo")) {
                    try {
                        storedTasks.add(new Todo(userInp.split("todo ")[1])); //i can try here
                        System.out.println(separator);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + storedTasks.get(storedTasks.size() - 1).toString());
                        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
                        System.out.println(separator);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(separator);
                    } catch (IOException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! Something went wrong");
                        System.out.println(separator);
                    }
                } else if (userInpSplit[0].equals("deadline")) {
                    try {
                        String content = userInp.split("deadline ")[1].split(" /by")[0];
                        String by = userInp.split("/by ")[1];
                        storedTasks.add(new Deadline(content, by));
                        System.out.println(separator);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + storedTasks.get(storedTasks.size() - 1).toString());
                        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
                        System.out.println(separator);
                        saveTasksToFile();
                    } catch (IOException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! Something went wrong");
                        System.out.println(separator);
                    }
                } else if (userInpSplit[0].equals("event")) {
                    try {
                        String content = userInp.split("event ")[1].split(" /from")[0];
                        String splitDates = userInp.split("/from ")[1];
                        String[] parsedDates = splitDates.split(" /to ");
                        storedTasks.add(new Event(content, parsedDates[0], parsedDates[1]));
                        System.out.println(separator);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + storedTasks.get(storedTasks.size() - 1).toString());
                        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
                        System.out.println(separator);
                        saveTasksToFile();
                    } catch (IOException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! Something went wrong");
                        System.out.println(separator);
                    }
                } else if (userInpSplit[0].equals("delete")) {
                    try {
                        int index = Integer.valueOf(userInpSplit[1]) - 1;
                        System.out.println(separator);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("   " + storedTasks.get(index).toString());
                        storedTasks.remove(index);
                        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
                        System.out.println(separator);
                        saveTasksToFile();
                    } catch (IOException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! Something went wrong");
                        System.out.println(separator);
                    }
                } else if (userInpSplit[0].equals("print")) {
                    try {
                        printTasksInFile();
                    } catch (IOException e) {
                        System.out.println(separator);
                        System.out.println("OOPS!!! Something went wrong");
                        System.out.println(separator);
                    }
                } else if (userInpSplit.length == 2) {
                    if (userInpSplit[0].equals("mark")) {
                        try {
                            int index = Integer.valueOf(userInpSplit[1]) - 1;
                            storedTasks.get(index).markAsDone();
                            System.out.println(separator);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("   " + storedTasks.get(index).toString());
                            System.out.println(separator);
                            saveTasksToFile();
                        } catch (IOException e) {
                            System.out.println(separator);
                            System.out.println("OOPS!!! Something went wrong");
                            System.out.println(separator);
                        }
                    } else if (userInpSplit[0].equals("unmark")) {
                        //unmark code
                        try {
                            int index = Integer.valueOf(userInpSplit[1]) - 1;
                            storedTasks.get(index).maskAsUndone();
                            System.out.println(separator);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("   " + storedTasks.get(index).toString());
                            System.out.println(separator);
                            saveTasksToFile();
                        } catch (IOException e) {
                            System.out.println(separator);
                            System.out.println("OOPS!!! Something went wrong");
                            System.out.println(separator);
                        }
                    }
                } else {
                    throw new BabbleBotException();
                }
            } catch (BabbleBotException b) {
                System.out.println(separator);
                System.out.println("Whoopsie daisy! Looks like I got all tangled up in my words there!\n" +
                        "Let's try that again in a way that might make a bit more sense.\n" +
                        "What do you need help with?");
                System.out.println(separator);
            }
        }
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
