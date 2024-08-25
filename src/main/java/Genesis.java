import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
class Task {
    protected String description;
    protected boolean isComplete;

    public Task (String description) {
        this.description = description;
        this.isComplete = false;
    }


    @Override
    public String toString() {
        String marked;
        if (isComplete) {
            marked = "[X] ";
        } else {
            marked = "[ ] ";
        }
        return marked + this.description;
    }

    public void mark() {
        this.isComplete = true;
    }
    public void unmark() {
        this.isComplete = false;
    }
}

class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String deadline;
    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}

class Event extends Task {
    protected String startTime;
    protected String endTime;
    public Event (String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}

class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index).description);
        }
    }

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
            System.out.println("Ok. I've marked this task as not done yet:\n" + tasks.get(index).description);
        }
    }

    public void deleteTask(int index) {
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I have removed the following task: \n" + removedTask.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    private boolean isValidIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return false;
        }
        return true;
    }
}

class CommandParser {
    private TaskManager taskManager;

    public CommandParser(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            taskManager.listTasks();
        } else if (input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.startsWith("delete ")) {
            handleDelete(input);
        } else if (input.startsWith("deadline ")) {
            handleDeadline(input);
        } else if (input.startsWith("todo ")) {
            handleTodo(input);
        } else if (input.startsWith("event ")) {
            handleEvent(input);
        } else {
            System.out.println("Sorry, I am not sure what task this is! Please enter a valid task.");
        }
    }

    private void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            taskManager.markTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    private void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            taskManager.unmarkTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    private void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            taskManager.deleteTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    private void handleDeadline(String input) {
        if (!input.contains("/by ")) {
            System.out.println("You need a deadline to add this task!");
            return;
        }
        String[] parts = input.split("/by ");
        String taskName = parts[0].replaceFirst("deadline ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        String deadline = parts[1].trim();
        Deadline deadlineTask = new Deadline(taskName, deadline);
        taskManager.addTask(deadlineTask);
    }

    private void handleTodo(String input) {
        String taskName = input.replaceFirst("todo ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        Todo todoTask = new Todo(taskName);
        taskManager.addTask(todoTask);
    }

    private void handleEvent(String input) {
        if (!input.contains("/from ") || !input.contains("/to ")) {
            System.out.println("You need a starting and ending date to add this task!");
            return;
        }
        String[] parts = input.split("/from ");
        String[] dateParts = parts[1].split("/to ");
        String taskName = parts[0].replaceFirst("event ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();
        Event eventTask = new Event(taskName, startDate, endDate);
        taskManager.addTask(eventTask);
    }
}

public class Genesis {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Genesis!\nWhat can I do for you?\n");

        TaskManager taskManager = new TaskManager();
        CommandParser parser = new CommandParser(taskManager);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            parser.parseCommand(input);
        }
    }
}
/*public class Genesis {

    public static void main(String[] args) {


        System.out.println("Hello! I'm Genesis!\n"
                + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + ". " + items.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= items.size()) {
                    System.out.println("No such task exists!");
                    continue;
                }
                Task current = items.get(index);
                current.mark();
                System.out.println("Nice! I've marked this as done:\n" +
                                    current.description);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= items.size()) {
                    System.out.println("No such task exists!");
                    continue;
                }
                Task current = items.get(index);
                current.unmark();
                System.out.println("Ok. I've marked this task as not done yet:\n" +
                        current.description);
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= items.size()) {
                    System.out.println("No such task exists!");
                    continue;
                }
                Task current = items.get(index);
                items.remove(index);
                System.out.println("Noted. I have removed the following task: \n"
                                   + current.toString()
                                   + "\nNow you have " + items.size() + " items in the list.");
            } else if (input.startsWith("deadline ")){
                if (!input.contains("/by ")) {
                    System.out.println("You need a deadline to add this task!");
                    continue;
                }
                String[] parts = input.split("/by ");
                String taskName = parts[0].replaceFirst("deadline ", "").trim();
                if (taskName.equals("")) {
                    System.out.println("You need a task description!");
                    continue;
                }
                String deadline = parts[1].trim();
                Deadline current = new Deadline(taskName, deadline);
                items.add(current);
                System.out.println("Got it. I've added this task:\n" + current.toString()
                                    + "\nYou now have " + items.size() + " items in the list.");
            } else if (input.startsWith("todo ")) {
                String taskName = input.replaceFirst("todo ", "").trim();
                if (taskName.equals("")) {
                    System.out.println("You need a task description!");
                    continue;
                }
                Todo current = new Todo(taskName);
                items.add(current);
                System.out.println("Got it. I've added this task:\n" + current.toString()
                        + "\nYou now have " + items.size() + " items in the list.");
            } else if (input.startsWith("event ")) {
                if (!input.contains("/from ")) {
                    System.out.println("You need a starting date to add this task!");
                    continue;
                } else if (!input.contains("/ to")) {
                    System.out.println("You need an ending date to add this task!");
                }
                String[] parts = input.split("/from ");
                String[] parts2 = parts[1].split("/to ");
                String taskName = parts[0].replaceFirst("event ", "").trim();
                if (taskName.equals("")) {
                    System.out.println("You need a task description!");
                    continue;
                }
                String startDate = parts2[0].trim();
                String endDate = parts2[1].trim();
                Event current = new Event(taskName, startDate, endDate);
                items.add(current);
                System.out.println("Got it. I've added this task:\n" + current.toString()
                        + "\nYou now have " + items.size() + " items in the list.");
            } else {
                System.out.println("Sorry, I am not sure what task this is! Please enter a valid task.");
            }
        }

    }
}*/
