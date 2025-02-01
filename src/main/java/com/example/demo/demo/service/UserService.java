package com.example.demo.demo.service;

import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.example.demo.demo.model.User;
import com.example.demo.demo.repository.UserRepository;
import java.util.List;
import javax.persistence.criteria.Predicate;
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    // public static Specification<User> checkIfExists(String username){
    //     return (Specification<User>) (root, criteriaQuery, cb) -> {
    //         final List<Predicate> predicates = new ArrayList<>();

    //         if (username != "" || username != null) {
    //             predicates.add(cb.equal(root.get("username"), username));
    //         }

    //         return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    //     };
    // }
    public boolean checkIfExists(String username){
        User user = userRepository.findByUsername(username);
        if(user==null){
            return false;
        }
        else return true;
    }
    
    public User getUserByUserName(String username){
        return userRepository.findByUsername(username);
    }


    public void saveUser(User user){
            userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
