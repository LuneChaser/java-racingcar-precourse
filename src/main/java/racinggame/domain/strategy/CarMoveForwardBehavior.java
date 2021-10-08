package racinggame.domain.strategy;

import racinggame.constants.RacingConstants;

public class CarMoveForwardBehavior implements CarMoveBehavior {
	@Override
	public int getMoveDistance() {
		return RacingConstants.MOVE_UNIT_FORWARD;
	}
}
