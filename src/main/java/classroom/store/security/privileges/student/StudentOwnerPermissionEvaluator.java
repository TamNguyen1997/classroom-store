package classroom.store.security.privileges.student;

import classroom.store.orm.StudentDb;
import classroom.store.security.AuthenticationFacade;
import classroom.store.security.Role;
import classroom.store.security.privileges.PermissionEvaluator;
import org.springframework.stereotype.Service;

@Service
public class StudentOwnerPermissionEvaluator implements PermissionEvaluator<StudentDb> {

    private final AuthenticationFacade authenticationFacade;

    public StudentOwnerPermissionEvaluator(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public boolean hasPermission(StudentDb studentDb) {
        return authenticationFacade.hasPrivilege(Role.STUDENT, studentDb.getId());
    }
}
