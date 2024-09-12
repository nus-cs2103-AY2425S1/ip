package broski.parser;

/**
 * Simple class that parses some input by users.
 */
public class Parser {
    /**
     * Parses and gets length of array after splitting.
     * @param reply response from the user.
     * @return length of array.
     */
    public int parseLength(String reply) {
        return reply.split(" /").length;
    }

    /**
     * Parses and gets integer found in response.
     * @param reply response from the user.
     * @return index needed for tasklist.
     */
    public int parseIndex(String reply) {
        return Integer.parseInt(reply.split(" ")[1]);
    }
}
