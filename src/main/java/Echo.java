import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;

        while (true) {
            input = myObj.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                switch (command) {
                    case "bye":
                        exitProgram();
                        return;
                    case "list":
                        listTasks(tasks);
                        break;
                    case "mark":
                        if (parts.length == 2) {
                            markTask(tasks, parts[1]);
                        } else {
                            throw new EchoException("Please specify the task number to mark.");
                        }
                        break;
                    case "unmark":
                        if (parts.length == 2) {
                            unmarkTask(tasks, parts[1]);
                        } else {
                            throw new EchoException("Please specify the task number to mark.");
                        }
                        break;
                    case "todo":
                        if (parts.length == 2 && !parts[1].isEmpty()) {
                            addTodo(tasks, input);
                        } else {
                            throw new EchoException(" The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        addDeadline(tasks, input);
                        break;
                    case "event":
                        addEvent(tasks, input);
                    
                    default:
                        throw new EchoException(" I'm sorry, but I don't know what that means :-(");

                }
            } catch (EchoException e) {
                System.out.println("OOPS!!!" + e.getMessage());
            }
        }
    }

    private static void addDeadline(List<Task> tasks, String input) throws EchoException {
        String[] details = input.split(" /by ", 2);
        if (details.length == 2) {
            Deadline deadlineTask = new Deadline(details[0], details[1]);
            tasks.add(deadlineTask);
            System.out.println("added: " + deadlineTask);
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    private static void addEvent(List<Task> tasks, String input) throws EchoException {
        String[] details = input.split(" /from ", 2);
        if (details.length == 2) {
            String[] times = details[1].split(" /to ", 2);
            if (times.length == 2) {
                Events eventTask = new Events(details[0], times[0], times[1]);
                tasks.add(eventTask);
                System.out.println("added: " + eventTask);
            }
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    private static void exitProgram() {
        System.out.println("Bye bye...");
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    private static void markTask(List<Task> tasks, String input) {
        try {
            int num = Integer.parseInt(input);
            if (num > 0 && num <= tasks.size()) {
                Task doneTask = tasks.get(num - 1);
                doneTask.setDone();
                System.out.println("Nice! I've marked this task as done:\n" + doneTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void unmarkTask(List<Task> tasks, String input) {
        int num = Integer.parseInt(input);
        if (num > 0 && num <= tasks.size()) {
            Task undoneTask = tasks.get(num - 1);
            undoneTask.setUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + undoneTask);
        }
    }

    private static void addTask(List<Task> tasks, String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        System.out.println("added: " + taskDescription);
    }

    private static void addTodo(List<Task> tasks, String taskDescription) {
        Todo todo = new Todo(taskDescription);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo.toString() + "Now you have " + tasks.size() + " tasks in the list");
    }



}
