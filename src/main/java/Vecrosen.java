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
            } else if (input.matches("todo .+")) {
                String desc = input.substring(5);
                list.add(new Task(desc));
                speak("Todo added: " + desc);
            } else if (input.matches("deadline .+ /by .+")) {
                int byStart = input.indexOf("/by ");
                String by = input.substring(byStart + 4);
                String desc = input.substring(9, byStart - 1);
                list.add(new Deadline(desc, by));
                speak("Deadline added: " + desc);
            } else if (input.matches("event .+ /begin .+ /end .+")) {
                int beginStart = input.indexOf("/begin ");
                int endStart = input.indexOf("/end ");
                String desc = input.substring(6, beginStart - 1);
                String begin = input.substring(beginStart + 7, endStart - 1);
                String end = input.substring(endStart + 5);
                list.add(new Event(desc, begin, end));
                speak("Event added: " + desc);
            } else if (input.matches("event .+ /end .+ /begin .+")) {
                int beginStart = input.indexOf("/begin ");
                int endStart = input.indexOf("/end ");
                String desc = input.substring(6, endStart - 1);
                String end = input.substring(endStart + 5, beginStart - 1);
                String begin = input.substring(beginStart + 7);
                list.add(new Event(desc, begin, end));
                speak("Event added: " + desc);
            } else if (input.matches("delete \\d+")) {
                int itemNo = Integer.parseInt(input.substring(7));
                if (itemNo < 1 || itemNo > list.size()) speak("Invalid task number!");
                else {
                    speak("Removing task: " + list.get(itemNo - 1).getDescription());
                    list.remove(itemNo - 1);
                    speak("You now have " + list.size() + " tasks left in record.");
                }
            } // TODO: make helper function
            else if (input.startsWith("todo")) {
                speak("Invalid format.");
                speak("Usage: todo [description]");
            } else if (input.startsWith("deadline")) {
                speak("Invalid format.");
                speak("Usage: deadline [description] /by [deadline]");
            } else if (input.startsWith("event")) {
                speak("Invalid format.");
                speak("Usage: event [description] /begin [startTime] /end [endTime]");
            } else if (input.startsWith("mark")) {
                speak("Invalid format.");
                speak("Usage: mark [taskID]");
            } else if (input.startsWith("unmark")) {
                speak("Invalid format.");
                speak("Usage: unmark [taskID]");
            } else if (input.startsWith("delete")) {
                speak("Invalid format.");
                speak("Usage: delete [taskID]");
            } else {
                speak("Sorry, I don't understand.");
                speak("Commands: todo deadline event mark unmark delete list bye");
            }
        }
        speak("Bye. Hope to see you again soon!");
    }
}
