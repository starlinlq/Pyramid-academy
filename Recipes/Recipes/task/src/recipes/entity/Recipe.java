package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TBL_RECIPE")
public class Recipe {
   @JsonIgnore
   @Id
   @SequenceGenerator(name = "recipe_sequence", sequenceName = "recipe_sequence", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_sequence")
   private long id;
   private String name;
   private String description;
   private List<String> ingredients;
   private List<String> directions;
}
