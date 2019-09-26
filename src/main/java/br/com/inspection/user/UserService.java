package br.com.inspection.user;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import br.com.inspection.userinformation.UserInformation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        user.getInformations().forEach(userInformation -> {
            if (userInformation.getUser() == null) {
                userInformation.setUser(userFromDb);
            }
            if (userInformation.getId() == null) {
                userInformation.setId(UUID.randomUUID());
                userInformation.setCreatedAt(OffsetDateTime.now());
                userInformation.setUpdatedAt(OffsetDateTime.now());
            }
            if (userFromDb.getInformations().stream().map(UserInformation::getId).anyMatch(id -> id.equals(userInformation.getId()))) {
                final Set<UserInformation> collect = userFromDb.getInformations().stream().filter(u -> u.equals(userInformation)).collect(Collectors.toSet());
                if (collect.isEmpty()) {
                    userInformation.setUpdatedAt(OffsetDateTime.now());
                }
            }
        });

        userFromDb.getInformations().clear();
        userFromDb.getInformations().addAll(user.getInformations());
        userFromDb.setUpdatedAt(OffsetDateTime.now());
    }

}
