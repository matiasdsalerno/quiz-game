package com.wedgame.quizcreator.model;

import com.wedgame.quizcreator.model.exception.CannotStartRoundException;

public enum RoundState {
    NOT_STARTED(false) {
        @Override
        public RoundState start() {
            return STARTED;
        }

        @Override
        public boolean canAnswer() {
            return false;
        }

        @Override
        public RoundState finish() {
            throw new UnsupportedOperationException("You can't finish a round that hasn't started");
        }
    },
    STARTED(false) {
        @Override
        public RoundState start() {
            throw new CannotStartRoundException("Cannot start round already started");
        }

        @Override
        public boolean canAnswer() {
            return true;
        }

        @Override
        public RoundState finish() {
            return COMPLETED;
        }
    },
    COMPLETED(true) {
        @Override
        public RoundState start() {
            throw new CannotStartRoundException("Cannot start round already completed");
        }

        @Override
        public boolean canAnswer() {
            return true;
        }

        @Override
        public RoundState finish() {
            throw new UnsupportedOperationException("You can't finish a round that hasn't started");
        }
    };

    private final boolean completed;

    RoundState(boolean isCompleted) {
        this.completed = isCompleted;
    }

    public void answer(Question question, Long answerId) {

    }

    public boolean isCompleted() {
        return completed;
    }

    public abstract RoundState start();

    public abstract boolean canAnswer();

    public abstract RoundState finish();
}
