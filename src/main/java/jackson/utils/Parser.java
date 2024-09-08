package jackson.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jackson.enums.Actions;
import jackson.exceptions.SyntaxException;

/**
 * Parser static class to handle regex checking and input parsing.
 * Regex check will check for a specified pattern in the input string and return any matches.
 * This helps to provide a standardized and simple way to check for edge cases and abnormal input.
 */
public class Parser {

    /* Bunch of regex patterns for user input checking */
    private static final Pattern TODO = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE = Pattern.compile("^deadline (.+) /by "
            + "(\\d{2}-\\d{2}-\\d{4}(?: \\d{2}:\\d{2})?)$");
    private static final Pattern EVENT = Pattern.compile("^event (.+) /from "
            + "(\\d{2}-\\d{2}-\\d{4}(?: \\d{2}:\\d{2})?) /to (\\d{2}-\\d{2}-\\d{4}(?: \\d{2}:\\d{2})?)$");
    private static final Pattern MARK = Pattern.compile("^mark (\\d+)$");
    private static final Pattern UNMARK = Pattern.compile("^unmark (\\d+)$");
    private static final Pattern LIST = Pattern.compile("^list$");
    private static final Pattern DELETE = Pattern.compile("^delete (\\d+)$");
    private static final Pattern FIND = Pattern.compile("^find ([\\w\\d ]+)$");
    private static final Pattern SECRET = Pattern.compile("^secret$");
    private static final Pattern BYE = Pattern.compile("^bye$");

    /**
     * Parses the provided query and checks it against the relevant regex.
     * Either throws an Exception if error is found, else returns a Response.
     * @param  query String containing user input.
     * @return Response containing Action Type and regex Matcher object.
     * @throws SyntaxException when unknown query is parsed.
     */
    public static Response parse(String query) throws SyntaxException {
        Actions.ActionType a;
        Matcher m = null;

        /*
            Generally:
            1. Match regex with input
            2. if regex doesn't match, throw exception (signifying wrong input format)
            3. if they do match, package action to be taken and parsed user input into Response object
        */
        if (query.startsWith("list")) {
            m = LIST.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("list");
            }
            a = Actions.ActionType.LIST;
        } else if (query.startsWith("todo")) {
            m = TODO.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("todo");
            }
            a = Actions.ActionType.TODO;
        } else if (query.startsWith("deadline")) {
            m = DEADLINE.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("deadline");
            }
            a = Actions.ActionType.DEADLINE;
        } else if (query.startsWith("event")) {
            m = EVENT.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("event");
            }
            a = Actions.ActionType.EVENT;
        } else if (query.startsWith("mark")) {
            m = MARK.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("mark");
            }
            a = Actions.ActionType.MARK;
        } else if (query.startsWith("unmark")) {
            m = UNMARK.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("unmark");
            }
            a = Actions.ActionType.UNMARK;
        } else if (query.startsWith("delete")) {
            m = DELETE.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("delete");
            }
            a = Actions.ActionType.DELETE;
        } else if (query.startsWith("find")) {
            m = FIND.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("find");
            }
            a = Actions.ActionType.FIND;
        } else if (query.startsWith("bye")) {
            m = BYE.matcher(query);
            if (!m.find()) {
                throw new SyntaxException("bye");
            }
            a = Actions.ActionType.BYE;
        } else if (SECRET.matcher(query).find()) {
            a = Actions.ActionType.SECRET;
        } else {
            a = Actions.ActionType.INVALID;
        }
        return new Response(a, m);
    }
}
