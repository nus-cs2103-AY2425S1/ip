import java.util.Scanner;

public class Serenity {
    public static void main(String[] args) {

        String horizontalLine = "__________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
            input = sc.next();
        }
        sc.close();

        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
