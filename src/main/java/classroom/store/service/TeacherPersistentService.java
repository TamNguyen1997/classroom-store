package classroom.store.service;

import classroom.store.orm.TeacherDb;
import classroom.store.repository.TeacherRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherPersistentService {

    private final TeacherRepository teacherRepository;

    public TeacherPersistentService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDb fetch(UUID id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find teacher with ID: " + id));
    }

    public TeacherDb create(TeacherDb teacherDb) {
        return teacherRepository.save(teacherDb);
    }

    public TeacherDb update(TeacherDb teacherDb) {
        return teacherRepository.save(teacherDb);
    }

    public void delete(TeacherDb teacherDb) {
        teacherRepository.delete(teacherDb);
    }
}
