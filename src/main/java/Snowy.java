import java.util.Scanner;

public class Snowy {
    public static void main(String[] args) {
        String line ="____________________________________________________________\n";
        System.out.println(line + " Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(line + "You said: " + input );

    }
}
