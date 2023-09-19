package classroom.store.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String JWT_CLAIM_NAME_AUTHORITIES = "authorities";
    private static final String DEFAULT_ROLE_PRE_FIX = "ROLE_";

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    public CustomJwtAuthenticationConverter() {
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(DEFAULT_ROLE_PRE_FIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(JWT_CLAIM_NAME_AUTHORITIES);
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new HashSet<>(jwtGrantedAuthoritiesConverter.convert(source));
        return new JwtAuthenticationToken(source, authorities);
    }

}
