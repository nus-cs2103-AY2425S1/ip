import java.util.Scanner;

public class Tina {
    public static void greet() {
        System.out.println("Hello! I'm Tina");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void doTask() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Parse.parseInput(input);
            } catch (TinaException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        TaskList.init();
        doTask();
        exit();
    }
}
