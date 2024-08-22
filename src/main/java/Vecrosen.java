import java.io.IOException;
import java.util.Scanner;

public class Vecrosen {

    private static void speak(String s) {
        System.out.print("    ");
        System.out.println(s);
    }

    public static void main(String[] args) {
        speak("Hello, I'm Vecrosen\nWhat can I do for you?");
        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            speak(input);
        }
        //speak("Bye. Hope to see you again soon!");
    }
}
