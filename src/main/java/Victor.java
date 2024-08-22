import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Victor {
    public static void main(String[] args) {
        String logo = ",---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.\n"
+ "|   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\    \n"
+ "|  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |\n"
+ "|  | _ |  |  `-'`\"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /\n"
+ "|  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __\n"
+ "\\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |\n"
+ " \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `\"/  \\  ) / |  | \\ `'   /\n"
+ "  \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``\".'  |  |  \\    /\n"
+ "   `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'\n";

        Scanner inp = new Scanner(System.in);

        System.out.println(logo);
        System.out.println("Hello! My name is Victor!");
        System.out.println("What can I do for you?");
        System.out.println("============================================================");
        String userInput = inp.nextLine();
        System.out.println("============================================================");
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("  ~  " + userInput);
            System.out.println("============================================================");
            userInput = inp.nextLine();
            System.out.println("============================================================");
        }

        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("============================================================");
    }
}
