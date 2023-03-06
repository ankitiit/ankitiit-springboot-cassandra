package ankitiit.springboot.cassandra.ecommerceapp.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    private double rating;
    private int inventoryId;
    private int popularity;
    private boolean reviewed; // Add this field to indicate if the product has been reviewed
    // getters and setters
}
