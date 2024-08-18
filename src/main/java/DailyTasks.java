import java.text.Format;
import java.util.Scanner;

public class DailyTasks {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + DailyTasks.BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public String[] tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        DailyTasks dailyTasks = new DailyTasks();
        dailyTasks.tasks = new String[100];
        int taskCounter = 0;

        System.out.println(Formatter.formatOutputMessage(DailyTasks.GREETING));

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println(Formatter.formatTaskListings(taskCounter, dailyTasks.tasks));
            } else {
                dailyTasks.tasks[taskCounter++] = userInput; // save the user's input into memory

                String formattedTask = Formatter.formatOutputMessage("added: " + userInput);
                System.out.println(formattedTask);
            }
        }

        System.out.println(Formatter.formatOutputMessage(DailyTasks.GOODBYE));
    }
}
