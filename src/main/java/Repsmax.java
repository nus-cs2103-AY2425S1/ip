import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Repsmax {
    private static final String FILE_PATH = "C:/Users/nicla/OneDrive/Desktop/Cs2103/repo/src/data.txt";
    public static void main(String[] args) {
        //initialise scanner
        Scanner scanner = new Scanner(System.in);
        //initialise string array
        List<Task> tasks = new ArrayList<>();
        //greet the user
        loadTasks(tasks);
        String greeting = "  ____________________________________________________________\n" +
                "   Hello! I'm Repsmax\n" +
                "   What can I do for you?\n" +
                "  ____________________________________________________________\n";
        String goodbye = "  ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "  ____________________________________________________________\n";
        System.out.println(greeting);
        while(true) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" " ,2);
            String command = splitInput[0];
            if (userInput.equals("bye")) {
                System.out.println(goodbye);
                saveTasks(tasks);
                break;
            }

            switch (command) {
                case "list":
                    System.out.println("  ____________________________________________________________\n");
                    System.out.println("  here are the task in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("   " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("  ____________________________________________________________\n");
                    break;

                case "mark":
                    try {
                        int markIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (markIndex >= 0 && markIndex < tasks.size()) {
                            System.out.println("  ____________________________________________________________\n");
                            System.out.println("  Nice! I've marked this task as done:\n");
                            tasks.get(markIndex).setDone();
                            System.out.println("  " + tasks.get(markIndex));
                            System.out.println("  ____________________________________________________________\n");
                            saveTasks(tasks);
                        } else {
                            System.out.println("  ____________________________________________________________\n");
                            System.out.println("  OOPS!!! The task number is out of range.\"");
                            System.out.println("  ____________________________________________________________\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  ____________________________________________________________\n");
                        System.out.println("  OOPS!!! The task number must be an integer.");
                        System.out.println("  ____________________________________________________________\n");
                    }
                    break;

                case "unmark":
                    try {
                        int unmarkIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                            System.out.println("  ____________________________________________________________\n");
                            System.out.println("  OK, I've marked this task as not done yet:\n");
                            tasks.get(unmarkIndex).setUndone();
                            System.out.println("  " + tasks.get(unmarkIndex));
                            System.out.println("  ____________________________________________________________\n");
                            saveTasks(tasks);
                        } else {
                            System.out.println("  ____________________________________________________________\n");
                            System.out.println("  OOPS!!! The task number is out of range.\"");
                            System.out.println("  ____________________________________________________________\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  ____________________________________________________________\n");
                        System.out.println("  OOPS!!! The task number must be an integer.");
                        System.out.println("  ____________________________________________________________\n");
                    }
                    break;

                case "todo":
                    if (splitInput.length > 1) {
                        String description = splitInput[1];
                        tasks.add(new Todo(description));
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("  ____________________________________________________________");
                        saveTasks(tasks);
                    } else {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("  ____________________________________________________________");
                    }
                    break;

                case "deadline":
                    try {
                        String[] parts = splitInput[1].split("/by ", 2);
                        String deaddescription = parts[0];
                        String by = parts[1];
                        tasks.add(new Deadline(deaddescription, by));
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("  ____________________________________________________________");
                        saveTasks(tasks);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  OOPS!!! The deadline command must include '/by <date/time>'.");
                        System.out.println("  ____________________________________________________________");
                    }
                    break;

                case "event":
                    try {
                        String[] Eventparts = splitInput[1].split("/from ", 2);
                        String[] fromTo = Eventparts[1].split("/to ", 2);
                        String Eventdescription = Eventparts[0];
                        String from = fromTo[0];
                        String to = fromTo[1];
                        tasks.add(new Event(Eventdescription, from, to));
                        System.out.println("  ____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("  ____________________________________________________________");
                        saveTasks(tasks);
                    }  catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  OOPS!!! The event command must include '/from <start date/time>' and '/to <end date/time>'.");
                        System.out.println("  ____________________________________________________________");
                    }
                    break;

                case "delete":
                    try {
                        int deleteindex = Integer.parseInt(splitInput[1]) - 1;
                        if (deleteindex >= 0 && deleteindex < tasks.size()) {
                            System.out.println("  ____________________________________________________________");
                            System.out.println("  Noted. I've removed this task:");
                            System.out.println("   " + tasks.remove(deleteindex));
                            System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("  ____________________________________________________________");
                            saveTasks(tasks);
                        } else {
                            System.out.println("  ____________________________________________________________");
                            System.out.println("  OOPS!!! The task number is out of range.");
                            System.out.println("  ____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("  OOPS!!! The task number must be an integer.");
                        System.out.println("  ____________________________________________________________");
                    }
                    break;

                default:
                    tasks.add(new Task(userInput));
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + userInput);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
    public static void saveTasks(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public static void loadTasks(List<Task> tasks) {
        try{
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No task found. Starting a new List");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(Task.fromFileFormat(line));
            }
            fileScanner.close();
        } catch (IOException e) {
        System.out.println("An error occurred while loading tasks.");
        }
    }
}