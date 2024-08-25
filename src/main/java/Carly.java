import java.util.Scanner;

public class Carly {
    private void chat() {
        String welcomeMsg = "Hello! I'm Carly\nWhat can I do for you?\n";
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(welcomeMsg);

        Scanner scan  = new Scanner(System.in);
        String input;

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(exitMsg);
                break;
            }
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        String logo = " ,-----.              ,--.          \n"
                    + "'  .--./,--,--.,--.--.|  |,--. ,--. \n"
                    + "|  |   ' ,-.  ||  .--'|  | \\  '  / \n"
                    + "'  '--'\\ '-'  ||  |   |  |  \\   ' \n"
                    + " `-----'`--`--'`--'   `--'.-'  /    \n"
                    + "                          `---'  ";

        System.out.println("Hello from\n" + logo);
        Carly carly = new Carly();
        carly.chat();
    }



}
