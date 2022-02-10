package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRepository {
    private Product[] products = new Product[0];

    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        System.arraycopy(products, 0, tmp, 0, products.length);
        tmp[tmp.length - 1] = product;
        this.products = tmp;
    }

    public Product[] findAll() {
        return this.products;
    }

    public void removeById(int id) {
        int deleteIndex = -1;
        for (Product product : products) {
            deleteIndex++;
            if (product.getId() == id) {
                int index = 0;
                int i = 0;
                Product[] tmp = new Product[products.length - 1];
                for (Product product1 : products) {
                    if (index != deleteIndex) {
                        tmp[i] = product1;
                        i++;
                    }
                    index++;
                }
                this.products = tmp;
            }
        }
        return;
    }
}
