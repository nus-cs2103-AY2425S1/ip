import java.util.ArrayList;
import java.util.Scanner;

public class Bro {
    public static void main(String[] args) {
        String line = "______________________________________________________\n";
        System.out.println("   " + line + "   Hello! I'm Bro\n   What can I do for you?\n"
                           + "   " + line);
        ArrayList<String> list = new ArrayList<>();
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            if (word.equalsIgnoreCase("list")) {
                int len = list.size();
                System.out.print("   " + line);
                for (int i = 0; i < len; i++) {
                    System.out.printf("   %d. %s\n", i + 1, list.get(i));
                }
                System.out.print("   " + line);
            } else {
                list.add(word);
                System.out.println("   " + line + "   added: " + word + "\n" + "   " + line);
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        System.out.println("   " + line + "   Bye. Hope to see you again soon!\n" + "   " + line);
    }
}
