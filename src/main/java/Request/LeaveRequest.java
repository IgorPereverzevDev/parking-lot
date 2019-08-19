package Request;


public final class LeaveRequest extends Request {

    public LeaveRequest(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    private int slot;


}
