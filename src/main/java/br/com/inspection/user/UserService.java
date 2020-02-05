package br.com.inspection.user;

import br.com.inspection.server.adapter.DozerAdapter;
import br.com.inspection.server.validation.exception.RegisterNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    @Inject
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final User byUserName = repository.findByUsername(userName);
        if (Objects.isNull(byUserName)) {
            throw new UsernameNotFoundException("UserName " + userName + " not found!");
        }
        return byUserName;
    }

    public UserVO findById(final UUID userId) {
        return DozerAdapter.parseObject(
                repository.findById(userId)
                .orElseThrow(RegisterNotFoundException::new), UserVO.class);
    }

    public UserVO update(final UserVO userVO) {
        final User userFromDb = this.repository.findById(userVO.getKey()).orElseThrow(RegisterNotFoundException::new);
        final User user = DozerAdapter.parseObject(userVO, User.class);
        this.doGenerateUpdateValues(user, userFromDb);
        return DozerAdapter.parseObject(this.repository.save(userFromDb), UserVO.class);
    }

    private void doGenerateUpdateValues(final User user, final User userFromDb) {
        userFromDb.getInformation().clear();
        userFromDb.getInformation().addAll(user.getInformation());
        userFromDb.setUpdatedAt(OffsetDateTime.now());
        userFromDb.setCreatedAt(userFromDb.getCreatedAt());
    }

}
