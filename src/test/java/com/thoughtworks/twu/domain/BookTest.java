package com.thoughtworks.twu.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

//Ensures book domain satisfy client's requirement
public class BookTest {

    private Book book;


    @Test
    public void shouldUnderstandBook() {
        book = new Book("author", "title", "image-src", "description", "isbn10", "isbn13");

        assertThat(book.getAuthor(), is("author"));
        assertThat(book.getTitle(), is("title"));
        assertThat(book.getImage(), is("image-src"));
        assertThat(book.getDescription(), is("description"));
        assertThat(book.getISBN10(), is("isbn10"));
        assertThat(book.getISBN13(), is("isbn13"));
        assertThat(book.getRecommendCount(), is(0));
    }

    @Test
    public void shouldAssignDefaultImageWhenBookHasNoImage() throws Exception {
        book = new Book("author", "title", "", "description", "isbn10", "isbn13");
        assertThat(book.getImage(), is(Book.DEFAULT_IMAGE_SRC));
    }

    @Test
    public void shouldNotAssignDefaultImageWhenBookImageIsAvailable() throws Exception {
        book = new Book("author", "title", "image-src", "description", "isbn10", "isbn13");
        assertThat(book.getImage(), is(not(Book.DEFAULT_IMAGE_SRC)));
    }

    @Test
    public void shouldAssignDefaultDescriptionWhenBookHasNoDescription() throws Exception {
        book = new Book("author", "title", "image-src", "  ", "isbn10", "isbn13");
        assertThat(book.getDescription(), is(Book.DEFAULT_DESCRIPTION));
    }

    @Test
    public void shouldNotAssignDefaultDescriptionWhenBookDescriptionIsAvailable() throws Exception {
        book = new Book("author", "title", "image-src", "description", "isbn10", "isbn13");
        assertThat(book.getDescription(), is(Book.DEFAULT_DESCRIPTION));
    }
}
