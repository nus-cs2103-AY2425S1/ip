import java.util.ArrayList;
import java.util.Scanner;

public class Lumina {

    private static final String ECHO_EXIT_STRING = "bye";
    private static final String ECHO_LIST_STRING = "list";
    private static final int indentWidth = 2;

    private ArrayList<String> messages;
    // Cosntructor
    public Lumina() {
        messages = new ArrayList<>();
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

    private void printMessage(String msg) {
        this.printSeparator();
        System.out.println(indentMessage(msg));
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

    private void addMessage(String msg) {
        this.messages.add(msg);
    }

    private void listMessages() {
        StringBuilder listedMessage = new StringBuilder();
        for (int i = 0; i < this.messages.size(); i++) {
            listedMessage.append(Integer.toString(i + 1) + ". ");
            listedMessage.append(this.messages.get(i));
            if (i < this.messages.size() - 1) {
                listedMessage.append("\n");
            }
        }
        this.printMessage(listedMessage.toString());
    }

    private void echo(Scanner sc) {
        String msg;
        while(true) {
            System.out.println();
            msg = sc.nextLine();
            if (msg.equals(Lumina.ECHO_EXIT_STRING)) {
                this.exit();
                break;
            }
            if (msg.equals(Lumina.ECHO_LIST_STRING)) {
                this.listMessages();
                continue;
            }
            this.addMessage(msg);
            this.printMessage("added: " + msg);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lumina lumina = new Lumina();
        lumina.greet();
        lumina.echo(sc);
    }
}
