package classroom.store.security.privileges.teacher;

import classroom.store.orm.TeacherDb;
import classroom.store.security.privileges.AbstractCompositeEvaluator;
import classroom.store.security.privileges.PermissionEvaluator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositeTeacherPermissionEvaluator extends AbstractCompositeEvaluator<TeacherDb> {

    public CompositeTeacherPermissionEvaluator(List<PermissionEvaluator<TeacherDb>> evaluators) {
        super(evaluators);
    }

}
