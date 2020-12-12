package bowling;

import bowling.bowler.Bowler;
import bowling.frame.FinalFrame;
import bowling.frame.Frame;
import bowling.frame.NormalFrame;

import java.util.LinkedList;
import java.util.Objects;

import static bowling.frame.Frame.FINAL_FRAME_NUMBER;
import static bowling.global.utils.ExceptionMessage.MESSAGE_GAME_OVER;

public class BowlingGame {

    private final int DECREASE_FRAME_NUMBER = 1;

    private final Bowler bowler;
    private final LinkedList<Frame> frames;

    private BowlingGame(Bowler bowler) {
        this.bowler = bowler;
        this.frames = new LinkedList<>();
        this.frames.add(NormalFrame.first());
    }

    public static BowlingGame create(Bowler bowler) {
        return new BowlingGame(bowler);
    }

    public Frame bowl(String fellPins) {
        Frame frame = getLastFrame().bowl(fellPins);

        if (canMoveNextFrame() && getLastFrame().isFinish()) {
            frames.add(next(getNextFrameNumber()));
        }
        return frame;
    }

    public Frame next(int nextFrameNumber) {
        validateNextFrame();
        if (nextFrameNumber == FINAL_FRAME_NUMBER) {
            return FinalFrame.create();
        }
        return NormalFrame.create(nextFrameNumber);
    }

    private void validateNextFrame() {
        if (!canMoveNextFrame()) {
            throw new RuntimeException(MESSAGE_GAME_OVER);
        }
    }

    public boolean equalsBowler(Bowler bowler) {
        return this.bowler.equals(bowler);
    }

    private boolean canMoveNextFrame() {
        return getNextFrameNumber() <= FINAL_FRAME_NUMBER;
    }

    private int getNextFrameNumber() {
        return getFrameNumber() + DECREASE_FRAME_NUMBER;
    }

    public int getFrameNumber() {
        return getLastFrame().getFrameNumber();
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public String getBowlerName() {
        return bowler.getName();
    }

    public boolean isEnd() {
        return !canMoveNextFrame() && getLastFrame().isFinish();
    }

    public boolean isCurrentFrameFinish(int frameNumber) {
        return frames.get(frameNumber - DECREASE_FRAME_NUMBER)
                .isFinish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return bowler.equals(that.bowler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowler);
    }
}
