package racinggame;

import java.util.ArrayList;

import nextstep.utils.Console;
import racinggame.constants.RacingConstants;
import racinggame.domain.car.RacingCarList;

public class RacingGame {
	RacingCarList cars;

	public RacingGame() {
		cars = new RacingCarList();
	}

	public void run() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		cars.add(Console.readLine());

		System.out.println("시도할 회수는 몇회인가요?");
		Integer racingCount = Integer.valueOf(Console.readLine());

		for (Integer index = 0; index < racingCount; index++) {
			cars.allCarMove();
		}

		printRacingResult(cars);

		printRacingWinner(getWinners(cars));
	}

	public void printRacingResult(RacingCarList cars) {
		System.out.println("실행 결과");

		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			String carMoveDistance = "";

			for (Integer distance = 0; distance < cars.getCarMoveDistance(carIndex); distance++) {
				carMoveDistance += RacingConstants.CAR_MOVE_VIEW;
			}

			System.out.println(cars.getCarName(carIndex) + " : " + carMoveDistance);
		}
	}

	public String getWinners(RacingCarList cars) {
		ArrayList<String> winners = new ArrayList<String>();
		int maxDistance = -1;

		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			maxDistance = Math.max(maxDistance, cars.getCarMoveDistance(carIndex));
		}

		for (Integer carIndex = 0; carIndex < cars.size(); carIndex++) {
			if (cars.getCarMoveDistance(carIndex) == maxDistance) {
				winners.add(cars.getCarName(carIndex));
			}
		}

		return String.join(",", winners);
	}

	public void printRacingWinner(String winners) {
		System.out.println("최종 우승자는 " + winners + " 입니다.");
	}
}
