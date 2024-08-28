package alex.ui;

public class Ui {
    public static final String LINE = "----------------------------------------------------";
    public static final String byeMessage = "Bye. Hope to see you again soon!";

    public void welcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Alex 👋🏼🤖 \n" +
                "What can I do for you? ");
        System.out.println(LINE);
    }
    public void byeMessage() {
        System.out.println(LINE);
        System.out.println(byeMessage);
        System.out.println(LINE);
    }
}
