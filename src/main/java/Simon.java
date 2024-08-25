import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.NoSuchElementException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = " Hello! I'm Simon \n" +
            " What can I do for you?\n";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");


    ArrayList<Task> taskList = new ArrayList<Task>(); // Create an ArrayList object
    String inputFile = "store.txt";
    int taskCount = 0;
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(inputFile)) {
            for (Task task : taskList) {
                writer.write(task.toSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }
    public void loadFile() {
        File file = new File(inputFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(inputFile + " Created");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        } else {
            try (Scanner sc = new Scanner(file)) {
                taskList.clear(); // Ensure the list is empty before loading
                taskCount = 0; // Reset task count
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(" \\| ");
                    Task task = null;

                    switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2], taskCount);
                        break;
                    case "D":
                            // Parse the deadline string into LocalDateTime
                            LocalDateTime deadline = LocalDateTime.parse(parts[3], SAVE_FORMATTER);
                            task = new Deadline(parts[2], taskCount, deadline);
                            break;
                    case "E":
                        task = new Events(parts[2], taskCount, parts[3], parts[4]);
                        break;
                    }

                    if (task != null) {
                        if (parts[1].equals("1")) task.markAsDone();
                        taskList.add(task);
                        taskCount++;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while loading the file: " + e.getMessage());
            } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                System.out.println("An error occurred while parsing the file: " + e.getMessage());
            }
        }
    }


    public void run() {

        System.out.print(WLC_MSG);
        Scanner sc = new Scanner(System.in);
        String input;
        loadFile();
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            try {
                if (input.isEmpty()) {
                    continue;
                } else if (input.equals("list")) {
                    printAllTasks();
                    continue;
                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    taskList.get(index).markAsDone();
                    String prMsg = printMessage("\tNice! I've marked this task as done:\n" + "\t" + taskList.get(index).toString());
                    System.out.print(prMsg);
                    saveToFile();
                    continue;
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    taskList.get(index).markAsNotDone();
                    String prMsg = printMessage("\tOK, I've marked this task as not done yet:\n" + "\t" + taskList.get(index).toString());
                    System.out.print(prMsg);
                    saveToFile();
                    continue;
                } else if (input.startsWith("deadline")) {
                    String[] info = input.substring(9).split("/by");
                    if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("The deadline command is missing description or date.");
                    }
                    String name = info[0].trim();
                    String deadlineString = info[1].trim();
                    LocalDateTime deadline = LocalDateTime.parse(deadlineString, INPUT_FORMATTER);
                    Deadline task = new Deadline(name, taskCount, deadline);
                    taskList.add(task);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + task.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    saveToFile();
                    continue;
                } else if (input.startsWith("event")) {
                    String[] info = input.substring(5).split("/from");
                    if (info.length < 2 || info[0].trim().isEmpty()) {
                        throw new IllegalArgumentException("The event command is missing description or date/time.");
                    }
                    String[] deadlines = info[1].split("/to");
                    if (deadlines.length < 2 || deadlines[0].trim().isEmpty() || deadlines[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("The event command is missing the start or end time.");
                    }
                    String from = deadlines[0].trim();
                    String to = deadlines[1].trim();
                    String name = info[0].trim();
                    Events event = new Events(name, taskCount, from, to);
                    taskList.add(event);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + event.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    saveToFile();
                    continue;
                } else if (input.startsWith("todo")) {
                    String info = input.substring(4).trim();
                    if (info.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    ToDo todo = new ToDo(info, taskCount);
                    taskList.add(todo);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + todo.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    saveToFile();
                    continue;
                }
                else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task deleted = taskList.remove(index);
                    taskCount--;
                    String prMsg = printMessage("Noted. I've removed this task:\n" + "\t" + deleted.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    saveToFile();

                }

                else {
                    throw new UnsupportedOperationException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (IndexOutOfBoundsException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (IllegalArgumentException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (UnsupportedOperationException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (Exception e) {
                String errorMsg = printMessage("OOPS!!! An unexpected error occurred: " + e.getMessage());
                System.out.print(errorMsg);
            }
        }
        System.out.print(printMessage(EXT_MSG));
    }
    public static void main(String[] args) {
        Simon simon = new Simon();
        simon.run();
    }

    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    private void printAllTasks() {
        System.out.print(HOR_LINE);
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
        System.out.print(HOR_LINE);
    }
}
