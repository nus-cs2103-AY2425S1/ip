import java.util.Objects;
import java.util.Scanner;

public class Edith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String horizontal = "____________________________________________________________";
        String greeting = " heyyy im edith! \n what can I do for you?";
        String farewell = " bye!! see you soon love <3";
        String linebreak = "\n";

        System.out.println(horizontal + linebreak +
                greeting + linebreak +
                horizontal);

        String line = scanner.nextLine();

        while (!Objects.equals(line, "bye")) {
            System.out.println(horizontal + linebreak + " " +
                    line + linebreak + horizontal);
            line = scanner.nextLine();
        }

        System.out.println(horizontal + linebreak + farewell + linebreak + horizontal);
    }
}
