import java.util.Scanner;

public class Colress {
    private static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static String spacer = "______________________________________\n";
    private static String greetingMessage = "Hello! My name is Colress.\n"
            + "What can I do for you?";
    private static String farewellMessage = "Well then, I hope to see you around soon!";
    private static String exitCommand = "bye";
    public static void print(String s) {
        System.out.println(spacer + s + "\n" + spacer);
    }

    public static void echo() {
        Colress.input = Colress.scanner.nextLine();
        while (!Colress.input.equals(exitCommand)) {
            print(input);
            Colress.input = Colress.scanner.nextLine();
        }
    }
    public static void main(String[] args) {
        print(greetingMessage);
        Colress.echo();
        print(farewellMessage);
    }
}
