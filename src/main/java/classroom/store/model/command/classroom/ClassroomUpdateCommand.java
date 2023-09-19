package classroom.store.model.command.classroom;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassroomUpdateCommand {
    @NotNull
    private String name;
}
