package br.com.inspection.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Inject
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getUserById(@PathVariable("id") final UUID id) {
        final UserVO userVO = userService.findById(id);

        userVO.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());

        return ResponseEntity.ok(userVO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable final UUID id, @Valid @RequestBody final UserVO userVO) {
        userVO.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(userVO));
    }

    @GetMapping(path = "/all", produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getUsers() {
        final Iterable<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }


}
