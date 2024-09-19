package hypebot.parser;

import hypebot.parser.command.CommandParser;
import hypebot.parser.command.FindQueryParser;
import hypebot.parser.task.FileTaskParser;
import hypebot.parser.task.TaskParser;
import hypebot.parser.task.UiTaskParser;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the base {@code Parser} class which all {@code Parser}s inherit from.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TaskParser
 * @see FileTaskParser
 * @see UiTaskParser
 * @see CommandParser
 * @see FindQueryParser
 */
public abstract class Parser {
    /**
     * Represents the base method implemented by all children of {@code Parser}.
     * Takes in the {@link String} full line of user input from {@link UiGuiMainWindow}
     * and returns the corresponding {@link Object}.
     *
     * @param input Some {@link String} form of input.
     * @return Appropriate {@link Object} to return.
     */
    public abstract Object parse(String input);
}
