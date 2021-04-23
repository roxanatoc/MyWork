package service.action;

import model.Action;
import repository.EntityNotFoundException;

import java.util.List;

public interface ActionService {

    boolean save(Action action);

    List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException;


}
