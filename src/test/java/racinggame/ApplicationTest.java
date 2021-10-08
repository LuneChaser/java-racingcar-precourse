package racinggame;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;
import racinggame.constants.RacingConstants;
import racinggame.domain.car.RacingCarList;
import racinggame.domain.strategy.CarMoveBehavior;
import racinggame.domain.strategy.CarMoveForwardBehavior;
import racinggame.domain.strategy.CarMoveStopBehavior;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

import java.util.NoSuchElementException;

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
		@DisplayName("자동차이름 오입력으로인한 에러발생")
		void 입력오류_자동차생성() {
			runNoLineFound("test,chaser");
			verify("[ERROR] 자동차 생성을 하지 못하였습니다. - 입력하신 자동차 이름을 확인해주세요.");
		}

		@Test
		@DisplayName("시도회수 오입력으로인한 에러발생")
		void 입력오류_시도회수() {
			runNoLineFound("test1,test2", "0");
			verify("[ERROR] 시도할 회수는 0이상입니다.");
		}

		@Test
		@DisplayName("유일한 우승자가 존재")
		void 다수의자동차_우승자_1명() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(()-> Randoms.pickNumberInRange(anyInt(), anyInt()))
											.thenReturn(1, 5)
											.thenReturn(6, 7);
				run("t1,t2", "2");
				verify("최종 우승자는 t2 입니다.");
			}
		}

		@Test
		@DisplayName("다수의 우승자가 존재")
		void 다수의자동차_우승자_2명() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(()-> Randoms.pickNumberInRange(anyInt(), anyInt()))
											.thenReturn(1, 2, 5)
											.thenReturn(5, 1, 7)
											.thenReturn(7, 4, 1);
;
				run("t1,t2,t3", "3");
				verify("최종 우승자는 t1,t3 입니다.");
			}
		}
	}

	@Nested
	@DisplayName("자동차 Domain의 기능 테스트")
	class 자동차 {
		@Test
		@DisplayName("생성오류-생성규칙(5이하) 위배")
		void 생성오류_이름5자초과() {
			RacingCarList cars = new RacingCarList();

			assertEquals(false, cars.add("TestName"));
		}

		@Test
		@DisplayName("생성오류-1개 생성시 자동차 이름 미입력")
		void 생성오류_1개생성_이름미입력() {
			RacingCarList cars = new RacingCarList();

			assertEquals(false, cars.add(""));
		}

		@Test
		@DisplayName("생성오류-다수 생성시 이름없는 항목존재")
		void 생성오류_다수생성_이름미입력() {
			RacingCarList cars = new RacingCarList();

			assertEquals(false, cars.add(",test"));
		}

		@Test
		@DisplayName("생성오류 발생시 이전에 생성된 항목의 유지여부 확인")
		void 생성오류_이전생성된항목유지() {
			RacingCarList cars = new RacingCarList();

			cars.add("test");
			
			cars.add("TestName");
			assertEquals(1, cars.size());
			assertEquals("test", cars.getCarName(0));

			cars.add("");
			assertEquals(1, cars.size());
			assertEquals("test", cars.getCarName(0));

			cars.add(",chaser");
			assertEquals(1, cars.size());
			assertEquals("test", cars.getCarName(0));
		}

		@Test
		@DisplayName("자동차를 1개만 생성")
		void 생성_1개() {
			RacingCarList cars = new RacingCarList();

			cars.add("test");

			assertEquals(1, cars.size());
			assertEquals("test", cars.getCarName(0));
		}

		@Test
		@DisplayName("자동차 2개만 생성")
		void 생성_2개() {
			RacingCarList cars = new RacingCarList();

			cars.add("test,pobi");
			
			assertEquals(2, cars.size());
			assertEquals("test", cars.getCarName(0));
			assertEquals("pobi", cars.getCarName(1));
		}

		@Test
		@DisplayName("정지로 1번만 이동")
		void 이동거리누적_정지() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
							.thenReturn(1);
				RacingCarList cars = new RacingCarList();
				
				cars.add("test");

				cars.allMove(1);

				assertEquals(0, cars.getCarMoveDistance(0));
			}
		}
		
		@Test
		@DisplayName("전진로 1번만 이동")
		void 이동거리누적_전진() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
							.thenReturn(6);
				RacingCarList cars = new RacingCarList();
				
				cars.add("test");

				cars.allMove(1);

				assertEquals(1, cars.getCarMoveDistance(0));
			}
		}
		
		@Test
		@DisplayName("연속하여 정지로 이동")
		void 이동거리누적_정지_정지() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
							.thenReturn(1, 3);
				RacingCarList cars = new RacingCarList();
				
				cars.add("test");

				cars.allMove(2);

				assertEquals(0, cars.getCarMoveDistance(0));
			}
		}

		@Test
		@DisplayName("연속하여 전진으로 이동")
		void 이동거리누적_전진_전진() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
							.thenReturn(6, 8);
				RacingCarList cars = new RacingCarList();
				
				cars.add("test");

				cars.allMove(2);

				assertEquals(2, cars.getCarMoveDistance(0));
			}
		}
		
		@Test
		@DisplayName("정지와 전진을 복합해서 이동")
		void 이동거리누적_전진_정지_전진() {
			try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
				mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
							.thenReturn(6, 2, 8);
				RacingCarList cars = new RacingCarList();
				
				cars.add("test");

				cars.allMove(3);

				assertEquals(2, cars.getCarMoveDistance(0));
			}
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
