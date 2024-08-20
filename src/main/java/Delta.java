import java.util.ArrayList;
import java.util.Scanner;

public class Delta {
    private static ArrayList<String> list = new ArrayList<String>();

    public static String sayHello() {
        return "\t____________________________________________________________\n"
                + "\t Hello! I'm Delta\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";
    }

    public static String sayBye() {
        return "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t_____________________________________________________________";
    }

    public static String add(String phrase) {
        list.add(phrase);
        return "\t____________________________________________________________\n"
                + "\t added: " + phrase + "\n"
                + "\t_____________________________________________________________";
    }

    public static String echo() {
        String output = "\t____________________________________________________________\n";
        for (int i = 0; i < list.size(); i++) {
            output += "\t " + (Integer.toString(i + 1)) + ". " + list.get(i) + "\n";
        }
        output += "\t_____________________________________________________________";
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(sayHello());

        while (sc.hasNextLine()) {
            String phrase = sc.nextLine();
            if (phrase.equals("bye")) {
                System.out.println(sayBye());
                break;
            } else if (phrase.equals("list")) {
                System.out.println(echo());
            } else {
                System.out.println(add(phrase));
            }
        }

        sc.close();
    }
}
