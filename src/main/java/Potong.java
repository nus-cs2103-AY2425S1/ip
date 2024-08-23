import java.util.Scanner;

public class Potong {

    private static final String LINE = "_________________________";
    public static void main(String[] args) {
        System.out.println("Hello! I'm Potong");
        System.out.println("What can I do for you?\n");
        System.out.println(Potong.LINE);

        Scanner input = new Scanner(System.in);
        InputData data = new InputData();
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println(data.toString());
                System.out.println(Potong.LINE);
                continue;
            }
            System.out.println(data.add(userInput));
            System.out.println(Potong.LINE);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(Potong.LINE);
    }

}
