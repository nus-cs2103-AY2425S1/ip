import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);

        String text = myObj.nextLine();
        System.out.println(text);
        System.out.println();

        while (!text.equals("bye")) {
            text = myObj.nextLine();
            System.out.println(text);
            System.out.println();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
