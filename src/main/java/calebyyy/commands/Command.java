package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.exceptions.CalebyyyException;

public abstract class Command {
    protected Calebyyy calebyyy;

    public Command(Calebyyy calebyyy) {
        this.calebyyy = calebyyy;
    }

    public abstract void execute(String input) throws CalebyyyException;
}