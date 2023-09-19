package classroom.store.model.command.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TeacherCreateCommand {
    @NotNull
    private String name;

    @NotNull
    private UUID classroomId;
}
