import java.util.Scanner;

public class Max {
    private Scanner scanner = new Scanner(System.in);
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
            String text = scanner.nextLine().trim();

            try {
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
                    if (temp.length != 2) {
                        throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
                    }
                    checkTask(temp[0].trim());
                    checkTask(temp[1].trim());
                    Deadline deadline = new Deadline(temp[0], temp[1]);
                    this.storedTasks[this.storedTasksIndex] = deadline;
                    this.storedTasksIndex++;
                    printTaskTypeAdded(deadline);
                } else if (text.startsWith("todo")) {
                    String temp = text.replace("todo", "").trim();
                    checkTask(temp);
                    Todo todo = new Todo(temp);
                    this.storedTasks[this.storedTasksIndex] = todo;
                    this.storedTasksIndex++;
                    printTaskTypeAdded(todo);
                } else if (text.startsWith("event")) {
                    String[] temp = text.replace("event ", "").split(" /from ");
                    if (temp.length != 2) {
                        throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
                    }
                    checkTask(temp[0].trim());
                    checkTask(temp[1].trim());
                    Event event = new Event(temp[0], temp[1]);
                    this.storedTasks[this.storedTasksIndex] = event;
                    this.storedTasksIndex++;
                    printTaskTypeAdded(event);
                } else {
                    throw new MaxException("What does that mean?:( Begin with todo, event, or deadline.");
                }
            } catch (MaxException e) {
                printLine();
                printMessage(e.getMessage());
            }
        }
        printBye();
    }

    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
    public void printTaskTypeAdded(Task task) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + task.toString());
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
