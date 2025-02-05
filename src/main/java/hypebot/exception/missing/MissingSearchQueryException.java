package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_SEARCH_QUERY_EMPTY;

import hypebot.command.FindCommand;
import hypebot.parser.Parser;
import hypebot.parser.command.FindQueryParser;

/**
 * Represents a {@code MissingSearchQueryException} associated with errors resulting
 * from missing search query.
 * <p>A child of {@link MissingArgumentException}.</p>
 * <p>Thrown whenever search query when parsing a {@link FindCommand} is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Parser
 * @see FindQueryParser
 */
public class MissingSearchQueryException extends MissingArgumentException {
    /**
     * Creates a new {@code MissingSearchDateException} with a message alerting that
     * search query for a {@link FindCommand} is missing.
     */
    public MissingSearchQueryException() {
        super(ERROR_SEARCH_QUERY_EMPTY);
    }
}
