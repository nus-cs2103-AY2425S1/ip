import java.util.ArrayList;
import java.util.Scanner;

public class Bwead {

    public static String name = "Bwead";
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> texts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello from " + name + "!");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else {
                texts.add(input);
                System.out.println("added: " + input);
            }
        }
    }

    public static void printlist() {
        for (int i = 1; i <= texts.size(); i++) {
            System.out.println(i + ": " + texts.get(i - 1));
        }
    }

}
