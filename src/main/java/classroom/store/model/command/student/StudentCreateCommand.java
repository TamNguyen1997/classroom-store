package classroom.store.model.command.student;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class StudentCreateCommand {
    @NotNull
    private String name;

    @NotNull
    private UUID classroomId;
}
