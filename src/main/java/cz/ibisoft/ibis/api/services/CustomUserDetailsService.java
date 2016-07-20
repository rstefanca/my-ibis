package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.Patient;
import cz.ibisoft.ibis.api.repositories.PatientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Richard Stefanca
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return patientRepository
                .findByContactEmail(userName)
                .map(CustomUserDetailsService::getUser)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    @NotNull
    private static User getUser(Patient p) {
        return new User(
                p.getId(),
                p.getPassword(),
                p.isActive(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("PACIENT", "write"));
    }
}
