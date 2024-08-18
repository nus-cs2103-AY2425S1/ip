import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tars {

    private List<Task> tasks;

    public Tars() {
        this.tasks = new ArrayList<>();
    }

    // Takes the input
    public String doSomething(String input) {
        String[] words = input.split(" ", 2);
        String first = words[0];
        String second = words.length > 1 ? words[1] : "";
        return switch (first) {
            case "bye" -> ("Bye. Hope to see you again soon!");
            case "list" -> listAllTasks();
            case "mark" -> {
                if (!second.isEmpty()) {
                    yield markDone(second);
                } else {
                    yield "Please specify which task to mark.";
                }
            }
            case "unmark" -> {
                if (!second.isEmpty()) {
                    yield markUndone(second);
                } else {
                    yield "Please specify which task to unmark.";
                }
            }
            case "todo" -> {
                if (!second.isEmpty()) {
                    Todo todo = new Todo(second, false);
                    addTask(todo);
                    yield getResponse(todo);
                } else {
                    yield "Please specify the task description";
                }
            }
            case "deadline" -> {
                if (!second.isEmpty()) {
                    String[] parts = second.split(" /by ", 2);
                    if (parts.length > 1) {
                        Deadline deadline = new Deadline(parts[0], false, parts[1]);
                        addTask(deadline);
                        yield getResponse(deadline);
                    } else {
                        yield "Please specify the deadline in the following format: deadline task /by date";
                    }
                } else {
                    yield "Please specify the task description";
                }
            }
            case "event" -> {
                if (!second.isEmpty()) {
                    String[] parts = second.split(" /from | /to ", 3);
                    if (parts.length > 2) {
                        Event event = new Event(parts[0], false, parts[1], parts[2]);
                        addTask(event);
                        yield getResponse(event);
                    } else {
                        yield "Please specify the event in the following format: event task /from time /to time";
                    }
                } else {
                    yield "Please specify the task description.";
                }
            }
            case "humour" -> ("One hundred percent");
            case "honesty" -> ("Ninety percent");
            case "caution" -> ("Cooper, this is no time for caution!");
            default -> "I'm sorry, I can't quite help you with that";
        };
    }

    private String listAllTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return ("Here are the tasks in your list: \n" + result.toString().trim());
    }

    private void addTask(Task task) {
        this.tasks.add(task);
    }

    private String markDone(String input) {
        if (input.isEmpty()) return "Task could not be found!";
        boolean canFind = false;
        for (Task currTask : this.tasks) {
            String taskName = currTask.getName();
            if (Objects.equals(taskName, input)) {
                canFind = true;
                currTask.setDone();
                return ("Nice! I've marked this task as done:\n" + "  " + currTask.toString());
            }
        }
        return ("The task you specified cannot be found. Please try again");
    }
    private String markUndone(String input) {
        if (input.isEmpty()) return "Task could not be found!";
        boolean canFind = false;
        for (Task currTask : this.tasks) {
            String taskName = currTask.getName();
            if (Objects.equals(taskName, input)) {
                canFind = true;
                currTask.setUndone();
                return ("OK, I've marked this task as not done yet:\n" + "  " + currTask.toString());
            }
        }
        return ("The task you specified cannot be found. Please try again");
    }

    private String getResponse(Task task) {
        return "Got it. I've added this task: \n" + task.toString() + "\n" + "Now you have "
                + this.tasks.size() + " tasks in the list\n";
    }

    public static void main(String[] args) {
        System.out.println("____________________________________");
        System.out.println("Hello! I'm TARS");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________");

        Tars tars = new Tars();
        Scanner scanner = new Scanner(System.in);
        String input;
        String output;
        do {
            input = scanner.nextLine();
            output = tars.doSomething(input);
            System.out.println("____________________________________");
            System.out.println(output);
            System.out.println("____________________________________");
        } while (!input.equals("bye"));

        scanner.close();


    }
}
