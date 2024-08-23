import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> list = new ArrayList<>();
    public static String addHorizontalLines(String dialog) {
        String res = "____________________________________________________________\n" +
                dialog + "\n" +
                "____________________________________________________________";
        return res;
    }

    public static void addToList(String dialog) {
        list.add(dialog);
        String res = "____________________________________________________________\n" +
                "added: " + dialog + "\n" +
                "____________________________________________________________";
        System.out.println(res);
    }

    public static void displayList() {
        String lString = "";
        int index = 1;
        for (String s : list) {
            if (index == list.size()) {
                lString += index + ". " + s;
                index++;
                break;
            }
            lString += index + ". " + s + "\n";
            index++;
        }
        lString = addHorizontalLines(lString);
        System.out.println(lString);
    }


    public static void main(String[] args) {
        String hi = " Hello! I'm Foo\n" +
                " What can I do for you?";
        System.out.println(addHorizontalLines(hi));
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                displayList();
                continue;
            }
            addToList(line);
        }
        System.out.println(addHorizontalLines("Bye. Hope to see you again soon!"));
    }
}
