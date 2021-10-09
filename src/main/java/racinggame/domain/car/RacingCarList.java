package racinggame.domain.car;

import java.util.ArrayList;

import nextstep.utils.Randoms;
import racinggame.constants.RacingConstants;
import racinggame.domain.strategy.CarMoveBehaviorFactory;

public class RacingCarList {
	private ArrayList<RacingCar> cars;

	public RacingCarList() {
		cars = new ArrayList<RacingCar>();
	}

	public boolean add(String carNames) {
		ArrayList<RacingCar> tempCars = new ArrayList<RacingCar>();

		for (String carName : carNames.split(RacingConstants.CAR_NAME_SEPERATOR)) {
			if (isInvalidCarName(carName.trim())) {
				return false;
			}

			tempCars.add(new RacingCar(carName.trim()));
		}

		cars.addAll(tempCars);

		return true;
	}

	private boolean isInvalidCarName(String carName) {
		return carName.length() > RacingConstants.CAR_NAME_LIMIT || carName.length() < 1;
	}

	public Integer size() {
		return cars.size();
	}

	public String getName(int carIndex) {
		return cars.get(carIndex).getName();
	}

	public void allMove() {
		for (RacingCar car : cars) {
			Integer moveBehaviorNumber = Randoms.pickNumberInRange(CarMoveBehaviorFactory.RANGE_MIN, CarMoveBehaviorFactory.RANGE_MAX);

			car.move(CarMoveBehaviorFactory.createBehavior(moveBehaviorNumber));
		}
	}

	public Integer getCarMoveDistance(int carIndex) {
		return cars.get(carIndex).getMoveDistance();
	}

	public String getNames() {
		ArrayList<String> carNames = new ArrayList<String>();

		for (Integer index = 0; index < cars.size(); index++) {
			carNames.add(getName(index));
		}

		return String.join(RacingConstants.CAR_NAME_SEPERATOR, carNames);
	}

	public RacingCarList findBy(Integer moveDistance) {
		RacingCarList tempRacingCars = new RacingCarList();

		for (RacingCar car : this.cars) {
			if (car.getMoveDistance() == moveDistance) {
				tempRacingCars.add(car.getName());
			}
		}

		return tempRacingCars;
	}
}
