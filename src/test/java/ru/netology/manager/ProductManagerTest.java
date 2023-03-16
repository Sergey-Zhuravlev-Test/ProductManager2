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

    Product book1 = new Book(123, "Fantasy", 2000, "Lord of rings");
    Product book2 = new Book(258, "Fantasy", 800, "Master");
    Product book3 = new Book(357, "Novel", 1200, "Bernard");


    Product smartphone1 = new Smartphone(741, "Iphone", 12500, "Apple");
    Product smartphone2 = new Smartphone(951, "Samsung", 25000, "Samsung Inc.");

    @BeforeEach
    public void SetUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }


    @Test
    void findProductBySmartphoneName() {

        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("Iphone");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void findProductByBookName() {
        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Novel");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findProductByEqualName() {
        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("Fantasy");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindAnyProduct() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Fairytale");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeProduct() {

        repo.removeByID(357);
        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }
}

//    @Test
//    void findProductBySmartphoneManufacturer() {
//
//        Product[] expected = {smartphone};
//        Product[] actual = manager.searchBy("Apple");
//
//        Assertions.assertArrayEquals(expected, actual);
//
//
//        @Test
//        void findProductByBookAuthor() {
//            Product[] expected = {book};
//            Product[] actual = manager.searchBy("Bernard");
//
//            Assertions.assertArrayEquals(expected, actual);
//        }