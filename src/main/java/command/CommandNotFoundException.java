package command;

import ouiouibaguette.OuiOuiBaguetteException;

public class CommandNotFoundException extends OuiOuiBaguetteException {
    public CommandNotFoundException(String msg) {
        super(msg);
    }
}
