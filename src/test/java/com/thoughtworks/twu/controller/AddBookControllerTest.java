package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.view.RedirectView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class AddBookControllerTest {
    private BookService bookService;
    private AddBookController addBookController;


    @Before
    public void setUp() {
        bookService = mock(BookService.class);
        addBookController = new AddBookController(bookService);
    }


    @Test
    public void shouldAddBookToDBIfNotInSystem(){
        if (true) {
            throw new EmptyStackException();
        }
        Book book = arrangeBookID();
        when(bookService.isBookFromResultsListInDB(book)).thenReturn(false);
        addBookController.addBook(book.getAuthor(), book.getTitle(), book.getImage(), book.getDescription(), book.getISBN10(), book.getISBN13());
        verify(bookService).insertBook(book);
    }


    @Test
    public void shouldNotAddBookToDBIfItIsInSystem(){
        Book book = arrangeBookID();
        when(bookService.isBookFromResultsListInDB(book)).thenReturn(true);
        addBookController.addBook(book.getAuthor(), book.getTitle(), book.getImage(), book.getDescription(), book.getISBN10(), book.getISBN13());
        verify(bookService,times(0)).insertBook(book);
    }

    @Test
    public void shouldRedirectToViewBook(){
        Book book = arrangeBookID();
        RedirectView redirectView = addBookController.addBook(book.getAuthor(), book.getTitle(), book.getImage(), book.getDescription(), book.getISBN10(), book.getISBN13());
        assertThat(redirectView.getUrl(), is("/viewbook?bookId=" + book.getId()));
    }

    private Book getBook() {
        String title = "A Present Book";
        String author = "author";
        String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
        String description = "This is a book about life, I love it very much!!";
        String ISBN10 = "0156027321";
        String ISBN13 = "978-0156027328";
        return new Book(author, title, image, description, ISBN10, ISBN13);
    }

    private Book arrangeBookID() {
        Book book = getBook();
        book.setId(3);
        when(bookService.updateBook(book)).thenReturn(book);
        return book;
    }

}
