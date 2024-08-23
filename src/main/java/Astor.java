import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Astor {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Astor! \n" +
                "What can I do for you?\n" +
                "--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            }
            if (input.equals("list")) {
                int index = 1;
                for (String s : list) {
                    System.out.println(index + ". " + s);
                    index++;
                }
                System.out.println("--------------------------------------");
            } else {
                list.add(input);
                System.out.println("added: " + input + "\n--------------------------------------");
            }
        }
    }
}
