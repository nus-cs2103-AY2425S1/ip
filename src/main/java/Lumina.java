import java.util.Scanner;

public class Lumina {

    private static final String ECHO_EXIT_STRING = "bye";
    private static final int indentWidth = 2;
    // Cosntructor
    public Lumina() {

    }

    private String indentMessage(String msg) {
        String[] lines = msg.split("\n");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < indentWidth; j++) {
                builder.append(" ");
            }
            builder.append(lines[i]);
            if (i < lines.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private void printMessage(String message) {
        this.printSeparator();
        System.out.println(indentMessage(message));
        this.printSeparator();
    }

    private void greet() {
        this.printMessage("Hello! I'm Lumina\n" +
                "What can I do for you?");
    }

    private void exit() {
        this.printMessage("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    private void echo(Scanner sc) {
        String msg;
        while(true) {
            msg = sc.nextLine();
            if (msg.equals(Lumina.ECHO_EXIT_STRING)) {
                this.exit();
                break;
            }
            this.printMessage(msg);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lumina lumina = new Lumina();
        lumina.greet();
        lumina.echo(sc);
    }
}
