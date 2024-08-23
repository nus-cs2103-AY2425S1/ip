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
            if (text.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ". " + "[" + "] " + tasks[j]);
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
