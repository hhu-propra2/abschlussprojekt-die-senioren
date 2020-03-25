package mops.gruppen1.Controller.Interaction;


import mops.gruppen1.applicationService.RestService;
import mops.gruppen1.data.DAOs.GroupDAO;
import mops.gruppen1.data.DAOs.UpdatedGroupsDAO;
import mops.gruppen1.data.DAOs.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gruppen1")
public class InteractionController {

    RestService restService;

    @Autowired
    public InteractionController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/isUserInGroup")
    public ResponseEntity<Map<String, Boolean>> isUserInGroup(@RequestParam(value = "userName", defaultValue = "") String userName,
                                 @RequestParam(value = "groupId", defaultValue = "") String groupId) {

        //Check if given username and groupId are not empty
        if(userName.equals("") || groupId.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Check if user is in group
        boolean isUserInGroup = restService.isUserInGroup(userName,groupId);

        //prepare content for response statement
        Map<String,Boolean> resultMap = new HashMap<>();
        resultMap.put("isUserInGroup",isUserInGroup);

        //return result
        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }

    @GetMapping("/isUserAdminInGroup")
    public Boolean isUserAdminInGroup(@RequestParam(value = "userName", defaultValue = "") String userName,
                          @RequestParam(value = "groupId", defaultValue = "") String groupId) {
        // if default-value => request is not valid, username must be given
        // check if user ist admin in Group
        //return yes/no?
        return true;
    }

    @GetMapping("/doesGroupExist")
    public Boolean doesGroupExist(@RequestParam(value = "groupId", defaultValue = "") String groupId) {
        // if default-value => request is not valid, username must be given
        // check if group exists
        //return yes/no?
        return true;
    }

    @GetMapping("/returnAllGroups")
    public List<GroupDAO> returnAllGroups() {
        List<GroupDAO> groupList = new ArrayList<GroupDAO>();
        // return List of GroupDAOs
        return groupList;
    }

    @GetMapping("/returnUsersOfGroup")
    public List<UserDAO> returnUsersOfGroup(@RequestParam(value = "groupId", defaultValue = "") String groupId) {
        List<UserDAO> userList = new ArrayList<UserDAO>();
        // if default-value => request is not valid, username must be given
        // return all UserDAOs of Group
        return userList;
    }

    @GetMapping("/returnGroupsOfUsers")
    public List<GroupDAO> returnGroupsOfUsers(@RequestParam(value = "userName", defaultValue = "") String userName) {
        List<GroupDAO> groupList = new ArrayList<GroupDAO>();
        // if default-value => request is not valid, username must be given
        // return all groupDAOs a user belongs to
        return groupList;
    }








}
