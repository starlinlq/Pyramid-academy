package recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
}
