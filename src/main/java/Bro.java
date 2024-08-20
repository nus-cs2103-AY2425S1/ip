import java.sql.SQLOutput;
import java.util.Scanner;

public class Bro {
    public static void main(String[] args) {
        String line = "______________________________________________________\n";
        System.out.println("   " + line + "   Hello! I'm Bro\n   What can I do for you?\n"
                           + "   " + line);
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            System.out.println("   " + line + "   " + word + "\n" + "   " + line);
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        System.out.println("   " + line + "   Bye. Hope to see you again soon!\n" + "   " + line);
    }
}
