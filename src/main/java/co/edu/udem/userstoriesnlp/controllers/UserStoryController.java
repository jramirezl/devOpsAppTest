package co.edu.udem.userstoriesnlp.controllers;

import co.edu.udem.userstoriesnlp.entities.UserStory;
import co.edu.udem.userstoriesnlp.service.UserStoryService;
import co.edu.udem.userstoriesnlp.service.UserStoryServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserStoryController {
    UserStoryServiceInt userStoryService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService){
        this.userStoryService = userStoryService;
    }

    @GetMapping("/user-stories")
    public ResponseEntity<Collection<UserStory>> getUserStories(){
        return ResponseEntity.ok().body(userStoryService.getUserStories());
    }

    @PostMapping("/user-story")
    public ResponseEntity<UserStory> setUserStory(@RequestBody UserStory userStory) {
        return ResponseEntity.ok().body(userStoryService.setUserStory(userStory));
    }

    @GetMapping(value = "/user-story/{id}", produces = "application/json")
    public ResponseEntity<Optional<UserStory>> getUserStory(@PathVariable Integer id){
        return ResponseEntity.ok().body(userStoryService.getUserStory(id));
    }

    @PutMapping("/user-story/{id}")
    public ResponseEntity<UserStory> updateUsuario(@RequestBody UserStory userStory, @PathVariable Integer id) {
        return ResponseEntity.ok().body(userStoryService.updateUserStory(id, userStory));
    }

    @DeleteMapping("/user-story/{id}")
    public ResponseEntity<UserStory> deleteUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok().body(userStoryService.deleteUserStory(id));
    }
}
