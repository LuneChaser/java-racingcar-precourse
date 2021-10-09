package racinggame.domain.car;

import racinggame.domain.strategy.CarMoveBehavior;

public class RacingCar {
	private String name;
	private Integer moveDistance;

	public RacingCar(String name) {
		this.name = name;
		this.moveDistance = 0;
	}

	public void move(CarMoveBehavior moveBehavior) {
		this.moveDistance += moveBehavior.getMoveDistance();
	}

	public Integer getMoveDistance() {
		return this.moveDistance;
	}

	public String getName() {
		return this.name;
	}
}
