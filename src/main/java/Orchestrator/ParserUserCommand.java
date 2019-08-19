package Orchestrator;

import Constant.ParkingUserCommand;
import Entity.Car;
import Request.*;

class ParserUserCommand {

    static Request parse(String command) {
        String[] lines = command.split(" ");
        Request request;
        switch (lines[0]) {
            case ParkingUserCommand.CREATE:
                request = new CreateRequest(cutValue(command));
                break;
            case ParkingUserCommand.PARK:
                request = new ParkRequest(constructCar(lines[1], lines[2]));
                break;
            case ParkingUserCommand.LEAVE:
                request = new LeaveRequest(cutValue(command));
                break;
            case ParkingUserCommand.STATUS:
                request = new StatusRequest();
                break;
            case ParkingUserCommand.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                request = new RequestRegistrationNumberForCarsWithColour(lines[1]);
                break;
            case ParkingUserCommand.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                request = new RequestSlotNumberForCarsWithColour(lines[1]);
                break;
            case ParkingUserCommand.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                request = new RequestSlotNumberForRegistrationNumber(lines[1]);
                break;
            default:
                throw new IllegalStateException("Unsupported command: " + command);
        }
        return request;
    }

    private static int cutValue(String command) {
        return Integer.parseInt(command.replaceAll("[^-?0-9]+", " ").trim());
    }

    private static Car constructCar(String plate, String color) {
        return new Car(plate, color);
    }
}
