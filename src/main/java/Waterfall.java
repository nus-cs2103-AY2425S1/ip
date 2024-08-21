import java.util.Arrays;
import java.util.Scanner;


public class Waterfall {
    public static void main(String[] args) {
        String chatBotName = "Waterfall";
        int indentSpace = 4;
        String welcomeMessage =
            ("____________________________________________________________\n"
            + "Hualalalalala I'm " + chatBotName + "\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n").indent(indentSpace);
        String byeMessage =
            ("____________________________________________________________\n"
            + "Shhhhhhhhhhhh. Hope to see you again soon!\n"
            + "____________________________________________________________\n").indent(indentSpace);
        Task[] taskList = new Task[100];
        int num = 0;
        System.out.println(welcomeMessage);
        Scanner userInput = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String nextInput = userInput.nextLine();
            switch (nextInput) {
                case "bye":
                    System.out.println(byeMessage);
                    exit = true;
                    break;
                case "list":
                    System.out.println(" ".repeat(indentSpace) + "___________________________________________________________");
                    for (int i = 0; i < num; i++) {
                        Task task = taskList[i];
                        if (task != null) {
                            String taskString = " ".repeat(indentSpace + 1) +
                                    Integer.toString(i + 1) + "."
                                    + task.toString();
                            System.out.println(taskString);
                        } else {
                            break;
                        }
                    }
                    System.out.println("____________________________________________________________".indent(indentSpace));
                    break;
                default:
                    if (nextInput.startsWith("mark ") && (nextInput.substring(5).matches("\\d+"))) {
                        int index = Integer.parseInt(nextInput.substring(5)) - 1;
                        if (index >= 100 || index < 0) {
                            System.out.println("Only range from 1 to 100 is possible!");
                            break;
                        }
                        if (taskList[index] == null) {
                            System.out.println("Oops, this task does not exist");
                            break;
                        }
                        Task task = taskList[index];
                        task.setDone(true);
                        String markResponse = ("____________________________________________________________\n"
                                + "Huluhuluhulu, I've marked this task as done:\n  "
                                + task.toString() + "\n"
                                + "____________________________________________________________\n").indent(indentSpace + 1);
                        System.out.println(markResponse);
                    } else if (nextInput.startsWith("unmark ") && (nextInput.substring(7).matches("\\d+"))) {
                        int index = Integer.parseInt(nextInput.substring(7)) - 1;
                        if (index >= 100 || index < 0) {
                            System.out.println("Only range from 1 to 100 is possible!");
                            break;
                        }
                        if (taskList[index] == null) {
                            System.out.println("Oops, this task does not exist");
                            break;
                        }
                        Task task = taskList[index];
                        task.setDone(false);
                        String unmarkResponse = ("____________________________________________________________\n"
                                + "Hohohohohoho, I've marked this task as not done yet:\n  "
                                + task.toString() + "\n"
                                + "____________________________________________________________\n").indent(indentSpace + 1);
                        System.out.println(unmarkResponse);
                    } else {
                        String echoString = ("____________________________________________________________\n"
                                + "added: " + nextInput + "\n"
                                + "____________________________________________________________\n").indent(indentSpace);
                        taskList[num] = new Task(nextInput);
                        num++;
                        System.out.println(echoString);
                    }
            }
        }
    }
}
