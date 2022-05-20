package com.spring.john.bootstrap;

import com.spring.john.model.Author;
import com.spring.john.model.Book;
import com.spring.john.model.Publisher;
import com.spring.john.repositories.AuthorRepository;
import com.spring.john.repositories.BookRepository;
import com.spring.john.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // == Publisher ==
        Publisher publisher = new Publisher();
        publisher.setName("James Benton");
        publisher.setAddressLine1("7801 Inky Binky Motto Dr");
        publisher.setCity("Mo");
        publisher.setState("Texans");
        publisher.setZip("78072");
        publisherRepository.save(publisher);
        System.out.println("Started in BootStrap");
        System.out.println("Number of publishers: " + publisherRepository.count());

        // == Author 1 ==
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123455");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        // == Author 2 ==
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "65765347675");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publisher books: " + publisher.getBooks().size());
    }
}
