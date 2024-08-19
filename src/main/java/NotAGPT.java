import java.util.Scanner;

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
        switch(s.toLowerCase()) {
            case "bye":
                NotAGPTExit();
                break;

            case "list":
                taskList.list();
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