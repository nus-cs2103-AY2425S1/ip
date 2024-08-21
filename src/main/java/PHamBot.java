import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                SayGoodbye();
                break;
            }
            if (input.equals("list")) {
                ListTasks();
            }
            else {
                input = AddTask(input);
                System.out.println(OutlineMessage(input));
            }
        }
    }

    private static String OutlineMessage(String msg) {
        String res= line + msg + "\n" + line;
        return res;
    }

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        System.out.println(OutlineMessage(greeting));
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        System.out.println(OutlineMessage(goodbye));
    }

    public static String AddTask(String task) {
        tasks.add(task);
        return "Added: " + task;
    }
    public static void ListTasks() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res = res + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        System.out.println(OutlineMessage(res));
    }
}
