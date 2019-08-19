package Request;

public final class CreateRequest extends Request {
    private int parkSlots;

    public CreateRequest(int parkSlots) {
        this.parkSlots = parkSlots;
    }

    public int getParkSlots() {
        return parkSlots;
    }

}
