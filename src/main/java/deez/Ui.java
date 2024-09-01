package deez;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * The Ui class contains utility methods for user interface.
 */
public class Ui {
    private Consumer<String> messageConsumer = (String message) -> {};

    public Ui() {
    }
    public Ui(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    /**
     * Say messages with a separator before and after the messages.
     *
     * @param msgs The messages to say. It can be an empty array.
     */
    public void say(String... msgs) {
        for (String msg : msgs) {
            messageConsumer.accept("> " + msg);
        }
    }

    /**
     * Print the elements of a given list to console.
     *
     * @param arrayList The list to print.
     */
    public void printList(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            say("<No items in list>");
            return;
        }
        // Print list
        int cnt = 1;
        for (Object o : arrayList) {
            messageConsumer.accept(cnt + ". " + o.toString());
            cnt += 1;
        }
    }
}
