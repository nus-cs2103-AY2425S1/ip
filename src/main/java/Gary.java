import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Gary {
    static List<Task> taskLists = new ArrayList<>();
    static String directoryPath = "src/textFile";
    static String filePath = "src/textFile/gary.txt";



    private static void loadTaskList() throws IOException {
            File directory = new File(directoryPath);
            File file = new File(filePath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();
                    String[] split = nextLine.split(" \\| ");
                    String taskType = split[0].trim();
                    Boolean isDone = split[1].equals("0") ? false : true;
                    if (taskType.equals("T")) {
                        Task task = new Todo(split[2]);
                        taskLists.add(task);
                        if (isDone) {
                            task.editStatus();
                        }
                    } else if (taskType.equals("D")) {
                        Task task = new Deadline(split[2], split[3]);
                        taskLists.add(task);
                        if (isDone) {
                            task.editStatus();
                        }
                    } else if (taskType.equals("E")) {
                        Task task = new Event(split[2], split[3], split[4]);
                        taskLists.add(task);
                        if (isDone) {
                            task.editStatus();
                        }
                    }

                }
                sc.close();
            }
        }
    private static void saveTask() throws IOException {
            File file = new File(filePath);
            FileWriter fileWriter =  new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : taskLists) {
                bufferedWriter.write(task.parseToFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
    }

    public static void main(String[] args) {
        Scanner detector = new Scanner(System.in);
        String greeting = "Hello! I'm Gary\nWhat can I do for you?";
        System.out.println(greeting);
        try {
            loadTaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                String userInput = detector.nextLine();

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskLists.size(); i++) {
                        Task task = taskLists.get(i);
                        System.out.println("\t" + (i + 1) + ". " + task.toString());
                    }
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(6).trim());
                    if (index > taskLists.size() || index <= 0) {
                        throw new Exception("Index needs to be within your task list size!!\nPlease try again!");
                    }
                    Task task = taskLists.remove(index - 1);
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                  System.out.println("Noted. I've removed this task from the list:\n " + task.toString() +
                            "\nNow you have " + taskLists.size() + " tasks in the list.\n");
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(4).trim());
                    if (index > taskLists.size() || index <= 0) {
                        throw new Exception("Index needs to be within your task list size!!\nPlease try again!");
                    }
                    Task task = taskLists.get(index - 1);
                    task.editStatus();
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + task.toString());
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(6).trim());
                    if (index > taskLists.size() || index <= 0) {
                        throw new Exception("Index needs to be within your task list size!!\nPlease try again!");
                    }
                    Task task = taskLists.get(index - 1);
                    task.editStatus();
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + task.toString());
                } else if (userInput.startsWith("todo")) {
                    Task toDo = new Todo(userInput.substring(4).trim());
                    if (toDo.description.length() == 0) {
                        throw new Exception("There is no name for the task.\nPlease " +
                                "input a new task with a name!!");
                    }
                    taskLists.add(toDo);
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + toDo.toString());
                    System.out.println("Now you have " + taskLists.size() + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    String[] division = userInput.substring(8).trim().split("/by");
                    if (division[0].length() == 0) {
                        throw new Exception("There is no description for this deadline task.\nPlease " +
                                "input a new deadline task with a description!!");
                    }
                    if (division.length != 2) {
                        throw new Exception("There is no deadline provided for this task.\nPlease " +
                                "include your deadline!!");
                    }
                    Task deadline = new Deadline(division[0].trim(), division[1].trim());
                    taskLists.add(deadline);
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + deadline.toString());
                    System.out.println("Now you have " + taskLists.size() + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    String[] division = userInput.substring(5).trim().split("/from");
                    if (division[0].length() == 0) {
                        throw new Exception("There is no description for this event task.\nPlease " +
                                "input a new event task with a description!!");
                    }
                    if (division.length == 1 || division[1].trim().split("/to").length != 2) {
                        throw new Exception("There is no start and end date/time for your event task.\nPlease " +
                                "include them for your event too!!");
                    }
                    String[] timeDivision = division[1].trim().split("/to");
                    Task event = new Event(division[0].trim(), timeDivision[0].trim(), timeDivision[1].trim());
                    taskLists.add(event);
                    try {
                        saveTask();
                    } catch (IOException e) {

                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event.toString());
                    System.out.println("Now you have " + taskLists.size() + " tasks in the list.");
                } else {
                    throw new Exception("Sorry! This is not a valid task!!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        detector.close();
    }
}

