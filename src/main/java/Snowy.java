import java.util.Scanner;

public class Snowy {
    public static void main(String[] args) {
        String line ="____________________________________________________________";
        System.out.println(line + " Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;

            while (!isBye) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    isBye = true;
                } else {
                    System.out.println(line + "\nYou said: " + input +"\n" + line);
                }
            }
        System.out.println("Bye. See you next time!");
        }
}
