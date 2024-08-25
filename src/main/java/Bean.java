import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Bean {
    private static final String NAME = "Bean";
    private static final String FILE_PATH = "./data/bean.txt";
    private Scanner scanner;
    private List<Task> tasks = new ArrayList<>();
    private int numOfTasks;

    public Bean() {
        this.scanner = new Scanner(System.in);
        loadTasks();
    }

    public void startConversation() {
        greetAndAsk();
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String details = parts.length > 1 ? parts[1] : "";

                if (isBye(command)) {
                    break;
                } else if (command.equals("list")) {
                    printList();
                } else if (command.equals("mark")) {
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException("Please key in a task number.");
                    }
                    markTask(Integer.parseInt(details));
                } else if (command.equals("unmark")) {
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException("Please key in a task number.");
                    }
                    unmarkTask(Integer.parseInt(details));
                } else if (command.equals("delete")) {
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException("Please key in a task number.");
                    }
                    deleteTask(Integer.parseInt(details));
                } else if (command.equals("todo")) {
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    TodoTask tt = new TodoTask(details);
                    tasks.add(tt);
                    numOfTasks++;
                    echo(tt);
                } else if (command.equals("deadline")){
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] deadlineParts = details.split(" /by ");
                    DeadlineTask dt = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                    tasks.add(dt);
                    numOfTasks++;
                    echo(dt);
                } else if (command.equals("event")){
                    if (details.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] eventParts = details.split(" /from | /to ");
                    EventTask et = new EventTask(eventParts[0], eventParts[1], eventParts[2]);
                    tasks.add(et);
                    numOfTasks++;
                    echo(et);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (EmptyDescriptionException | UnknownCommandException e) {
                System.out.println("______________________________");
                System.out.println(e.getMessage());
                System.out.println("______________________________");
            }

        }
        exit();

    }

    private void greetAndAsk() {
        System.out.println("______________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("______________________________");
    }

    private void exit() {
        System.out.println("______________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("______________________________");
        scanner.close();
    }

    private boolean isBye(String s) {
        return s.equalsIgnoreCase("bye");  // Ignoring case sensitivity for better user experience
    }

    private void echo(Task task) {
        System.out.println("______________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
        saveTasks();
    }

    private void printList() {
        System.out.println("______________________________");
        System.out.println("Here are the tasks in your list");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    private void markTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        tasks.get(taskIndex).completeTask();

        System.out.println("______________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("______________________________");
        saveTasks();
    }

    private void unmarkTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        tasks.get(taskIndex).undoTask();
        System.out.println("______________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("______________________________");
        saveTasks();
    }

    private void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task removedTask = tasks.remove(taskIndex);
        numOfTasks--;
        System.out.println("______________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
        saveTasks();
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create directory if it doesn't exist
                file.createNewFile(); // Create file if it doesn't exist
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        TodoTask todo = new TodoTask(description);
                        if (isDone) todo.completeTask();
                        tasks.add(todo);
                        break;
                    case "D":
                        String by = parts[3];
                        DeadlineTask deadline = new DeadlineTask(description, by);
                        if (isDone) deadline.completeTask();
                        tasks.add(deadline);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        EventTask event = new EventTask(description, from, to);
                        if (isDone) event.completeTask();
                        tasks.add(event);
                        break;
                }
                numOfTasks++;
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.startConversation();
    }
}