//package CommandLine;
//import Task.Task;
//
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import CommandLine.Line;
//import Task.TaskList;
//import CommandLine.Parser;
//
//import static java.lang.Integer.parseInt;
//
//public class NotAGPT {
//
//    public static void main(String[] args) {
//        Line line = new Line();
//        TaskList tasks = new TaskList();
//        Parser parser = new Parser();
//        Scanner scanner = new Scanner(System.in);
//        String logo = " _   _       _      _    ____ ____ _____ \n" +
//                "| \\ | | ___ | |_   / \\  / ___|  _ \\_   _|\n" +
//                "|  \\| |/ _ \\| __| / _ \\| |  _| |_) || |  \n" +
//                "| |\\  | (_) | |_ / ___ \\ |_| |  __/ | |  \n" +
//                "|_| \\_|\\___/ \\__/_/   \\_\\____|_|    |_|  ";
//
//        System.out.println("Hello from\n" + logo);
//        line.drawLine();
//        System.out.println("    Hello! I'm NotAGPT");
//        System.out.println("    What can I do for you?");
//        line.drawLine();
//        while (true) {
//            try {
//                String next = scanner.nextLine();
//                String response = parser.parse(tasks, next);
//            } catch (NoSuchElementException e) {
//                break;
//            }
//        }
//    }
//
//    private String getResponse(String s) {
//        return parser.parse(tasks,)
//    }
//}


package CommandLine;

import Task.TaskList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class NotAGPT {

    private Line line = new Line();
    private TaskList tasks = new TaskList();
    private Parser parser = new Parser();

    public static void main(String[] args) {
        new NotAGPT().run();
    }

    public void run() {
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
            try {
                String next = scanner.nextLine();
                String response = getResponse(next);
                System.out.println(response);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    public String getResponse(String input) {
        return parser.parse(tasks, input);
    }
}
