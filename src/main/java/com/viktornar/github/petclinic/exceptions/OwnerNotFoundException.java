package com.viktornar.github.petclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OwnerNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public OwnerNotFoundException(String message) {
    super(message);
  }
}