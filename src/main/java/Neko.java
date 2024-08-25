import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Neko {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String ADD_COMMAND = "add";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon meow ฅ ฅ";
    private static final String FILE_PATH = "./data/neko.txt";
    private static final String DIRECTORY_PATH = "./data";
    private static final ArrayList<Task> taskList = new ArrayList<>(100);
    private static final String DATE_TIME_FORMAT = "eee, d MMM uuuu h:mma";
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter parseFormatter= DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static void main(String[] args) {

        File f = new File(FILE_PATH);
        System.out.println(GREETING_MESSAGE);

        if (f.exists()) {
            try {
                loadFile(f);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        String input = scanner.nextLine();

        while (!input.equals(EXIT_COMMAND)) {
            try {
                handleInput(input);
            } catch (NekoException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        System.out.println(EXIT_MESSAGE);
        scanner.close();
    }

    private static void loadFile(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            char c = str.charAt(0);
            Task task = null;
            String[] parts;
            boolean isDone = false;
            String taskName;
            switch (c) {
            case 'T':
                parts = str.split("\\|", 3);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                task = new Todo(taskName);
                break;
            case 'D':
                parts = str.split("\\|", 4);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                String deadline = parts[3].trim();
                task = new Deadline(taskName, LocalDateTime.parse(deadline, parseFormatter));
                break;
            case 'E':
                parts = str.split("\\|", 5);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                String start = parts[3].trim();
                String end = parts[4].trim();
                task = new Event(taskName, LocalDateTime.parse(start, parseFormatter), LocalDateTime.parse(end, parseFormatter));
                break;
            }
            if (task != null)  {
                if (isDone) task.markAsDone();
                taskList.add(task);
            }
        }
    }

    public static void writeFile(String text) throws IOException {
        // Create the file if it doesn't exist
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(text);
        fw.close();
    }

    private static void handleInput(String input) throws NekoException {
        if (input.equals(LIST_COMMAND)) {
            listTask();
        } else if (input.startsWith(MARK_COMMAND)) {
            markTask(input);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            unmarkTask(input);
        } else if (input.equals(ADD_COMMAND)) {
            addTask();
        } else if (input.startsWith(DELETE_COMMAND)) {
            deleteTask(input);
        } else {
            throw new NekoException("Gomenasai! Neko doesn't know what that means :(");
        }
    }

    private static void addTask() throws NekoException {
        System.out.println("Nyaa~ What kind of task would you like to add today?\n  1: Todo (Just a simple task meow)\n" +
                "  2: Deadline (Something with a time limit meow)\n  3: Event (A task with a start and end time meow)\n" +
                "Please enter the number of the task type you'd like to add meow~");

        Task task = null;
        while (task == null) {
            String taskType = scanner.nextLine().trim();
            switch (taskType) {
                case "1":
                    task = addTodo();
                    break;
                case "2":
                    task = addDeadline();
                    break;
                case "3":
                    task = addEvent();
                    break;
                default:
                    System.out.println("Oops /ᐠ > ˕ <マ, that's not a valid option meow! Please enter 1, 2, or 3 meow!");
                    break;
            }
        }
        taskList.add(task);
        System.out.println("Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n "
                + task + "\nNow you have " + taskList.size() + " tasks in your list meow");
    }

    private static String getTaskName() {
        while (true) {
            System.out.println("What will this task be called meow?");
            String taskName = scanner.nextLine().trim();
            if (!taskName.isEmpty()) {
                return taskName;
            }
            System.out.println("Meow /ᐠ > ˕ <マ The description of a task cannot be empty, please try again meow");
        }
    }

    private static LocalDateTime getDateTime(String prompt) {
        while (true) {
            System.out.println(prompt + " (e.g., 2024-01-01T13:00 for January 1, 2024, at 1:00 PM)");
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Meow /ᐠ > ˕ <マ Invalid date/time format ! Please use 'yyyy-MM-ddTHH:mm' format.");
            }
        }
    }
    private static Task addTodo() {
        String taskName = getTaskName();
        try {
            writeFile("T | 0 | " + taskName + "\n");
            return new Todo(taskName);
        } catch (IOException e) {
            System.out.println("Nyaa! There was an error saving your task: " + e.getMessage());
        }
        return null;
    }

    private static Task addDeadline() {
        String taskName = getTaskName();
        LocalDateTime deadline = getDateTime("Enter the deadline date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        Task task = new Deadline(taskName, deadline);
        try {
            writeFile("D | 0 | " + taskName + " | " + task.getTime() + "\n");
        } catch (IOException e) {
            System.out.println("Nyaa! There was an error saving your task: " + e.getMessage());
        }
        return task;
    }

    private static Task addEvent() {
        String taskName = getTaskName();
        LocalDateTime startDateTime = getDateTime("Enter the start date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        LocalDateTime endDateTime = getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");

        while (endDateTime.isBefore(startDateTime)) {
            System.out.println("End time cannot be before start time meow! Please enter the end date and time again");
            endDateTime = getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        }

        Task task = new Event(taskName, startDateTime, endDateTime);
        try {
            writeFile("E | 0 | " + taskName + " | " + task.getTime() + "\n");
        } catch (IOException e) {
            System.out.println("Nyaa! There was an error saving your task: " + e.getMessage());
        }
        return task;
    }
    private static void listTask() {
        if (taskList.isEmpty()) {
            System.out.println("You don't have any tasks yet meow!");
            return;
        }
        System.out.println("Here are the task in your list meow:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + "." + task);
        }
    }

    private static void markTask(String input) throws NekoException {
        int index = Integer.parseInt(input.substring(MARK_COMMAND.length()).trim()) - 1;
        checkValidIndex(index);
        Task task = taskList.get(index);
        if (task.markAsDone()) {
            System.out.println("Nice meow! I've marked this task as done:\n " + task);
        } else {
            throw new NekoException("The task is already marked as done!");
        }
    }

    private static void unmarkTask(String input) throws NekoException {
        int index = Integer.parseInt(input.substring(UNMARK_COMMAND.length()).trim()) - 1;
        checkValidIndex(index);
        Task task = taskList.get(index);
        if (task.markAsNotDone()) {
            System.out.println("Ok meow, I've marked this task as not done yet:\n " + task);
        } else {
            throw new NekoException("The task is not marked as done yet!");
        }
    }

    private static void deleteTask(String input) throws NekoException {
        int index = Integer.parseInt(input.substring(DELETE_COMMAND.length()).trim()) - 1;
        checkValidIndex(index);
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Noted meow. I've removed this task\n " + task +"\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void checkValidIndex(int index) throws NekoException {
        if (index >= taskList.size()) {
            throw new NekoException("You only have " + taskList.size() + " tasks now meow!");
        }
        if (index < 0 || index >= 100) {
            throw new NekoException("Invalid task number meow! Please enter a number between 1 and " + taskList.size() + ".");
        }
    }
}
