package com.xry.web.mongo;

import com.xry.repo.mongo.entity.BookDO;
import com.xry.repo.mongo.repo.mmdb.BookRepo;
import com.xry.web.WebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@Slf4j
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    public void saveTest() {
        BookDO bookDO = new BookDO();
        bookDO.setAuthor("周树明13");
        bookDO.setName("java虚拟机13");
        bookDO.setPrice(29.8);

        BookDO res = bookRepo.save(bookDO);
        log.info("save book res = {}", res);
    }
}
