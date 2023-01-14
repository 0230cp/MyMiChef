package cp.demo.domain.repository;

import cp.demo.domain.entity.IngredientEntity;
import cp.demo.domain.entity.MenuEntity;
import cp.demo.domain.entity.MyIngredientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    public void 올바른값출력(){
        // given
        final IngredientEntity ingredientEntity = IngredientEntity.builder()
                .menuEntity(MenuEntity.builder()
                        .menuId("1").build())
                .name("재료")
                .build();
        final IngredientEntity savedIngredEntity = ingredientRepository.save(ingredientEntity);

        final MyIngredientEntity myIngredientEntity = MyIngredientEntity.builder()
                .ingredName("재료")
                .build();

        // when
        final IngredientEntity result = ingredientRepository.findByName("재료");

        // then
        assertThat(result).isEqualTo(savedIngredEntity);
    }
}
