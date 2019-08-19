package Response;

import Entity.Car;

import java.util.List;

public class ResponseRegistrationNumberForCarsWithColour extends Response {

    public ResponseRegistrationNumberForCarsWithColour(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    private List<Car> cars;
}
