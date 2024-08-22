package action;

import util.Ui;

/**
 * Action that echos the user's input
 *
 * @author dwsc37
 */
public class EchoAction extends Action {
    private final String input;

    public EchoAction(String input) {
        this.input = input;
    }

    @Override
    public String execute() {
        return input;
    }
}
