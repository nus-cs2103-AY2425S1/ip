import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class SeanBot {
    private static final String PATH = "./data/seanbot.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] part = userInput.split(" ", 2);
                String first = part[0];

                if (first.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveTasks();
                    break;
                } else if (first.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (first.equals("mark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of mark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks.get(second).Done();
                    saveTasks();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(second));
                } else if (first.equals("unmark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of unmark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks.get(second).Undone();
                    saveTasks();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(second));
                } else if (first.equals("todo")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(part[1]);
                    tasks.add(todo);
                    saveTasks();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("deadline")) {
                    String[] specifications = part[1].split(" /by ");
                    if (specifications.length < 2) {
                        throw new SeanBotException("The description or a deadline cannot be empty.");
                    }
                    Task deadline = new Deadline(specifications[0], specifications[1]);
                    tasks.add(deadline);
                    saveTasks();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("event")) {
                    String[] firstPart = part[1].split(" /from ", 2);
                    if (firstPart.length < 2) {
                        throw new SeanBotException("The description of an event cannot be empty.");
                    }
                    String[] secondPart = firstPart[1].split(" /to ", 2);
                    if (secondPart.length < 2) {
                        throw new SeanBotException("The end time of an event cannot be empty.");
                    }
                    Task event = new Event(firstPart[0].trim(), secondPart[0].trim(), secondPart[1].trim());
                    tasks.add(event);
                    saveTasks();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("delete")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number to delete must be valid.");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number to delete must be valid.");
                    }
                    Task remove = tasks.remove(second);
                    saveTasks();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + remove);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new SeanBotException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (SeanBotException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } 
        }
        scanner.close();
    }

    private static void saveTasks() {
        try (FileWriter fw = new FileWriter(PATH);) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close(); 
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    private static void loadTasks() {
        File file = new File(PATH);
    
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    
        if (!file.exists()) {
            try {
                file.createNewFile();  
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
                return;
            }
        }
    
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }
                if (task != null && parts[1].equals("1")) {
                    task.Done();
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
}   
