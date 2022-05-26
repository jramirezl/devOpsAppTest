package co.edu.udem.userstoriesnlp.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NonNull
  private String name;
  @NonNull
  private String description;
}
