import java.util.ArrayList;

public class Ui {
    static void say(String... msgs) {
        System.out.println("____________________________________________________________");
        for (String msg : msgs) {
            System.out.println("> " + msg);
        }
        System.out.println("____________________________________________________________");
    }

    static void printList(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            Ui.say("<No items in list>");
            return;
        }
        // Print list
        int cnt = 1;
        System.out.println("____________________________________________________________");
        for (Object o: arrayList) {
            System.out.println(cnt + ". " + o.toString());
            cnt += 1;
        }
        System.out.println("____________________________________________________________");
    }
}
