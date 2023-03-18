package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;


public class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();

    Product book1 = new Book(123, "Fantasy", 2000, "Lord of rings");
    Product book2 = new Book(258, "Fantasy", 800, "Master");
    Product book3 = new Book(357, "Novel", 1200, "Bernard");


    Product smartphone1 = new Smartphone(741, "Iphone", 12500, "Apple");
    Product smartphone2 = new Smartphone(951, "Samsung", 25000, "Samsung Inc.");

    @BeforeEach
    public void SetUp() {
        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone1);
        repo.save(smartphone2);
    }

    @Test
    void removeWhenProductFound() {

        repo.removeByID(258);
        Product[] expected = {book1, book3, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test

    public void removeWhenProductNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> repo.removeByID(555));

    }

    @Test
    void addNewProduct() {
        Product smartphone3 = new Smartphone(456, "Xiaomi", 14500, "Xiaomi Inc.");
        repo.save(smartphone3);
        Product[] expected = {book1, book2, book3, smartphone1, smartphone2, smartphone3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void addNewProductWithSameId() {
        Product smartphone3 = new Smartphone(123, "Xiaomi", 14500, "Xiaomi Inc.");
        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.save(smartphone3));

    }

}