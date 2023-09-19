package classroom.store.security.privileges.student;

import classroom.store.orm.StudentDb;
import classroom.store.security.privileges.AbstractCompositeEvaluator;
import classroom.store.security.privileges.PermissionEvaluator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositeStudentPermissionEvaluator extends AbstractCompositeEvaluator<StudentDb> {

    public CompositeStudentPermissionEvaluator(List<PermissionEvaluator<StudentDb>> evaluators) {
        super(evaluators);
    }

}
