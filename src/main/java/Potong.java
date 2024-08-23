import java.util.Scanner;

public class Potong {

    private static final String LINE = "_________________________";
    public static void main(String[] args) {
        System.out.println("Hello! I'm Potong");
        System.out.println("What can I do for you?\n");
        System.out.println(Potong.LINE);

        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            System.out.println(userInput);
            System.out.println(Potong.LINE);
            if (userInput.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(Potong.LINE);
    }

}
