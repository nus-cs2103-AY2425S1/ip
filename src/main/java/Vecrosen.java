import java.io.IOException;
import java.io.StringReader;
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
        ArrayList<Task> list = new ArrayList<Task>();
        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("bye")) break;
            else if (input.matches("mark \\d+")) {
                int itemNo = Integer.parseInt(input.substring(5));
                if (itemNo < 1 || itemNo > list.size()) speak("Invalid task number!");
                else {
                    list.get(itemNo-1).setDone(true);
                    speak("Task marked as complete: " + list.get(itemNo-1).getDescription());
                }
            } else if (input.matches("unmark \\d+")) {
                int itemNo = Integer.parseInt(input.substring(7));
                if (itemNo < 1 || itemNo > list.size()) speak("Invalid task number!");
                else {
                    list.get(itemNo-1).setDone(false);
                    speak("Task marked as incomplete: " + list.get(itemNo-1).getDescription());
                }
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); ++i) {
                    speak((i+1) + "." + list.get(i).toString());
                }
            } else {
                list.add(new Task(input));
                speak("added: " + input);
            }
        }
        speak("Bye. Hope to see you again soon!");
    }
}
