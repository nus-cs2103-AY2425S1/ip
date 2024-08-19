import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class NotAGPT {

    public static void main(String[] args) {
        Line line = new Line();
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String logo = " _   _       _      _    ____ ____ _____ \n" +
                "| \\ | | ___ | |_   / \\  / ___|  _ \\_   _|\n" +
                "|  \\| |/ _ \\| __| / _ \\| |  _| |_) || |  \n" +
                "| |\\  | (_) | |_ / ___ \\ |_| |  __/ | |  \n" +
                "|_| \\_|\\___/ \\__/_/   \\_\\____|_|    |_|  ";

        System.out.println("Hello from\n" + logo);
        line.drawLine();
        System.out.println("    Hello! I'm NotAGPT");
        System.out.println("    What can I do for you?");
        line.drawLine();
        while (true) {
            String next = scanner.nextLine();
            inputHelper(tasks, next);
        }
    }

    public static void inputHelper(TaskList taskList, String s) {
        Line line = new Line();
        String[] parts = s.split(" ");
        String command = parts[0].toLowerCase();
        switch(command) {
            case "bye":
                NotAGPTExit();
                break;

            case "list":
                taskList.list();
                break;

            case "mark":
                if (parts.length > 1) {
                    String idx = parts[1];
                    taskList.markAsDone(idx);
                } else {
                    line.drawLine();
                    System.out.println("    Enter a task number");
                    line.drawLine();
                }
                break;

            case "unmark":
                if (parts.length > 1) {
                    String idx = parts[1];
                    taskList.markAsUndone(idx);
                } else {
                    line.drawLine();
                    System.out.println("    Enter a task number");
                    line.drawLine();
                }
                break;


            default:
                taskList.add(s);
        }


    }

    public static void NotAGPTExit() {
        Line line = new Line();
        line.drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        line.drawLine();
        System.exit(0);
    }
}