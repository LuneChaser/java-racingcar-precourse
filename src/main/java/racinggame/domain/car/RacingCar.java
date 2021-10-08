package racinggame.domain.car;

import racinggame.domain.strategy.CarMoveBehavior;

public class RacingCar {
	String carName;
	Integer totalMoveCount = 0;

	public RacingCar(String carName) {
		this.carName = carName;
	}

	public void move(CarMoveBehavior moveBehavior) {
		totalMoveCount += moveBehavior.getMoveDistance();
	}

	public Integer getMoveDistance() {
		return this.totalMoveCount;
	}

	public String getName() {
		return carName;
	}

	public Integer getTotalMoveCount() {
		return totalMoveCount;
	}
}
