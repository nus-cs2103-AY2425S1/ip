import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatterBuilder;
public class Bao {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String file_Path = "./data/bao.txt";
    private static DateTimeFormatter inputDateFormat = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/d"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/dc"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/d"))
            .toFormatter();
    private static DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter dateOnlyFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    private static String baoHappy =
              "     ___\n"
            + "   /     \\\n"
            + "  /       \\\n"
            + " |  ^   ^  |\n"
            + " |    3    |\n"
            + " \\________/\n";

    private static String baoSad =
              "     ___\n"
            + "   /     \\\n"
            + "  /       \\\n"
            + " |  T   T  |\n"
            + " |    ^    |\n"
            + " \\________/\n";
    public static void main(String[] args) {
        loadFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Bao says hello! Bao's name is Bao but you can call me Bao");
        System.out.println("Bao is ready for instructions");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(baoSad);
                    System.out.println("Bye :( Come back soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    showTasks();
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark")) {
                    String number = input.substring(5).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList.get(index).mark();
                        saveTasks();
                        System.out.println("Bao has marked it as done!");
                        System.out.println(taskList.get(index));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to mark!");
                    }
                } else if (input.startsWith("unmark")) {
                    String number = input.substring(7).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList.get(index).unmark();
                        saveTasks();
                        System.out.println("Bao has marked it as not done!");
                        System.out.println(taskList.get(index));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to unmark!");
                    }
                } else if (input.startsWith("todo")) {
                    String task = input.substring(5).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "T");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline")) {
                    String task = input.substring(9).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "D");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    String task = input.substring(6).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "E");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete")) {
                    if (taskList.isEmpty()) {
                        throw new IllegalArgumentException("Bao's list is empty");
                    }
                    String number = input.substring(7).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        deleteTask(index);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to delete!");
                    }
                } else if (input.startsWith("on")) {
                    String inputDate = input.substring(3).trim();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        showTasksOn(date);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Bao needs a valid date format such as 2024-08-28");
                    }
                } else {
                    throw new UnsupportedOperationException("Bao needs a proper command :(");
                }
            } catch (Exception e){
                System.out.println(baoSad);
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void checkIndex(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Bao needs you to refer to tasks within the range!");
        }
    }

    private static void deleteTask(int index) {
        Task removed = taskList.remove(index);
        saveTasks();
        System.out.println("Bao has removed this task:");
        System.out.println(removed.toString());
        System.out.println("Bao is now tracking " + taskList.size() + " tasks");
    }

    private static void showTasks() {
        if (taskList.isEmpty()) {
            System.out.println("Bao is tracking nothing!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    private static void addTask(String taskDescription, String type) {
        if (taskList.size() < 100) {
            switch (type) {
                case "T" -> {
                    taskList.add(new ToDo(taskDescription));
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                }
                case "D" -> {
                    int byIndex = taskDescription.indexOf("/by ");
                    if (byIndex == -1) {
                        throw new IllegalArgumentException("Bao needs the deadline to be after /by");
                    } else if (byIndex == 0) {
                        throw new IllegalArgumentException("Bao needs a valid description of the task!");
                    }
                    String deadline = taskDescription.substring(byIndex + 4);
                    String description = taskDescription.substring(0, byIndex - 1);
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(deadline, inputDateFormat);
                        taskList.add(new Deadline(description, dateTime));
                        System.out.println("Bao got it! Bao is now tracking:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Bao needs a valid date format");
                    }

                }
                case "E" -> {
                    int fromIndex = taskDescription.indexOf("/from ");
                    int toIndex = taskDescription.indexOf("/to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new IllegalArgumentException("Bao needs the start and end to be after /from and /to " +
                                "and formatted properly");
                    } else if (fromIndex == 0) {
                        throw new IllegalArgumentException("Bao needs a valid description of the task!");
                    }
                    String fromString = taskDescription.substring(fromIndex + 6, toIndex - 1);
                    String toString = taskDescription.substring(toIndex + 4);
                    String description = taskDescription.substring(0, fromIndex - 1);
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a valid description of the task!");
                    }
                    try {
                        LocalDateTime from = LocalDateTime.parse(fromString, inputDateFormat);
                        LocalDateTime to = LocalDateTime.parse(toString, inputDateFormat);
                        taskList.add(new Event(description, from, to));
                        System.out.println("Bao got it! Bao is now tracking:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Bao needs a valid date format");
                    }
                }
            }
            saveTasks();
        } else {
            System.out.println(baoSad);
            System.out.println("Bao cannot remember so many things :(");
        }
    }

    private static void showTasksOn(LocalDate date) {
        System.out.println("Bao showing tasks on " + date.format(dateOnlyFormat) + ":");
        boolean found = false;
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDate().toLocalDate().equals(date)) {
                    System.out.println(deadlineTask);
                    found = true;
                }
            } else if (task instanceof Event){
                Event eventTask = (Event) task;
                if (eventTask.getFromDateTime().toLocalDate().equals(date)) {
                    System.out.println(eventTask);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Bao cannot find any tasks on this date!");
        }
    }

    private static void saveTasks() {
        try {
            File file = new File(file_Path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(file);
            for (Task task : taskList) {
                writer.println(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(baoSad);
            System.out.println("Bao could not save tasks: " + e.getMessage());
        }
    }

    private static void loadFile() {
        try {
            File file = new File(file_Path);
            // Break if file does not exist
            if (!file.exists()) {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(" \\| ");
                String type = lineParts[0];
                boolean isDone = lineParts[1].equals("1");
                String description = lineParts[2];
                String[] duration;
                switch (type) {
                    case "T" -> taskList.add(new ToDo(description));
                    case "D" -> {
                        LocalDateTime deadline = LocalDateTime.parse(lineParts[3], fileDateFormat);
                        taskList.add(new Deadline(description, deadline));
                    }
                    case "E" -> {
                        duration = lineParts[3].split("-");
                        LocalDateTime from = LocalDateTime.parse(duration[0], fileDateFormat);
                        LocalDateTime to = LocalDateTime.parse(duration[0], fileDateFormat);
                        taskList.add(new Event(description, from, to));
                    }
                }
                if (isDone) {
                    taskList.get(taskList.size() - 1).mark();
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Bao could not load this file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Bao was fed a corrupted file, starting new one!");
            taskList.clear();
        }
    }
}
