package util;

import action.Action;
import action.EchoAction;

/**
 * Handles parsing of user input
 *
 * @author dwsc37
 */
public class Parser {
    public Action parseInput(String input) {
        return new EchoAction(input);
    }
}
