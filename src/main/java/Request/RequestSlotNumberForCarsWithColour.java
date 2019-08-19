package Request;

public class RequestSlotNumberForCarsWithColour extends Request {


    public String getColour() {
        return colour;
    }

    private String colour;

    public RequestSlotNumberForCarsWithColour(String colour) {
        this.colour = colour;
    }
}
