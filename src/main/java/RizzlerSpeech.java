public class RizzlerSpeech extends Speech {
    private static final String DEFAULT_PREFIX = "\t";
    private String prefix;

    RizzlerSpeech() {
        this(DEFAULT_PREFIX);
    }

    RizzlerSpeech(String prefix) {
        super();
        this.prefix = prefix;
    }

    RizzlerSpeech(String line, String prefix) {
        super(line);
        this.prefix = prefix;
    }

    public void changePrefix(String newPrefix) {
        this.prefix = newPrefix;
    }

    @Override
    public void say(String text) {
        super.say(this.prefix + text);
    }

    @Override
    public void lineBreak() {
        super.lineBreak(this.prefix, "");

    }

    public void list(String[] log) {
        this.lineBreak();
        for (int i = 0; i < log.length; i++) {
            this.say((i + 1) + ". " + log[i]);
        }
        this.lineBreak();
    }

    public void greet() {
        this.lineBreak();
        this.say("hey. i'm the rizzler.");
        this.say("what do you want");
        this.lineBreak();
    }

    public void bidFarewell() {
        this.lineBreak();
        this.say("sure, cya.");
        this.lineBreak();
    }
}
