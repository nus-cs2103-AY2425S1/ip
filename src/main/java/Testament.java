import java.util.Scanner;

public class Testament {
    final static String LINE = "____________________________________________________________";
    static TaskList taskList;
    public static void main(String[] args) {

        boolean powerOn = true;
        taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        printDialogue("Morning!\n Nice day for a stroll, don't you think?");
        while (powerOn) {
            String userInput = scanner.nextLine();
            String[] splitUserInput = userInput.split(" ", 2);

            if (splitUserInput[0].equals("bye")) {
                printDialogue("I'd say it's time for a tea break. Milk and sugar for you?");
                powerOn = false;

            } else if (splitUserInput[0].equals("schedule")) {
                printDialogue(taskList.toString());

            } else if (splitUserInput[0].equals("mark")) {
                if (splitUserInput.length < 2) {
                    printDialogue("Please do specify the task to mark");
                    continue;
                }

                int taskNumber = Integer.parseInt(splitUserInput[1]);
                String taskDetails;
                try {
                    taskList.mark(taskNumber);
                    taskDetails = taskList.getTask(taskNumber);
                } catch (NotInTaskListException e) {
                    printDialogue(e.getMessage());
                    continue;
                }
                printDialogue("Congratulations on completing your task:\n" + taskDetails);

            } else if (splitUserInput[0].equals("unmark")) {
                if (splitUserInput.length < 2) {
                    printDialogue("Please do specify the task to unmark");
                    continue;
                }

                int taskNumber = Integer.parseInt(splitUserInput[1]);
                String taskDetails;
                try {
                    taskList.unMark(taskNumber);
                    taskDetails = taskList.getTask(taskNumber);
                } catch (NotInTaskListException e) {
                    printDialogue(e.getMessage());
                    continue;
                }
                printDialogue("This task has been unmarked:\n" + taskDetails);

            } else {
                try {
                    taskList.add(Task.of(userInput));
                } catch (TestamentException e) {
                    printDialogue(e.getMessage());
                    continue;
                }
                String latestTask;
                try {
                    latestTask = taskList.getTask(taskList.getSize());
                } catch (NotInTaskListException e) {
                    printDialogue(e.getMessage());
                    continue;
                }
                printDialogue(
                        String.format("I've added the following task to your schedule:\n%s\n" +
                                        "You have %d tasks to complete",
                                latestTask, taskList.getSize())
                );
            }
        }
    }
    
    private static void printDialogue(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }
}