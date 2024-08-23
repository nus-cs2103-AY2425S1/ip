import java.util.Objects;
import java.util.Scanner;
import java.util.*;
public class Optimus {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Optimus");
        System.out.println("What can I do for you?");
        Scanner stringScanner = new Scanner(System.in);
        String [] tasks = new String [100];
        int i = 0;

        while (true) {
            String text = stringScanner.nextLine();
            if (text.equals("bye")){
                break;
            }
            else if (text.equals("list")){
                for (int j = 0; j < tasks.length; j++) {
                    System.out.println(j + ". " + text);
                }
            }
            else {
                tasks[i] = text;
                System.out.println("added: " + text);
                i++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
