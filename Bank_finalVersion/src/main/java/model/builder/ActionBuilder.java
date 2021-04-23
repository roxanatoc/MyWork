package model.builder;

import model.Action;

public class ActionBuilder {

    private Action action;

    public ActionBuilder() {
       action = new Action();
    }

    public ActionBuilder setUsername(String username) {
        action.setUsername(username);
        return this;
    }

    public ActionBuilder setActionName(String actionName) {
        action.setActionName(actionName);
        return this;
    }

    public ActionBuilder setId(Long id) {
        action.setId(id);
        return this;
    }

    public Action build() {
        return action;
    }
}
