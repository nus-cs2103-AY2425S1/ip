import java.util.Scanner;

public class Azir {
    public static void main(String[] args) {
        String input;
        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Azir");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");

        Scanner obj = new Scanner(System.in);

        while (!(input = obj.nextLine()).equals("bye")) {
            System.out.println("----------------------------------");
            System.out.println(input);
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }
}
