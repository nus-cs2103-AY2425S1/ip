import java.util.Objects;
import java.util.Scanner;


public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";

    private static void greeting_line() {
        System.out.println(DASH_BREAK);
        String greeting = "Hello I'm " + NAME + "!\n"
                + "What can I help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closing_line() {
        System.out.println(DASH_BREAK);
        String closing = "Byeee! Nice to meet you!\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    private static void main_chat(String answer) {
        System.out.println(DASH_BREAK);
        System.out.println(answer);
        System.out.println(DASH_BREAK);

    }

    public static void main(String[] args) {
        greeting_line();
        String answer = "";
        while (!Objects.equals(answer, "bye")) {
            Scanner ansObj = new Scanner(System.in);
            answer  = ansObj.nextLine();
            main_chat(answer);
        }
        closing_line();
    }
}
