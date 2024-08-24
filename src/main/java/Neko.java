import java.io.FileNotFoundException;
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
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon meow ฅ ฅ";
    private static final String FILE_PATH = "./data/neko.txt";
    private static final String DIRECTORY_PATH = "./data";
    private static final ArrayList<Task> taskList = new ArrayList<>(100);

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

        Scanner scanner = new Scanner(System.in);
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
                task = new Deadline(taskName, deadline);
                break;
            case 'E':
                parts = str.split("\\|", 5);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                String start = parts[3].trim();
                String end = parts[4].trim();
                task = new Event(taskName, start, end);
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
        System.out.println("Writing..");
        fw.close();
    }

    private static void handleInput(String input) throws NekoException {
        if (input.equals(LIST_COMMAND)) {
            listTask();
        } else if (input.startsWith(MARK_COMMAND)) {
            markTask(input);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            unmarkTask(input);
        } else if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
            addTask(input);
        } else if (input.startsWith(DELETE_COMMAND)) {
            deleteTask(input);
        } else {
            throw new NekoException("Gomenasai! Neko doesn't know what that means :(");
        }
    }

    private static void addTask(String input) throws NekoException {
        Task task;
        if (input.startsWith(TODO_COMMAND)) {
            String taskName = input.substring(TODO_COMMAND.length()).trim();
            if (taskName == "") {
                throw new NekoException("The description of a todo cannot be empty!");
            }
            task = new Todo(taskName);
            try {
                writeFile("T | 0 | " + taskName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            String[] parts = input.split("/", 2);

            if (parts.length < 2 || !parts[1].startsWith("by")) {
                throw new NekoException("The deadline must contain a '/by' to separate the task description and the deadline!");
            }

            String taskName = parts[0].substring(DEADLINE_COMMAND.length()).trim();
            String deadline = parts[1].substring(2).trim();

            if (taskName.isEmpty()) {
                throw new NekoException("The description of a deadline cannot be empty!");
            }
            if (deadline.isEmpty()) {
                throw new NekoException("The deadline of a deadline cannot be empty!");
            }
            task = new Deadline(taskName, deadline);
            try {
                writeFile("D | 0 | " + taskName + " | " + deadline);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String[] parts = input.split("/", 3);

            if (parts.length < 3 || !parts[1].startsWith("from") || !parts[2].startsWith("to")) {
                throw new NekoException("The event must contain '/from' and '/to' to specify the start and end times!");
            }
            String taskName = parts[0].substring(EVENT_COMMAND.length()).trim();
            String start = parts[1].substring(4).trim();
            String end = parts[2].substring(2).trim();

            if (taskName.isEmpty()) {
                throw new NekoException("The description of an event cannot be empty!");
            }
            if (start.isEmpty()) {
                throw new NekoException("The start time of an event cannot be empty!");
            }
            if (end.isEmpty()) {
                throw new NekoException("The end time of an event cannot be empty!");
            }
            task = new Event(taskName, start, end);
            try {
                writeFile("D | 0 | " + taskName + " | " + start + " | " + end);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        taskList.add(task);
        System.out.println("Got it meow. I've added this task ฅ/ᐠᓀ ﻌ ᓂマ\n "
                + task + "\nNow you have " + taskList.size() + " tasks in your list meow");
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
            throw new NekoException("You only have " + taskList.size() + " tasks now!");
        }
        if (index < 0 || index >= 100) {
            throw new NekoException("Invalid task number! Please enter a number between 1 and " + taskList.size() + ".");
        }
    }
}
