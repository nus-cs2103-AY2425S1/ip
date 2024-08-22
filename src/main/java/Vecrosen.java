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
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5);
                list.add(new Task(desc));
                speak("Todo added: " + desc);
            } else if (input.matches("deadline .+ /by .+")) {
                int byStart = input.indexOf("/by ");
                String by = input.substring(byStart + 4);
                String desc = input.substring(9, byStart - 1);
                list.add(new Task(desc + " (by: " + by + ')'));
                speak("Deadline added: " + desc);
            } else if (input.matches("event .+ /begin .+ /end .+")) {
                int beginStart = input.indexOf("/begin ");
                int endStart = input.indexOf("/end ");
                String desc = input.substring(6, beginStart - 1);
                String begin = input.substring(beginStart + 7, endStart - 1);
                String end = input.substring(endStart + 5);
                list.add(new Task(desc + " (begins: " + begin + ", ends: " + end + ")"));
                speak("Event added: " + desc);
            } else {
                speak("Sorry, I don't understand.");
            }
        }
        speak("Bye. Hope to see you again soon!");
    }
}
