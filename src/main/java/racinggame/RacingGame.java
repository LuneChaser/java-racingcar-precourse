package racinggame;

import nextstep.utils.Console;
import racinggame.constants.RacingConstants;
import racinggame.domain.car.RacingCarList;

public class RacingGame {
	RacingCarList racingCars;

	public RacingGame() {
		racingCars = new RacingCarList();
	}

	public void run() {
		racingCars = getRacingCars();

		Integer racingCount = getRacingCount();

		for (Integer index = 0; index < racingCount; index++) {
			racingCars.allMove();
		}

		printRacingResult(racingCars);

		printRacingWinner(getWinners(racingCars));
	}

	private RacingCarList getRacingCars() {
		RacingCarList tempRacingCars = new RacingCarList();
		Integer racingCarCount = 0;

		while (racingCarCount < 1) {
			System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
			tempRacingCars.add(Console.readLine());

			racingCarCount = tempRacingCars.size();
			printInvalidRacingCarGenerate(racingCarCount);
		}

		return tempRacingCars;
	}

	private Integer getRacingCount() {
		Integer racingCount = 0;

		while (racingCount < 1) {
			System.out.println("시도할 회수는 몇회인가요?");
			racingCount = Integer.valueOf(Console.readLine());

			printInvalidRacingCount(racingCount);
		}

		return racingCount;
	}

	private void printInvalidRacingCarGenerate(Integer racingCarCount) {
		if (racingCarCount < 1) {
			System.out.println("[ERROR] 자동차 생성을 하지 못하였습니다. - 입력하신 자동차 이름을 확인해주세요.");
		}
	}

	private void printInvalidRacingCount(Integer racingCount) {
		if (racingCount < 1) {
			System.out.println("[ERROR] 시도할 회수는 0이상입니다.");
		}
	}

	public void printRacingResult(RacingCarList cars) {
		System.out.println("실행 결과");

		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			System.out.println(cars.getName(carIndex) + " : " + printCarDistance(cars.getCarMoveDistance(carIndex)));
		}
	}

	private String printCarDistance(Integer moveDistance) {
		String carMoveDistance = "";

		for (Integer distance = 0; distance < moveDistance; distance++) {
			carMoveDistance += RacingConstants.CAR_MOVE_VIEW;
		}

		return carMoveDistance;
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

	public void printRacingWinner(String winners) {
		System.out.println("최종 우승자는 " + winners + " 입니다.");
	}
}
