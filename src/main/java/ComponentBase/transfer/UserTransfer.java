package ComponentBase.transfer;

import java.util.Map;

/**
 * Created by panit on 5/4/2016.
 */
public class UserTransfer {
    private final String name;
    private final Map<String, Boolean> roles;


    public UserTransfer(String name, Map<String, Boolean> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Map<String, Boolean> getRoles() {
        return roles;
    }
}