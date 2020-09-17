package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {
    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Test
    public void findAll() {
        String description = "Cake";
        recipeReactiveRepository.save(new Recipe(description)).block();
        Recipe uom = recipeReactiveRepository.findAll().blockFirst();
        assertThat(uom.getDescription(), equalTo(description));
        assertThat(uom.getId(), is(notNullValue()));
    }
}
