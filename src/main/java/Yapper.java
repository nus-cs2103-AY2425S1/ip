import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Yapper {
    public static void main(String[] args) {

        String divider = "____________________________________________________________";
        String name = "Yapper";

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(divider);
            } else {
                list.add(input);
                System.out.println(divider);
                System.out.println("added: " + input);
                System.out.println(divider);
            }
        }
    }
}
