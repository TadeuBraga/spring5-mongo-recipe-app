package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Category {
    @Id
    private String id;
    private String description;
    private Set<Recipe> recipes;
}
