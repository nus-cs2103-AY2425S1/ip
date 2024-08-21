import java.util.Scanner;
import java.util.ArrayList;
public class Neko {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String greetingMessage = "  ∧,,,∧  \n( ̳̳• · ̳• ) \n づ Meow! I'm Neko\nWhat can I do for you? ";
    private static final String exitMessage = "Bye! Hope to see you again soon meow ฅ ฅ";
    private static int numOfTask = 0;
    private static final ArrayList<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {

        System.out.println(greetingMessage);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            handleInput(input);
            input = scanner.nextLine();
        }

        System.out.println(exitMessage);
        scanner.close();
    }

    private static void handleInput(String input) {
        if (input.equals(LIST_COMMAND)) {
            listTask();
        } else if (input.startsWith(MARK_COMMAND)) {
            markTask(input);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            unmarkTask(input);
        } else {
            addTask(input);
        }
    }

    private static void addTask(String input) {
        taskList.add(new Task(input));
        numOfTask++;
        System.out.println("added: " + input + " ฅ/ᐠᓀ ﻌ ᓂマ");
    }

    private static void listTask() {
        System.out.println("Here are the task in your list meow: ");
        for (int i = 0; i < numOfTask; i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + "." + task);
        }
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("Nice meow! I've marked this task as done: \n " + task);
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        Task task = taskList.get(index);
        task.markAsNotDone();
        System.out.println("Ok meow, I've marked this task as not done yet: \n " + task);
    }


}
