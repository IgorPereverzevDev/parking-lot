package Request;

import Entity.Car;

public final class ParkRequest extends Request {

    public ParkRequest(Car car) {
        this.car = car;
    }

    private Car car;

    public Car getCar() {
        return car;
    }


}

