package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ValidatorTestRestController extends ApiRestController {
    private final Validator validator;

    @PostMapping("/validateUser")
    ValidationResult getUserValidationResult(@Valid @RequestBody User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        String violationsMessage = violations.stream()
                .map(v -> v.getPropertyPath() + " - " + v.getMessage())
                .reduce("", (messages, message) -> {
                    if (messages.isEmpty()) {
                        return messages + message;
                    }
                    return messages + ", " + message;
                });

        return new ValidationResult(violationsMessage, violations.isEmpty());
    }

    @Data
    public static class ValidationResult {
        private final String message;
        private final boolean isValid;
    }
}
