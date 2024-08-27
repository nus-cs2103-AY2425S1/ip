import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SecondMind {
    private static final String line = "____________________________________________________________";
    private static final String logo = "SecondMind";
    private static final String DATA_FILE_PATH = "../../../SecondMind.txt";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private static int taskCount() {
        return taskList.size();
    }

    private static void printLineSeparator() {
        System.out.println(line);
    }

    private static void printErrorMessage(Exception e) {
        printLineSeparator();
        System.out.println(e);
        printLineSeparator();
    }

    private static void greetUser() {
        printLineSeparator();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void appendToFile(String data) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
        fw.write(data);
        fw.close();
    }

    private static Task createToDo(String[] taskInfo) throws EmptyToDoException {
        if (taskInfo.length == 1) {
            throw new EmptyToDoException();
        }
        taskInfo[0] = "";
        String taskDescription = String.join(" ", taskInfo);
        taskDescription = taskDescription.substring(1);
        String data = "\nT|0|" + taskDescription;
        try {
            appendToFile(data);
        } catch (IOException e) {
            printErrorMessage(e);
        }
        return new ToDoTask(taskDescription);
    }

    private static Task createDeadline(String task) {
        String[] taskInfo = task.split("/");
        taskInfo = String.join(" ", taskInfo).split(" by ");
        //Prefix of taskInfo[0] is "deadline "
        String taskDescription = taskInfo[0].substring(9);
        //Strip whitespace at the end of taskDescription
        taskDescription = taskDescription.trim();
        String taskDeadline = taskInfo[1];
        String data = "\nD|0|" + taskDescription + "|" + taskDeadline;
        try {
            appendToFile(data);
        } catch (IOException e) {
            printErrorMessage(e);
        }
        return new DeadlineTask(taskDescription, taskDeadline);
    }

    private static Task createEvent(String task) {
        String[] taskInfo = task.split(" /", 3);
        //Prefix of taskInfo[0] is "event "
        String taskDescription = taskInfo[0].substring(6);
        //Prefix of taskInfo[1] is "from "
        String taskStart = taskInfo[1].substring(5);
        //Prefix of taskInfo[2] is "to "
        String taskEnd = taskInfo[2].substring(3);
        return new EventTask(taskDescription, taskStart, taskEnd);
    }

    private static Task createTask(String task) throws EmptyCommandException, EmptyToDoException, UnknownCommandException {
        String[] taskInfo = task.split(" ");
        String taskType = taskInfo[0];
        if (taskInfo[0].equals("")) {
            throw new EmptyCommandException();
        } else if (taskType.equals("todo")) {
            try {
                return createToDo(taskInfo);
            } catch (EmptyToDoException e) {
                throw e;
            }
        } else if (taskType.equals("deadline")) {
            return createDeadline(task);
        } else if (taskType.equals("event")) {
            return createEvent(task);
        } else {
            throw new UnknownCommandException();
        }
    }

    private static void addToTaskList(String task) {
        try {
            Task curr = createTask(task);
            taskList.add(curr);
            printLineSeparator();
            System.out.println("Got it. I have added the following task:\n\t" + curr);
            System.out.println("You have a grand total of " + taskCount() + " task(s)");
            printLineSeparator();
        } catch (EmptyCommandException e) {
            printErrorMessage(e);
        } catch (EmptyToDoException e) {
            printErrorMessage(e);
        } catch (UnknownCommandException e) {
            printErrorMessage(e);
        }
    }

    private static void printTaskList() {
        printLineSeparator();
        for (int i = 0; i < taskCount(); i++) {
            System.out.println(String.format("%d. %s", i+1, taskList.get(i)));
        }
        printLineSeparator();
    }

    private static void markAsDone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0) {
            printLineSeparator();
            System.out.println("Warning! Task numbering starts from 1!");
            printLineSeparator();
            return;
        } else if (taskNumber > taskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        Task curr = taskList.get(taskNumber-1);
        curr.markAsDone();
        printLineSeparator();
        System.out.println("Well done! You have completed the following task:");
        System.out.println(curr);
        printLineSeparator();
    }

    private static void markAsUndone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0) {
            printLineSeparator();
            System.out.println("Warning! Task numbering starts from 1!");
            printLineSeparator();
            return;
        } else if (taskNumber > taskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        Task curr = taskList.get(taskNumber-1);
        curr.markAsUndone();
        printLineSeparator();
        System.out.println("I've marked the following task as incomplete:");
        System.out.println(curr);
        printLineSeparator();
    }

    private static void delete(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0) {
            printLineSeparator();
            System.out.println("Warning! Task numbering starts from 1!");
            printLineSeparator();
            return;
        } else if (taskNumber > taskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        Task curr = taskList.get(taskNumber-1);
        taskList.remove(taskNumber-1);
        printLineSeparator();
        System.out.println("I've removed the following task:");
        System.out.println("\t" + curr);
        System.out.println("You have a grand total of " + taskCount() + " task(s)");
        printLineSeparator();
    }

    private static boolean processInput(String input) {
        String[] newInput = input.split(" ");
        String command = newInput[0];
        if (command.equals("bye")) {
            return true;
        } else if (command.equals("mark")) {
            try {
                markAsDone(Integer.parseInt(newInput[1]));
            } catch (NumberFormatException e) {
                printErrorMessage(new Exception("Warning! Invalid number format has been detected!"));
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskCount() + " tasks in your task list.");
                printLineSeparator();
            }
        } else if (command.equals("unmark")) {
            try {
                markAsUndone(Integer.parseInt(newInput[1]));
            } catch (NumberFormatException e) {
                printErrorMessage(new Exception("Warning! Invalid number format has been detected!"));
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskCount() + " tasks in your task list.");
                printLineSeparator();
            }
        } else if (command.equals("delete")) {
            try {
                delete(Integer.parseInt(newInput[1]));
            } catch (NumberFormatException e) {
                printErrorMessage(new Exception("Warning! Invalid number format has been detected!"));
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskCount() + " tasks in your task list.");
                printLineSeparator();
            }
        } else if (command.equals("list")) {
            printTaskList();
        } else {
            addToTaskList(input);
        }
        return false;
    }

    private static void getInput() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            boolean exit = processInput(reader.nextLine());
            if (exit) {
                break;
            }
        }
        reader.close();
    }

    private static void exitProgram() {
        printLineSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    private static Task textToTask(String text) {
        String[] taskInfo = text.split("\\|");
        String taskType = taskInfo[0];
        Task curr;
        if (taskType.equals("T")) {
            curr = new ToDoTask(taskInfo[2]);
        } else if (taskType.equals("D")) {
            curr = new DeadlineTask(taskInfo[2], taskInfo[3]);
        } else {
            curr = new EventTask(taskInfo[2], taskInfo[3], taskInfo[4]);
        }
        if (taskInfo[1] == "1") {
            curr.markAsDone();
        }
        return curr;
    }

    private static void loadTaskList() {
        try {
            File f = new File(DATA_FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task curr = textToTask(s.nextLine());
                taskList.add(curr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void main(String[] args) {
        loadTaskList();
        greetUser();
        getInput();
        exitProgram();
    }
}
