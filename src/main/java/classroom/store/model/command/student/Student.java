package classroom.store.model.command.student;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Student {
    private String name;
    private UUID classroomId;
}
