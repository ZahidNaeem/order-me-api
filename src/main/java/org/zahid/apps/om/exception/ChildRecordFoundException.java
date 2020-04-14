package org.zahid.apps.om.exception;

public class ChildRecordFoundException extends AppException {

  public ChildRecordFoundException(String message) {
    super(message);
  }

  public ChildRecordFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
