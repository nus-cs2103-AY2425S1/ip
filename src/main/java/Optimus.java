import java.util.Objects;
import java.util.Scanner;
public class Optimus {
    boolean isLive;
    static String linebreak = "____________________________";

    public Optimus() {
        this.isLive = true;
        this.greet();
    }

    public boolean getStatus() {
        return this.isLive;
    }

    public void echo(String input) {
        System.out.println(input);
        System.out.println(Optimus.linebreak);
    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    private void leave() {
        this.isLive = false;
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }
    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        Scanner scanner = new Scanner(System.in);
        while (optimus.getStatus()) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                optimus.leave();
            } else {
                optimus.echo(input);
            }

        }
    }
}
