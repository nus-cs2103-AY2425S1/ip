public class Deadline extends Task{
    private String date;

    private Deadline(String name, String date) throws CheeseException {
        super(name);
        this.date = date;
    }

    Deadline(String[] data) throws CheeseException {
        super(data);
        if(data.length != 4) throw new CheeseException("Incorrect data format");
        date = data[3];
    }

    /**
     * Factory method to ensure correct creation of Deadline
     * @param input String
     * @return Deadline
     * @throws CheeseException custom exception
     */
    public static Deadline of(String input) throws CheeseException {
        String[] tokens = input.replace("deadline", "").strip().split("/by");
        if (tokens.length < 2) throw new CheeseException("Deadline needs a /by");
        if (tokens[1].isBlank()) throw new CheeseException("Deadline needs a date!");
        return new Deadline(tokens[0].strip(), tokens[1].strip());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }

    @Override
    public String dataString() {
        String s = super.dataString();
        s = s.replace("T,", "D,");
        s += "," + date;
        return s;
    }
}
