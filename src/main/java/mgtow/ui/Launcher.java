package mgtow.ui;

import javafx.application.Application;
import mgtow.Mgtow;

public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            new Mgtow("./data/MGTOW.txt").run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}