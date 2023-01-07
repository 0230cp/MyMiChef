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

    @ManyToOne
    @JoinColumn(name = "menuId")
    private MenuEntity menuEntity;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "capacity", columnDefinition = "Long")
    private Long capacity;

    @Column(name = "unit", columnDefinition = "varchar")
    private String unit;

}
