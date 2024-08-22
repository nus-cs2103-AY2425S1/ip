import java.io.IOException;
import java.io.StringReader;
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
            else if (input.matches("mark \\d+")) {
                int itemNo = Integer.parseInt(input.substring(5));
                if (itemNo < 1 || itemNo > list.size()) speak("Invalid task number!","");
                else {
                    done.set(itemNo-1, true);
                    speak("Task marked as complete: " , list.get(itemNo-1));
                }
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); ++i) {
                    char isDone = ' ';
                    if (done.get(i)) isDone = 'X';
                    speak((i+1) + ".[" + isDone + "] ", list.get(i));
                }
            } else {
                list.add(input);
                done.add(false);
                speak("added: ", input);
            }
        }
        speak("Bye. ","Hope to see you again soon!");
    }
}
