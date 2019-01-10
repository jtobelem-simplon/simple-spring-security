package co.simplon.springboot.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	
    @RequestMapping(path = "/public",method = RequestMethod.GET)
    public String publicResource() {
        return "This resource is public !";
    }

    @RequestMapping(path = "/authenticated",method = RequestMethod.GET)
    public String authResource() {
        return "This resource is available for all authenticated users !";
    }
	
    @RequestMapping(path = "/developper",method = RequestMethod.GET)
    public String developperResource() {
        return "This resource is available for authenticated users with role Developper !";
    }
    
    @RequestMapping(path = "/manager",method = RequestMethod.GET)
    public String managerResource() {
        return "This resource is available for authenticated users with role Manager !";
    }
    
    @RequestMapping(path = "/admin",method = RequestMethod.GET)
    public String adminResource() {
        return "This resource is available for admins (Admin role)!";
    }
    
    @RequestMapping(path = "/deny",method = RequestMethod.GET)
    public String deniedResource() {
        return "This resource is denied for all users !";
    }
}
