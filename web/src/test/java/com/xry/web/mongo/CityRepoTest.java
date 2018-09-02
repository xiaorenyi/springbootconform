package com.xry.web.mongo;


import com.xry.repo.mongo.entity.CityDO;
import com.xry.repo.mongo.repo.ggdb.CityRepo;
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
public class CityRepoTest {

    @Autowired
    private CityRepo cityRepo;

    @Test
    public void saveTest(){
        CityDO cityDO = new CityDO();
        cityDO.setName("阳江123");
        cityDO.setSpecialty("阳江十八子123");
        CityDO res = cityRepo.save(cityDO);
        log.info("save city res = {}",res);
    }

}
