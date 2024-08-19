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
            if(input.equals("list")) {
                TaskList.listTask();
            }
            else if(input.startsWith("mark")) {
                int idx = Integer.parseInt(input.substring(5));
                TaskList.markTask(idx);
            }
            else if(input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7));
                TaskList.unmarkTask(idx);
            } else {
                TaskList.addTask(new Task(input));
                System.out.println("added: " + input);
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
        doTask();
        exit();
    }
}
