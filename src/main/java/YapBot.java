import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class YapBot {
    private static final String PREFIXLINE = "\n-------------------------------------------";
    private static final String POSTFIXLINE = "-------------------------------------------\n";

    public static void list(ArrayList<String> stored) {
        Iterator<String> iterateStored = stored.iterator();
        System.out.println(PREFIXLINE);
        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println(index + ". " + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIXLINE);
    }

    public static void main(String[] args) {

        System.out.println(PREFIXLINE + "\nHello, I am YapBot. \nHow can I help?\n" + POSTFIXLINE);

        Scanner in = new Scanner(System.in);
        ArrayList<String> stored = new ArrayList<>();
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(stored);
                input = in.nextLine();
            } else {
                stored.add(input);
                System.out.println(PREFIXLINE + "\nAdded: " + input + "\n" + POSTFIXLINE);
                input = in.nextLine();
            }

        }

        in.close();
        System.out.println(PREFIXLINE + "\nAlright, enough yapping for one day.\n" + POSTFIXLINE);
    }
}
