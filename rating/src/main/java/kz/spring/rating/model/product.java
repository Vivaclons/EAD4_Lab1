package kz.spring.rating.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String product_name;
    private String product_cast;
}
