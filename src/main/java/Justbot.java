import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";
        String[] tasks = new String[100];
        Scanner scanner = new Scanner(System.in);
        int tasksIndex = 0;
        String input = "";

        Commands.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("------------------------------------------");
                break;
            }

            if (input.equals("list")) {
                Commands.returnTaskList(tasks, tasksIndex);
                continue;
            }

            Commands.addTask(tasks, tasksIndex, input);
            tasksIndex += 1;
        }

        Commands.bye();

    }

}
