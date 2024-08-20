import java.util.Scanner;

public class Shenhe {
    public static void main(String[] args) {
        String logo = "Shenhe";
        System.out.println("Hello, Ying. I'm Shenhe. The assumption that every person has somewhere to call home is \n" +
                "naive. I got used to living in the mountains alongside the birds and beasts a long time ago. \n" +
                "You, are not the only traveller, but the most interesting one.");
        System.out.println("What do you want today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Main loop
        while (true) {
            userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                break;
            }
        }

        System.out.println("Bye travaller. Hope to see you again soon.");
        System.out.println("____________________________________________________________");
    }
}
