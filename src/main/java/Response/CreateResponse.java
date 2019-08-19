package Response;


public final class CreateResponse extends Response {
    public CreateResponse(int createSlots) {
        this.createSlots = createSlots;
    }

    public int getSlots() {
        return createSlots;
    }

    private int createSlots;
}
