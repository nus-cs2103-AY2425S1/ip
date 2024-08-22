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
        ArrayList<Boolean> done = new ArrayList<Boolean>();
        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); ++i) {
                    char isDone = ' ';
                    if (done.get(i)) isDone = 'X';
                    speak((i+1) + ".[" + isDone + "] ", list.get(i));
                }
            } else {
                list.add(input);
                done.add(list.size() % 2 == 0);
                speak("added: ", input);
            }
        }
        speak("Bye. ","Hope to see you again soon!");
    }
}
