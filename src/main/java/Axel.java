import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Axel {
    private static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        //Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("YO! YO! It's Axel, at your service.");
        System.out.println("Hit me up with anything that I can help with!");
        System.out.println("____________________________________________________________");
        //Repeatedly prompts for user's input
        while (true) {
            //Reads user's input
            userInput = scanner.nextLine();
            try {
                    //Display tasks
                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    displayTasks();
                    System.out.println("____________________________________________________________");
                    //Mark task as done
                } else if (userInput.startsWith("mark")) {
                    markTaskAsDone(userInput);
                    //Mark task as not done
                } else if (userInput.startsWith("unmark")) {
                    markTaskAsNotDone(userInput);
                    //Add to do task
                } else if (userInput.startsWith("todo")) {
                    toDo(userInput);
                    //Add deadline task
                } else if (userInput.startsWith("deadline")) {
                    deadline(userInput);
                    //Add event task
                } else if (userInput.startsWith("event")) {
                    event(userInput);
                    //Delete task
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                    //Exit message
                } else if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Sad to see you go... goodbye!!");
                    System.out.println("____________________________________________________________");
                    break;
                } else {
                    throw new UnrecognisedCommandException();
                }
            } catch (AxelException e) {
                System.out.println("____________________________________________________________");
                System.out.println("HOLD YOUR HORSES!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
    private static void toDo(String userInput) throws TaskException {
        if (userInput.length() <= 5) {
            throw new TaskException("Come on now, I need a task name...");
        }
        String taskName = userInput.substring(5).trim();
        if (taskName.isEmpty()) {
            throw new TaskException("Come on now, I need a task name...");
        }
        addTask(new ToDoTask(taskName));
    }
    private static void deadline(String userInput) throws TaskException {
        String[] splittedString = userInput.split(" /by ");
        if (splittedString.length < 2 || splittedString[0].length() <= 9) {
            throw new TaskException("You sure your task name and deadline is valid?");
        }
        String taskName = splittedString[0].substring(9).trim();
        String by = splittedString[1].trim();
        if (taskName.isEmpty() || by.isEmpty()) {
            throw new TaskException("I can't work with an empty task name and deadline...");
        }
        addTask(new DeadlineTask(taskName, by));
    }
    private static void event(String userInput) throws TaskException {
        String[] splittedString = userInput.split(" /from | /to ");
        if (splittedString.length < 3 || splittedString[0].length() <= 6) {
            throw new TaskException("I need a valid task name, start and end time!!");
        }
        String taskName = splittedString[0].substring(6).trim();
        String from = splittedString[1].trim();
        String to = splittedString[2].trim();
        if (taskName.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new TaskException("You may have forgotten to provide me your task name, start and end time...");
        }
        addTask(new EventTask(taskName, from, to));
    }
    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    private static void displayTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }
    private static void deleteTask(String userInput) throws InvalidTaskNumberException {
        int taskIndex = parseTaskIndex(userInput, "delete");
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskNumberException("Invalid task number. The number must be between 1 and " + taskList.size() + "!");
        }
        Task removedTask = taskList.remove(taskIndex);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    //Mark as done
    private static void markTaskAsDone(String userInput) throws InvalidTaskNumberException {
        int taskIndex = parseTaskIndex(userInput, "mark");
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskNumberException("Invalid task number. The number is between 1 and " + taskList.size()+ "!");
        }
        taskList.get(taskIndex).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(taskIndex));
        System.out.println("____________________________________________________________");
    }
    //Mark as not done
    private static void markTaskAsNotDone(String userInput) throws InvalidTaskNumberException {
        int taskIndex = parseTaskIndex(userInput, "unmark");
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskNumberException("Invalid task number. The number is between 1 and " + taskList.size() + "!");
        }
        taskList.get(taskIndex).markAsNotDone();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(taskIndex));
        System.out.println("____________________________________________________________");
    }
    private static int parseTaskIndex(String userInput, String command) throws InvalidTaskNumberException {
        try {
            return Integer.parseInt(userInput.substring(command.length()).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("I need a valid task number...");
        }
    }
}
