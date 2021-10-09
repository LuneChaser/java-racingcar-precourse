package racinggame.domain.car;

import java.util.ArrayList;

import racinggame.constants.RacingConstants;
import racinggame.domain.strategy.CarMoveBehaviorFactory;
import racinggame.util.RacingGameRandomNumber;

public class RacingCarList {
	private final ArrayList<RacingCar> cars;

	public RacingCarList() {
		cars = new ArrayList<RacingCar>();
	}

	public boolean add(String carName) {
		if (isInvalidCarName(carName)) {
			return false;
		}

		this.addCarAll(generateCars(carName));

		return true;
	}

	private boolean isInvalidCarName(String carName) {
		boolean isInvalid = false;

		for (String carNameItem : carName.split(RacingConstants.CAR_NAME_SEPERATOR)) {
			isInvalid |= checkInvalidCarNameCondition(carNameItem.trim());
		}

		return isInvalid;
	}

	private boolean checkInvalidCarNameCondition(String carName) {
		return carName.length() > RacingConstants.CAR_NAME_LIMIT || carName.length() < 1;
	}

	public void addCarAll(RacingCarList carList) {
		for (Integer carIndex = 0; carIndex < carList.size(); carIndex++) {
			this.addCar(carList.get(carIndex));
		}
	}

	public RacingCar get(Integer carIndex) {
		return cars.get(carIndex);
	}

	private RacingCarList addCar(RacingCar car) {
		this.cars.add(car);
		return this;
	}

	private RacingCarList generateCars(String carName) {
		RacingCarList tempCars = new RacingCarList();

		for (String carNameItem : carName.split(RacingConstants.CAR_NAME_SEPERATOR)) {
			tempCars.addCar(new RacingCar(carNameItem.trim()));
		}

		return tempCars;
	}

	public Integer size() {
		return cars.size();
	}

	public String getName(int carIndex) {
		return cars.get(carIndex).getName();
	}

	public void allMove() {
		for (RacingCar car : cars) {
			car.move(CarMoveBehaviorFactory.createBehavior(RacingGameRandomNumber.gererate()));
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
