package Response;

import Entity.Car;

public final class CloseTicketResponse extends Response {
    public CloseTicketResponse(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    private Car car;
}
