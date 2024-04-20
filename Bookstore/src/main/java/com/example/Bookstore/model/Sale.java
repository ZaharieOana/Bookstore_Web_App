package com.example.Bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double sum;
    private LocalDate date;
    @ManyToOne
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    @Override
    public String toString() {
        StringBuilder booksString = new StringBuilder("[ ");
        for(Book b : books)
            booksString.append(b.getTitle()).append(", ");
        booksString.append("]");
        return "Sale{" +
                "id=" + id.toString() +
                ", sum=" + sum +
                ", date=" + date.toString() +
                ", user=" + user.toString() +
                ", books=" + booksString +
                '}';
    }

    public void setSum() {
        sum = 0;
        for(Book b : books)
            sum += b.getPrice();
    }
}
