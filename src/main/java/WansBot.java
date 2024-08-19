import java.util.Scanner;

public class WansBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList userTaskList = new TaskList();
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";
        String hr = "----------------------------------------------------------------------";

        System.out.println(hr + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + hr);

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(hr + "\nWans:"
                        + "\nHere are your tasks!\n"
                        + userTaskList.toString());
            } else if (userInput.equalsIgnoreCase("")) {

            } else if (userInput.equalsIgnoreCase("bye")) {
                String exit = "|  _ \\ \\   / /  ____|"
                        + "\n| |_) \\ \\_/ /| |__"
                        + "\n|  _ < \\   / |  __|"
                        + "\n| |_) | | |  | |____"
                        + "\n|____/  |_|  |______";
                System.out.println(hr + "\nWans: \n"
                        + exit
                        + "\nI'll miss you :( (I really wanna go home)\n" + hr);
                System.exit(0);
            } else {
                userTaskList.add(new Task(userInput));
                System.out.println(hr + "\nWans:\n"
                        + "Ok! I've added " + userInput
                        + "\n" + hr);
            }
        }
    }
}
