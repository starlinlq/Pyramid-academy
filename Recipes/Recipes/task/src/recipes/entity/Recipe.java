package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
   @NotBlank
   private String name;
   @NotBlank
   private String description;
   @Size(min = 1)
   @ElementCollection
   private List<String> ingredients;
   @Size(min = 1)
   @ElementCollection
   private List<String> directions;
}
