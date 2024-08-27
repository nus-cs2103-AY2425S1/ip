import java.util.Scanner;

public class Opus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Hello! I'm Opus");
        System.out.println(" What can I do for you?");

        while(true) {
            String s = scanner.nextLine();
            if (s.equals("bye")) {
                break;
            }
            System.out.println(s);
        }

        System.out.println(" Bye. Hope to see you again soon!");
    }
}

// how to run this file?
// javac Opus.java
// java Opus