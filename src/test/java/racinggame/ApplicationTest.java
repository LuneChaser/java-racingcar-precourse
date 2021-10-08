package racinggame;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;
import racinggame.domain.constants.RacingConstants;
import racinggame.domain.strategy.CarMoveBehavior;
import racinggame.domain.strategy.CarMoveForwardBehavior;
import racinggame.domain.strategy.CarMoveStopBehavior;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class ApplicationTest extends NSTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private static final String ERROR_MESSAGE = "[ERROR]";

    @BeforeEach
    void beforeEach() {
        setUp();
    }

    @Test
    void 전진_정지() {
        assertRandomTest(() -> {
            run("pobi,woni", "1");
            verify("pobi : -", "woni : ", "최종 우승자는 pobi 입니다.");
        }, MOVING_FORWARD, STOP);
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() -> {
            runNoLineFound("pobi,javaji");
            verify(ERROR_MESSAGE);
        });
    }

	@Nested
	@DisplayName("시나리오에따른 인수테스트")
	class 인수테스트 {
		@Test
		@DisplayName("유일한 우승자가 존재")
		void 다수의자동차_우승자_1명() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			}
		}

		@Test
		@DisplayName("다수의 우승자가 존재")
		void 다수의자동차_우승자_2명() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			}
		}
	}

	@Nested
	@DisplayName("자동차 Domain의 기능 테스트")
	class 자동차 {
		@Test
		@DisplayName("생성오류-생성규칙(5이하) 위배")
		void 생성오류_이름5자초과() {
		}

		@Test
		@DisplayName("생성오류-자동차 0개만 생성")
		void 생성오류_생성_0개() {
		}

		@Test
		@DisplayName("자동차를 1개만 생성")
		void 생성_1개() {
		}

		@Test
		@DisplayName("자동차 2개만 생성")
		void 생성_2개() {
		}

		@Test
		@DisplayName("정지로 1번만 이동")
		void 이동거리누적_정지() {
		}
		
		@Test
		@DisplayName("전진로 1번만 이동")
		void 이동거리누적_전진() {
		}
		
		@Test
		@DisplayName("연속하여 정지로 이동")
		void 이동거리누적_정지_정지() {
		}

		@Test
		@DisplayName("연속하여 전진으로 이동")
		void 이동거리누적_전진_전진() {
		}
		
		@Test
		@DisplayName("정지와 전진을 복합해서 이동")
		void 이동거리누적_전진_정지_전진() {
		}
	}

	@Nested
    @DisplayName("자동차를 움직이는 방법")
	class 자동차_움직임_전략 {
		@Test
		@DisplayName("앞으로 움직임")
		void 전진() {
			CarMoveBehavior carMoveBehavior = new CarMoveForwardBehavior();

			assertEquals(RacingConstants.MOVE_UNIT_FORWARD, carMoveBehavior.getMoveDistance());  
		}

		@Test
		@DisplayName("움직이지 않음")
		void 정지() {
			CarMoveBehavior carMoveBehavior = new CarMoveStopBehavior();

			assertEquals(RacingConstants.MOVE_UNIT_STOP, carMoveBehavior.getMoveDistance());  
		}
	}

    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
