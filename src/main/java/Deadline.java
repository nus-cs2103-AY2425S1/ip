public class Deadline extends Task {

    String end;

    private Deadline(String str, String end, boolean marked) {
        super(str, marked);
        this.end = end;
    }

    public static Deadline of(String s) throws BigdogException {
        if (s.length() <= 9) {
            throw new BigdogException("deadline can't be empty! Theres nothing to do!");
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                return new Deadline(s.substring(9, i - 1), s.substring(i + 1), false);

            }
        }
        throw new BigdogException("Come on! Set a due by date and get to work!");
    }
    public static Deadline of(String s, boolean marked) throws BigdogException {
        if (s.length() <= 4) {
            throw new BigdogException("data file corrupted!");
        }

        for (int i = 5; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                return new Deadline(s.substring(4, i - 1), s.substring(i + 2), marked);
            }
        }
        throw new BigdogException("Come on! Set a due by date and get to work!");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.end;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + end.substring(0, 2) + ":" + end.substring(2) + ")";
    }

}
