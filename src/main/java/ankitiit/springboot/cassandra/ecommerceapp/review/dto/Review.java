package ankitiit.springboot.cassandra.ecommerceapp.review.dto;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("reviews")
public class Review {
    @PrimaryKey
    private int id;
    private int productId;
    private int customerId;
    private String comment;
    private int rating;
    // getters and setters
}
