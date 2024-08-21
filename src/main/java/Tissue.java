import java.util.Scanner;

public class Tissue {
    public static void main(String[] args) {
        chatFunction();
    }

    private static void chatFunction() {
        String line = "--------------------------------------------------------------";
        String indent = "       ";
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(line);
        String in = scanner.nextLine();

        while (!in.equals("bye")) {
            System.out.println(line);
            System.out.print(indent);
            System.out.println(in);
            System.out.println(line);
            in = scanner.nextLine();
        }
        scanner.close();
        System.out.println(line);
        System.out.print(indent);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
