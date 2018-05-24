package bookStore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {



    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    Book book;
    BookDTO bookDTO;

    @Before
    public void setUp() {

        bookService = new BookServiceImpl(bookRepository);

        List<Book> books = new ArrayList<>();

        book = new Book("OCTAV", "ALO", "88888", "DRAMA", 888l,88.8);
        books.add(book);

        when(bookRepository.findAllByGenre("DRAMA")).thenReturn(books);
        when(bookRepository.findAll()).thenReturn(books);

        when(bookRepository.findAllByTitle("ALO")).thenReturn(books);
        when(bookRepository.findAllByAuthor("OCTAV")).thenReturn(books);

    }

    @Test
    public void getAll() {
     //   List<Book > books = bookService.getAll();
        assertTrue(bookService.getAll().size() == 1);
    }

    @Test
    public void create() {
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());

        BookDTO bookDTO = new BookDTO();
        assertThat(bookService.create(bookDTO), is(notNullValue()));
     }



    @Test
    public void findAllByGenre() {
      //  List<Book > books = bookService.findAllByGenre("DRAMA");
        assertTrue(bookService.findAllByGenre("DRAMA").size() == 1);
    }

    @Test
    public void findAllByAuthor() {
       // List<Book > books = bookService.findAllByAuthor("OCTAV");
        assertTrue(bookService.findAllByAuthor("OCTAV").size() == 1);
    }

    @Test
    public void findAllByTitle() {

       // List<Book > books = bookService.findAllByTitle("ALO");
        assertTrue(bookService.findAllByTitle("ALO").size() == 1);
    }

}