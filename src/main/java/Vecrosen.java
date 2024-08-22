import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vecrosen {

    private static void speak(String prefix, String s) {
        System.out.print("    ");
        System.out.print(prefix);
        System.out.println(s);
    }

    public static void main(String[] args) {
        speak("Hello, I'm Vecrosen\n","What can I do for you?");
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); ++i) {
                    // let's avoid string concatenation
                    speak((i+1) + ". ", list.get(i));
                }
            } else {
                list.add(input);
                speak("added: ", input);
            }
        }
        speak("Bye. ","Hope to see you again soon!");
    }
}
