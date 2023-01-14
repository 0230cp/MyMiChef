package cp.demo.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredtbl")
@Entity
public class IngredientEntity {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menuId")
    private MenuEntity menuEntity;

    @Column(name = "name", columnDefinition = "varchar(256)")
    private String name;

    @Column(name = "capacity", columnDefinition = "INT")
    private int capacity;

    @Column(name = "unit", columnDefinition = "varchar(256)")
    private String unit;

}
