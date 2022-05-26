package co.edu.udem.userstoriesnlp.repositories;

import co.edu.udem.userstoriesnlp.entities.UserStory;
import org.springframework.data.repository.CrudRepository;

public interface UserStoryRepository extends CrudRepository<UserStory, Integer> {
}
