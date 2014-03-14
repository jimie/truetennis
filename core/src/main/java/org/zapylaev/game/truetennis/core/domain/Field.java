package org.zapylaev.game.truetennis.core.domain;

public class Field {
    private Player mLeftPlayer;
    private Player mRightPlayer;
    private Ball mBall;
    private int mLeftPlayerScore;
    private int mRightPlayerScore;

    public Player getLeftPlayer() {
        return mLeftPlayer;
    }

    public void setLeftPlayer(Player leftPlayer) {
        mLeftPlayer = leftPlayer;
    }

    public Player getRightPlayer() {
        return mRightPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        mRightPlayer = rightPlayer;
    }

    public Ball getBall() {
        return mBall;
    }

    public void setBall(Ball ball) {
        mBall = ball;
    }

    public int getLeftPlayerScore() {
        return mLeftPlayerScore;
    }

    public void setLeftPlayerScore(int leftPlayerScore) {
        mLeftPlayerScore = leftPlayerScore;
    }

    public int getRightPlayerScore() {
        return mRightPlayerScore;
    }

    public void setRightPlayerScore(int rightPlayerScore) {
        mRightPlayerScore = rightPlayerScore;
    }
}
