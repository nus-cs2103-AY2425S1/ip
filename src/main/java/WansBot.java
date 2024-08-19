import java.util.Scanner;

public class WansBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        String hr = "----------------------------------------------------------------------";

        System.out.println(hr + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (even though I lowkey don't want to)\n" + hr);

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();
            TaskList userTaskList = new TaskList();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println();
            } else if (userInput.equalsIgnoreCase("")) {

            } else if (userInput.equalsIgnoreCase("bye")) {
                String exit = "|  _ \\ \\   / /  ____|"
                        + "\n| |_) \\ \\_/ /| |__"
                        + "\n|  _ < \\   / |  __|"
                        + "\n| |_) | | |  | |____"
                        + "\n|____/  |_|  |______";
                System.out.println("Wans: \n"
                        + exit
                        + "\nI'll miss you :( (I really wanna go home)\n" + hr);
                System.exit(0);
            } else {
                 userTaskList.add(userInput);
                System.out.println(hr + "\nWans:\n"
                        + "Ok! I've added " + userInput
                        + "\n" + hr);
            }
        }
    }
}
