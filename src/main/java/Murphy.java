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
                    Murphy.addItem(input);
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    Murphy.addItem(input);
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
                    Murphy.addItem(input);
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    Murphy.addItem(input);
                    continue;
                }
                if (index > Murphy.numOfTasks || index <= 0) {
                    System.out.println("Out of the range of tasks!");
                    continue;
                }
                Murphy.unmarkItem(index);
            } else {
                Murphy.addItem(input);
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

    private static void addItem(String item) {
        System.out.println("added: " + item);
        Murphy.tasks[Murphy.numOfTasks++] = new Task(item);
    }

    private static void markItem(int index) {
        Murphy.tasks[index - 1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Murphy.tasks[index - 1]);
    }

    private static void unmarkItem(int index) {
        Murphy.tasks[index - 1].unmark();
        System.out.println("Guess Murphy struck:");
        System.out.println(Murphy.tasks[index - 1]);
    }
}