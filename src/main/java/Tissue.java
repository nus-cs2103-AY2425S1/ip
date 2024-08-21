import java.util.Scanner;
import java.util.ArrayList;

public class Tissue {
    private static ArrayList<Task> textArray = new ArrayList<>();
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";

    public static void main(String[] args) {
        chatFunction();
    }

    private static void chatFunction() {
        System.out.println(LINE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String in = scanner.next();

        while (!in.equals("bye")) {
            System.out.println(LINE);

            if (in.equals("list")) {
                System.out.println(listText());
            } else if (in.equals("mark")) {

                Task task = textArray.get(scanner.nextInt() - 1).markTask();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);

            } else if (in.equals("unmark")) {

                Task task = textArray.get(scanner.nextInt() - 1).unmarkTask();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);

            } else {
                System.out.println(INDENT + "added: " + in);
                storeText(in);
            }

            System.out.println(LINE);
            in = scanner.next();
        }

        scanner.close();

        System.out.println(LINE);
        System.out.print(INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void storeText(String value) {
        textArray.add(new Task(false, value));
    }

    private static String listText() {
        String parsedText = "";
        for (int i = 0; i < textArray.size(); i++) {
            Task task = textArray.get(i);
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + task + "\n";


        }
        return parsedText;
    }

    private static class Task {
        private boolean done;
        private String task;

        public Task(boolean done, String task) {
            this.done = done;
            this.task = task;
        }

        private Task markTask() {
            done = true;
            return this;
        }

        private Task unmarkTask() {
            done = false;
            return this;
        }

        public boolean getDone() {
            return done;
        }

        public String getTask() {
            return task;
        }

        @Override
        public String toString() {
            return done ? "[X] " + task : "[ ] " + task;
        }

    }
}
