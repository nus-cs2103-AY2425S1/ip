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
        prLine();
        System.out.println("Hello! I'm Murphy");
        System.out.println("What can I do for you?");
        prLine();
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
                    System.out.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.out.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.numOfTasks || index <= 0) {
                    System.out.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.markItem(index);
            } else if (input.startsWith("unmark ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.out.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.out.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.numOfTasks || index <= 0) {
                    System.out.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.unmarkItem(index);
            } else if(input.startsWith("todo ")){
                try {
                    Task todo = new Todo(input.substring(5));
                    Murphy.addItem(todo);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("todo usage: \"todo [description]\"");
                    prLine();
                }
            } else if(input.startsWith("deadline ")) {
                if (!input.contains("/by ")) {
                    System.out.println("deadline usage: \"deadline [description] /by [by when]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/by ");
                try {
                    Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
                    Murphy.addItem(deadline);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("deadline usage: \"deadline [description] /by [by when]\"");
                    prLine();
                }
            } else if (input.startsWith("event ")) {
                if (!input.contains("/from ") || !input.contains("/to ")) {
                    System.out.println("event usage: \"event [description] /from [by when] /to [to when]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/from ");
                String[] split2 = split[1].split("/to ");
                try {
                    Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
                    Murphy.addItem(event);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("event usage: \"event [description] /from [by when] /to [to when]\"");
                    prLine();
                }
            } else {
                System.out.println("Command not found");
                prLine();
            }
        }
    }

    private static void prLine() {
        System.out.println("____________________");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        prLine();
    }

    private static void list() {
        for (int i = 0; i < Murphy.numOfTasks; i++) {
            System.out.println((i+1) + ". " + Murphy.tasks[i]);
        }
        prLine();
    }

    private static void addItem(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        Murphy.tasks[Murphy.numOfTasks++] = task;
        System.out.println("Now you have " + Murphy.numOfTasks + " task(s) in the list.");
        prLine();
    }

    private static void markItem(int index) {
        Murphy.tasks[index - 1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Murphy.tasks[index - 1]);
        prLine();
    }

    private static void unmarkItem(int index) {
        Murphy.tasks[index - 1].unmark();
        System.out.println("I've unmarked this task. Guess Murphy struck?");
        System.out.println(Murphy.tasks[index - 1]);
        prLine();
    }
}