public class Event extends Task{

    String start;
    String end;

    private Event(String str, String start, String end, boolean marked) {
        super(str, marked);
        this.start = start;
        this.end = end;
    }

    public static Event of(String s) throws BigdogException {
        if (s.length() <= 6) {
            throw new BigdogException("event can't be empty! If theres no event then go and sleep!");
        }

        for (int j = s.length() - 1; j > 5; j--) {
            if (s.charAt(j) != '/') {
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '/') {
                    return new Event(s.substring(6, i - 1), s.substring(i + 1, j - 1),
                            s.substring(j + 1), false);
                }
            }
        }
        throw new BigdogException("Event has to have a start and end!");
    }

    public static Event of(String s, boolean marked) throws BigdogException {
        if (s.length() <= 4) {
               throw new BigdogException("event can't be empty! If theres no event then go and sleep!");
        }

            for (int j = s.length() - 1; j > 3; j--) {
                if (s.charAt(j) != '|') {
                    continue;
                }
                for (int i = 5; i < s.length(); i++) {
                    if (s.charAt(i) == '|') {
                            return new Event(s.substring(4, i - 1), s.substring(i + 2, j - 1),
                                            s.substring(j + 2), marked);
                    }
                }
            }
        throw new BigdogException("Event has to have a start and end!");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.start + " | " + this.end;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + start + " " + end + ")";
    }

}
