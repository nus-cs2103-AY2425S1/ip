import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Alex {

    public static final String byeMessage = "Bye. Hope to see you again soon!";
    public static final String LINE = "----------------------------------------------------";
    ArrayList<Task> list = new ArrayList<>();
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/alex.txt";

    public Alex() {
        createDirectory();
        loadTasksFromFile();
    }
    private void createDirectory() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String desc;
            while ((desc = reader.readLine()) != null) {

                Task task;
                if (desc.startsWith("[T]")) {
                    String details = desc.substring(6).trim();
                    task = new Todo(details);
                } else if (desc.startsWith("[D]")) {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String dueInter = info[1].substring(4).trim();
                    LocalDate dueDate = LocalDate.parse(dueInter);
                    task = new Deadline(item, dueDate);
                } else {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String startInter = info[1].substring(6).trim();
                    LocalDate start = LocalDate.parse(startInter);
                    String dueInter = info[2].substring(4).trim();
                    LocalDate dueBy = LocalDate.parse(dueInter);
                    task = new Event(item, start, dueBy);
                }
                task.isDone = desc.substring(4,5).equals("X") ? true : false;
                list.add(task);
            }
        } catch (IOException e) {
            try {
                File dataFile = new File(FILE_PATH);
                dataFile.createNewFile();
            } catch (IOException ee) {
                System.err.println("Error reading tasks from file: " + ee.getMessage());
            }
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : list) {
                writer.write(task.toBeSavedAsData());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
        }
    }
    public void handleList() {
        System.out.println(LINE + "\n" +
                "Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(LINE);
        scan();
    }
    public void handleMark(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        saveTasksToFile();
        scan();
    }
    public void handleUnmark(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        saveTasksToFile();
        scan();
    }
    public void handleTodo(String input) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out some details. Try again");
        } else {
            Todo todo = new Todo(description);
            list.add(todo);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + todo.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
        }
        saveTasksToFile();
        scan();
    }
    public void handleDeadline(String input) {
        String description = input.substring(8).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the deadline task. Try again");
        } else {
            try {
                String[] details = description.split("/", 2);
                String task = details[0].trim();
                String dueInter = details[1].substring(2).trim();
                LocalDate dueDate = LocalDate.parse(dueInter);
                if (task.isEmpty() || dueInter.isEmpty()) {
                    System.out.println("You missed out some details. Try again");
                } else {
                    Deadline deadline = new Deadline(task, dueDate);
                    list.add(deadline);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task: \n" + deadline.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date entered. Use this format: YYYY-MM-DD");
                System.out.println(LINE);
            }
        }
        saveTasksToFile();
        scan();
    }
    public void handleEvent(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the event task. Try again");
        } else {
            try {
                String[] details = description.split("/", 3);
                String task = details[0].trim();
                String startInter = details[1].substring(4).trim();
                LocalDate startDate = LocalDate.parse(startInter);
                String endInter = details[2].substring(2).trim();
                LocalDate endDate = LocalDate.parse(endInter);
                if (task.isEmpty() || startInter.isEmpty() || endInter.isEmpty()) {
                    System.out.println("You missed out some details. Try again");
                } else {
                    Event event = new Event(task, startDate, endDate);
                    list.add(event);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task: \n" + event.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date(s) entered. Use this format: YYYY-MM-DD");
                System.out.println(LINE);
            }
        }
        saveTasksToFile();
        scan();
    }
    public void handleDelete(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            list.remove(index);
            System.out.println("OK, I've deleted this task: \n" + task.toString() +
                    "\n Now you have " + list.size() + " tasks left in the list.");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        saveTasksToFile();
        scan();
    }
    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println(byeMessage);
            System.out.println(LINE);
        }
        else if (userInput.equalsIgnoreCase("list")) {
            handleList();
        } else if (userInput.startsWith("mark")) {
            handleMark(userInput);
        } else if (userInput.startsWith("unmark")) {
            handleUnmark(userInput);
        } else if (userInput.startsWith("todo")) {
            handleTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            handleDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            handleEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            handleDelete(userInput);
        } else {
            System.out.println(LINE);
            System.out.println("Sorry, I don't understand that command. Did you make a typo?");
            System.out.println(LINE);
            scan();
        }
    }
    public static void main(String[] args) {
        Alex alex = new Alex();

        System.out.println(LINE);
        System.out.println("Hello! I'm Alex 👋🏼🤖 \n" +
                    "What can I do for you? ");
        System.out.println(LINE);

        alex.scan();
    }
}
