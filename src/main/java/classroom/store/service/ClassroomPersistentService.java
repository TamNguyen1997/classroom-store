package classroom.store.service;

import classroom.store.orm.ClassRoomDb;
import classroom.store.repository.ClassroomRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassroomPersistentService {

    private final ClassroomRepository classRoomRepository;

    public ClassroomPersistentService(ClassroomRepository classroomRepository) {
        this.classRoomRepository = classroomRepository;
    }

    public ClassRoomDb fetch(UUID id) {
        return classRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find class room with ID: " + id));
    }

    public ClassRoomDb create(ClassRoomDb classRoomDb) {
        return classRoomRepository.save(classRoomDb);
    }

    public ClassRoomDb update(ClassRoomDb classRoomDb) {
        return classRoomRepository.save(classRoomDb);
    }

    public void delete(ClassRoomDb classRoomDb) {
        classRoomRepository.delete(classRoomDb);
    }
}
