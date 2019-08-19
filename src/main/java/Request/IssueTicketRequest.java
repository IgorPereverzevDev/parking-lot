package Request;

import Entity.Car;

public final class IssueTicketRequest extends Request {
    private Car car;

    public Car getCar() {
        return car;
    }

    public IssueTicketRequest(Car car) {
        this.car = car;
    }


}
