package Response;

public class NullResponse extends Response {

    public NullResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;
}
