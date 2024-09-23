package duck;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * Class representing a command parser.
 */
public class Parser {
    private final String line;
    private final StringTokenizer st;
    private String token;

    /**
     * Constructor for Parser.
     *
     * @param line Input line.
     */
    public Parser(String line) {
        this.line = line;
        this.st = new StringTokenizer(line);
    }

    /**
     * Returns true if the buffer has more tokens,
     * false otherwise.
     *
     * @return true if the buffer has more tokens,
     *         false otherwise
     */
    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    /**
     * Returns the next token without removing it from
     * the buffer.
     *
     * @return the next token
     */
    public String peekToken() {
        if (token == null && st.hasMoreTokens()) {
            token = st.nextToken();
        }
        return token;
    }

    /**
     * Returns the next token and remove it from
     * the buffer.
     *
     * @return the next token
     */
    private String nextToken() {
        String output = peekToken();
        token = null;
        return output;
    }

    /**
     * Returns the next token as an int and remove it from
     * the buffer.
     *
     * @return the next token
     * @throws NumberFormatException
     */
    public int getInt() throws NumberFormatException {
        return Integer.parseInt(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    /**
     * Returns the whole line and purge the buffer.
     *
     * @return the whole line
     */
    public String getWholeLine() {
        getRemainingLine();
        return line;
    }

    /**
     * Joins the remaining tokens in the line and purge the buffer.
     *
     * @return the remaining line
     */
    public String getRemainingLine() {
        // Remove pointer to the next token
        token = null;

        StringJoiner sj = new StringJoiner(" ");
        while (st.hasMoreTokens()) {
            sj.add(st.nextToken());
        }
        return sj.toString();
    }

    /**
     * Reads tokens until before the pattern specified has been found
     * and joins them into a single line. (Excludes the pattern.)
     *
     * @return tokens until before the pattern joined into a single line
     */
    public String getUntil(String pattern) {
        StringJoiner sj = new StringJoiner(" ");
        while (hasMoreTokens()) {
            String word = nextToken();
            if (word.startsWith(pattern)) {
                token = word;
                break;
            }
            sj.add(word);
        }
        return sj.toString();
    }

    /**
     * Searches for words beginning with "/" and parses the corresponding argument
     * values.
     *
     * @return A map from patterns (e.g. "/by", "/after") to argument
     *         names (e.g. "due_date", "earliest_date").
     */
    public Map<String, String> parseArgs() {
        Map<String, String> parts = new HashMap<>();
        String argName = "";

        while (hasMoreTokens()) {
            String part = getUntil("/");
            parts.put(argName, part);
            argName = getWord();
        }
        return parts;
    }
}
