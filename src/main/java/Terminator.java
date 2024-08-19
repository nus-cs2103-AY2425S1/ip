import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Terminator {
    public static void main(String[] args) {
        String hline = "\t____________________________________________________________\n";
        String logo = """
                                     <((((((\\\\\\
                                     /      . }\\
                                     ;--..--._|}
                  (\\                 '--/\\--'  )
                   \\\\                | '-'  :'|
                    \\\\               . -==- .-|
                     \\\\               \\.__.'   \\--._
                     [\\\\          __.--|       //  _/'--.
                     \\ \\\\       .'-._ ('-----'/ __/      \\
                      \\ \\\\     /   __>|      | '--.       |
                       \\ \\\\   |   \\   |     /    /       /
                        \\ '\\ /     \\  |     |  _/       /
                         \\  \\       \\ |     | /        /
                          \\  \\      \\        /
                """;
        String greeting = hline +
                logo +
                "\tDevice booted successfully.\n" +
                "\tState your request.\n" +
                hline;
        System.out.println(greeting);

        String exit = hline + "\tAll mission objectives fulfilled. Terminating...\n" + hline;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        Pattern markPattern = Pattern.compile("^mark \\d+$");
        Matcher markMatcher;

        Pattern unmarkPattern = Pattern.compile("^unmark \\d+$");
        Matcher unmarkMatcher;

        ArrayList<Task> todoList = new ArrayList<>();

        while (!userInput.equals("bye")) {
            System.out.print(hline);

            markMatcher = markPattern.matcher(userInput);
            unmarkMatcher = unmarkPattern.matcher(userInput);

            // Handle user input
            if (userInput.equals("list")) {
                System.out.println("Listing current mission objectives:");
                for (int i = 0; i < todoList.size(); i++) {
                    Task currentTask = todoList.get(i);
                    System.out.println("\t" + (i + 1) + ".[" + currentTask.getStatusIcon() + "] "
                            + currentTask.description);
                }
            } else if (markMatcher.find()) {
                int idx = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (idx >= 0 && idx < todoList.size()) {
                    System.out.println("Task fulfilled. Awaiting next directive:");
                    Task currentTask = todoList.get(idx);
                    currentTask.markAsDone();
                    System.out.println("\t\t[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                }
            } else if (unmarkMatcher.find()) {
                int idx = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (idx >= 0 && idx < todoList.size()) {
                    System.out.println("Objective reopened:");
                    Task currentTask = todoList.get(idx);
                    currentTask.markAsIncomplete();
                    System.out.println("\t\t[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                }
            } else {
                Task newTask = new Task(userInput);
                todoList.add(newTask);
                System.out.println("\tadded: " + userInput);
            }

            System.out.println(hline);
            userInput = sc.nextLine();
        }
        System.out.println(exit);
    }
}
