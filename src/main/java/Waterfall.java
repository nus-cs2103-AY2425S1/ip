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
                            String taskString = " ".repeat(indentSpace + 1) + Integer.toString(i + 1) + ". " + taskList[i];
                            System.out.println(taskString);
                        } else {
                            break;
                        }
                    }
                    System.out.println("____________________________________________________________".indent(indentSpace));
                    break;
                default:
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
