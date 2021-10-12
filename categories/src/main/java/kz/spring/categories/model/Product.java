package kz.spring.categories.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
   Long id;
   String name;
   String description;
}
