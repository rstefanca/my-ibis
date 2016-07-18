package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.repositories.PacientRepository;
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
    private PacientRepository pacientRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return pacientRepository
                .findByKontaktEmail(userName)
                .map(p -> new User(p.getKontakt().getEmail(), p.getNastaveniUctu().getHeslo(), AuthorityUtils.createAuthorityList("PACIENT", "write")))
                .orElseThrow(() -> new UsernameNotFoundException(userName));
    }
}
