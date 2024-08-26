import javafx.util.converter.LocalDateTimeStringConverter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Max {
    private Scanner scanner = new Scanner(System.in);
    private List<Task> storedTasks;
    private static final String FILE_PATH = "./data/max.txt";

    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public Max() {
        storedTasks = new ArrayList<>();
        loadList();
    }
    public void runMax() {
        printHello();
        boolean running = true;

        while (running) {
            String text = scanner.nextLine().trim();
            try {
                if (text.equals("bye")) {
                    running = false;
                } else if (text.equals("list")) {
                    list();
                } else if (text.startsWith("mark")) {
                    int index = Integer.parseInt(text.replace("mark ", ""));
                    markDone(index);
                } else if (text.startsWith("unmark")) {
                    int index = Integer.parseInt(text.replace("unmark ", ""));
                    markNotDone(index);
                } else if (text.startsWith("deadline")) {
                    handleDeadline(text);
                } else if (text.startsWith("todo")) {
                    handleTodo(text);
                } else if (text.startsWith("event")) {
                    handleEvent(text);
                } else if (text.startsWith("delete")){
                    int index = Integer.parseInt(text.replace("delete ", ""));
                    deleteTask(index);
                } else {
                    throw new MaxException("What does that mean?:( Begin with todo, event, or deadline.");
                }
                saveTasks();
            } catch (MaxException e) {
                printLine();
                printMessage(e.getMessage());
            }
        }
        printBye();
    }

    private void handleDeadline(String text) throws MaxException {
        String[] temp = text.replace("deadline ", "").split(" /by ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        LocalDateTime LDT = parseDate(temp[1]);
        if (LDT != null) {
            Deadline deadline = new Deadline(temp[0], LDT);
            this.storedTasks.add(deadline);
            printTaskTypeAdded(deadline);
        } else {
            Deadline deadline = new Deadline(temp[0], temp[1]);
            this.storedTasks.add(deadline);
            printTaskTypeAdded(deadline);
        }
        saveTasks();
    }

    private void handleEvent(String text) throws MaxException {
        String[] temp = text.replace("event ", "").split(" /from ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        Event event = new Event(temp[0], temp[1]);
        this.storedTasks.add(event);
        printTaskTypeAdded(event);

        saveTasks();
    }

    private void handleTodo(String text) throws MaxException {
        String temp = text.replace("todo", "").trim();
        checkTask(temp);
        Todo todo = new Todo(temp);
        this.storedTasks.add(todo);
        saveTasks();
        printTaskTypeAdded(todo);
    }

    private void loadList() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    LocalDateTime LDT = parseDate(parts[3]);
                    if (LDT != null) {
                        task = new Deadline(parts[2], LDT);
                    } else {
                        task = new Deadline(parts[2], parts[3]);
                    }
                    break;
                case "E":
                    task = new Event(parts[2], parts[3]);
                    break;
                default:
                    throw new MaxException("Unknown task type found in file.");
                }
                if (parts[1].equals("1")) {
                    task.markDone();
                }
                storedTasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An IO Exception has occured. " + e.getMessage());
        } catch (MaxException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

    }

    private void saveTasks() throws MaxException {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : storedTasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MaxException("An IOException occurred.");
        }
    }

    private LocalDateTime parseDate(String date) throws MaxException {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime LDT = LocalDateTime.parse(date, converter);
            return LDT;
        } catch (DateTimeParseException e){
            throw new MaxException("Invalid date format! Please use d/M/yyyy HHmm. "
                    + "For example, '2/12/2024 1800'");
        }
    }

    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
    public void printTaskTypeAdded(Task task) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + storedTasks.size() + " tasks in the list.");
        printLine();
    }

    public void printMessage(String message) {
        System.out.println("\t " + message);
        printLine();
    }

    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\t What can I do for you?");
    }

    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void list() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.storedTasks.size(); i++) {
            int count = i + 1;
            System.out.println("\t " + count + "." + storedTasks.get(i).toString());
        }
        printLine();
    }

    public void deleteTask(int index) throws MaxException {
        int arrayIndexToDelete = index - 1;
        if (arrayIndexToDelete >= storedTasks.size() || arrayIndexToDelete < 0) {
            throw new MaxException("This task does not exist! Deletion unsuccessful.");
        }

        Task removedTask = storedTasks.remove(arrayIndexToDelete);

        printLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + removedTask.toString());
        System.out.println("\t Now you have " + storedTasks.size() + " tasks in the list.");
        printLine();

    }
    
    public void markDone(int index) {
        int arrayIndex = index - 1;
        storedTasks.get(arrayIndex).markDone();
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + storedTasks.get(arrayIndex).toString());
        printLine();
    }

    public void markNotDone(int index) {
        int arrayIndex = index - 1;
        storedTasks.get(arrayIndex).markNotDone();
        printLine();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + storedTasks.get(arrayIndex).toString());
        printLine();
    }

}
