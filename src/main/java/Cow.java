import java.util.Scanner;
public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        Message greetings = new Message(
                " Hello! I'm COW\n"
                        + " What can I do for you?"
        );

        System.out.println(greetings);

        while (true) {
            String command = scanner.nextLine();
            new Command(command).action();
        }
    }
}
