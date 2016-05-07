package ComponentBase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by waiti on 5/1/2016.
 */
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;
    //user crud
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> getUser(){return userService.getUsers();}

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id){return userService.getUser(id);}

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public @ResponseBody
    User add(@RequestBody User user, BindingResult bindingResult){
        return userService.create(user);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") String id){
        return userService.delete(id);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public  User edit(@PathVariable("id") String id,@RequestBody User user, BindingResult bindingResult){
        return userService.edit(user);
    }
    //service
    @RequestMapping(value = "/user/search/{name}",method = RequestMethod.GET)
    public List<User> findUserBySurName (@PathVariable("name") String name){return userService.findByName(name);}
}
