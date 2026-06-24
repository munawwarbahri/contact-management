package contactmanagement.restful.service;

import contactmanagement.restful.entity.User;
import contactmanagement.restful.model.RegisterUserRequest;
import contactmanagement.restful.repository.UserRepository;
import contactmanagement.restful.security.BCrypt;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;


@Service
public class UserService {

    @Autowired //injek user repository
    private UserRepository userRepository;

    private Validator validator;

    @Transactional
    public void register(RegisterUserRequest request) {
        // Implement the logic to register a new user
        // For example, you can create a new User entity and save it to the database
        // You may also want to check if the username already exists before saving
        Set<ConstraintViolation<RegisterUserRequest>> consctraintViolations = validator.validate(request);
        if (consctraintViolations.size() != 0) {
            //error
            throw new ConstraintViolationException(consctraintViolations);
        }

        if(userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(),  BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }
}
