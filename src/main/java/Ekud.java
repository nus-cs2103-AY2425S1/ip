import java.util.Objects;
import java.util.Scanner;

public class Ekud {
    public static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";
    public static final String END_COMMAND = "bye";

    public void greet() {
        String greeting = "\tHeyo! My name is EKuD!! You can call me Eku-chan :)"
                + "\n\tHow may I be of assistance!";
        System.out.println(LINE_SEPARATOR);
        System.out.println(greeting);
        System.out.println(LINE_SEPARATOR);
    }

    public void sayGoodbye() {
        String goodbye = "\tI hope you enjoyed your stay!\n\tSee you next time!";
        System.out.println(goodbye);
        System.out.println(LINE_SEPARATOR);
    }

    public void echo(String message) {
        System.out.println("\t" + message);
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        Scanner sc = new Scanner(System.in);
        String command = "";

        ekud.greet();
        while (true) {
            System.out.println();
            command = sc.nextLine();
            System.out.println(LINE_SEPARATOR);
            // check for user escape
            if (Objects.equals(command, END_COMMAND)) {
                break;
            }
            ekud.echo(command);
        }
        ekud.sayGoodbye();
    }
}
