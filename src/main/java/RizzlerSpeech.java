import java.util.HashMap;

public class RizzlerSpeech extends Speech {
    private static final String DEFAULT_PREFIX = "\t";
    private static final HashMap<String, String[]> RESPONSES = new HashMap<String, String[]>();
    private String prefix;


    RizzlerSpeech() {
        this(DEFAULT_PREFIX);
    }

    RizzlerSpeech(String prefix) {
        super();
        this.prefix = prefix;
        initResponses();
    }

    private static void initResponses() {
        RESPONSES.put("list", new String[]{"these are the things we gotta do:"});
        RESPONSES.put("add", new String[]{"add a new thing"});
        RESPONSES.put("greet", new String[]{"how ya' doin. i'm the rizzler.",
                "how may i be of service to you today?"});
        RESPONSES.put("farewell", new String[]{"aight bet, cya."});
        RESPONSES.put("mark", new String[]{"niceeee, i'll note that you've completed this."});
        RESPONSES.put("unmark", new String[]{"hey no worries, "});
    }

    public void changePrefix(String newPrefix) {
        this.prefix = newPrefix;
    }

    @Override
    public void say(String text) {
        super.say(this.prefix + text);
    }

    public void say(String[] textArr) {
        for (String text : textArr) {
            this.say(text);
        }
    }

    public void say() {
        System.out.print(this.prefix);
        super.lineBreak();
    }

    public void list(Task[] log) {
        this.say();
        this.say(RESPONSES.get("list"));
        for (int i = 0; i < log.length; i++) {
            this.say((i + 1) + ". " + log[i]);
        }
        this.say();
    }

    public void greet() {
        this.say();
        this.say(RESPONSES.get("greet"));
        this.say();
    }

    public void bidFarewell() {
        this.say();
        this.say(RESPONSES.get("farewell"));
        this.say();
    }
}
