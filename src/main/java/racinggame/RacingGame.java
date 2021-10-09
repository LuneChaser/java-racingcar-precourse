package racinggame;

import racinggame.domain.car.RacingCarList;
import racinggame.ui.RacingGameUi;

public class RacingGame {
	RacingCarList racingCars;

	public RacingGame() {
		racingCars = new RacingCarList();
	}

	public void run() {
		racingCars = RacingGameUi.getRacingCars();

		Integer racingCount = RacingGameUi.getRacingCount();

		for (Integer index = 0; index < racingCount; index++) {
			racingCars.allMove();
		}

		RacingGameUi.printRacingResult(racingCars);

		RacingGameUi.printRacingWinner(getWinners(racingCars));
	}

	public String getWinners(RacingCarList cars) {
		RacingCarList winners = getWinnerList(getMaxMoveDistance(cars), cars);

		return winners.getNames();
	}

	public Integer getMaxMoveDistance(RacingCarList cars) {
		Integer maxDistance = -1;

		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			maxDistance = Math.max(maxDistance, cars.getCarMoveDistance(carIndex));
		}

		return maxDistance;
	}

	public RacingCarList getWinnerList(Integer maxDistance, RacingCarList racingCars) {
		return racingCars.findBy(maxDistance);
	}
}
