package Response;

public class ResponseSlotNumberForRegistrationNumber extends Response {

    public ResponseSlotNumberForRegistrationNumber(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    private int slot;
}
