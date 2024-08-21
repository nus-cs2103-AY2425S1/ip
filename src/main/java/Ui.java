import java.util.Scanner;

public class Ui {
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Tina");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void doTask(TaskList tasks) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input, tasks);
            } catch (TinaException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
