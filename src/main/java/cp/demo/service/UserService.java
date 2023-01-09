package cp.demo.service;

import cp.demo.domain.entity.SecurityDetails;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public SecurityDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user=userRepository.findById(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("계정이 존재하지 않습니다.");
        return new SecurityDetails(user.get());
    }
}
