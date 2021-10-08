package racinggame.domain.strategy;

import racinggame.domain.constants.RacingConstants;

public class CarMoveStopBehavior implements CarMoveBehavior {
	@Override
	public int getMoveDistance() {
		return RacingConstants.MOVE_UNIT_STOP;
	}
}
