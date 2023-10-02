package com.nurullah.questapp.core.utilities;

public class SuccesDataResult<T> extends DataResult<T>{


    public SuccesDataResult(boolean success, String message, T data) {
        super(true, message, data);
    }


}
