package com.nurullah.questapp.core.utilities;

public class ErrorDataResult<T> extends DataResult<T>{
    public ErrorDataResult(boolean success, String message, T data) {
        super(false, message, data);
    }
}
