package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void findAll() {
        String description = "Teaspoon";
        unitOfMeasureReactiveRepository.save(new UnitOfMeasure(null, description)).block();
        UnitOfMeasure uom = unitOfMeasureReactiveRepository.findAll().blockFirst();
         assertThat(uom.getDescription(), equalTo(description));
         assertThat(uom.getId(), is(notNullValue()));
    }

    @Test
    public void findByDescription() {
        String description = "Cup";
        unitOfMeasureReactiveRepository.save(new UnitOfMeasure(null, description)).block();
        UnitOfMeasure uom = unitOfMeasureReactiveRepository.findByDescription(description).block();
        assertThat(uom.getId(), is(notNullValue()));
    }
}
