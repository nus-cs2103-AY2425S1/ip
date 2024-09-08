package Bonnie;

import java.io.IOException;
import java.util.ArrayList;

public class Bonnie {

    private static String username;
    private static ArrayList<Task> tasklist = new ArrayList<>();

    public static void main(String[] args) throws EmptyTodoException, UnknownCommandException,
            DeadlineFormatException, IOException {
        startApp();
    }

    protected static void startApp() throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        Storage.init();
        Ui.init();
    }
}
