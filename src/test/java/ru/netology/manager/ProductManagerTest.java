package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private final ProductRepository repository = new ProductRepository();
    private final ProductManager manager = new ProductManager(repository);

    Product first = new Product(1, "First", 10);
    Product second = new Product(2, "Second", 20);
    Book third = new Book(3, "Third", 30, "John");
    Book fourth = new Book(4, "Fourth", 40, "Smith");
    Smartphone fifth = new Smartphone(5, "Fifth", 50, "Pineapple");
    Smartphone sixth = new Smartphone(5, "Sixth", 60, "Cherry");

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    void shouldAddToEmpty() {
        ProductRepository repository1 = new ProductRepository();
        ProductManager manager1 = new ProductManager(repository1);

        manager1.add(first);

        assertArrayEquals(new Product[]{first}, repository1.findAll());
    }


    @Test
    void shouldRemoveByIdWhenExist() {
        manager.removeById(3);

        assertArrayEquals(new Product[]{first, second, fourth, fifth, sixth}, repository.findAll());
    }

    @Test
    void shouldRemoveByIdWhenNotExist() {
        manager.removeById(7);

        assertArrayEquals(new Product[]{first, second, third, fourth, fifth, sixth}, repository.findAll());
    }

    @Test
    void shouldSearchOneByName() {

        assertArrayEquals(new Product[]{third}, manager.searchBy("Third"));
    }

    @Test
    void shouldSearchOneByAuthor() {

        assertArrayEquals(new Product[]{fourth}, manager.searchBy("Smith"));
    }

    @Test
    void shouldSearchOneByManufacturer() {

        assertArrayEquals(new Product[]{sixth}, manager.searchBy("Cherry"));
    }

    @Test
    void shouldSearchSeveral() {

        assertArrayEquals(new Product[]{first, third, fourth, sixth}, manager.searchBy("r"));
    }

    @Test
    void shouldNotFind() {

        assertArrayEquals(new Product[0], manager.searchBy("Snow"));
    }

    @Test
    void shouldNotFindInEmpty() {
        ProductRepository repository1 = new ProductRepository();
        ProductManager manager1 = new ProductManager(repository1);

        assertArrayEquals(new Product[0], manager1.searchBy("Snow"));
    }
}