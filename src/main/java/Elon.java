import java.util.ArrayList;
import java.util.Scanner;
public class Elon {
    private static ArrayList<Task> list = new ArrayList<>();
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTask() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
        public void setDone() {
            this.isDone = true;
        }

        public void setNotDone() {
            this.isDone = false;
        }
    }

    private static void drawLine() {
        System.out.println("-------------------------------------------------------");
    }

    private static void List() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                drawLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. ", i + 1) + list.get(i).getTask());
                }
                drawLine();
                input = scanner.next();
                continue;
            } else if (input.equals("mark")) {
                drawLine();
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(scanner.next()) - 1;
                list.get(index).setDone();
                System.out.println(list.get(index).getTask());
                drawLine();
                input = scanner.next();
                continue;
            } else if (input.equals("unmark")) {
                drawLine();
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(scanner.next()) - 1;
                list.get(index).setNotDone();
                System.out.println(list.get(index).getTask());
                drawLine();
                input = scanner.next();
            }
            drawLine();
            System.out.println("added: " + input);
            drawLine();
            list.add(new Task(input));
            input = scanner.next();
        }
        scanner.close();
        drawLine();
        System.out.println(" Bye. Hope to see you again soon!");
        drawLine();
    }
    public static void main(String[] args) {
        drawLine();
        System.out.println(" Hello! I'm Elon");
        System.out.println(" What can I do for you?");
        drawLine();
        Elon.List();
    }
}
