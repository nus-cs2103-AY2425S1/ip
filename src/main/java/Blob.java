import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Blob {
    private static boolean active = true;
    private static int noOfTasks = 0;
    private static HashMap<Integer, Task> db = new HashMap<>();

    public static class Task {
        private String name;
        private boolean isDone;
        private int taskNumber;

        public Task(String name, int taskNumber, boolean isDone) {
            this.name = name;
            this.isDone = isDone;
            this.taskNumber = taskNumber;
            System.out.println(String.format("added: %s", name));
        }

        public void complete() {
            this.isDone = true;
        }

        public void undo() {
            this.isDone = false;
        }

        public String check() {
            return isDone ? "X" : " ";
        }

        @Override
        public String toString() {
            return "[" + this.check() + "] " + this.name;
        }
    }

    public static void evaluateAction(String action) {
        String[] arr = action.split(" ");
        for (int i = 0; i < arr.length; i++) {
            String act = arr[i].toLowerCase();
            if (Objects.equals(act, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________");
                active = false;
                break;
            } else if (Objects.equals(act, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (Map.Entry<Integer, Task> entry : db.entrySet()) {
                    Task t = entry.getValue();
                    System.out.println(String.format("%d. %s", t.taskNumber, t));
                }
                System.out.println("______________________________________________");
                break;
            } else if (Objects.equals(act, "mark")) {
                try {
                    Integer index = Integer.valueOf(arr[i + 1]);
                    Task t = db.get(index);
                    t.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }

                break;
            } else if (Objects.equals(act, "unmark")) {
                try {
                    Integer index = Integer.valueOf(arr[i + 1]);
                    Task t = db.get(index);
                    t.undo();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }

                break;
            } else {
                noOfTasks++;
                Task t = new Task(action, noOfTasks, false);
                db.put(t.taskNumber, t);
                System.out.println("______________________________________________");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner human = new Scanner(System.in);
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while (active) {
            String action = human.nextLine();
            System.out.println("______________________________________________");
            evaluateAction(action);
        }
    }
}
