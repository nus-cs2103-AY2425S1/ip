package features.ui;

import config.Config;
import features.command.Command;
import utils.Utils;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    public Command readCommand() {
        return new Command(sc.nextLine());
    }

    public void updateCommand(Command cmd) {
        cmd.setName(sc.nextLine());
    }
    public void intro() {
        System.out.println(Config.logo);
        Utils.printItem(Config.intro);
    }

    public void outro() {
        Utils.printItem(Config.outro);
    }
}
