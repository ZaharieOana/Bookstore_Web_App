package com.example.Bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

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
    private DateTimeLiteralExpression.DateTime date;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Book> books;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", sum=" + sum +
                ", date=" + date +
                ", user=" + user +
                ", books=" + books +
                '}';
    }
}
