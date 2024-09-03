import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Ui {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";

    public void printGreetingLine() {
        System.out.println(DASH_BREAK);
        String greeting = "hello i'm " + NAME + "!\n"
                + "what can i help you with?";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    public void printClosingLine() {
        String closing = "byeee! nice to meet you :)";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    public void printLine() {
        System.out.println(DASH_BREAK);
    }
}
