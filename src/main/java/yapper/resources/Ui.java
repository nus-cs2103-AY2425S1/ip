package yapper.resources;

/**
 * UI class
 */
public class Ui {

    private static final String DIVIDER = "-------------------------------------------------";
    private static final String NAME = "yapper.main.Yapper";

    public Ui() {
    }

    public void intro() {
        String[] temp = {
                "Hello! I'm " + NAME,
                "What can I do for you?"
        };
        wrapText(temp);
    }

    public static void showLine() {
        System.out.println(DIVIDER);
    }

    public static void wrapText(String text) {
        showLine();
        System.out.println(text);
        showLine();
    }

    public static void wrapText(String[] texts) {
        System.out.println(DIVIDER);
        for (String s : texts) {
            System.out.println(s);
        }
        System.out.println(DIVIDER);
    }

    public static void errorCaught(String errorMessage) {
        wrapText(errorMessage);
    }

}
