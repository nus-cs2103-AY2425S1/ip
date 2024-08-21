import java.util.Scanner;

public class Murphy {
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
            System.out.println(input);
        }
    }
    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________");
    }
}