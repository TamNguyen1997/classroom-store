package classroom.store.service;

import classroom.store.orm.StudentDb;
import classroom.store.repository.StudentRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentPersistentService {

    private final StudentRepository studentRepository;

    public StudentPersistentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDb fetch(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find student with ID: " + id));
    }

    public StudentDb create(StudentDb studentDb) {
        return studentRepository.save(studentDb);
    }

    public StudentDb update(StudentDb studentDb) {
        return studentRepository.save(studentDb);
    }

    public void delete(StudentDb studentDb) {
        studentRepository.delete(studentDb);
    }
}
