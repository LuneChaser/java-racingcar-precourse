package racinggame.domain.strategy;

public class CarMoveBehaviorFactory {
	public static final int CAR_MOVE_BEHAVIOR_MAX = 9;
	public static final int CAR_MOVE_BEHAVIOR_MIN = 0;
	private static final int CAR_MOVE_BEHAVIOR_FORWARD_MAX = 9;
	private static final int CAR_MOVE_BEHAVIOR_FORWARD_MIN = 4;
	private static final int CAR_MOVE_BEHAVIOR_STOP_MAX = 3;
	private static final int CAR_MOVE_BEHAVIOR_STOP_MIN = 0;

	public static CarMoveBehavior createBehavior(Integer moveBehaviorNumber) {
		CarMoveBehavior moveBehavior = new CarMoveStopBehavior();

		if (isStopBehaviorNumber(moveBehaviorNumber)) {
			moveBehavior = new CarMoveStopBehavior();
		} else if (isForwardBehaviorNumber(moveBehaviorNumber)) {
			moveBehavior = new CarMoveForwardBehavior();
		}

		return moveBehavior;
	}

	private static boolean isForwardBehaviorNumber(Integer moveBehaviorNumber) {
		return moveBehaviorNumber >= CAR_MOVE_BEHAVIOR_FORWARD_MIN
				&& moveBehaviorNumber <= CAR_MOVE_BEHAVIOR_FORWARD_MAX;
	}

	private static boolean isStopBehaviorNumber(Integer moveBehaviorNumber) {
		return moveBehaviorNumber >= CAR_MOVE_BEHAVIOR_STOP_MIN
				&& moveBehaviorNumber <= CAR_MOVE_BEHAVIOR_STOP_MAX;
	}
}
