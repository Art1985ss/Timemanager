package com.javaguru.timemanager.users;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long create(User user){
        return userRepository.save(user).getId();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No user found with this id : " + id));
    }

    public User findByName(String name){
        return userRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("No user found with name : " + name));
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No user found with id : " + id + " to delete"));
        userRepository.delete(user);
    }

    public void update(User user){
        userRepository.save(user);
    }
}
