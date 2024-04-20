package com.example.Bookstore.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private boolean available;
    private int stock;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //@Cascade(CascadeType.ALL)
    private BookType type;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", stock=" + stock +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
