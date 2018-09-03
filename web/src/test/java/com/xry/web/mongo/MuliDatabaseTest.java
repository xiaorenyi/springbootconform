package com.xry.web.mongo;


import com.xry.repo.mongo.entity.BookDO;
import com.xry.repo.mongo.entity.CityDO;
import com.xry.repo.mongo.repo.ggdb.CityRepo;
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
public class MuliDatabaseTest {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CityRepo cityRepo;

    @Test
    public void muliDbTest(){

        BookDO bookDO = new BookDO();
        bookDO.setAuthor("蔡叔");
        bookDO.setName("认知突围33");
        bookDO.setPrice(27.3);
        BookDO bookRes = bookRepo.save(bookDO);
        log.info("save book res = {}",bookRes);

        CityDO cityDO = new CityDO();
        cityDO.setName("上海88");
        cityDO.setSpecialty("东方之珠28");
        CityDO cityRes = cityRepo.save(cityDO);
        log.info("save city res = {}",cityRes);
    }
}
