package co.edu.udem.userstoriesnlp.service;

import co.edu.udem.userstoriesnlp.exceptions.InvalidRequestException;
import co.edu.udem.userstoriesnlp.exceptions.NotFoundException;
import co.edu.udem.userstoriesnlp.entities.UserStory;
import co.edu.udem.userstoriesnlp.repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserStoryServiceImpl implements UserStoryService {
  UserStoryRepository userStoryRepository;

  @Autowired
  public UserStoryServiceImpl(UserStoryRepository userStoryRepository) {
    this.userStoryRepository = userStoryRepository;
  }

  @Override
  public UserStory setUserStory(UserStory userStory) {
    return userStoryRepository.save(userStory);
  }

  @Override
  public Optional<UserStory> getUserStory(Integer id) {
    var optionalRecord = userStoryRepository.findById(id);
    if (optionalRecord == null || optionalRecord.isEmpty()) {
      throw new NotFoundException("User story with ID " + id + " does not exist.");
    }
    return optionalRecord;
  }

  @Override
  public UserStory updateUserStory(Integer id, UserStory userStory) {
    if (id == null || userStory == null) {
      throw new InvalidRequestException("User story or ID must not be null!");
    }
    Optional<UserStory> optionalRecord = getUserStory(id);
    var foundUserStory = optionalRecord.get();
    foundUserStory.setName(userStory.getName());
    foundUserStory.setDescription(userStory.getDescription());
    userStoryRepository.save(foundUserStory);
    return foundUserStory;
  }

  @Override
  public UserStory deleteUserStory(Integer id) {
    Optional<UserStory> userStory = getUserStory(id);
    userStoryRepository.delete(userStory.get());
    return userStory.get();
  }

  @Override
  public Collection<UserStory> getUserStories() {
    Collection<UserStory> userStories = new ArrayList<>();
    Iterable<UserStory> list = userStoryRepository.findAll();
    list.forEach(userStories::add);
    return userStories;
  }
}
