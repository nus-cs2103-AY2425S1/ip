package hypebot.parser.command;

import java.util.regex.Pattern;

import hypebot.command.FindCommand;
import hypebot.command.HappeningCommand;
import hypebot.exception.missing.MissingArgumentException;
import hypebot.exception.missing.MissingSearchQueryException;
import hypebot.parser.Parser;
import hypebot.task.Task;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code FindQueryParser} which parses search keywords for a {@link FindCommand}
 * from a full line inputted by the user at {@link UiGuiMainWindow} and returns the corresponding
 * regex {@link Pattern} to search for.
 * <p>A child of {@link Parser}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see MissingArgumentException
 * @see MissingSearchQueryException
 */
public class FindQueryParser extends Parser {
    /**
     * Takes in the user input separated into words and checks that search keywords
     * to find {@link Task}s with matching names were entered by user.
     *
     * @param inputWords {@link String} array of user input split by word.
     * @throws MissingSearchQueryException If user has not put in any keywords as a search query.
     */
    private void checkKeywordsExist(String[] inputWords) throws MissingSearchQueryException {
        if (inputWords.length == 1) {
            throw new MissingSearchQueryException();
        }
    }

    /**
     * Takes in the full line entered by user and returns the {@link String} array
     * of keywords to search for {@link Task}s with matching names.
     *
     * @param line Line read from user interface from {@link UiGuiMainWindow}.
     * @return {@link String} array of keywords to search for {@link Task}s with matching names.
     * @throws MissingSearchQueryException If user has not put in any keywords as a search query.
     */
    private String[] extractKeywords(String line) throws MissingSearchQueryException {
        /* Splits the full line entered by user and checks that search query was entered. */
        String[] inputWords = line.split(" /")[0].split(" ");
        checkKeywordsExist(inputWords);

        /* Extracts the keywords for the search query only. */
        String[] keywords = new String[inputWords.length - 1];
        System.arraycopy(inputWords, 1, keywords, 0, inputWords.length - 1);

        return keywords;
    }

    /**
     * Takes in the full line entered by user and returns the {@link String} form of the
     * regex {@link Pattern} for finding {@link Task}s whose names contain any of the
     * keywords specified.
     *
     * @param line Line read from user interface from {@link UiGuiMainWindow}.
     * @return {@link String} form of the regex {@link Pattern} searching a match to any keywords.
     * @throws MissingSearchQueryException If user has not put in any keywords as a search query.
     */
    private String assembleKeywordsRegex(String line) throws MissingSearchQueryException {
        String[] keywords = extractKeywords(line);
        StringBuilder keywordsSb = new StringBuilder();
        for (int i = 0; i < keywords.length - 1; i++) {
            keywordsSb.append(keywords[i].toLowerCase()).append("|");
        }
        keywordsSb.append(keywords[keywords.length - 1]);
        return keywordsSb.toString().trim();
    }

    /**
     * Takes in the full line entered by user and returns the regex {@link Pattern}
     * for finding {@link Task}s whose names contain any of the keywords specified
     * to be taken in by a {@link HappeningCommand} during instantiation.
     *
     * @param line Line read from user interface from {@link UiGuiMainWindow}.
     * @return Regex {@link Pattern} indicating a match to any keywords specified by user.
     * @throws MissingArgumentException If user has not put in any keywords as a search query.
     */
    @Override
    public Pattern parse(String line) throws MissingArgumentException {
        String keywordsRegex = assembleKeywordsRegex(line);
        return Pattern.compile(keywordsRegex, Pattern.CASE_INSENSITIVE);
    }
}
