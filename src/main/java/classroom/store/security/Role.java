package classroom.store.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Role {
    public static final String TEACHER = "TEACHER";
    public static final String STUDENT = "STUDENT";
    public static final String ADMIN = "ADMIN";
    public static final String AUTHORIZE_PREFIX = "hasAnyRole('";
    public static final String AUTHORIZE_SUFFIX = "')";
    public static final String PERMIT_CLASSROOM = AUTHORIZE_PREFIX + Role.ADMIN + AUTHORIZE_SUFFIX;
    public static final String PERMIT_STUDENT = AUTHORIZE_PREFIX + Role.STUDENT +  "', '" + Role.ADMIN + AUTHORIZE_SUFFIX;
    public static final String PERMIT_TEACHER = AUTHORIZE_PREFIX + Role.TEACHER +  "', '" + Role.ADMIN + AUTHORIZE_SUFFIX;

}
