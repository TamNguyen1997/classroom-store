package classroom.store.repository;

import classroom.store.orm.ClassRoomDb;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClassroomRepository extends CrudRepository<ClassRoomDb, UUID> {
}
