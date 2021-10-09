package racinggame.util;

import nextstep.utils.Randoms;
import racinggame.domain.strategy.CarMoveBehaviorFactory;

public class RacingGameRandomNumber {
	public static Integer gererate() {
		return Randoms.pickNumberInRange(CarMoveBehaviorFactory.RANGE_MIN, CarMoveBehaviorFactory.RANGE_MAX);
	}
}
