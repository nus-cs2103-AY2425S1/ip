import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tanjiro {
    public static void main(String[] args) {
        Response r = new Response();
        Input i = new Input();

        r.greet();

        String user_input = i.read();
        while (!((user_input.toLowerCase()).contains("bye"))) {
            if (user_input.equalsIgnoreCase("list")) {
                Task.list_task();
            } else {
                Task task = new Task(user_input);
                task.add_task(task);
            }

            user_input = i.read();
        }

        r.goodbye();

    }

}
