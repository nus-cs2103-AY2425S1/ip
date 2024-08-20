import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Parser class to handle regex checking and input parsing.
 */
public class Parser {

    /* Bunch of regex patterns for user input checking */
    private static final Pattern TODO = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern EVENT = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
    private static final Pattern MARK = Pattern.compile("^mark ([0-9]+)$");
    private static final Pattern UNMARK = Pattern.compile("^unmark ([0-9]+)$");
    private static final Pattern LIST = Pattern.compile("^list$");
    private static final Pattern DELETE = Pattern.compile("^delete ([0-9]+)$");
    private static final Pattern SECRET = Pattern.compile("^national anthem$");
    private static final Pattern BYE = Pattern.compile("^bye$");

    /**
     * Parses the provided query and checks it against the relevant regex.
     * Either throws an Exception if error is found, else returns a Response
     * @param  query String containing user input
     * @return Response containing Action Type and regex Matcher object
     * @throws JacksonException Jackson-specific exception
     */
    public static Response parse(String query) throws JacksonException {
        Actions.ACTIONS a;
        Matcher m = null;

        /*
            Generally:
            1. Match regex with input
            2. if regex doesn't match, throw exception (signifying wrong input format)
            3. if they do match, package action to be taken and parsed user input into Response object
        */
        if (query.startsWith("list")) {
            m = LIST.matcher(query);
            if (!m.find()) throw new SyntaxException("list");
            a = Actions.ACTIONS.LIST;
        } else if (query.startsWith("todo")) {
            m = TODO.matcher(query);
            if (!m.find()) throw new SyntaxException("todo");
            a = Actions.ACTIONS.TODO;
        } else if (query.startsWith("deadline")) {
            m = DEADLINE.matcher(query);
            if (!m.find()) throw new SyntaxException("deadline");
            a = Actions.ACTIONS.DEADLINE;
        } else if (query.startsWith("event")) {
            m = EVENT.matcher(query);
            if (!m.find()) throw new SyntaxException("event");
            a = Actions.ACTIONS.EVENT;
        } else if (query.startsWith("mark")) {
            m = MARK.matcher(query);
            if (!m.find()) throw new SyntaxException("mark");
            a = Actions.ACTIONS.MARK;
        } else if (query.startsWith("unmark")) {
            m = UNMARK.matcher(query);
            if (!m.find()) throw new SyntaxException("unmark");
            a = Actions.ACTIONS.UNMARK;
        } else if (query.startsWith("delete")) {
            m = DELETE.matcher(query);
            if (!m.find()) throw new SyntaxException("delete");
            a = Actions.ACTIONS.DELETE;
        } else if (query.startsWith("bye")) {
            m = BYE.matcher(query);
            if (!m.find()) throw new SyntaxException("bye");
            a = Actions.ACTIONS.BYE;
        } else if (SECRET.matcher(query).find()) {
            a = Actions.ACTIONS.SECRET;
        } else {
            a = Actions.ACTIONS.INVALID;
        }
        return new Response(a, m);
    }
}
