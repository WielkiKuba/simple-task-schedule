package com.grafik.grafikManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public void addUser(User user){userRepository.save(user);}

    public void addUser(String name){
        User userTemp = new User();
        userTemp.setName(name);
        addUser(userTemp);
    }

    public void delUser(User user){userRepository.delete(user);}

    public List<User> getByName(String name){return userRepository.getByName(name);}
    public List<User> getAll(){return userRepository.findAll();}
    public Optional<User> getById(Long id){return userRepository.findById(id);}
}
