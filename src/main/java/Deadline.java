public class Deadline extends Task {

    String end;

    private Deadline(String str, String end) {
        super(str);
        this.end = end.substring(0, 2) + ":" + end.substring(2);
    }

    public static Deadline of(String s) throws BigdogException {
        if (s.length() <= 9) {
            throw new BigdogException("deadline can't be empty! Theres nothing to do!");
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                return new Deadline(s.substring(9, i - 1), s.substring(i + 1));
            }
        }
        throw new BigdogException("Come on! Set a due by date and get to work!");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + end + ")";
    }

}
