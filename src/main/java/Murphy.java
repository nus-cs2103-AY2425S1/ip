import java.util.Scanner;

public class Murphy {
    private static final Task[] tasks = new Task[100];
    private static int numOfTasks = 0;
    public static void main(String[] args) {
        /*
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        */
        System.out.println("________________");
        System.out.println("Hello! I'm Murphy");
        System.out.println("What can I do for you?");
        System.out.println("________________");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Murphy.bye();
                scanner.close();
                return;
            }
            if (input.equals("list")) {
                Murphy.list();
            } else if (input.startsWith("mark ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    //Murphy.addItem(input);
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    //Murphy.addItem(input);
                    continue;
                }
                if (index > Murphy.numOfTasks || index <= 0) {
                    System.out.println("Out of the range of tasks!");
                    continue;
                }
                Murphy.markItem(index);
            } else if (input.startsWith("unmark ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    //Murphy.addItem(input);
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    //Murphy.addItem(input);
                    continue;
                }
                if (index > Murphy.numOfTasks || index <= 0) {
                    System.out.println("Out of the range of tasks!");
                    continue;
                }
                Murphy.unmarkItem(index);
            } else if(input.startsWith("todo ")){
                Task todo = new Todo(input.substring(5));
                Murphy.addItem(todo);
            } else if(input.startsWith("deadline ")) {
                if (!input.contains("/by ")) {
                    System.out.println("Provide a by time for deadline");
                    continue;
                }
                String[] split = input.split("/by ");
                Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
                Murphy.addItem(deadline);
            } else if (input.startsWith("event ")) {
                if (!input.contains("/from ") || !input.contains("/to ")) {
                    System.out.println("Provide a from and to time for event");
                    continue;
                }
                String[] split = input.split("/from ");
                String[] split2 = split[1].split("/to ");
                Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
                Murphy.addItem(event);
            } else {
                System.out.println("Command not found");
            }
        }
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________");
    }

    private static void list() {
        for (int i = 0; i < Murphy.numOfTasks; i++) {
            System.out.println((i+1) + ". " + Murphy.tasks[i]);
        }
    }

    private static void addItem(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        Murphy.tasks[Murphy.numOfTasks++] = task;
        System.out.println("Now you have " + Murphy.numOfTasks + " task(s) in the list.");
    }

    private static void markItem(int index) {
        Murphy.tasks[index - 1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Murphy.tasks[index - 1]);
    }

    private static void unmarkItem(int index) {
        Murphy.tasks[index - 1].unmark();
        System.out.println("I've unmarked this task. Guess Murphy struck?");
        System.out.println(Murphy.tasks[index - 1]);
    }
}