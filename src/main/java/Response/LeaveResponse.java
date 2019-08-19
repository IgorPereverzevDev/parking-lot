package Response;

public final class LeaveResponse extends Response {

    public LeaveResponse(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    private int slot;


}
