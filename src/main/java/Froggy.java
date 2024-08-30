import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Froggy {
    private static final String FILE_PATH = "./data/taskList.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Froggy(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        ui = new Ui();
        parser = new Parser(tasks);
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            Command c = parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        ui.showExit();
        storage.saveTasks(tasks.getTasks());
        ui.close();
    }

    public static void main(String[] args) {
        new Froggy("./data/taskList.txt").run();
    }
}
        /*Scanner scanner = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Froggy!\n"
                + "Enter tasks and I will store it.\n"
                + "Type 'list' to view tasks or 'bye' to exit.\n"
                + "_______________________________";
        String exit = "Bye. Hope to see you again soon!\n"
                + "_______________________________";
        String LINE = "_______________________________";

        System.out.println(greeting);
        tasks = loadTasks();

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Task List:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1).toString());
                }
                System.out.println("_______________________________");
            } else if (input.toLowerCase().startsWith("mark ") || input.equalsIgnoreCase("mark")) {
                if (input.length() <= 5) {
                    System.out.println("Error: Please enter the index of the task to be marked.");
                    System.out.println(LINE);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).setStatus(true);
                            System.out.println("Marked the following task as done:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(LINE);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(LINE);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'mark'.");
                        System.out.println(LINE);
                    }
                }
            } else if (input.toLowerCase().startsWith("unmark ") || input.equalsIgnoreCase("unmark")) {
                if (input.length() <= 7) {
                    System.out.println("Error: Please enter the index of the task to be unmarked.");
                    System.out.println(LINE);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).setStatus(false);
                            System.out.println("Marked the following task as undone:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(LINE);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(LINE);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'unmark'.");
                        System.out.println(LINE);
                    }
                }
            } else if (input.toLowerCase().startsWith("delete ") || input.equalsIgnoreCase("delete")){
                if (input.length() <= 7) {
                    System.out.println("Error: Please enter the index of the task to be deleted.");
                    System.out.println(LINE);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            System.out.println("Deleted the following task:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(LINE);
                            tasks.remove(index);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(LINE);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'delete'.");
                        System.out.println(LINE);
                    }
                }
            } else {
                //Handle Task input
                if (input.toLowerCase().startsWith("todo ")) {
                    if (input.length() == 5) {
                        System.out.println("Error: No description for ToDo task.");
                        System.out.println("Please input a description for the task");
                        System.out.println(LINE);
                    } else {
                        String desc = input.substring(5);
                        Todo current = new Todo(desc);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(LINE);
                    }
                } else if (input.toLowerCase().startsWith("deadline ")) {
                    int index = input.toLowerCase().indexOf("/by ");
                    if (index != -1) {
                        String desc = input.substring(9,index - 1);
                        String by = input.substring(index + 4);
                        Deadline current = new Deadline(desc, by);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(LINE);
                    }
                } else if (input.toLowerCase().startsWith("event ")) {
                    int index = input.toLowerCase().indexOf("/from ");
                    int index2 = input.toLowerCase().indexOf("/to ");
                    if (index != -1) {
                        String desc = input.substring(6, index - 1);
                        String from = input.substring(index + 6, index2 - 1);
                        String to = input.substring(index2 + 4);
                        Event current = new Event(desc, from, to);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(LINE);
                    }
                } else {
                    System.out.println("Input not recognised. Please input a valid action:");
                    System.out.println("todo, event, deadline, mark, unmark, list, bye");
                    System.out.println(LINE);
                }
            }
            saveTasks(tasks);
        }
        scanner.close();
    }

    private static List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            if (file.exists()) {
                System.out.println("Task list found.");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        boolean isDone = (line.charAt(2) == '1');
                        switch (line.charAt(0)) {
                        case 'T':
                            Task newTodo = new Todo(line.substring(4));
                            newTodo.setStatus(isDone);
                            taskList.add(newTodo);
                            break;
                        case 'D':
                            int index = line.indexOf('|');
                            String by = line.substring(index + 2);
                            Task newDeadline = new Deadline(line.substring(4, index - 1), by);
                            newDeadline.setStatus(isDone);
                            taskList.add(newDeadline);
                            break;
                        case 'E':
                            int index1 = line.indexOf('|');
                            int index2 = line.indexOf('|', index1 + 1);
                            String from = line.substring(index1 + 2, index2 - 1);
                            String to = line.substring(index2 + 2);
                            Task newEvent = new Event(line.substring(4, index1 - 1), from, to);
                            newEvent.setStatus(isDone);
                            taskList.add(newEvent);
                            break;
                        }
                    }
                }
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("No task list found. Created new task list.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read task list file.");
        }
        return taskList;
    }

    private static void saveTasks(List<Task> newTaskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task t : newTaskList) {
                writer.write(t.toTxt());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to save task list.");
        }
    }
}*/
