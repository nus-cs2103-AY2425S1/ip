import java.util.Scanner;

public class Timo {
    public static void main(String[] args) {
        System.out.println("----------------------------\n");
        System.out.println("Hello! I'm Timo\nWhat can I do for you?\n");

        Scanner echo = new Scanner(System.in);
        String input = "";
        while (true) {
            input = echo.nextLine();
            if (!input.equals("bye")) {
                System.out.println("----------------------------\n");
                System.out.println(input);
                System.out.println("----------------------------\n");
            } else {
                System.out.println("----------------------------\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("----------------------------\n");
                break;
            }
        }



    }
}
