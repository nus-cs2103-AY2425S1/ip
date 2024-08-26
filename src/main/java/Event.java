public class Event extends Task{

    String start;
    String end;

    private Event(String str, String start, String end) {
        super(str);
        this.start = start.substring(0, 4) + ":" + start.substring(4);
        this.end = end.substring(0, 2) + ":" + end.substring(2);
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
                            s.substring(j + 1));
                }
            }
        }
        throw new BigdogException("Event has to have a start and end!");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + start + " " + end + ")";
    }

}
