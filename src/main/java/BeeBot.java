import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BeeBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean exit_status = false;
        String greet = "Hello! I'm BeeBot\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again!\n";
        ArrayList<Task> taskList = new ArrayList<>();

        speak(greet);

        while (!exit_status) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String cmd = parts[0];

            switch (cmd) {
                case "bye":
                    speak(exit);
                    exit_status = true;
                    break;
                case "list":
                    int size = taskList.size();
                    String listStr = "";
                    for (int i = 0; i < size; i++) {
                        listStr += taskList.get(i).stringify();
                    }
                    speak(listStr);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(parts[1]);
                    Task currTask = taskList.get(taskNum - 1);
                    currTask.markAsDone();
                    speak("Nice! I've marked this task as done:\n" + currTask.stringify());
                    break;
                case "unmark":
                    int taskNum2 = Integer.parseInt(parts[1]);
                    Task curr = taskList.get(taskNum2 - 1);
                    curr.markAsUndone();
                    speak("Nice! I've marked this task as not done yet:\n" + curr.stringify());
                    break;
                default:
                    int num = taskList.size() + 1;
                    Task newTask = new Task(num, input);
                    taskList.add(newTask);
                    speak("added: " + input + "\n");
                    break;
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }
}