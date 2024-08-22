public class StorageCorruptedException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "seems like file for storage has been WrEckEd (corrupted)...\n"
                + "Sorry master, I'm afraid I couldn't load any existing tasks for you TT\n";
    }

}
