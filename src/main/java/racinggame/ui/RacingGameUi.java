package racinggame.ui;

import nextstep.utils.Console;
import racinggame.constants.RacingConstants;
import racinggame.domain.car.RacingCarList;

public class RacingGameUi {
	public static String getCarName() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(" + RacingConstants.CAR_NAME_SEPERATOR + ") 기준으로 구분)");

		return Console.readLine();
	}

	public static RacingCarList getRacingCars() {
		RacingCarList tempRacingCars = new RacingCarList();

		while (tempRacingCars.size() < 1) {
			tempRacingCars = generateRacingCars();
		}

		return tempRacingCars;
	}

	private static RacingCarList generateRacingCars() {
		RacingCarList tempRacingCars = new RacingCarList();

		if (!tempRacingCars.add(getCarName())) {
			System.out.println("[ERROR] 자동차 생성을 하지 못하였습니다. - 입력하신 자동차 이름을 확인해주세요.");
		}

		return tempRacingCars;
	}

	public static Integer getRacingCount() {
		String tempRacingCount = "";

		do {
			System.out.println("시도할 회수는 몇회인가요?");
			tempRacingCount = Console.readLine();
		} while (isInvalidRacingCount(tempRacingCount));

		return Integer.valueOf(tempRacingCount);
	}

	private static boolean isInvalidRacingCount(String racingCountString) {
		if (racingCountString.isBlank()) {
			System.out.println("[ERROR] 시도할 회수을 입력해주세요.");
			return true;
		}

		if (!isNumeric(racingCountString)) {
			System.out.println("[ERROR] 시도할 회수는 숫자로만 입력되어야합니다.");
			return true;
		}

		if (!isRacingCountOverZero(Integer.valueOf(racingCountString))) {
			System.out.println("[ERROR] 시도할 회수는 0이상입니다.");
			return true;
		}

		return false;
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	private static boolean isRacingCountOverZero(Integer racingCount) {
		return racingCount > 0;
	}

	public static void printRacingResult(RacingCarList cars) {
		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			printMoveDistanceForCar(cars.getName(carIndex), cars.getCarMoveDistance(carIndex));
		}

		System.out.println("");
	}

	public static void printRacingResultTitle() {
		System.out.println("");
		System.out.println("실행 결과");
	}

	private static void printMoveDistanceForCar(String carName, Integer moveDistance) {
		System.out.println(carName + " : " + printCarMoveDistance(moveDistance));
	}

	private static String printCarMoveDistance(Integer moveDistance) {
		String carMoveDistance = "";

		for (Integer distance = 0; distance < moveDistance; distance++) {
			carMoveDistance += RacingConstants.CAR_MOVE_VIEW;
		}

		return carMoveDistance;
	}

	public static void printRacingWinner(String winners) {
		System.out.println("최종 우승자는 " + winners + " 입니다.");
	}
}
