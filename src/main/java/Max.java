import java.util.Scanner;

public class Max {
    private final Scanner scanner = new Scanner(System.in);
    private Task[] storedTasks;
    private int storedTasksIndex;

    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public Max() {
        storedTasks = new Task[100];
        storedTasksIndex= 0;
    }
    public void runMax() {
        printHello();

        boolean running = true;

        while (running) {
            String text = scanner.nextLine();

            if (text.equals("bye")) {
                running = false;
            } else if (text.equals("list")) {
                list();
            } else if (text.startsWith("mark")) {
                int index = Integer.parseInt(text.replace("mark ", ""));
                markDone(index);
            } else if (text.startsWith("unmark")) {
                int index = Integer.parseInt(text.replace("unmark ", ""));
                markNotDone(index);
            } else if (text.startsWith("deadline")) {
                String[] temp = text.replace("deadline ", "").split(" /by ");
                Deadline deadline = new Deadline(temp[0], temp[1]);
                this.storedTasks[this.storedTasksIndex] = deadline;
                this.storedTasksIndex++;
                printTaskTypeAdded(deadline);
            } else if (text.startsWith("todo")) {
                Todo todo = new Todo(text.replace("todo ", ""));
                this.storedTasks[this.storedTasksIndex] = todo;
                this.storedTasksIndex++;
                printTaskTypeAdded(todo);
            } else if (text.startsWith("event")) {
                String[] temp = text.replace("event ", "").split(" /from ");
                Event event = new Event(temp[0], temp[1]);
                this.storedTasks[this.storedTasksIndex] = event;
                this.storedTasksIndex++;
                printTaskTypeAdded(event);
            } else {
                this.storedTasks[this.storedTasksIndex] = new Task(text);
                this.storedTasksIndex++;
                echo(text);
            }
        }
        printBye();
    }
    public void printTaskTypeAdded(Task task) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\t Now you have " + storedTasksIndex + " tasks in the list.");
        printLine();
    }

    public void printMessage(String message) {
        System.out.println("\t " + message);
        printLine();
    }

    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\t What can I do for you?");
    }

    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void echo(String text) {
        printLine();
        printMessage("added: " + text);
    }

    public void list() {
        printLine();
        for (int i = 0; i < this.storedTasksIndex; i++) {
            int count = i + 1;
            System.out.println("\t " + count + "." + this.storedTasks[i].toString());
        }
        printLine();
    }

    public void markDone(int index) {
        int arrayIndex = index - 1;
        storedTasks[arrayIndex].markDone();
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + storedTasks[arrayIndex].toString());
        printLine();
    }

    public void markNotDone(int index) {
        int arrayIndex = index - 1;
        storedTasks[arrayIndex].markNotDone();
        printLine();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + storedTasks[arrayIndex].toString());
        printLine();
    }
}
