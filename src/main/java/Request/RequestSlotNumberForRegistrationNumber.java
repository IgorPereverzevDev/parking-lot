package Request;

public class RequestSlotNumberForRegistrationNumber extends Request {

    public RequestSlotNumberForRegistrationNumber(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    private String plate;
}
