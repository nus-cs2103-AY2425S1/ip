import java.util.Scanner;

public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final String name;

    public Jay(String name) {
        this.name = name;
    }

    public void start() {
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(this.quit());
                break;
            } else {
                System.out.println(this.processCommand(command));
            }
        }
    }

    private String greet() {
        String greet =
                " Hello! I'm " + this.name + "\n" +
                " What can I do for you?";
        return formatCommand(greet);
    }

    private String quit() {
        return formatCommand("Bye. Hope to see you again soon!");
    }

    private String processCommand(String command) {
        return formatCommand(command);
    }

    private String formatCommand(String command) {
        return "____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________\n";
    }

}
