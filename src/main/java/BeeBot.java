import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BeeBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean exit_status = false;
        String greet = "Hello! I'm BeeBot\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again!\n";
        ArrayList<String> list = new ArrayList<>();

        speak(greet);

        while (!exit_status) {
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "bye":
                    speak(exit);
                    exit_status = true;
                    break;
                case "list":
                    int size = list.size();
                    String listStr = "";
                    for (int i = 0; i < size; i++) {
                        listStr += list.get(i) + "\n";
                    }
                    speak(listStr);
                    break;
                default:
                    int num = list.size() + 1;
                    list.add(num + ": " + cmd);
                    speak("added: " + cmd + "\n");
                    break;
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }
}