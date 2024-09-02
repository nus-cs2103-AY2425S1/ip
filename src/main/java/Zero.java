import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
public class Zero {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;

        // read data file
        try {
            File fileObj = new File("./data/zero.txt");
            Scanner myReader = new Scanner(fileObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] items = data.split(",");
                switch (items[0]) {
                    case "T":
                        Todo newTodo = new Todo(items[2]);
                        if (items[1].equals("1")) {
                            newTodo.markAsDone();
                        };
                        tasks.add(newTodo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(items[2], items[3]);
                        if (items[1].equals("1")) {
                            newDeadline.markAsDone();
                        };
                        tasks.add(newDeadline);
                        break;
                    case "E":
                        Event newEvent = new Event(items[2], items[3], items[4]);
                        if (items[1].equals("1")) {
                            newEvent.markAsDone();
                        };
                        tasks.add(newEvent);
                        break;
                }
                taskCount++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //intro message.
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Zero");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Baibai!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    listTasks(tasks);
                } else if (input.startsWith("delete")) {
                    handleDelete(tasks, input);
                } else if (input.startsWith("mark")) {
                    handleMark(tasks, input, taskCount);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(tasks, input, taskCount);
                } else if (input.startsWith("todo")) {
                    handleTodo(tasks, input, taskCount);
                    taskCount++;
                } else if (input.startsWith("deadline")) {
                    handleDeadline(tasks, input, taskCount);
                    taskCount++;
                } else if (input.startsWith("event")) {
                    handleEvent(tasks, input, taskCount);
                    taskCount++;
                } else {
                    throw new ZeroException("分かりません");
                }
                // save the task after every command
                handleSave(tasks);
            } catch (ZeroException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" oopsie, " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    private static void handleSave(ArrayList<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter("./data/zero.txt");
            for (Task task : tasks) {
                myWriter.write(task.toFormatted());
            }
            myWriter.close();
        } catch (IOException e){
                System.out.println("An error occurred: ");
                e.printStackTrace();
        }
    }
    private static void handleDelete(ArrayList<Task> tasks, String input) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int index = Integer.parseInt(strArr[1]) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            Task removedTask = tasks.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to delete.");
        }
    }

    //its getting abit too long so gonna shift it here
    private static void handleMark(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            if (choice >= taskCount || choice < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            tasks.get(choice).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(choice));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("PLease enter a valid task number to mark.");
        }
    }

    private static void handleUnmark(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            if (choice >= taskCount || choice < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            tasks.get(choice).markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(choice));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to unmark.");
        }
    }

    private static void handleTodo(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        if (input.trim().equals("todo")) {
            throw new ZeroException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();  // extract description
        tasks.add(new Todo(description));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeadline(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2 || parts[0].trim().equals("deadline")) {
            throw new ZeroException("The description of a deadline or the date/time cannot be empty.");
        }
        String description = parts[0].substring(9).trim();  // extract description
        String by = parts[1].trim();  // extract deadline
        tasks.add(new Deadline(description, by));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEvent(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        String[] parts = input.split("/from | /to ");
        if (parts.length < 3 || parts[0].trim().equals("event")) {
            throw new ZeroException("The description of an event or the date/time cannot be empty.");
        }
        String description = parts[0].substring(6).trim();  // extract description
        String from = parts[1].trim();  // extract start time
        String to = parts[2].trim();  // extract end time
        tasks.add(new Event(description, from, to));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
