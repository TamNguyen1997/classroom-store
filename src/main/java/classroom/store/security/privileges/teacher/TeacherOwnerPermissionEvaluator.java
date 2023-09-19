package classroom.store.security.privileges.teacher;

import classroom.store.orm.TeacherDb;
import classroom.store.security.AuthenticationFacade;
import classroom.store.security.Role;
import classroom.store.security.privileges.PermissionEvaluator;
import org.springframework.stereotype.Service;

@Service
public class TeacherOwnerPermissionEvaluator implements PermissionEvaluator<TeacherDb> {

    private final AuthenticationFacade authenticationFacade;

    public TeacherOwnerPermissionEvaluator(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public boolean hasPermission(TeacherDb teacherDb) {
        return authenticationFacade.hasPrivilege(Role.TEACHER, teacherDb.getId());
    }
}
