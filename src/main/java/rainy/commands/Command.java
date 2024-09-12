package rainy.commands;

import rainy.database.Parser;
import rainy.database.UI;

public class Command {
    protected Parser ps;
    protected UI ui;

    public Command() {
        this.ps = new Parser();
        this.ui = new UI();
    }
}