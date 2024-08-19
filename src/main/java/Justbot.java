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
            String command = input.split(" ")[0];

            switch(command) {
                case "bye":
                    System.out.println("------------------------------------------");
                    Commands.bye();
                    return;
                case "list":
                    Commands.returnTaskList(tasks, tasksIndex);
                    break;
                case "mark":
                    int markNumber = Integer.parseInt(input.split(" ")[1]);
                    Commands.markTask(tasks, markNumber);
                    break;
                case "unmark":
                    int unmarkNumber = Integer.parseInt(input.split(" ")[1]);
                    Commands.unmarkTask(tasks, unmarkNumber);
                    break;
                case "deadline":
                    String[] splitPartsDeadline = input.split("/by");
                    String commandAndDescriptionDeadline = splitPartsDeadline[0].trim();
                    String deadlineDescription = commandAndDescriptionDeadline.substring(command.length()).trim();
                    String by = splitPartsDeadline[1];
                    Commands.addTask(tasks, tasksIndex, new Deadline(deadlineDescription, by));
                    tasksIndex += 1;
                    break;
                case "event":
                    String[] splitPartsEvent = input.split("/from");
                    String commandAndDescriptionEvent = splitPartsEvent[0].trim();
                    String startAndEnd = splitPartsEvent[1].trim();
                    String eventDescription = commandAndDescriptionEvent.substring(command.length()).trim();
                    String eventStart = startAndEnd.split("/to")[0].trim();
                    String eventEnd = startAndEnd.split("/to")[1].trim();
                    Commands.addTask(tasks, tasksIndex, new Event(eventDescription, eventStart, eventEnd));
                    tasksIndex += 1;
                    break;
                case "todo":
                    String[] splitPartsTodo = input.split(" ", 2);
                    String description = splitPartsTodo[1];
                    Commands.addTask(tasks, tasksIndex, new Todo(description));
                    tasksIndex += 1;
                    break;
                default:
                    System.out.println("Invalid command!");
                    System.out.println("------------------------------------------");
            }

        }


    }

}
