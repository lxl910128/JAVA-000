package club.gaiaproject.homework.source.controller;

import club.gaiaproject.homework.source.domain.po.User;
import club.gaiaproject.homework.source.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        // User user = userRepository.getOne(1L);
        User newUser = new User();
        newUser.setName("tom");
        newUser.setCreateTime(new Date());
        newUser.setDepartment("");
        newUser.setPassword("aaa");
        newUser.setPhone("123");
        newUser.setUsername("tom");
        newUser.setRemark("remark");
        newUser.setStatus(1);
        newUser.setUpdateTime(new Date());
        newUser.setSuperuser(false);
        userRepository.save(newUser);
        return newUser.getName();
    }
    
    @GetMapping("/slave")
    //@ReadOnly
    public String getByIdFromSlave() {
        User user = userRepository.getOne(2L);
        return user.getName();
    }
}
