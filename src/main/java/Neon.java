import java.util.Objects;
import java.util.Scanner;


public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";
    private static String[] list = new String[100];
    private static int last_list_index = 0;
    private static void greeting_line() {
        System.out.println(DASH_BREAK);
        String greeting = "Hello I'm " + NAME + "!\n"
                + "What can I help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closing_line() {
        System.out.println(DASH_BREAK);
        String closing = "Byeee! Nice to meet you :)\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    private static void main_chat(String answer) {
        System.out.println(DASH_BREAK);
        System.out.println("adding to list : " + answer);
        System.out.println(DASH_BREAK);

        list[last_list_index] = answer;
        last_list_index++;
    }

    private static void print_list() {
        System.out.println(DASH_BREAK);
        for(int i = 0; i < last_list_index; i++) {
            System.out.println(i + ". " + list[i]);
        }
        System.out.println(DASH_BREAK);
    }

    public static void main(String[] args) {
        boolean chatting = true;
        greeting_line();
        String answer = "";
        while (chatting) {
            Scanner ansObj = new Scanner(System.in);
            answer  = ansObj.nextLine();

            switch (answer) {
                case "bye":
                    chatting = false;
                    break;
                case "list":
                    print_list();
                    break;
                default:
                    main_chat(answer);
                    break;
            }
        }
        closing_line();
    }
}
