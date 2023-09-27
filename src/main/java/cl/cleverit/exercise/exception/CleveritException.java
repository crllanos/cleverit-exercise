package cl.cleverit.exercise.exception;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleveritException {
    private String message;
}
