package club.gaiaproject.homework.source.controller;

import club.gaiaproject.homework.source.common.ReadOnly;
import club.gaiaproject.homework.source.domain.po.User;
import club.gaiaproject.homework.source.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
@RestController
@RequestMapping
public class HelloController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/master")
    public String getByIdFromMaster() {
        User user = userRepository.getOne(1L);
        return user.getName();
    }
    
    @GetMapping("/slave")
    @ReadOnly
    public String getByIdFromSlave() {
        User user = userRepository.getOne(127L);
        return user.getName();
    }
}
