import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hamyo {

    private static boolean active = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            greet();
            loadData();
            while (active) {
                try {
                    String command = scanner.nextLine();
                    String commandType = command.split(" ")[0];
                    String commandFields = command.substring(commandType.length());
                    switch (commandType) {
                    case "todo":
                        add(TaskType.TODO, commandFields);
                        break;
                    case "deadline":
                        add(TaskType.DEADLINE, commandFields);
                        break;
                    case "event":
                        add(TaskType.EVENT, commandFields);
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        mark(commandFields);
                        break;
                    case "unmark":
                        unmark(commandFields);
                        break;
                    case "delete":
                        delete(commandFields);
                        break;
                    case "bye":
                        terminate();
                        break;
                    default:
                        throw new HamyoException("Invalid Command!");
                    }
                    saveData();
                } catch (HamyoException e) {
                    System.out.println(e.toString());
                    printLine();
                }
            }
            scanner.close();
        } catch (HamyoException e) {
            System.out.println(e.toString());
            printLine();
        }
    }

    public static void printLine() {
        System.out.println("________________________________________________________________________________");
    }

    private static void printLogo() {
        System.out.println(
            "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
            "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$");
    }

    public static void greet() {
        printLine();
        printLogo();
        System.out.println("\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?");
        printLine();
    }

    public static void terminate() {
        active = false;
        System.out.println("Annyeong! Till we meet again. <3");
        printLine();
    }

    public static void add (TaskType taskType, String task) throws HamyoException {
        if (taskType.equals(TaskType.TODO)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: todo [task description]");
            }
            tasks.add(new ToDo(new String[]{task.substring(1)}));
        } else if (taskType.equals(TaskType.DEADLINE)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            String[] split = task.substring(1).split(" /by ");
            if (split.length != 2) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            tasks.add(new Deadline(split));
        } else if (taskType.equals(TaskType.EVENT)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            String[] split = task.substring(1).split(" /from | /to ");
            if (split.length != 3) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            tasks.add(new Event(split));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.printf("There are %d tasks in the list now.\n", tasks.size());
        printLine();
    }

    public static void listTasks() throws HamyoException {
        System.out.println("These are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public static void mark(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: mark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: mark [index]");
            }
            tasks.get(index).mark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: mark [index]");
        }
    }

    public static void unmark(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: unmark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: unmark [index]");
            }
            tasks.get(index).unmark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: unmark [index]");
        }
    }

    public static void delete(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: delete [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: delete [index]");
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index).toString());
            tasks.remove(index);
            System.out.printf("There are %d tasks in the list now.\n", tasks.size());
            printLine();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: delete [index]");
        }
    }

    public static void loadData() throws HamyoException {
        try {
            File savedTasks = new File("./savedTasks.txt");
            if (!savedTasks.exists()) {
                savedTasks.createNewFile();
            }
            Scanner scannedTasks = new Scanner(savedTasks);
            int currTask = 0;
            while (scannedTasks.hasNext()) {
                currTask++;
                String[] task = scannedTasks.nextLine().split(" \\| ");
                switch (task[0]) {
                case "T":
                    tasks.add(new ToDo(new String[]{task[2]}));
                    break;
                case "D":
                    tasks.add(new Deadline(new String[]{task[2], task[3]}));
                    break;
                case "E":
                    tasks.add(new Event(new String[]{task[2], task[3], task[4]}));
                    break;
                default:
                    throw new HamyoException("Invalid case " + task[0] + ".");
                }
                switch (task[1]) {
                case "1":
                    mark(" " + currTask);
                    break;
                case "0":
                    break;
                default:
                    throw new HamyoException("Invalid boolean " + task[1] + ".");
            }
            }
        } catch (HamyoException e) {
            throw new HamyoException("Possible File Corruption. " + e.getMessage());
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }

    public static void saveData() throws HamyoException {
        try {
            FileWriter fw = new FileWriter("./savedTasks.txt");
            StringBuilder newData = new StringBuilder();
            for (Task task : tasks) {
                newData.append(task.toFileFormat()).append(System.lineSeparator());
            }
            //System.out.println(newData.toString());
            fw.write(newData.toString());
            fw.close();
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }
}
