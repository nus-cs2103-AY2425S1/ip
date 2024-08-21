import java.util.Scanner;

public class Lict {
    private final static String name = "Lict";
    private final static String horizontal_line = "__________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(horizontal_line);
            System.out.println(input);
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
}
