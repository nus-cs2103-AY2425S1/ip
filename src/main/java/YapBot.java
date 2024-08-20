import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class YapBot {
    private static final String PREFIXLINE = "\n-------------------------------------------";
    private static final String POSTFIXLINE = "-------------------------------------------\n";

    public static void list(ArrayList<Task> stored) {
        Iterator<Task> iterateStored = stored.iterator();
        System.out.println(PREFIXLINE);
        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println(index + "." + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIXLINE);
    }

    public static void main(String[] args) {

        System.out.println(PREFIXLINE + "\nHello, I am YapBot. \nHow can I help?\n" + POSTFIXLINE);

        Scanner in = new Scanner(System.in);
        ArrayList<Task> stored = new ArrayList<>();
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(stored);
                input = in.nextLine();
            } else if (input.contains("unmark")) {
                int index = input.charAt(input.length() - 1);
                stored.get(index - 1).changeDone(false);
            } else if (input.contains("mark")) {
                int index = Integer.parseInt(input.substring(input.length() - 1));
                stored.get(index - 1).changeDone(true);
                input = in.nextLine();
            }  else {
                Task taskToAdd = new Task(input);
                stored.add(taskToAdd);
                System.out.println(PREFIXLINE + "\nAdded: " + input + "\n" + POSTFIXLINE);
                input = in.nextLine();
            }
        }

        in.close();
        System.out.println(PREFIXLINE + "\nAlright, enough yapping for one day.\n" + POSTFIXLINE);
    }
}
