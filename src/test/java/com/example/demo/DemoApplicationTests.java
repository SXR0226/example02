package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserRepository ur;
    Logger logger= LoggerFactory.getLogger(DemoApplicationTests.class);
    @Test
    public void contextLoads() {
        User u1=new User("abc");
        ur.addUser(u1);
        User u2=new User("cde");
        User u3=new User("fgh");
        ur.addUser(u2);
        ur.addUser(u3);
    }
    @Test
    public void addAddressTest(){
        /*Address ad1=new Address("123");*/
        Address ad2=new Address("456");
        ur.addAddress(ad2, 1);
        /*Address ad3=new Address("789");
        Address ad4=new Address("740");
        Address ad5=new Address("852");
        ur.addAddress(ad1, 1);

        ur.addAddress(ad3, 2);
        ur.addAddress(ad4, 3);
        ur.addAddress(ad5, 4);*/
    }
    @Test
    public void updateUserTest(){
        ur.updateUser(2, "bbb");
    }
    @Test
    public void upDateAddressTest(){
        ur.updateAddress(4, 3);
    }
    @Test
    public void  listAddressTest(){
        ur.listAddresses(1).forEach(a->{
            logger.debug(a.getDetial());
        });
    }
    @Test
    public void removeAddressTest(){
        ur.removeAddress(2);
    }
    @Test
    public void removeUserTest(){
        ur.remaveUser(4);
    }
}
