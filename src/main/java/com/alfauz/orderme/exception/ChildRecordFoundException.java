package com.alfauz.orderme.exception;

public class ChildRecordFoundException extends AppException {

  public ChildRecordFoundException(String message) {
    super(message);
  }

  public ChildRecordFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
