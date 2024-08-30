import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {

    private static final String FILE_PATH = "./data/buddy.txt";

    private static LocalDateTime formatDate (String date) throws BuddyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BuddyException("you need to state the date in the format 'd/M/yyyy HHmm'");
        }


    }

    private static String localDateTimeString (LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    private static void saveTasks(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                String line = "";
                if (task.getTaskType().equals("T")) {
                    line = String.format("T | %d | %s", task.isDone ? 1 : 0, task.description);
                } else if (task.getTaskType().equals("D")) {
                    Deadlines deadline = (Deadlines) task;
                    int index = task.description.indexOf("(by:");
                    line = String.format("D | %d | %s | %s", task.isDone ? 1 : 0, deadline.description.substring(0, index).trim(), localDateTimeString(deadline.deadline));
                } else if (task.getTaskType().equals("E")) {
                    Events event = (Events) task;
                    int index = task.description.indexOf("(from:");
                    line = String.format("E | %d | %s | %s | %s", task.isDone ? 1 : 0, event.description.substring(0, index).trim(), localDateTimeString(event.start), localDateTimeString(event.end));
                }
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    String taskType = parts[0].trim();
                    String description = parts[2].trim();

                    Task task;
                    switch (taskType) {
                        case "T":
                            task = new ToDos(description);
                            break;
                        case "D":
                            String deadline = parts[3].trim();
                            LocalDateTime formatDeadline = formatDate(deadline);
                            task = new Deadlines(description, formatDeadline);
                            break;
                        case "E":
                            String startTime = parts[3].trim();
                            String endTime = parts[4].trim();
                            LocalDateTime formatStartDate = formatDate(startTime);
                            LocalDateTime formatEndDate = formatDate(endTime);

                            task = new Events(description, formatStartDate, formatEndDate);
                            break;
                        default:
                            throw new BuddyException("Unknown task type found in file.");
                    }
                    if (parts[1].trim().equals("1")) {
                        task.markAsDone();
                    }

                    list.add(task);
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("    No previous task list found, starting fresh!");
        } catch (BuddyException e) {
            System.out.println("    OOPS!!! The task list file seems to be corrupted... ");
        }

        return list;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = loadTasks();
        System.out.println("    ---------------------------------------------------------");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ---------------------------------------------------------\n");

        while (true) {
            String userInput = scanner.nextLine().trim();

            try {
                System.out.println("\n    ---------------------------------------------------------");

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("    Bye! See ya soon!");
                    System.out.println("    ---------------------------------------------------------");
                    break;

                } else if (userInput.equalsIgnoreCase("list")) {
                    if (list.isEmpty()) {
                        System.out.println("    List is empty!!");
                    } else {
                        System.out.println("    Here are the tasks in your list: ");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.printf("    %d. [%s][%s] %s%n", i + 1, list.get(i).getTaskType(), list.get(i).getStatusIcon(), list.get(i).description);
                        }
                    }

                } else if (userInput.startsWith("mark") && userInput.matches("mark \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(4).trim()) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        if (list.get(taskIndex).isDone) {
                            System.out.println("    Uhh, its already been marked buddy!");
                        } else {
                            list.get(taskIndex).markAsDone();
                            System.out.println("    Nice one buddy! Marked this as done...");
                            System.out.printf("    [%s][%s] %s%n", list.get(taskIndex).getTaskType(), list.get(taskIndex).getStatusIcon(), list.get(taskIndex).description);
                        }
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else if (userInput.startsWith("unmark") && userInput.matches("unmark \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(6).trim()) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        if (!list.get(taskIndex).isDone) {
                            System.out.println("    Uhh, its already been unmarked buddy!");
                        } else {
                            System.out.println("    Alright buddy, let's give that task another shot!");
                            list.get(taskIndex).markAsUndone();
                            System.out.printf("    [%s][%s] %s%n", list.get(taskIndex).getTaskType(), list.get(taskIndex).getStatusIcon(), list.get(taskIndex).description);
                        }
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else if (userInput.isBlank()) {
                    throw new BuddyException("The description cannot be empty.");

                } else if (userInput.startsWith("todo")) {
                    String taskDesc = userInput.substring(4).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of a todo cannot be empty.");
                    }
                    Task t = new ToDos(taskDesc);
                    list.add(t);

                    System.out.println("    Gotcha! I've added this task: ");
                    System.out.printf("         [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                    System.out.printf("    Now, you have %d tasks in the list!", list.size());

                } else if (userInput.startsWith("deadline")) {
                    String taskDesc = userInput.substring(8).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of a deadline cannot be empty.");
                    }
                    String[] parts = taskDesc.split("/by ", 2);

                    if (parts.length == 2) {
                        String desc = parts[0].trim();
                        String day = parts[1].trim();
                        LocalDateTime date = formatDate(day);
                        Task t = new Deadlines(desc, date);
                        list.add(t);

                        System.out.println("    Gotcha! I've added this task: ");
                        System.out.printf("         [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                        System.out.printf("    Now, you have %d tasks in the list!", list.size());
                    } else {
                        throw new BuddyException("When do ya need to get it done by?\n    (include '/by' after your description followed by the deadline in the format, 'd/M/yyyy HHmm')");
                    }

                } else if (userInput.startsWith("event")) {
                    String taskDesc = userInput.substring(5).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of an event cannot be empty.");
                    }
                    String[] parts = taskDesc.split("/from ", 2);

                    if (parts.length == 2) {
                        String task = parts[0].trim();
                        String dateTimeAndEnd = parts[1].trim();

                        String[] dateTimeAndEndParts = dateTimeAndEnd.split("/to ", 2);

                        if (dateTimeAndEndParts.length == 2) {
                            String startTime = dateTimeAndEndParts[0].trim();
                            String endTime = dateTimeAndEndParts[1].trim();
                            LocalDateTime formattedStartTime = formatDate(startTime);
                            LocalDateTime formattedEndTime = formatDate(endTime);
                            Task t = new Events(task, formattedStartTime, formattedEndTime);
                            list.add(t);

                            System.out.println("    Gotcha! I've added this task: ");
                            System.out.printf("     [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                            System.out.printf("    Now, you have %d tasks in the list!", list.size());
                        } else {
                            throw new BuddyException("There's no end date?\n    (include '/to' after start date)");
                        }
                    } else {
                        throw new BuddyException("There's no start date?\n    (include '/from' after your description)");
                    }

                } else if (userInput.startsWith("delete") && userInput.matches("delete \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(6)) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        Task removedTask = list.remove(taskIndex);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.printf("      [%s][%s] %s%n", removedTask.getTaskType(), removedTask.getStatusIcon(), removedTask.description);
                        System.out.printf("    Now you have %d tasks in the list.%n", list.size());
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else {
                    throw new BuddyException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (BuddyException e) {
                System.out.println("    OOPS!!! " + e.getMessage());
            }

            System.out.println("\n    ---------------------------------------------------------\n");
        }

        scanner.close();
        saveTasks(list);
    }
}

