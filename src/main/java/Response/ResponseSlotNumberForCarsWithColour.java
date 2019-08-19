package Response;

import java.util.List;

public class ResponseSlotNumberForCarsWithColour extends Response {
    public ResponseSlotNumberForCarsWithColour(List<Integer> numbersOfTickets) {
        this.numbersOfTickets = numbersOfTickets;
    }

    public List<Integer> getNumbersOfTickets() {
        return numbersOfTickets;
    }

    private List <Integer> numbersOfTickets;
}
