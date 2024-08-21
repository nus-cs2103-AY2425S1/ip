import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Nugget {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        printIntro();

        while (true) {
            String text = scanner.nextLine().trim();

            try {
                if (text.equals("bye")) {
                    break;
                }
                handleInput(text, tasks);
            } catch (NuggetException e) {
                System.out.println("________________________________________");
                System.out.println(e.getMessage());
                System.out.println("________________________________________");
            }
        }

        printEnd();
    }

    private static void handleInput(String text, ArrayList<Task> tasks) throws NuggetException {
        String[] splitText = text.split("/");

        if (splitText.length == 1) {
            splitText = splitText[0].split(" ");
            if (text.equals("list")) {
                int len = tasks.size();
                System.out.println("________________________________________");
                for (int i = 0; i < len; i++) {
                    String formattedMessage = String.format("%d.%s", i + 1, tasks.get(i));
                    System.out.println(formattedMessage);
                }
                System.out.println("________________________________________");
            } else if (splitText[0].equals("mark")) {
                validateTaskNumber(splitText, tasks);
                int index = Integer.parseInt(splitText[1]) - 1;
                tasks.get(index).markTask();
                System.out.println("________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index));
                System.out.println("________________________________________");
            } else if (splitText[0].equals("unmark")) {
                validateTaskNumber(splitText, tasks);
                int index = Integer.parseInt(splitText[1]) - 1;
                tasks.get(index).unmarkTask();
                System.out.println("________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(index));
                System.out.println("________________________________________");
            } else if (splitText[0].equals("delete")){
                validateTaskNumber(splitText, tasks);
                int index = Integer.parseInt(splitText[1]) - 1;
                System.out.println("________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(index));
                tasks.remove(index);
                int numOfTasks = tasks.size();
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
                System.out.println("________________________________________");
            } else if (splitText[0].equals("todo")) {
                if (splitText.length < 2) {
                    throw new EmptyDescriptionException();
                }
                String description = String.join(" ", Arrays.copyOfRange(splitText, 1, splitText.length));
                Task newTask = new Todo(description);
                tasks.add(newTask);
                int numOfTasks = tasks.size();
                System.out.println("________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
                System.out.println("________________________________________");
            } else {
                throw new UnknownCommandException();
            }
        } else if (splitText.length == 2) {
            String[] taskParts = splitText[0].split(" ", 2);
            if (taskParts.length < 2) throw new EmptyDescriptionException();
            String taskType = taskParts[0];
            String taskDescription = taskParts[1];
            String date = splitText[1].replaceFirst("^by\\s+", "");
            Task deadline = new Deadline(taskDescription, date);
            tasks.add(deadline);
            int numOfTasks = tasks.size();
            System.out.println("________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
            System.out.println("________________________________________");
        } else if (splitText.length == 3) {
            String description = splitText[0].replaceFirst("event ", "").trim();
            String start = splitText[1].replaceFirst("from ", "").trim();
            String end = splitText[2].replaceFirst("to ", "").trim();
            Task event = new Event(description, start, end);
            tasks.add(event);
            int numOfTasks = tasks.size();
            System.out.println("________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
            System.out.println("________________________________________");
        } else {
            throw new UnknownCommandException();
        }
    }

    private static void validateTaskNumber(String[] splitText, ArrayList<Task> tasks) throws NuggetException {
        if (splitText.length < 2) {
            throw new EmptyDescriptionException();
        }
        int index = Integer.parseInt(splitText[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
    }

    public static void printIntro() {
        System.out.println("________________________________________");
        System.out.println("Hello! I'm Nugget");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
    }

    public static void printEnd() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
