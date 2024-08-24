package chatkaki.commands;

import chatkaki.Ui;

public class CommandUnknown extends Command {
    private String[] inputs;

    public CommandUnknown(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        Ui.printMessage("Command not found, try another one!");
        Ui.printMessage("Commands available are as follows:" + System.lineSeparator()
                + "todo <description>" + System.lineSeparator()
                + "deadline <description> /by <date>" + System.lineSeparator()
                + "event <description> /from <date> /to <date>" + System.lineSeparator()
                + "list" + System.lineSeparator()
                + "mark <index>" + System.lineSeparator()
                + "unmark <index>" + System.lineSeparator()
                + "delete <index>" + System.lineSeparator()
                + "bye");
    }
}