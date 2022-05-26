package co.edu.udem.userstoriesnlp.service;

import co.edu.udem.userstoriesnlp.entities.UserStory;

import java.util.Collection;
import java.util.Optional;

public interface UserStoryService {

  Collection<UserStory> getUserStories();

  UserStory setUserStory(UserStory userStory);

  Optional<UserStory> getUserStory(Integer id);

  UserStory updateUserStory(Integer id, UserStory userStory);

  UserStory deleteUserStory(Integer id);
}
