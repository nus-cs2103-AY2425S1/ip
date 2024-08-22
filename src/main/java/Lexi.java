import java.util.Scanner;

public class Lexi {
    private static Task[] tasks = new Task[100];
    private static int count = 0;
    private static String LINE_BREAK = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        greet();
        String response = userInput.nextLine();
        while (!response.equals("bye")) {
            try {
                String[] parts = response.split(" ");
                if (parts[0].contains("mark")) {
                    handleMark(parts);
                } else if (parts[0].equals("todo")) {
                    handleTodo(response);
                } else if (parts[0].equals("deadline")) {
                    handleDeadline(response);
                } else if (parts[0].equals("event")) {
                    handleEvent(response);
                } else if (response.equals("list")) {
                    listTasks();
                } else {
                    throw new LexiException("Sorry I don't know what the means :(\n");
                }
            } catch (LexiException e) {
                System.out.println(e.getMessage());
            } finally {
                response = userInput.nextLine();
            }
        }
            userInput.close();
            bye();
        }

    private static void handleEvent(String response) throws LexiException {
        String[] parts = response.split(" /from ");
        if(parts[0].equals(response)) {
            throw new LexiException("Please key in the command in this format\n"
                    + "\"event <task> /by <deadline>\n");
        }
        String taskName = parts[0].substring(6);
        String[] range = parts[1].split(" /to ");
        if(parts[1].equals(range[0])) {
            throw new LexiException("Please key in the command in this format\n"
                    + "\"event <task> /by <deadline>\n");
        }
        String from  = range[0];
        String to = range[1];
        addTask(new Event(taskName, from, to));
    }

    private static void handleDeadline(String response) throws LexiException {
        String[] parts = response.split(" /by ");
        if(parts[0].equals(response)) {
            throw new LexiException("Please key in the command in this format\n"
            + "\"deadline <task> /by <deadline>\n");
        }
        String taskName = parts[0].substring(9);
        String by = parts[1];
        addTask(new Deadline(taskName, by));
    }

    private static void handleTodo(String response) throws LexiException{
        if(response.length() < 6) {
            throw new LexiException("Sorry! The description of a todo cannot be empty\n" +
                    "Please write in this format \"todo <description>\"\n");
        }

        String taskName = response.substring(5);

        if(taskName.isBlank()) {
            throw new LexiException("Sorry! The description of a todo cannot be blank\n" +
                    "Please write in this format \"todo <description>\"\n");
        }
        addTask(new Todo(taskName));
    }

    private static void handleMark(String[] parts) throws LexiException {
        if(parts[1].isEmpty() || parts[1].isBlank()) {
            throw new LexiException("Please enter your command in this format\n" +
            "\"mark <number>\"");
        }
        int taskNumber =  Integer.parseInt(parts[1]) - 1;
        if(taskNumber > count - 1) {
            throw new LexiException("Sorry! That task does not exist.\nPlease key in the correct task number");
        }
        Task taskToBeMarked = tasks[taskNumber];
        if(parts[0].equals("unmark")) {
            unmarkTask(taskToBeMarked);
        } else {
            markTask(taskToBeMarked);
        }
    }
    private static void unmarkTask(Task taskToBeMarked) throws LexiException {
            if(!taskToBeMarked.getIsDone()) {
                throw new LexiException("This task has already been unmarked!\n");
            }
            taskToBeMarked.undoTask();
            System.out.println(LINE_BREAK);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + taskToBeMarked);
            System.out.println(LINE_BREAK + "\n");

    }

    public static void markTask(Task taskToBeMarked) throws LexiException {
        if(taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been marked!\n");
        }
        taskToBeMarked.doTask();
        System.out.println(LINE_BREAK);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);
        System.out.println(LINE_BREAK + "\n");
    }


    public static void listTasks() {
        System.out.println(LINE_BREAK);
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0;i<count;i++) {
            Task currTask = tasks[i];
            System.out.printf("  %d. %s%n", i+1, currTask);
        }
        System.out.println(LINE_BREAK + "\n");
    }

    public static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println(" Hello! I'm Lexi\n What can I do for you?");
        System.out.println(LINE_BREAK + "\n");
    }
    public static void bye() {
        System.out.println(LINE_BREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK + "\n");
    }
    public static void addTask(Task task) {
        tasks[count] = task;
        count++;
        System.out.println(LINE_BREAK);
        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", count, count == 1 ? "" : "s");
        System.out.println(LINE_BREAK + "\n");
    }

}
