package com.nurullah.questapp.core.utilities;

public class ErrorResult extends Result{
    public ErrorResult(boolean success, String message) {
        super(false, message);
    }
}
