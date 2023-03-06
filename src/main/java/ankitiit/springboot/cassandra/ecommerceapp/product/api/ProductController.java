package ankitiit.springboot.cassandra.ecommerceapp.product.api;

import ankitiit.springboot.cassandra.ecommerceapp.product.dto.Product;
import ankitiit.springboot.cassandra.ecommerceapp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public List<Product> getProductByCategory(@PathVariable int id) {
        return productService.getProductByCategory(id);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Product> getProductByPrice(@PathVariable double maxPrice) {
        return productService.getProductByPrice(maxPrice);
    }

    @GetMapping("/rating/{minRating}")
    public List<Product> getProductByRating(@PathVariable double minRating) {
        return productService.getProductByRating(minRating);
    }

   /* @GetMapping("/stock/{minStock}")
    public List<Product> getProductByStock(@PathVariable int minStock) {
        return productService.getProductByStock(minStock);
    }*/

    @GetMapping("/popular")
    public List<Product> getPopularProducts() {
        return productService.getPopularProducts();
    }

    @GetMapping("/reviewed")
    public List<Product> getReviewedProducts() {
        return productService.getReviewedProducts();
    }

   /* @GetMapping("/review/customer/{customerId}")
    public List<Product> getProductsReviewedByCustomer(@PathVariable int customerId) {
        return productService.getProductsReviewedByCustomer(customerId);
    }*/

   /* @GetMapping("/review/rating/{minRating}")
    public List<Product> getProductsReviewedWithRating(@PathVariable int minRating) {
        return productService.getProductsReviewedWithRating(minRating);
    }*/

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

}
