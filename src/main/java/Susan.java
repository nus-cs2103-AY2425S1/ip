import java.util.*;

public class Susan {
    private TaskManagement tm;
    private CommandHandler ch;
    private Scanner sc;

    private Susan() {
        tm = new TaskManagement();
        sc = new Scanner(System.in);
        ch = new CommandHandler(tm);
    }

    public static Susan init() {
        return new Susan();
    }

    public static void main(String[] args) {
        Susan s = Susan.init();
        s.run();
    }

    public void run() {
        intro();

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            ch.handleCommand(command);
            command = sc.nextLine();
        }

        outro();
    }

    private void intro() {
        System.out.println(Config.logo);
        Utils.printItem(Config.intro);
    }

    private void outro() {
        Utils.printItem(Config.outro);
    }
}

