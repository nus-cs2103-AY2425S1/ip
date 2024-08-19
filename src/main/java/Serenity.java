import java.util.ArrayList;
import java.util.Scanner;

public class Serenity {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        String horizontalLine = "__________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
            }
            System.out.println(horizontalLine);
            input = sc.nextLine();
        }
        sc.close();

        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
