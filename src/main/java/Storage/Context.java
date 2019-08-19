package Storage;

public class Context {
    private static Storage STORAGE;

    private Context() {}

    public static Storage getStorage() {
        if (STORAGE == null) {
            STORAGE = new Storage();
        }
        return STORAGE;
    }
}
