package classroom.store.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationFacade {
    private static final String PRIVILEGE = "privileges";

    public Map<String, List<UUID>> getPrivileges() {
        return (Map<String, List<UUID>>) getJwt().getClaims().get(PRIVILEGE);
    }

    public boolean hasPrivilege(String role, UUID scopeId) {
        return getPrivileges().get(role).contains(scopeId);
    }

    public boolean hasRole(String role) {
        return getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()).contains(role);
    }

    private SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    private Authentication getAuthentication() {
        return getSecurityContext().getAuthentication();
    }

    private Jwt getJwt() {
        if(getAuthentication().getPrincipal() instanceof Jwt) {
            return (Jwt) getAuthentication().getPrincipal();
        }
        return null;
    }
}
