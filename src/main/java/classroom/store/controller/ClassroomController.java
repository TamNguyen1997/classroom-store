package classroom.store.controller;

import classroom.store.model.command.classroom.ClassroomCreateCommand;
import classroom.store.model.command.classroom.ClassroomUpdateCommand;
import classroom.store.orm.ClassRoomDb;
import classroom.store.security.Role;
import classroom.store.service.ClassroomPersistentService;
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
@RequestMapping(value = "/classrooms")
@PreAuthorize(Role.PERMIT_CLASSROOM)
public class ClassroomController {

    private final ClassroomPersistentService classroomPersistentService;

    private final ModelMapper modelMapper;

    public ClassroomController(ClassroomPersistentService classroomPersistentService, ModelMapper modelMapper) {
        this.classroomPersistentService = classroomPersistentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClassRoomDb> fetch(@PathVariable("id") UUID id) {
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(id);
        return ResponseEntity.ok(classRoomDb);
    }

    @PostMapping
    public ResponseEntity<ClassRoomDb> create(@RequestBody @Valid ClassroomCreateCommand createCommand) {
        ClassRoomDb classRoomDb = modelMapper.map(createCommand, ClassRoomDb.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomPersistentService.create(classRoomDb));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomDb> update(@PathVariable("id") UUID id, @RequestBody @Valid ClassroomUpdateCommand updateCommand) {
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(id);
        modelMapper.map(updateCommand, classRoomDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomPersistentService.update(classRoomDb));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(id);
        classroomPersistentService.delete(classRoomDb);
        return ResponseEntity.noContent().build();
    }
}
