package classroom.store.model.command.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherUpdateCommand {
    @NotNull
    private String name;
}
