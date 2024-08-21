import java.util.Scanner;

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

    public static void echoUser() {
        Scanner botScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        String userInput = botScanner.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
            userInput = botScanner.nextLine();
        }
        Molly.sayBye();

    }


    public static void main(String[] args) {
        Molly.greetUser();
        Molly.echoUser();
    }
}
