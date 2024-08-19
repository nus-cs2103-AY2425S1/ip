import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";
        Task[] tasks = new Task[100];
        Scanner scanner = new Scanner(System.in);
        int tasksIndex = 0;
        String input = "";

        Commands.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            switch(command) {
                case "bye":
                    System.out.println("------------------------------------------");
                    Commands.bye();
                    return;
                case "list":
                    Commands.returnTaskList(tasks, tasksIndex);
                    break;
                case "mark":
                    int markNumber = Integer.parseInt(parts[1]);
                    Commands.markTask(tasks, markNumber);
                    break;
                case "unmark":
                    int unmarkNumber = Integer.parseInt(parts[1]);
                    Commands.unmarkTask(tasks, unmarkNumber);
                    break;
                default:
                    Commands.addTask(tasks, tasksIndex, new Task(input));
                    tasksIndex += 1;
            }

        }


    }

}
