import java.util.Scanner;

public class KillJoy {

    public void start() {
        System.out.println(this.logoString());
        System.out.println(this.welcomeString());

        Scanner user = new Scanner(System.in);

        while(true) {
            String input = user.nextLine();
            if (input.equals("bye")) {
                System.out.println(this.exitString());
                break;
            }
            userInput(input);
        }
    }
    private void userInput(String input) {
        if(input.equals("bye")) {
            exitString();
        }
        System.out.println("    ------------------------------------");
        System.out.println("    " + input);
        System.out.println("    ------------------------------------");
    }
    private String exitString() {
        return "    Bye. Hope to see you again soon!\n" +
                "   ------------------------------------";
    }

    private String welcomeString() {
        return "   Hello! I'm KillJoy \n   What can I do for you?\n" +
                "   ------------------------------------";
    }

    private String logoString() {
        return "   ------------------------------------\n" +
                "    _  ___ _ _      _             \n" +
                "   | |/ (_) | |    | |            \n" +
                "   | ' / _| | |    | | ___  _   _ \n" +
                "   |  < | | | |_   | |/ _ \\| | | |\n" +
                "   | . \\| | | | |__| | (_) | |_| |\n" +
                "   |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
                "                             __/ |\n" +
                "                            |___/ \n" +
                "   ------------------------------------";
    }

    public static void main(String[] args) {
        KillJoy kj = new KillJoy();
        kj.start();
    }
}
