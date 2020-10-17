package bowling.score;

import bowling.global.exception.InputPinsNullPointerException;
import bowling.global.exception.OutOfPinsRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Pin {

    private static final int PINS_MIN_VALUE = 0;
    private static final int PINS_MAX_VALUE = 10;

    private final int fellPins;

    private Pin(int fellPins) {
        this.fellPins = fellPins;
        validateOutofPinsRange(fellPins);
    }

    public static Pin bowl(String fellPins) {
        validatePinsIsNull(fellPins);
        return new Pin(new StringParser(fellPins).toInt());
    }

    public boolean isStrike() {
        return this.fellPins == PINS_MAX_VALUE;
    }

    public boolean isSpare(Pin fellPins) {
        int nextPins = fellPins.getFellPins();
        return this.fellPins + nextPins == PINS_MAX_VALUE;
    }

    public boolean isGutter() {
        return this.fellPins == PINS_MIN_VALUE;
    }

    public int getFellPins() {
        return fellPins;
    }

    private static void validatePinsIsNull(String score) {
        if (Strings.isBlank(score)) {
            throw new InputPinsNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofPinsRange(int fellPins) {
        if (fellPins < PINS_MIN_VALUE || fellPins > PINS_MAX_VALUE) {
            throw new OutOfPinsRangeException(ExceptionMessage.INVALID_PITCH_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return fellPins == pin.fellPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fellPins);
    }

    @Override
    public String toString() {
        return String.valueOf(fellPins);
    }
}
