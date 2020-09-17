package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
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
public class CategoryReactiveRepositoryIT {
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findAll() {
        String description = "Cake";
        categoryReactiveRepository.save(new Category(null, description, new HashSet<>())).block();
        Category uom = categoryReactiveRepository.findAll().blockFirst();
        assertThat(uom.getDescription(), equalTo(description));
        assertThat(uom.getId(), is(notNullValue()));
    }

    @Test
    public void findByDescription() {
        String description = "Dessert";
        categoryReactiveRepository.save(new Category(null, description, new HashSet<>())).block();
        Category uom = categoryReactiveRepository.findByDescription(description).block();
        assertThat(uom.getId(), is(notNullValue()));
    }
}
