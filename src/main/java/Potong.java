import java.util.Locale;
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
            System.out.println(Potong.LINE);
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println(data.toString());
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("mark")) {
                int index = Integer.valueOf(userInput.substring(5, 6));
                System.out.println(data.mark(index));
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("unmark")) {
                int index = Integer.valueOf(userInput.substring(7, 8));
                System.out.println(data.unmark(index));
                System.out.println(Potong.LINE);
                continue;
            }
            System.out.println(data.add(new Task(userInput)));
            System.out.println(Potong.LINE);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(Potong.LINE);
    }

}
