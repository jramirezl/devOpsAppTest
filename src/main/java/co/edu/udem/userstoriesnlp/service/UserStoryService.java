package co.edu.udem.userstoriesnlp.service;

import co.edu.udem.userstoriesnlp.entities.UserStory;
import co.edu.udem.userstoriesnlp.repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserStoryService implements UserStoryServiceInt {
    UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryService(UserStoryRepository userStoryRepository){
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public UserStory setUserStory(UserStory userStory){
        return userStoryRepository.save(userStory);
    }

    @Override
    public Optional<UserStory> getUserStory(Integer id) {
        return userStoryRepository.findById(id);
    }

    @Override
    public UserStory updateUserStory(Integer id, UserStory userStory) {
        Optional<UserStory> optionalUserStory = getUserStory(id);
        if(optionalUserStory.isPresent()) {
            var foundUserStory = optionalUserStory.get();
            foundUserStory.setName(userStory.getName());
            foundUserStory.setDescription(userStory.getDescription());
            userStoryRepository.save(foundUserStory);
            return foundUserStory;
        }
        return null;
    }

    @Override
    public UserStory deleteUserStory(Integer id) {
        Optional<UserStory> userStory = getUserStory(id);
        if(userStory.isPresent()) {
            userStoryRepository.delete(userStory.get());
            return userStory.get();
        }
        return null;
    }

    @Override
    public Collection<UserStory> getUserStories(){
        Collection<UserStory> userStories = new ArrayList<>();
        Iterable<UserStory> list = userStoryRepository.findAll();
        list.forEach(userStories::add);
        return userStories;
    }
}
