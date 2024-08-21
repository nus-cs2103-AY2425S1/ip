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
        String[] taskList = new String[100];
        Boolean[] doneList = new Boolean[100];
        Arrays.fill(doneList, false);
        int num = 0;
        System.out.println(welcomeMessage);
        Scanner userInput = new Scanner(System.in);
        boolean completed = false;
        while (!completed) {
            String nextInput = userInput.nextLine();
            switch (nextInput) {
                case "bye":
                    System.out.println(byeMessage);
                    completed = true;
                    break;
                case "list":
                    System.out.println(" ".repeat(indentSpace) + "___________________________________________________________");
                    for (int i = 0; i < num; i++) {
                        if (taskList[i] != null) {
                            String taskString = " ".repeat(indentSpace + 1) +
                                    Integer.toString(i + 1)
                                    + ".["
                                    + (doneList[i] ? "X" : " ")
                                    + "] " +
                                    taskList[i];
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
                        doneList[index] = true;
                        String markResponse = ("____________________________________________________________\n"
                                + "Huluhuluhulu, I've marked this task as done:\n"
                                + "  [X] " + taskList[index] + "\n"
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
                        doneList[index] = false;
                        String unmarkResponse = ("____________________________________________________________\n"
                                + "Huluhuluhulu, I've marked this task as not done yet:\n"
                                + "  [ ] " + taskList[index] + "\n"
                                + "____________________________________________________________\n").indent(indentSpace + 1);
                        System.out.println(unmarkResponse);
                    } else {
                        String echoString = ("____________________________________________________________\n"
                                + "added: " + nextInput + "\n"
                                + "____________________________________________________________\n").indent(indentSpace);
                        taskList[num] = nextInput;
                        num++;
                        System.out.println(echoString);
                    }
            }
        }
    }
}
