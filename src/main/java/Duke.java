import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int current = 0;
        Scanner reader = new Scanner(System.in);
        String greet = "Hello! I'm Bob \n"
                + "What can I do for you? \n";
        String bye = "Bye. Hope to see you again soon!";
        String input = "";


        System.out.println(greet);
        while (true) {
            input = reader.nextLine();

            if(input.equals("bye")){
                System.out.println(bye);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < current; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            }else{
                tasks[current] = input;
                current++;
                System.out.println("added: " + input);
            }

        }
    }
}
