package classroom.store.repository;

import classroom.store.orm.StudentDb;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<StudentDb, UUID> {
}
