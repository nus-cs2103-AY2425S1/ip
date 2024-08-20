import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Will {

    static class Task {
        final String desc;
        boolean isDone;

        Task(String desc) {
            this.desc = desc;
            this.isDone = false;
        }

        void markDone() {
            this.isDone = true;
        }

        void markNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + desc;
        }
    }

    static void printTask(ArrayList<Task> tasks) {
        System.out.println("_____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("_____________________________________");
    }

    static void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            System.out.println("_____________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index - 1));
            System.out.println("_____________________________________");
        } else {
            System.out.println("No Task Found");
        }
    }

    static void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            System.out.println("_____________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index - 1));
            System.out.println("_____________________________________");
        } else {
            System.out.println("No Task Found");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = "WILL";
        System.out.println("Hello! I'm" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________");

        while(true) {
            String userInput = scanner.nextLine();
            System.out.println("_____________________________________");

            if(Objects.equals(userInput, "bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if(Objects.equals(userInput, "list")){
                printTask(tasks);
                continue;
            }

            if(userInput.startsWith("mark ")){
                int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                markTask(tasks, taskNumber);
                continue;
            }

            if(userInput.startsWith("unmark ")){
                int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                unmarkTask(tasks, taskNumber);
                continue;
            }
            tasks.add(new Task(userInput));
            System.out.println("added: " + userInput);
            System.out.println("_____________________________________");
        }
    }
}

