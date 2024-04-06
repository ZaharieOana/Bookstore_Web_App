package com.example.Bookstore;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookType;
import com.example.Bookstore.model.Sale;
import com.example.Bookstore.model.User;
import com.example.Bookstore.service.BookService;
import com.example.Bookstore.service.BookTypeService;
import com.example.Bookstore.service.SaleService;
import com.example.Bookstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService, BookService bookService, BookTypeService bookTypeService, SaleService saleService){
		return args -> {
			BookType bookType1 = new BookType();
			bookType1.setName("horror");
			BookType bookType2 = new BookType();
			bookType2.setName("children");

			Book book1 = new Book();
			book1.setTitle("IT");
			book1.setAuthor("Stephen King");
			book1.setStock(20);
			book1.setPrice(50);
			book1.setType(bookType1);

			Book book2 = new Book();
			book2.setTitle("The Little Prince");
			book2.setAuthor("Antoine De Saint-Exupery");
			book2.setStock(30);
			book2.setPrice(35);
			book2.setType(bookType2);

			User user1 = new User();
			user1.setName("Pop Ana");
			user1.setType(UserType.CLIENT);
			user1.setAge(20);
			user1.setEmail("anapop@gmail.com");
			user1.setPassword("AnaPop");

			User user2 = new User();
			user2.setName("Dan Vlad");
			user2.setType(UserType.CLIENT);
			user2.setAge(20);
			user2.setEmail("danvlad@gmail.com");
			user2.setPassword("DanVlad1234");

			userService.saveUser(user1);
			userService.saveUser(user2);
			bookTypeService.saveBookType(bookType1);
			bookTypeService.saveBookType(bookType2);
			bookService.saveBook(book1);
			bookService.saveBook(book2);

			List<User> users = userService.findAll();
			List<Book> books = bookService.findAll();
			List<BookType> bookTypes = bookTypeService.findAll();

			saleService.makeSale(books, user1);
			saleService.makeSale(books, user1);
			List<Sale> sales = saleService.findAll();

			System.out.println("Users:");
			for(User u : users)
				System.out.println(u);
			System.out.println("Books:");
			for(Book b : books)
				System.out.println(b);
			System.out.println("Book Types:");
			for(BookType t : bookTypes)
				System.out.println(t);
			System.out.println("Sales:");
			for(Sale s : sales)
				System.out.println(s);

			System.out.println("Find book by title \"IT\": " + bookService.findBookByTitle("IT"));
			System.out.println("Find book by type \"children\": " + bookService.findBooksByType(bookType2));
			System.out.println("Find user by email \"danvlad@gmail.com\": " + userService.findUserByEmail("danvlad@gmail.com"));

			userService.deleteUser(userService.findUserByEmail("danvlad@gmail.com"));
			users = userService.findAll();
			System.out.println("Users after deleting danvlad@gmail.com:");
			for(User u : users)
				System.out.println(u);

			bookService.addToStock(bookService.findBookByID(2L), 100);

			books = bookService.findAll();
			System.out.println("Books after a sale was made and 100 of \"The Little Prince\" were added:");
			for(Book b : books)
				System.out.println(b);
		};
	}

}
