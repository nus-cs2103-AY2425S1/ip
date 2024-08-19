import java.util.ArrayList;
import java.util.Scanner;
public class Elon {
    private static void List() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        String input = scanner.next();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(String.format("%d. ", i) + list.get(i - 1));
                }
                input = scanner.next();
                continue;
            }
            System.out.println("added: " + input);
            System.out.println("-------------------------------------------------------");
            list.add(input);
            input = scanner.next();
        }
        scanner.close();
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------------------------");
    }
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------");
        System.out.println(" Hello! I'm Elon");
        System.out.println(" What can I do for you?");
        System.out.println("-------------------------------------------------------");
        Elon.List();
    }
}
