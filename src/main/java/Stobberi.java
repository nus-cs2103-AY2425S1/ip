import java.util.Scanner;

public class Stobberi {
    private static String nameOfChatBot = "Stobberi";
    private static String helloGreeting =
            "Hello! I'm " + nameOfChatBot + ".\n"
            + "What can I do for you?";
    private static String goodByeGreeting = "Bye. Hope to see you again soon! :)\n";

    private static String displayForm(String phrase) {
        return
                "___________________________________________\n"
                + phrase
                + "\n___________________________________________\n";
    }

    private static void Echo() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            System.out.println(displayForm(temp));
            temp = scanner.nextLine();
        }
    }
    public static void main(String[] args) {
        System.out.println(displayForm(helloGreeting));
        Echo();
        System.out.println(displayForm(goodByeGreeting));
    }
}
