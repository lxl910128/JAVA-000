package club.gaiaproject.homewrok.tcc.a.controller;

import club.gaiaproject.homewrok.tcc.a.service.BackendServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@RestController
@RequestMapping("/backend")
@Slf4j
public class BackendController {
    @Autowired
    private BackendServiceImpl backendService;
    
    @GetMapping("/transfer")
    public String transfer() {
        return backendService.transfer();
    }
    
    
}
