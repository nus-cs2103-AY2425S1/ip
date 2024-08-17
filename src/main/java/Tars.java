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
            case "humour" -> ("One hundred percent");
            case "honesty" -> ("Ninety percent");
            default -> {
                addTask(input);
                yield ("added: " + input);
            }
        };
    }

    private String listAllTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return ("Here are the tasks in your list: \n" + result.toString().trim());
    }

    private void addTask(String input) {
        this.tasks.add(new Task(input, false));
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
