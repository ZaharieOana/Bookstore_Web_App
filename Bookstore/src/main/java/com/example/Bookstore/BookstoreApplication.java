package com.example.Bookstore;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.dto.*;
import com.example.Bookstore.mapper.BookMapper;
import com.example.Bookstore.mapper.BookTypeMapper;
import com.example.Bookstore.mapper.UserMapper;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookType;
import com.example.Bookstore.model.Sale;
import com.example.Bookstore.model.User;
import com.example.Bookstore.service.BookService;
import com.example.Bookstore.service.BookTypeService;
import com.example.Bookstore.service.SaleService;
import com.example.Bookstore.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Bookstore", version = "1.0", description = "ProiectPS"))
@Validated
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


//	@Bean
//	CommandLineRunner init(UserService userService, BookService bookService, BookTypeService bookTypeService, SaleService saleService){
//		return args -> {
//			BookType bookType1 = new BookType();
//			bookType1.setName("horror");
//			BookType bookType2 = new BookType();
//			bookType2.setName("children");
//
//			Book book1 = new Book();
//			book1.setTitle("IT");
//			book1.setAuthor("Stephen King");
//			book1.setStock(20);
//			book1.setPrice(50);
//			book1.setType(bookType1);
//
//			Book book2 = new Book();
//			book2.setTitle("The Little Prince");
//			book2.setAuthor("Antoine De Saint-Exupery");
//			book2.setStock(30);
//			book2.setPrice(35);
//			book2.setType(bookType2);
//
//			User user1 = new User();
//			user1.setName("Pop Ana");
//			user1.setType(UserType.CLIENT);
//			user1.setAge(20);
//			user1.setEmail("anapop@gmail.com");
//			user1.setPassword("AnaPop");
//			user1.setPhone("1111");
//
//			User user2 = new User();
//			user2.setName("Dan Vlad");
//			user2.setType(UserType.CLIENT);
//			user2.setAge(20);
//			user2.setEmail("danvlad@gmail.com");
//			user2.setPassword("DanVlad1234");
//			user2.setPhone("2222");
//
//			userService.saveUser(UserMapper.toCreationDTO(user1));
//			userService.saveUser(UserMapper.toCreationDTO(user2));
//			bookTypeService.saveBookType(BookTypeMapper.toDTO(bookType1));
//			bookTypeService.saveBookType(BookTypeMapper.toDTO(bookType2));
//			bookService.saveBook(BookMapper.toDTO(book1));
//			bookService.saveBook(BookMapper.toDTO(book2));
//
//			List<UserDTO> users = userService.findAll();
//			List<BookDTO> books = bookService.findAll();
//			List<BookTypeDTO> bookTypes = bookTypeService.findAll();
//
//			saleService.makeSale(new SaleCreationDTO(books, 1L));
//			saleService.makeSale(new SaleCreationDTO(books, 1L));
//			List<SaleDTO> sales = saleService.findAll();
//
//			System.out.println("Users:");
//			for(UserDTO u : users)
//				System.out.println(u);
//			System.out.println("Books:");
//			for(BookDTO b : books)
//				System.out.println(b);
//			System.out.println("Book Types:");
//			for(BookTypeDTO t : bookTypes)
//				System.out.println(t);
//			System.out.println("Sales:");
//			for(SaleDTO s : sales)
//				System.out.println(s);
//
//			System.out.println("Find book by title \"IT\": " + bookService.findBookByTitle("IT"));
//			System.out.println("Find book by type \"children\": " + bookService.findBooksByType(BookTypeMapper.toDTO(bookType2)));
//			System.out.println("Find user by email \"danvlad@gmail.com\": " + userService.findUserByEmail("danvlad@gmail.com"));
//
//			userService.deleteUser(userService.findUserByEmail("danvlad@gmail.com"));
//			users = userService.findAll();
//			System.out.println("Users after deleting danvlad@gmail.com:");
//			for(UserDTO u : users)
//				System.out.println(u);
//
//			bookService.addToStock(bookService.findBookByID(2L), 100);
//
//			books = bookService.findAll();
//			System.out.println("Books after a sale was made and 100 of \"The Little Prince\" were added:");
//			for(BookDTO b : books)
//				System.out.println(b);
//		};
//	}

}
