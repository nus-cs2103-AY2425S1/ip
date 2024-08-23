import java.util.Scanner;  // Import the Scanner class

public class Meerkat {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String lines = "____________________________________________________________";
        String greeting = """
                Hello! I'm a meerkat from singapore
                What can I do for you?
                """;
        String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;
        System.out.println(lines + "\n" + greeting + lines);
        while (true) {
            String input = sc.next();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(lines + "\n" + bye + lines);
                break;
            }
            System.out.println(lines + "\n" + input + "\n" + lines);
        }
        System.exit(0);
    }
}
