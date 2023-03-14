package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;


class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book = new Book(123, "Master", 2000, "Bernard");
    Product smartphone = new Smartphone(741, "Iphone", 12500, "Apple");

    @BeforeEach
    public void SetUp() {
        manager.add(book);
        manager.add(smartphone);

    }


    @Test
    void findProductBySmartphoneName() {

        Product[] expected = {smartphone};
        Product[] actual = manager.searchBy("Iphone");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void findProductBySmartphoneManufacturer() {

        Product[] expected = {smartphone};
        Product[] actual = manager.searchBy("Apple");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void findProductByBookName() {
        Product[] expected = {book};
        Product[] actual = manager.searchBy("Master");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findProductByBookAuthor() {
        Product[] expected = {book};
        Product[] actual = manager.searchBy("Bernard");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeProduct() {

        repo.removeByID(123);
        Product[] expected = {smartphone};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }
}