package apiinventory.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository usersRepository;

    public Users save(Users user) {
        return usersRepository.save(user);
    }

    public List<Users> findAll() {
        return (List<Users>) usersRepository.findAll();
    }

    public Users findById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    public Users update(Users user) {
        return usersRepository.save(user);
    }

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }
}