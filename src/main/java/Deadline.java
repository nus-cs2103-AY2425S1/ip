public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws SpongebobException{
        super(description);
        this.by = by;

        if (description.equals(" ") || by.equals(" ")) {
            String msg = "";
            if (description.equals(" ")) {
                msg += " Description,";
            }
            if (by.equals(" ")) {
                msg += " By";
            }

            throw new SpongebobException("Barnacles! You missed out " + msg + "!");
        }

    }





    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
