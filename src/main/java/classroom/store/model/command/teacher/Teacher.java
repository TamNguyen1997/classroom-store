package classroom.store.model.command.teacher;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Teacher {
    private UUID id;
    private String name;
    private UUID classroomId;
}
