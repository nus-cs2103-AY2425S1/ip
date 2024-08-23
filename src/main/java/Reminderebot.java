import java.util.Scanner;
public class Reminderebot {
    private String[] tasks = new String[100];
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    static int index = 0;
    private static final String greetingText = topBuffer +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            topBuffer;

    private static final String goodbyeText = topBuffer +
            " Bye. Hope to see you again soon!\n" +
            bottomBuffer;

    public static void main(String[] args) {
        Reminderebot reminderebot = new Reminderebot();
        Scanner sc = new Scanner(System.in);
        reminderebot.greeting();
        while (true) {
            System.out.println("");
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    reminderebot.goodbye();
                    break;
                case "list":
                    reminderebot.printTasks();
                    break;
                default:
                    reminderebot.addTasks(command);
                    System.out.println("____________________________________________________________\n" +
                            "added: " + command +
                            "\n____________________________________________________________"
                    );
            }
            if (command.equals("bye")) break;
        }
    }

    private void greeting() {
        System.out.println(greetingText);
    }

    private void goodbye() {
        System.out.println(goodbyeText);
    }

    private void printTasks() {
        StringBuilder output = new StringBuilder();
        output.append(topBuffer);
        for (int i=0; i<index; i++) {
            output.append(tasks[i]).append("\n");
        }
        output.append(bottomBuffer);
        String taskList = output.toString();
        System.out.println(taskList);
    }

    private void addTasks(String task) {
        tasks[index] = (index+1) + ". " + task;
        index++;
    }
}
