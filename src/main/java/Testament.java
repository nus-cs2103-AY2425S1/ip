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
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                taskList.mark(taskNumber);
                printDialogue("Congratulations on completing your task:\n" + taskList.getTask(taskNumber));

            } else if (splitUserInput[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                taskList.unMark(taskNumber);
                printDialogue("This task has been unmarked:\n" + taskList.getTask(taskNumber));

            } else if (splitUserInput[0].equals("todo")) {
                printDialogue(splitUserInput[1] + " has been added to your schedule.");
                taskList.add(new ToDo(splitUserInput[1]));
            } else {
                printDialogue(userInput + " has been added to your schedule.");
                taskList.add(new Task(userInput));

            }
        }
    }

    private static void printDialogue(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }
}