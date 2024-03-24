package com.example.Bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int sum;
    private LocalDate date;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Book> books;

    @Override
    public String toString() {
//        StringBuilder booksString = new StringBuilder("\n");
//        for(Book b : books)
//            booksString.append(b.toString()).append("\n");
        return "Sale{" +
                "id=" + id.toString() +
                ", sum=" + sum +
                ", date=" + date.toString() +
                ", user=" + user.toString() +
//                ", books=" + booksString +
                '}';
    }

    public void setSum() {
        sum = 0;
        for(Book b : books)
            sum += b.getPrice();
    }
}
