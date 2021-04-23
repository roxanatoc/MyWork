package repository.action;

import model.Action;
import repository.EntityNotFoundException;
import java.util.List;

public interface ActionRepository {

   boolean save(Action action);

   List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException;

}
