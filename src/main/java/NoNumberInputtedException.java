public class NoNumberInputtedException extends DukeException {
    NoNumberInputtedException() {

        super("test");
    }

    @Override
    public String toString() {

        return "wah shag bro, you forgot to add a number after your mark/unmark/delete command";
    }
}
