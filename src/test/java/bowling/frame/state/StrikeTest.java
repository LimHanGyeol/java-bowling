package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = Strike.of(Pin.bowl("10"));
    }

    @Test
    @DisplayName("투구 종료 여부 확인 - 종료")
    void isFinished() {
        assertThat(state.isFinish()).isTrue();
    }

    @Test
    @DisplayName("볼링 결과 - 스트라이크")
    void result() {
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("볼링 결과 마크 - 스트라이크")
    void resultMark() {
        assertThat(state.getBowlResults().get(0)).isEqualTo("X");
    }
}
