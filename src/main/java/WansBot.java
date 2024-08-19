import java.util.Scanner;

public class WansBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        String hr = "----------------------------------------------------------------------";

        System.out.println(hr + "\nHello! I'm\n" + logo);
        System.out.println("What can I do for you?\n" + hr);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("")) {

            } else if (userInput.equalsIgnoreCase("")) {

            } else if (userInput.equalsIgnoreCase("exit")) {
                String exit = "|  _ \\ \\   / /  ____|"
                        + "\n| |_) \\ \\_/ /| |__"
                        + "\n|  _ < \\   / |  __|"
                        + "\n| |_) | | |  | |____"
                        + "\n|____/  |_|  |______";
                System.out.println(exit + "\nWe'll miss you :(");
                System.out.println(hr);
                System.exit(0);
            }
            System.out.println(hr);
        }
    }
}
