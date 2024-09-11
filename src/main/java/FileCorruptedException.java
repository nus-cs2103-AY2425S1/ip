public class FileCorruptedException extends BobException {
    public FileCorruptedException() {
        super("Yikes, looks like the data file's busted.\n" +
                "We're just gonna start with a fresh list.");
    }
}
