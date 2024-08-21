import java.util.Scanner;
public class Derek {

    private static String user;
    private static String logo = " ---    ---\n"
            +"| # |  | # |\n"
            +" ---    ---\n"
            +"  \\      /\n"
            +"    ----\n";

    private static String sadLogo = " ---    ---\n"
            + "| # |  | # |\n"
            + " ---    ---\n"
            + "    ----\n"
            + "  /      \\\n";

    private static String leavingMessage = String.format("No, Ok...\n" + sadLogo);

    public static void main(String[] args) {
        introduction();
    }

    public static void introduction() {

        System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
        String userInput =
                "Your response (Y/N): ";
        System.out.println(userInput);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        if (response.equals("Y")) {
            System.out.println("Great! I have always wanted a friend!\n"
                                + "What do I call you?");
            Scanner name = new Scanner(System.in);
            user = name.nextLine();
            System.out.println("Nice meeting you, " + user + "I need to go now");
        } else {

            System.out.println(leavingMessage);
        }
    }
}



