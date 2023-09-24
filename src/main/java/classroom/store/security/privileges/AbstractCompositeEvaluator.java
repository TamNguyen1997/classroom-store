package classroom.store.security.privileges;

import java.util.List;

public abstract class AbstractCompositeEvaluator<T> {

    private final List<? extends PermissionEvaluator<T>> permissionEvaluators;

    public AbstractCompositeEvaluator(List<? extends PermissionEvaluator<T>> permissionEvaluators) {
        this.permissionEvaluators = permissionEvaluators;
    }

    public boolean hasAccess(T target) {
        for(PermissionEvaluator<T> evaluator : permissionEvaluators) {
            if(evaluator.hasPermission(target)) {
                return true;
            }
        }

        return false;
    }
}

