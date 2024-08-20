import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Blob {
    private static boolean active = true;
    private static ArrayList<String> db = new ArrayList<>();
    public static void main(String[] args) {
        Scanner human = new Scanner(System.in);
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while (active) {
            String action = human.nextLine();
            System.out.println("______________________________________________");
            if (Objects.equals(action.toLowerCase(), "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________");
                active = false;
            } else if (Objects.equals(action.toLowerCase(), "list")){
                int i = 1;
                for (String act : db) {
                    System.out.println(String.format("%d. %s", i, act));
                    i++;
                }
                System.out.println("______________________________________________");
            } else {
                db.add(action);
                System.out.println(String.format("added: %s", action));
                System.out.println("______________________________________________");
            }
        }
    }
}
