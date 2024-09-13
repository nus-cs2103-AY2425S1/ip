package neon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Ui {
    private static final String NAME = "neon.Neon";
    private static final String DASH_BREAK = "-----------------------------------\n";

    public String printGreetingLine() {
        StringBuilder response = new StringBuilder();
        response.append(DASH_BREAK);
        response.append("hello i'm " + NAME + "!\n"
                + "what can i help you with?");
        response.append(DASH_BREAK);
        return response.toString();
    }

    public String printClosingLine() {
        StringBuilder response = new StringBuilder();
        response.append("byeee! nice to meet you :)\n");
        response.append(DASH_BREAK);
        return response.toString();
    }

    public void printLine() {
        System.out.println(DASH_BREAK);
    }
}
