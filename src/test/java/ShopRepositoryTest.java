import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {

    @Test
    public void testRemoveExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Хлеб", 50);
        Product product2 = new Product(2, "Молоко", 80);
        Product product3 = new Product(3, "Сыр", 200);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(2);

        Product[] products = repository.findAll();
        Product[] expectedProducts = {product1, product3};
        Assertions.assertArrayEquals(expectedProducts, products);
        Assertions.assertNull(repository.findById(2));
    }

    @Test
    public void testRemoveNonExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Хлеб", 50);
        Product product2 = new Product(2, "Молоко", 80);

        repository.add(product1);
        repository.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(999);
        });

        Product[] products = repository.findAll();
        Product[] expectedProducts = {product1, product2};
        Assertions.assertArrayEquals(expectedProducts, products);
    }
}