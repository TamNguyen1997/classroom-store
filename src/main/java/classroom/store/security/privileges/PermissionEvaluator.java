package classroom.store.security.privileges;

public interface PermissionEvaluator<T> {
    default boolean hasPermission(T target) { return false; }
}
