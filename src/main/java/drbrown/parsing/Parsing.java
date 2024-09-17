package drbrown.parsing;

import drbrown.command.Command;
import drbrown.utils.DrBrownException;

public abstract class Parsing {

    private String[] inputSplit;

    public Parsing(String[] inputSplit) {
        this.inputSplit = inputSplit;
    }

    public String[] getInputSplit() {
        return this.inputSplit;
    }
    public abstract Command parse() throws DrBrownException;

}
