import java.util.Scanner;

public class KillJoy {

    private String[] tasks = new String[100];
    private int taskCount = 0;

    public void start() {
        System.out.println(this.getLogoString());
        System.out.println(this.getWelcomeString());

        Scanner user = new Scanner(System.in);

        while(true) {
            String input = user.nextLine();

            if (input.equals("bye")) {
                System.out.println(this.getExitString());
                break;
            } else if (input.equals("list")){
                this.printTasks();
            } else {
                this.processUserInput(input);
            }
        }
    }
    private void processUserInput(String input) {

        System.out.println("    ------------------------------------");
        this.tasks[this.taskCount] = input;
        taskCount++;
        System.out.println("    added: " + input);
        System.out.println("    ------------------------------------");

    }

    private void printTasks() {
        System.out.println("    ------------------------------------");
        for (int i = 0; i < this.taskCount; i++) {
            System.out.println("    " + (i + 1) + ". " + this.tasks[i]);
        }
        System.out.println("    ------------------------------------");
    }
    private String getExitString() {
        return "   ------------------------------------\n" +
                "    Bye. Hope to see you again soon!\n" +
                "   ------------------------------------";
    }

    private String getWelcomeString() {
        return "   Hello! I'm KillJoy \n   What can I do for you?\n" +
                "   ------------------------------------";
    }

    private String getLogoString() {
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
