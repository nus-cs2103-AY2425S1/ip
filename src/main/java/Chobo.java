import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Chobo {
    private static final String FILE_PATH = "./data/chobo.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String line = "----------------------------------------";
    private static int totalTask = 0;
    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(line);
        InputException deadlineError = new InputException("deadline");
        InputException eventError = new InputException("event");
        InputException todoError = new InputException("todo");
        InputException invalidTaskNumber = new InputException("id");
        while (true) {
            try {
                String original = scanner.nextLine();
                String[] input = original.split(" ", 2);
                String action = input[0];
                if (action.equals("bye")) {
                    break;
                } else if (action.equals("list")) {
                    listTask();
                } else if (action.equals("mark") || action.equals("unmark") || action.equals("delete")) {
                    if (input.length<2) {
                        throw invalidTaskNumber;
                    }
                    int taskId = Integer.parseInt(input[1]) - 1;
                    if (taskId > totalTask - 1 || taskId < 0) {
                        throw invalidTaskNumber;
                    }
                    if (action.equals("mark")) {
                        markTask(tasks.get(taskId));
                    } else if (action.equals("unmark")) {
                        unmarkTask(tasks.get(taskId));
                    } else {
                        deleteTask(taskId);
                    }
                } else if (action.equals("todo")) {
                    if(input.length<2){
                        throw todoError;
                    }
                    addTask(new ToDo(input[1], false));
                } else if (action.equals("deadline")){
                    if(input.length<2){
                        throw deadlineError;
                    }
                    String[] part = input[1].split("/by", 2);
                    if(part.length<2){
                        throw deadlineError;
                    }
                    addTask(new Deadline(part[0], false, part[1]));
                } else if (action.equals("event")) {
                    if(input.length<2){
                        throw eventError;
                    }
                    String[] part = input[1].split("/from", 2);
                    if(part.length<2){
                        throw eventError;
                    }
                    String[] dates = part[1].split("/to", 2);
                    if(dates.length<2){
                        throw eventError;
                    }
                    addTask(new Event(part[0], false, dates[0], dates[1]));
                } else {
                    throw new InputException("Invalid");
                }
            }
            catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }
    
    public static void saveTasks() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("no dirc");
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString()+ System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't save tasks.");
        }
    }
    public static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String task = parts[0];
                boolean isDone = parts[1].equals("1");
                String name =  parts[2];

                if(task.equals("T")) {
                    tasks.add(new ToDo(name,isDone));
                    totalTask++;
                } else if(task.equals("D")) {
                    String by = parts[3];
                    tasks.add(new Deadline(name,isDone,by));
                    totalTask++;
                } else {
                    String from = parts[3];
                    String to = parts[4];
                    tasks.add(new Event(name,isDone,from, to));
                    totalTask++;
                }
            }
        } catch (IOException e) {
            System.out.println("error loading tasks");
        }
    }

    private static void markTask(Task task) {
        System.out.println(line);
        task.mark();
        saveTasks();
        System.out.println("Nice! I have marked this task as done:\n" + task);
        System.out.println(line);
    }

    private static void unmarkTask(Task task) {
        System.out.println(line);
        task.unmark();
        saveTasks();
        System.out.println("OK, I have marked this task as not done yet\n" + task);
        System.out.println(line);
    }
    private static void listTask() {
        System.out.println(line);
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + "." + tasks.get(i));
        }
        System.out.println(line);
    }

    private static void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("added: " + newTask  );
        totalTask++;
        System.out.println(totalTask + " task(s) in the list");
        System.out.println(line);
        saveTasks();
    }

    private static void deleteTask(int taskPos) {
        Task removedTask = tasks.remove(taskPos);
        saveTasks();
        System.out.println(line);
        System.out.println("deleted: " + removedTask);
        totalTask--;
        System.out.println(totalTask + "task(s) left in the list");
    }
}





