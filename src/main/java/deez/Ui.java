package deez;

import java.util.ArrayList;

public class Ui {
    private static void printSeperator () {
        System.out.println("____________________________________________________________");
    }

    static void say(String... msgs) {
        printSeperator();
        for (String msg : msgs) {
            System.out.println("> " + msg);
        }
        printSeperator();
    }

    static void printList(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            Ui.say("<No items in list>");
            return;
        }
        // Print list
        int cnt = 1;
        printSeperator();
        for (Object o: arrayList) {
            System.out.println(cnt + ". " + o.toString());
            cnt += 1;
        }
        printSeperator();
    }
}
