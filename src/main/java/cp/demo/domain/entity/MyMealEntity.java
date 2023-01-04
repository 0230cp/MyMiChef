package cp.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "mymealtbl")
@Entity
public class MyMealEntity {

    @Column(name = "menuId", columnDefinition = "INT")
    private String menuid;

}
