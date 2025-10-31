import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {

    @Test
    public void testRemoveExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Хлеб", 50);
        Product product2 = new Product(2, "Молоко", 80);
        Product product3 = new Product(3, "Сыр", 200);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        // Act
        repository.removeById(2);

        // Assert
        Product[] products = repository.findAll();
        Assertions.assertEquals(2, products.length);
        Assertions.assertEquals(product1, products[0]);
        Assertions.assertEquals(product3, products[1]);
        Assertions.assertNull(repository.findById(2));
    }

    @Test
    public void testRemoveNonExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Хлеб", 50);
        Product product2 = new Product(2, "Молоко", 80);

        repository.add(product1);
        repository.add(product2);

        // Act & Assert
        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> repository.removeById(3)
        );

        Assertions.assertEquals("Element with id: 3 not found", exception.getMessage());

        // Проверяем, что массив не изменился
        Product[] products = repository.findAll();
        Assertions.assertEquals(2, products.length);
    }
}