package mel.utils;

import mel.main.Mel;

import java.util.Objects;

public class UI {
    private final Mel mel;

    public UI(Mel mel) {
        this.mel = mel;
    }
    private static final String LOGO = " __    __        __  \n"
            + "|   \\/   | ____ |  | \n"
            + "| |\\  /| |/ __ \\|  | \n"
            + "| | \\/ | |  ___/|  | \n"
            + "|_|    |_|\\____||__| ";
    private static final String LINE = "____________________________________";

    public void hello() {
        System.out.println(LOGO + "\n" + LINE + "\n"
                + "Hihi! Mel here (:\n"
                + "What you need?\n" + LINE);
    }

    public boolean read(String input) {
        System.out.println(LINE);
        boolean isBye;
        if (input.length() > 100) {
            System.out.println("Mel's eyes explode "
                    + "reading your many words XD");
            isBye = false;
        } else if (Objects.equals(input, "bye")) {
            System.out.println("Buh-bye :)");
            isBye = true;
        } else {
            mel.taskAction(input);
            isBye = false;
        }
        System.out.println(LINE);
        return isBye;
    }

    public void println(String str) {
        System.out.println(str);
    }
}
