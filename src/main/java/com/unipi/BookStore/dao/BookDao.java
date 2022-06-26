package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.model.Review;
import com.unipi.BookStore.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    Connection connection = null;
    AuthorDao authorDao;
    public BookDao()
    {
        authorDao = new AuthorDao();
        connection = DbUtil.getConnection();
    }
    public List<Book> getAllBooks()
    {
        List<Book> bookList = new ArrayList<Book>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from books");
            while (rs.next())
            {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setDescription(rs.getString("desc"));
                book.setGenre(rs.getString("genre"));
                book.setSold(rs.getInt("sold"));
                book.setImage(rs.getString("image"));
                book.setPrice(rs.getFloat("price"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }


    public Book getBook(int id)
    {
        Book book = new Book();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("select * from books where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setDescription(rs.getString("desc"));
                book.setGenre(rs.getString("genre"));
                book.setSold(rs.getInt("sold"));
                book.setImage(rs.getString("image"));
                book.setPrice(rs.getFloat("price"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int AddBook(Book book)
    {
        int result = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(" INSERT INTO `bookstore`.`books` (`id`, `name`, `author`, `desc`, `genre`, `sold`, `image`, `price`) VALUES (?, ?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setInt(6,book.getSold());
            preparedStatement.setString(7, book.getImage());
            preparedStatement.setFloat(8,book.getPrice());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public List<Book> search(String key)
    {
        List<Book> searchlist = new ArrayList<Book>();
        List<Book> bookList = getAllBooks();
        for (Book book : bookList)
        {
            if (containsIgnoreCase(book.getName(),key))
            {
                searchlist.add(book);
            }
            else if(containsIgnoreCase(book.getAuthor(),key))
            {
                searchlist.add(book);
            }
            else if(containsIgnoreCase(book.getGenre(),key))
            {
                searchlist.add(book);
            }
        }
        return searchlist;

    }
    public List<String> getAuthors()
    {
        List<String> authors = new ArrayList<String>();
        List<Book> bookList = getAllBooks();
        for (Book book : bookList)
        {
            if (!authors.contains(book.getAuthor()))
            {
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }
    public List<String> getGenres()
    {
        List<String> genres = new ArrayList<String>();
        List<Book> bookList = getAllBooks();
        for (Book book : bookList)
        {
            if(!genres.contains(book.getGenre()))
            {genres.add(book.getGenre());}
        }
        return genres;
    }
    public List<Book> searchGenre(String genre)
    {
        List<Book> bookList = getAllBooks();
        List<Book> searchlist = new ArrayList<Book>();
        for (Book book : bookList)
        {
            if (book.getGenre().equalsIgnoreCase(genre))
            {
                searchlist.add(book);
            }
        }
        return searchlist;
    }
    public List<Book> searchAuthor(String author)
    {
        List<Book> bookList = getAllBooks();
        List<Book> searchlist = new ArrayList<Book>();
        for (Book book : bookList)
        {
            if (book.getAuthor().equalsIgnoreCase(author))
            {
                searchlist.add(book);
            }
        }
        return searchlist;
    }
    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }
    public List<Review> getbookReviews (int id)
    {
        List<Review> reviews = new ArrayList<Review>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from reviews where book_id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Review review = new Review();
                review.setBook_id(id);
                review.setUsername(rs.getString("username"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return reviews;
    }

    public void addReview(Review review)
    {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO reviews (book_id,username,rating,comment) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, review.getBook_id());
            preparedStatement.setString(2, review.getUsername());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getComment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public float getCurrentRate(Book book)
    {
        int rate = 0;
        int c = 0;
        List<Review> reviews = getbookReviews(book.getId());
        for(Review r : reviews)
        {
            rate += r.getRating();
            c += 1;
        }
        if (c>0){return (float)rate/(float)c;}
        return 0;
    }

    public void updateBook(Book book) {
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement("update books set  name=?,  desc =?, genre =?, price =? ,image =? where id=?");
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `bookstore`.`books` SET `name`=?, `desc`=?, `genre`=?, `price`=?, `image`=? WHERE  `id`=?;");

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setFloat(4, book.getPrice());
            preparedStatement.setString(5, book.getImage());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
