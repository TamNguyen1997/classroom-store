package classroom.store.controller;

import classroom.store.exception.ForbiddenException;
import classroom.store.model.command.teacher.TeacherCreateCommand;
import classroom.store.model.command.teacher.TeacherUpdateCommand;
import classroom.store.orm.ClassRoomDb;
import classroom.store.orm.TeacherDb;
import classroom.store.security.Role;
import classroom.store.security.privileges.teacher.CompositeTeacherPermissionEvaluator;
import classroom.store.service.ClassroomPersistentService;
import classroom.store.service.TeacherPersistentService;
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
@RequestMapping(value = "/teachers")
@PreAuthorize(Role.PERMIT_TEACHER)
public class TeacherController {

    private final TeacherPersistentService teacherPersistentService;
    private final ClassroomPersistentService classroomPersistentService;

    private final CompositeTeacherPermissionEvaluator permissionEvaluator;

    private final ModelMapper modelMapper;

    public TeacherController(TeacherPersistentService teacherPersistentService,
                             ModelMapper modelMapper,
                             CompositeTeacherPermissionEvaluator permissionEvaluator,
                             ClassroomPersistentService classroomPersistentService) {
        this.teacherPersistentService = teacherPersistentService;
        this.modelMapper = modelMapper;
        this.permissionEvaluator = permissionEvaluator;
        this.classroomPersistentService = classroomPersistentService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeacherDb> fetch(@PathVariable("id") UUID id) {
        TeacherDb teacherDb = teacherPersistentService.fetch(id);
        return ResponseEntity.ok(teacherDb);
    }

    @PostMapping
    public ResponseEntity<TeacherDb> create(@RequestBody @Valid TeacherCreateCommand teacherCreateCommand) {
        TeacherDb teacherDb = modelMapper.map(teacherCreateCommand, TeacherDb.class);
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(teacherCreateCommand.getClassroomId());
        teacherDb.setClassRoomDb(classRoomDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherPersistentService.create(teacherDb));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDb> update(@PathVariable("id") UUID id, @RequestBody @Valid TeacherUpdateCommand updateCommand) {
        TeacherDb teacherDb = teacherPersistentService.fetch(id);
        if (permissionEvaluator.hasAccess(teacherDb)) {
            throw new ForbiddenException("Forbidden - Don't have access to teacher " + id);
        }
        modelMapper.map(updateCommand, teacherDb);
        ClassRoomDb classRoomDb = classroomPersistentService.fetch(updateCommand.getClassroomId());
        teacherDb.setClassRoomDb(classRoomDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherPersistentService.update(teacherDb));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        TeacherDb teacherDb = teacherPersistentService.fetch(id);
        if (permissionEvaluator.hasAccess(teacherDb)) {
            throw new ForbiddenException("Forbidden - Don't have access to teacher " + id);
        }
        teacherPersistentService.delete(teacherDb);
        return ResponseEntity.noContent().build();
    }
}
