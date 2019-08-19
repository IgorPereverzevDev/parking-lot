package Orchestrator;

import Constant.ParkingPresentCommand;
import Entity.Car;
import Entity.Ticket;
import Response.*;

import java.util.Collection;
import java.util.stream.Collectors;

class ResponsePresenter {
    static void show(Response response) {
        switch (response.getClass().getSimpleName()) {
            case ParkingPresentCommand.CREATE_RESPONSE:
                create((CreateResponse) response);
                break;
            case ParkingPresentCommand.PARK_RESPONSE:
                park((ParkResponse) response);
                break;
            case ParkingPresentCommand.LEAVE_RESPONSE:
                leave((LeaveResponse) response);
                break;
            case ParkingPresentCommand.STATUS_RESPONSE:
                status((StatusResponse) response);
                break;
            case ParkingPresentCommand.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_RESPONSE:
                registrationNumbersForCarsWithColour((ResponseRegistrationNumberForCarsWithColour) response);
                break;
            case ParkingPresentCommand.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_RESPONSE:
                getSlotNumbersForCarsWithColour((ResponseSlotNumberForCarsWithColour) response);
                break;
            case ParkingPresentCommand.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_RESPONSE:
                getSlotNumberForRegistrationNumber((ResponseSlotNumberForRegistrationNumber) response);
                break;
            case ParkingPresentCommand.OTHER:
                other((NullResponse) response);
                break;
            default:
                throw new IllegalStateException("Unexpected response: " + response.getClass().getSimpleName());
        }
    }

    private static void create(CreateResponse createResponse) {
        System.out.println("Created a parking lot with " + createResponse.getSlots() + " slots");
    }

    private static void park(ParkResponse parkResponse) {
        System.out.println("Allocated slot number: " + parkResponse.getTicket().getSlot());
    }

    private static void leave(LeaveResponse leaveResponse) {
        System.out.println("Slot number " + leaveResponse.getSlot() + " is free");
    }

    private static void status(StatusResponse statusResponse) {
        Collection<Ticket> tickets = statusResponse.getTickets().stream()
                .filter(t->!t.getClass().getSimpleName().equals("EmptyTicket"))
                .collect(Collectors.toList());
        System.out.println("Slot No.  Registration No  Colour");
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getSlot() + "   " + ticket.getCar().getPlate() + "   " + ticket.getCar().getColor());
        }
    }

    private static void registrationNumbersForCarsWithColour(
            ResponseRegistrationNumberForCarsWithColour responseRegistrationNumberForCarsWithColour) {
        String cars = responseRegistrationNumberForCarsWithColour.getCars().stream().map(Car::getPlate)
                .collect(Collectors.joining(", "));
        System.out.println(cars);
    }


    private static void getSlotNumbersForCarsWithColour(ResponseSlotNumberForCarsWithColour
                                                                responseSlotNumberForCarsWithColour) {
        String slots = responseSlotNumberForCarsWithColour.getNumbersOfTickets()
                .stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(slots);
    }


    private static void getSlotNumberForRegistrationNumber(ResponseSlotNumberForRegistrationNumber
                                                                   responseSlotNumberForRegistrationNumber) {
        System.out.println(responseSlotNumberForRegistrationNumber.getSlot());
    }

    private static void other(NullResponse response) {
        System.out.println(response.getMessage());
    }

}
