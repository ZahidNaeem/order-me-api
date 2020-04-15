package com.alfauz.orderme.exception;

public class UserTypeNotFoundException extends RuntimeException {
  public UserTypeNotFoundException(final String message) {
    super(message);
  }
}
