package racinggame.domain.car;

import java.util.ArrayList;

import nextstep.utils.Randoms;
import racinggame.constants.RacingConstants;
import racinggame.domain.strategy.CarMoveBehavior;
import racinggame.domain.strategy.CarMoveForwardBehavior;
import racinggame.domain.strategy.CarMoveStopBehavior;

public class RacingCarList {
	ArrayList<RacingCar> cars;

	public RacingCarList() {
		cars = new ArrayList<RacingCar>();
	}

	public boolean add(String carNames) {
		ArrayList<RacingCar> tempCars = new ArrayList<RacingCar>();

		for (String carName : carNames.split(RacingConstants.CAR_NAME_SEPERATOR)) {
			if (isInvalidGenerate(carName.trim())) {
				return false;
			}

			tempCars.add(new RacingCar(carName.trim()));
		}

		cars = tempCars;

		return true;
	}

	private boolean isInvalidGenerate(String carName) {
		if (carName.length() > RacingConstants.CAR_NAME_LIMIT || carName.length() < 1) {
			return true;
		}

		return false;
	}

	public Integer size() {
		return cars.size();
	}

	public String getCarName(int carIndex) {
		return cars.get(carIndex).carName;
	}

	public void allCarMove() {
		for (RacingCar car : cars) {
			int randomNumber = Randoms.pickNumberInRange(0, 9);
			CarMoveBehavior moveBehavior = new CarMoveStopBehavior();

			if (randomNumber >= 0 && randomNumber <= 3) {
				moveBehavior = new CarMoveStopBehavior();
			} else if (randomNumber >= 4 && randomNumber <= 9) {
				moveBehavior = new CarMoveForwardBehavior();
			}

			car.move(moveBehavior);
		}
	}

	public Integer getCarMoveDistance(int carIndex) {
		return cars.get(carIndex).getMoveDistance();
	}
}
