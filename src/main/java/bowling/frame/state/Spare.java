package bowling.frame.state;

import bowling.score.Pin;

public class Spare extends Done {

    private final Pin previousPins;
    private final Pin currentPins;

    private Spare(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
    }

    public static Spare of(Pin previousPins, Pin nextPins) {
        return new Spare(previousPins, nextPins);
    }

    @Override
    public State bowl(String falledPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "/";
    }
}
