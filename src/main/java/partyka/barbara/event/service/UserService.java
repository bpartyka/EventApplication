package partyka.barbara.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import partyka.barbara.event.model.dto.UserDTO;
import partyka.barbara.event.model.dto.mapper.UserToDTOMapper;
import partyka.barbara.event.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserToDTOMapper::map)
                .collect(Collectors.toList());
    }
}
