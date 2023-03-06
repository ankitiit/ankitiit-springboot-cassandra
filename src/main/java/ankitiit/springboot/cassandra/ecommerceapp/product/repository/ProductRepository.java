package ankitiit.springboot.cassandra.ecommerceapp.product.repository;

import ankitiit.springboot.cassandra.ecommerceapp.product.dto.Product;
import ankitiit.springboot.cassandra.ecommerceapp.review.dto.Review;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CassandraRepository<Product, Integer> {
    List<Product> findByCategoryId(int categoryId);
    List<Product> findByPriceLessThan(double maxPrice);
    List<Product> findByRatingGreaterThan(double minRating);
   // List<Product> findByInventoryStockGreaterThan(int minStock);
    List<Product> findTopByOrderByPopularityDesc();
    List<Product> findByReviewed(boolean reviewed);
    //List<Product> findByReviewsCustomerIdIn(int customerId);
    //List<Product> findByReviewsIn(List<Review> reviews);
}
