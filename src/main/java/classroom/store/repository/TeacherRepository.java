package classroom.store.repository;

import classroom.store.orm.TeacherDb;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<TeacherDb, UUID> {
}
