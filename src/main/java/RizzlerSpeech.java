import java.util.HashMap;

/**
 * Manages all speech outputting from the <code>Rizzler</code> class.
 * Responsible for printing and formatting of <code>Rizzler</code> output.
 */
public class RizzlerSpeech extends Speech {
    private static final String DEFAULT_PREFIX = "\t";
    private static final HashMap<String, String[]> RESPONSES = new HashMap<>();
    private String prefix;

    /**
     * Initialises a <code>RizzlerSpeech</code> object with the default prefix, a tab.
     */
    RizzlerSpeech() {
        this(DEFAULT_PREFIX);
    }

    /**
     * Initialises a <code>RizzlerSpeech</code> object with the given prefix.
     * @param prefix The prefix that will be pre-ended to every message printed by this object.
     */
    RizzlerSpeech(String prefix) {
        super();
        this.prefix = prefix;
        initResponses();
    }

    /**
     * Initialises <code>Map</code> of responses for the <code>RizzlerSpeech</code> object.
     */
    private static void initResponses() {
        RESPONSES.put("list", new String[]{"these are the things we gotta do:"});
        RESPONSES.put("add", new String[]{"add a new thing"});
        RESPONSES.put("greet", new String[]{"how ya' doin. i'm the rizzler.",
                "how may i be of service to you today?"});
        RESPONSES.put("farewell", new String[]{"aight bet, cya."});
        RESPONSES.put("mark", new String[]{"niceeee, i'll note that you've completed this."});
        RESPONSES.put("unmark", new String[]{"hey no worries, "});
    }

    /**
     * Changes current <code>RizzlerSpeech</code> object's prefix.
     * @param newPrefix New prefix to replace the old prefix.
     */
    public void changePrefix(String newPrefix) {
        this.prefix = newPrefix;
    }

    /**
     * Prints out the given text with a prefix pre-pended.
     * @param text <code>String</code> to be printed as output.
     */
    @Override
    public void say(String text) {
        super.say(this.prefix + text);
    }

    /**
     * Prints out every <code>String</code> in the given textArr with prefixes pre-pended.
     * @param textArr Array of <code>String</code> objects to be printed as output.
     */
    public void say(String[] textArr) {
        for (String text : textArr) {
            this.say(text);
        }
    }

    /**
     * Prints out the linebreak string with a prefix pre-pended.
     */
    public void say() {
        System.out.print(this.prefix);
        super.lineBreak();
    }

    /**
     * Prints out every task in the given <code>log</code> with proper prefixing and formatting.
     * @param log An array of <code>Task</code> objects to be printed as output to the user.
     */
    public void list(Task[] log) {
        this.say();
        this.say(RESPONSES.get("list"));
        for (int i = 0; i < log.length; i++) {
            this.say((i + 1) + ". " + log[i]);
        }
        this.say();
    }

    /**
     * Prints out the pre-determined greeting with proper prefixing and formatting.
     */
    public void greet() {
        this.say();
        this.say(RESPONSES.get("greet"));
        this.say();
    }

    /**
     * Prints out the pre-determined farewell message with proper prefixing and formatting.
     */
    public void bidFarewell() {
        this.say();
        this.say(RESPONSES.get("farewell"));
        this.say();
    }
}
