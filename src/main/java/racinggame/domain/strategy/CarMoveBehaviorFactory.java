package racinggame.domain.strategy;

public class CarMoveBehaviorFactory {
	public static final int RANGE_MAX = 9;
	public static final int RANGE_MIN = 0;
	private static final int FORWARD_RANGE_MAX = 9;
	private static final int FORWARD_RANGE_MIN = 4;
	private static final int STOP_RANGE_MAX = 3;
	private static final int STOP_RANGE_MIN = 0;

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
		return moveBehaviorNumber >= FORWARD_RANGE_MIN && moveBehaviorNumber <= FORWARD_RANGE_MAX;
	}

	private static boolean isStopBehaviorNumber(Integer moveBehaviorNumber) {
		return moveBehaviorNumber >= STOP_RANGE_MIN && moveBehaviorNumber <= STOP_RANGE_MAX;
	}
}
