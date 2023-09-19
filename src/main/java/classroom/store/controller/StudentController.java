package classroom.store.controller;

import classroom.store.model.command.student.StudentCreateCommand;
import classroom.store.model.command.student.StudentUpdateCommand;
import classroom.store.orm.ClassRoomDb;
import classroom.store.orm.StudentDb;
import classroom.store.security.Role;
import classroom.store.security.privileges.student.CompositeStudentPermissionEvaluator;
import classroom.store.service.ClassroomPersistentService;
import classroom.store.service.StudentPersistentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/students")
@PreAuthorize(Role.PERMIT_STUDENT)
public class StudentController {

    private final StudentPersistentService studentPersistentService;

    private final ModelMapper modelMapper;

    private final ClassroomPersistentService classroomPersistentService;

    private final CompositeStudentPermissionEvaluator permissionEvaluator;

    public StudentController(StudentPersistentService studentPersistentService,
                             ModelMapper modelMapper,
                             ClassroomPersistentService classroomPersistentService,
                             CompositeStudentPermissionEvaluator permissionEvaluator) {
        this.studentPersistentService = studentPersistentService;
        this.modelMapper = modelMapper;
        this.classroomPersistentService = classroomPersistentService;
        this.permissionEvaluator = permissionEvaluator;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDb> fetch(@PathVariable("id") UUID id) {
        StudentDb studentDb = studentPersistentService.fetch(id);
        return ResponseEntity.ok(studentDb);
    }

    @PostMapping
    public ResponseEntity<StudentDb> create(@RequestBody @Valid StudentCreateCommand studentCreateCommand) {
        StudentDb studentDb = modelMapper.map(studentCreateCommand, StudentDb.class);
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(studentCreateCommand.getClassroomId());
        studentDb.setClassRoomDb(classRoomDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentPersistentService.create(studentDb));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDb> update(@PathVariable("id") UUID id, @RequestBody @Valid StudentUpdateCommand updateCommand) {
        StudentDb studentDb = studentPersistentService.fetch(id);
        permissionEvaluator.hasAccess(studentDb);
        modelMapper.map(updateCommand, studentDb);
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(updateCommand.getClassroomId());
        studentDb.setClassRoomDb(classRoomDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentPersistentService.update(studentDb));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        StudentDb studentDb = studentPersistentService.fetch(id);
        permissionEvaluator.hasAccess(studentDb);
        studentPersistentService.delete(studentDb);
        return ResponseEntity.noContent().build();
    }
}
