import java.util.Scanner;

public class Murphy {
    private static String[] tasks = new String[100];
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
            } else {
                System.out.println("added: " + input);
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
        Murphy.tasks[Murphy.numOfTasks++] = item;
    }
}