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
        final User userFromDb = this.repository
                .findById(userVO.getKey()).orElse(null);

        final User user = DozerAdapter.parseObject(userVO, User.class);

        if (userFromDb != null) {
            this.doGenerateUpdateValues(user, userFromDb);
            return DozerAdapter.parseObject(this.repository.save(userFromDb), UserVO.class);
        } else {
            throw new RegisterNotFoundException();
        }
    }

    private void doGenerateUpdateValues(final User user, final User userFromDb) {
        userFromDb.getInformations().clear();
        final List<UserInformation> oldInformation = user.getInformations().stream().filter(userInformation -> userInformation.getId() != null).collect(Collectors.toList());
        final List<UserInformation> newInformation = user.getInformations().stream().filter(userInformation -> userInformation.getId() == null).collect(Collectors.toList());
        newInformation.forEach(userInformation -> {
            userInformation.setId(UUID.randomUUID());
            userInformation.setUser(user);
            userInformation.setCreatedAt(OffsetDateTime.now());
            userInformation.setUpdatedAt(OffsetDateTime.now());
        });
        userFromDb.getInformations().addAll(oldInformation);
        userFromDb.getInformations().addAll(newInformation);
        userFromDb.setUpdatedAt(OffsetDateTime.now());
    }

}
