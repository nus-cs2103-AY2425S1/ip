package duck;

import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Parser {
    private final String line;
    private final StringTokenizer st;
    private String token;

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
     */
    public int getInt() {
        // TODO: Handle exception
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
     * Read tokens until before the pattern specified has been found
     * and joins them into a single line. (Excludes the pattern.)
     *
     * @return tokens until before the pattern joined into a single line
     */
    public String getUntil(String pattern) {
        StringJoiner sj = new StringJoiner(" ");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (word.equals(pattern)) {
                token = word;
                break;
            }
            sj.add(word);
        }
        return sj.toString();
    }

    /**
     * Read tokens until before the pattern specified has been found
     * and joins them into a single line. (Excludes the pattern.) Then,
     * remove the pattern.
     *
     * @return tokens until before the pattern joined into a single line
     */
    public String getUntilAndRemovePattern(String pattern) {
        String output = getUntil(pattern);
        nextToken();
        return output;
    }
}
