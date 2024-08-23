import java.util.Scanner;

public class Jude {
    public static void main(String[] args) {
        String name = "Jude";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you, poyo?");

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon, poyo!");
    }
}
