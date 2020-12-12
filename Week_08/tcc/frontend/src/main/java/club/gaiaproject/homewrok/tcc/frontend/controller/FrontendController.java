package club.gaiaproject.homewrok.tcc.frontend.controller;

import club.gaiaproject.homewrok.tcc.frontend.service.FrontendServiceImpl;
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
@RequestMapping("/frontend")
@Slf4j
public class FrontendController {
    @Autowired
    private FrontendServiceImpl frontendService;
    
    @GetMapping("/buy")
    public String test() {
        return frontendService.buy();
    }
    
    
}
