package ankitiit.springboot.cassandra.ecommerceapp.product.service;

import ankitiit.springboot.cassandra.ecommerceapp.product.dto.Product;
import ankitiit.springboot.cassandra.ecommerceapp.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductByCategory(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProductByPrice(double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    public List<Product> getProductByRating(double minRating) {
        return productRepository.findByRatingGreaterThan(minRating);
    }

   /* public List<Product> getProductByStock(int minStock) {
        return productRepository.findByInventoryStockGreaterThan(minStock);
    }*/

    public List<Product> getPopularProducts() {
        return productRepository.findTopByOrderByPopularityDesc();
    }

    public List<Product> getReviewedProducts() {
        return productRepository.findByReviewed(true);
    }

   /* public List<Product> getProductsReviewedByCustomer(int customerId) {
        return productRepository.findByReviewsCustomerIdIn(customerId);
    }*/

 /*   public List<Product> getProductsReviewedWithRating(int minRating) {
        return productRepository.findByReviewsRatingGreaterThan(minRating);
    }*/

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
