import com.example.generated.library.tables.Author;
import com.example.generated.library.tables.records.AuthorRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class DBTest {
    private final String DB_URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";
    private Connection connection;

    @BeforeTest
    public void setup() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    @AfterTest
    public void cleanup() throws SQLException {
        connection.rollback(); // rollback any uncommitted changes
        connection.close();
    }

    @Test
    public void verifyDataInsertion() {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        AuthorRecord author = new AuthorRecord();
        author.setFirstName("Mr");
        author.setLastName("Test");
        create.insertInto(Author.AUTHOR)
                .set(author)
                .execute();
        Result<AuthorRecord> result = create.selectFrom(Author.AUTHOR)
                .where(Author.AUTHOR.FIRST_NAME.eq("Mr"))
                .and(Author.AUTHOR.LAST_NAME.eq("Test"))
                .fetch();
        assertEquals(1, result.size());
        assertEquals(author.getFirstName(), result.get(0).getFirstName());
        assertEquals(author.getLastName(), result.get(0).getLastName());
    }

    @Test
    public void testRetrieveData() {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        AuthorRecord author = new AuthorRecord();
        author.setFirstName("John");
        author.setLastName("Doe");
        create.insertInto(Author.AUTHOR)
                .set(author)
                .execute();
        Result<AuthorRecord> result = create.selectFrom(Author.AUTHOR)
                .where(Author.AUTHOR.FIRST_NAME.eq("John")
                        .and(Author.AUTHOR.LAST_NAME.eq("Doe")))
                .fetch();

//        for (AuthorRecord authorRecord : result) {
//            System.out.println(authorRecord.getFirstName() + " " + authorRecord.getLastName());
//        }
//        assertEquals(1, result.size());
        assertEquals(author.getFirstName(), result.get(0).getFirstName());
        assertEquals(author.getLastName(), result.get(0).getLastName());
    }

    @Test
    public void testModifyData() {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        AuthorRecord author = new AuthorRecord();
        author.setFirstName("John");
        author.setLastName("Doe");
        create.insertInto(Author.AUTHOR)
                .set(author)
                .execute();
        author.setLastName("Smith");
        create.update(Author.AUTHOR)
                .set(Author.AUTHOR.LAST_NAME, author.getLastName())
                .where(Author.AUTHOR.FIRST_NAME.eq("John"))
                .execute();
        Result<AuthorRecord> result = create.selectFrom(Author.AUTHOR)
                .where(Author.AUTHOR.FIRST_NAME.eq("John"))
                .fetch();
        assertEquals(1, result.size());
        assertEquals(author.getFirstName(), result.get(0).getFirstName());
        assertEquals(author.getLastName(), result.get(0).getLastName());
    }

    @Test
    public void testDeleteData() {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        AuthorRecord author = new AuthorRecord();
        author.setFirstName("John");
        author.setLastName("Doe");
        create.insertInto(Author.AUTHOR)
                .set(author)
                .execute();
        create.deleteFrom(Author.AUTHOR)
                .where(Author.AUTHOR.FIRST_NAME.eq("John"))
                .and(Author.AUTHOR.LAST_NAME.eq("Doe"))
                .execute();
        Result<AuthorRecord> result = create.selectFrom(Author.AUTHOR)
                .where(Author.AUTHOR.FIRST_NAME.eq("John"))
                .and(Author.AUTHOR.LAST_NAME.eq("Doe"))
                .fetch();
        assertEquals(0, result.size());
    }
}