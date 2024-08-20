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
        while (true) {
            String nextInput = userInput.nextLine();
            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                System.out.println("___________________________________________________________".indent(indentSpace));
                for (int i = 0; i < num; i++) {
                    if (taskList[i] != null) {
                        String taskString = (Integer.toString(i + 1) + ". " + taskList[i]).indent(indentSpace + 1);
                        System.out.println(taskString);
                    } else {
                        break;
                    }
                }
                System.out.println("____________________________________________________________".indent(indentSpace));
            } else {
                String echoString = ("____________________________________________________________\n"
                + "added: " + nextInput + "\n"
                + "____________________________________________________________\n").indent(indentSpace);
                taskList[num] = nextInput;
                num++;
                System.out.println(echoString);
            }
        }
        System.out.println(byeMessage);
    }
}
