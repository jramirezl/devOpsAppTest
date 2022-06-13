package co.edu.udem.userstoriesnlp.controllers;

import co.edu.udem.userstoriesnlp.entities.UserStory;
import co.edu.udem.userstoriesnlp.service.UserStoryServiceImpl;
import co.edu.udem.userstoriesnlp.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/userstory")
public class UserStoryController {
  UserStoryService userStoryService;

  @Autowired
  public UserStoryController(UserStoryServiceImpl userStoryService) {
    this.userStoryService = userStoryService;
  }

  @GetMapping
  public ResponseEntity<Collection<UserStory>> getUserStories() {
    return ResponseEntity.ok().body(userStoryService.getUserStories());
  }

  @PostMapping
  public ResponseEntity<UserStory> setUserStory(@RequestBody UserStory userStory) {
    return ResponseEntity.ok().body(userStoryService.setUserStory(userStory));
  }

  @GetMapping(value = "{id}", produces = "application/json")
  public ResponseEntity<Optional<UserStory>> getUserStory(@PathVariable Integer id) {
    return ResponseEntity.ok().body(userStoryService.getUserStory(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<UserStory> updateUserStory(
      @RequestBody UserStory userStory, @PathVariable Integer id) {
    return ResponseEntity.ok().body(userStoryService.updateUserStory(id, userStory));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<UserStory> deleteUserStory(@PathVariable Integer id) {
    return ResponseEntity.ok().body(userStoryService.deleteUserStory(id));
  }
}
