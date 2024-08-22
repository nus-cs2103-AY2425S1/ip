import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vecrosen {

    private static void speak(String s) {
        System.out.print("    ");
        System.out.println(s);
    }

    public static void main(String[] args) {
        speak("Hello, I'm Vecrosen\nWhat can I do for you?");
        ArrayList<String> list = new ArrayList<String>();
        list.add("Write code");
        list.add("Create list");
        list.add("???");
        list.add("Profit!");
        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); ++i) {
                    // let's avoid string concatenation
                    System.out.print("    " + (i+1) + ". ");
                    System.out.println(list.get(i));
                }
            } else speak(input);
        }
        speak("Bye. Hope to see you again soon!");
    }
}
