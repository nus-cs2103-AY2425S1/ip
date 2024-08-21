import java.util.Scanner;
import java.util.ArrayList;

public class Molly {
    public static String name = "Molly";

    public Molly() {

    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + Molly.name);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void listToString(ArrayList<String> botMemory) {
        for (int i = 0; i < botMemory.size(); i++) {
            System.out.println((i + 1) + ". " + botMemory.get(i));
        }
    }

    public static void assistUser() {
        ArrayList<String> botMemory = new ArrayList<>();
        Scanner botScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        String userInput = botScanner.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            if (!userInput.toLowerCase().equals("list")) {
                botMemory.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                Molly.listToString(botMemory);
                System.out.println("____________________________________________________________");
            }
            userInput = botScanner.nextLine();
        }
        Molly.sayBye();

    }


    public static void main(String[] args) {
        Molly.greetUser();
        Molly.assistUser();
    }
}
