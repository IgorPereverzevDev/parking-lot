package Request;

public class RequestRegistrationNumberForCarsWithColour extends Request {

    public String getColour() {
        return colour;
    }

    private String colour;

    public RequestRegistrationNumberForCarsWithColour(String colour) {
        this.colour = colour;
    }
}
