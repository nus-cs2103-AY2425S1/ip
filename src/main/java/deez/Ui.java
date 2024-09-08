package deez;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The Ui class contains utility methods for user interface.
 */
public class Ui {
    // TODO: We should probably abstract the payload of this consumer to support
    //       a more diverse range of actions
    private Consumer<String> messageConsumer;

    /**
     * Initialise Ui in a headless manner
     */
    public Ui() {
        messageConsumer = (String message) -> {
            System.out.println("WARN: Ui is running in headless mode");
            System.out.println(message);
        };
    }

    /**
     * Initialise Ui with a message consumer
     */
    public Ui(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    /**
     * Send messages to the user
     *
     * @param msgs The messages to say. It can be an empty array.
     */
    public void say(String... msgs) {
        for (String msg : msgs) {
            messageConsumer.accept(msg);
        }
    }

    /**
     * Renders a numbered list of items to the user.
     *
     * @param arrayList The list to print.
     */
    public void printList(List<?> arrayList) {
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
