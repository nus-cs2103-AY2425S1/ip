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

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
        public void setDone() {
            this.isDone = true;
        }

        public void setNotDone() {
            this.isDone = false;
        }
    }
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Deadline extends Task {
        protected String by;
        public Deadline(String description, String by) {
            super(description);
            this.by =by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String start;
        protected String end;
        public Event(String description, String start, String end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
        }
    }

    private static void drawLine() {
        System.out.println("-------------------------------------------------------");
    }

    private static void List() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArr = input.split(" ");
        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                drawLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. ", i + 1) + list.get(i).toString());
                }
                drawLine();
                input = scanner.nextLine();
                inputArr = input.split(" ");
                continue;
            } else if (inputArr[0].equals("mark")) {
                drawLine();
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(inputArr[1]) - 1;
                list.get(index).setDone();
                System.out.println(list.get(index).toString());
                drawLine();
                input = scanner.nextLine();
                inputArr = input.split(" ");
                continue;
            } else if (inputArr[0].equals("unmark")) {
                drawLine();
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(inputArr[1]) - 1;
                list.get(index).setNotDone();
                System.out.println(list.get(index).toString());
                drawLine();
                input = scanner.nextLine();
                inputArr = input.split(" ");
                continue;
            } else {
                drawLine();
                System.out.println("Got it. I've added this task:");
                if (inputArr[0].equals("todo")) {
                    String task = "";
                    for (int i = 1; i < inputArr.length; i++) {
                        task += inputArr[i] + " ";
                    }
                    task = task.strip();
                    ToDo todo = new ToDo(task);
                    list.add(todo);
                    System.out.println(todo.toString());
                } else if (inputArr[0].equals("deadline")) {
                    int i = 1;
                    String task = "";
                    while (!inputArr[i].equals("/by")) {
                        task += inputArr[i] + " ";
                        i++;
                    }
                    task = task.strip();
                    String by = "";
                    for (int j = i+1; j < inputArr.length; j++) {
                        by += inputArr[j] + " ";
                    }
                    by = by.strip();
                    Deadline deadline = new Deadline(task, by);
                    list.add(deadline);
                    System.out.println(deadline.toString());
                } else if (inputArr[0].equals("event")) {
                    int i = 1;
                    String task = "";
                    while (!inputArr[i].equals("/from")) {
                        task += inputArr[i] + " ";
                        i++;
                    }
                    task = task.strip();
                    i++;
                    String from = "";
                    while (!inputArr[i].equals("/to")) {
                        from += inputArr[i] + " ";
                        i++;
                    }
                    from = from.strip();
                    i++;
                    String to = "";
                    for (int j = i; j < inputArr.length; j++) {
                        to += inputArr[j] + " ";
                    }
                    to = to.strip();
                    Event event = new Event(task, from, to);
                    list.add(event);
                    System.out.println(event.toString());
                }
                System.out.println(String.format("Now you have %d tasks in the list.",list.size()));
                drawLine();
                input = scanner.nextLine();
                inputArr = input.split(" ");
            }
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
