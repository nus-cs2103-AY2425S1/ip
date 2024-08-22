import java.util.Scanner;
import java.util.ArrayList;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final ArrayList<Task> taskList = new ArrayList<>(100);

    private static class Task {
        private final String TASK_NAME;
        private boolean isDone = false;

        private Task(String name) {
            this.TASK_NAME = name;
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsNotDone() {
            isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + TASK_NAME;
        }
    }

    // Inserts line indentation in response messages
    private static void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    private static void addTask(String name) {
        taskList.add(new Task(name));
        say(" added: " + name);
    }

    private static void listTask() {
        int listSize = taskList.size();
        String listInString = "";
        for (int i = 0; i < listSize; i += 1) {
            String taskString = String.format(" %d.%s", i + 1, taskList.get(i));
            listInString += taskString;
            if (i < listSize - 1) {
                listInString += "\n";
            }
        }
        say(listInString);
    }

    public static void main(String[] args) {
        say(GREETING_MESSAGE);
        boolean conversationIsOngoing = true;
        Scanner userInputReader = new Scanner(System.in);

        do {
            String userInput = userInputReader.nextLine();
            String[] processedInput = userInput.trim().split("\\s+");
            String command = processedInput[0];

            switch (command) {
            case "bye":
                conversationIsOngoing = false;
                userInputReader.close();
                say(GOODBYE_MESSAGE);
                break;
            case "list":
                listTask();
                break;
            case "mark":
            case "unmark":
                try {
                    if (processedInput.length > 1) {
                        int taskNumber = Integer.parseInt(processedInput[1]);
                        Task task = taskList.get(taskNumber - 1);

                        switch (command) {
                        case "mark":
                            task.markAsDone();
                            say("Nice! I have marked this task as done:\n   " + task);
                            break;
                        case "unmark":
                            task.markAsNotDone();
                            say("OK, I've marked this task as not done yet:\n   " + task);
                            break;
                        }
                    } else {
                        say(" You have to give me a valid task number!\n e.g. mark 2");
                    }
                } catch (NumberFormatException e) {
                    // if the string after the mark command is not an integer
                    say(" You have to give me a valid task number!\n e.g. mark 2");
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = String.format(" There is no such task!\n "
                            + "You currently have %d tasks", taskList.size());
                    say(errorMessage);
                }
                break;
            default:
                addTask(userInput);
            }
        } while (conversationIsOngoing);
    }
}
