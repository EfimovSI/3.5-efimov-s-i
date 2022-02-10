package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductManager {
    private ProductRepository repository = new ProductRepository();

    public void add(Product product) {
        repository.add(product);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String text) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getAuthor().contains(text)) {
                return true;
            }
        }
        if (product instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) product;
            if (smartphone.getManufacturer().contains(text)) return true;
        }
        return product.getName().contains(text);
    }
}
