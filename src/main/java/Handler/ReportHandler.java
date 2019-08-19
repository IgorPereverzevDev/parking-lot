package Handler;

import Constant.ParkingRequestCommand;
import Entity.Car;
import Entity.Ticket;
import Request.Request;
import Request.RequestRegistrationNumberForCarsWithColour;
import Request.RequestSlotNumberForCarsWithColour;
import Request.RequestSlotNumberForRegistrationNumber;
import Response.*;
import Storage.Context;

import java.util.*;
import java.util.stream.Collectors;

public class ReportHandler implements MessageHandler {
    @Override
    public Response execute(Request request) {
        Response response;
        switch (request.getClass().getSimpleName()) {
            case ParkingRequestCommand.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_REQUEST:
                response = getSlotNumberForRegistrationNumber((RequestSlotNumberForRegistrationNumber) request);
                break;
            case ParkingRequestCommand.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_REQUEST:
                response = getSlotNumbersForCarsWithColour((RequestSlotNumberForCarsWithColour) request);
                break;
            case ParkingRequestCommand.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_REQUEST:
                response = registrationNumbersForCarsWithColour((RequestRegistrationNumberForCarsWithColour) request);
                break;
            default:
                throw new IllegalStateException("Unexpected request: " + request.getClass().getSimpleName());
        }
        return response;
    }

    @Override
    public Collection<String> getSupportedRequest() {
        return new HashSet<>(Arrays.asList(RequestSlotNumberForRegistrationNumber.class.getSimpleName(),
                RequestSlotNumberForCarsWithColour.class.getSimpleName(),
                RequestRegistrationNumberForCarsWithColour.class.getSimpleName()));
    }

    private Response registrationNumbersForCarsWithColour(RequestRegistrationNumberForCarsWithColour request) {
        List<Car> cars = Context.getStorage().getCars();
        List<Car> foundCars = cars.stream().filter(c -> c.getColor().equalsIgnoreCase(request.getColour())).collect(Collectors.toList());
        return new ResponseRegistrationNumberForCarsWithColour(foundCars);


    }

    private Response getSlotNumberForRegistrationNumber(RequestSlotNumberForRegistrationNumber request) {
        List<Ticket> tickets = Context.getStorage().getTickets();
        Optional<Ticket> ticket = tickets.stream().filter(t -> t.getCar().getPlate().equalsIgnoreCase(request.getPlate())).findFirst();
        return ticket.isPresent() ? new ResponseSlotNumberForRegistrationNumber(ticket.get().getSlot()) :
                new NullResponse("Not found");


    }

    private Response getSlotNumbersForCarsWithColour(RequestSlotNumberForCarsWithColour request) {
        List<Ticket> tickets = Context.getStorage().getTickets();
        List<Integer> numberOfTickets =  tickets.stream()
                .filter(t -> t.getCar().getColor().equalsIgnoreCase(request.getColour()))
                .map(Ticket::getSlot)
                .collect(Collectors.toList());
        return new ResponseSlotNumberForCarsWithColour(numberOfTickets);
    }


}
