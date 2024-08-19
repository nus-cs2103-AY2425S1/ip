import java.text.Format;
import java.util.Scanner;

public class DailyTasks {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + DailyTasks.BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public Task[] tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        DailyTasks dailyTasks = new DailyTasks();
        dailyTasks.tasks = new Task[100];
        int taskCounter = 0;

        System.out.println(Formatter.formatOutputMessage(DailyTasks.GREETING));

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            if (userInput.equals("list")) {
                System.out.println(Formatter.formatTaskListings(taskCounter, dailyTasks.tasks));
            } else if (userInput.contains("unmark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1; // minus 1 because array is 0-indexed

                dailyTasks.tasks[index].setNotDone();
                System.out.println(Formatter.formatUnmarkTask(dailyTasks.tasks[index]));
            } else if (userInput.contains("mark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;

                dailyTasks.tasks[index].setDone();
                System.out.println(Formatter.formatMarkTask(dailyTasks.tasks[index]));
            } else if (userInput.contains("bye")) {
                System.out.println(Formatter.formatOutputMessage(DailyTasks.GOODBYE));
                return;
            } else {
                if (userInput.contains("todo")) {
                    String description = userInput.split(" ", 2)[1];
                    dailyTasks.tasks[taskCounter++] = new ToDos(description);
                } else if (userInput.contains("deadline")) {
                    String[] deadlineInformation = userInput.split("/by");
                    String description = deadlineInformation[0].replace("deadline", "").trim();
                    String date = deadlineInformation[1].trim();

                    dailyTasks.tasks[taskCounter++] = new Deadline(description, date);
                } else if (userInput.contains("event")) {
                    String[] removeFrom = userInput.split("/from");
                    String description = removeFrom[0].replace("event", "").trim();

                    String[] removeTo = removeFrom[1].split("/to");
                    String start = removeTo[0].trim();
                    String end = removeTo[1].trim();

                    dailyTasks.tasks[taskCounter++] = new Event(description, start, end);
                } else {
                    System.out.println("Please enter a valid Task!");
                    return;
                }

                String formattedTask = Formatter.formatAddTask(taskCounter, dailyTasks.tasks[taskCounter - 1]);
                System.out.println(formattedTask);
            }
        }
    }
}
