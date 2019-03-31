package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    /**
     * 添加用户，并返回包括数据库时间戳的用户对象
     * @param user
     * @return
     */
    public User addUser(User user){
        em.persist(user);
        return user;
    }
    /**
     * 添加地址，并指定地址对应的用户
     * @param address
     * @param uid
     * @return
     */
    public Address addAddress(Address address, int uid) {
        User u=em.find(User.class,uid);
        address.setUser(u);
        em.persist(address);
        return address;
    }

    /**
     * 更新指定ID用户的姓名
     * @param uid
     * @param newName
     * @return
     */
    public User updateUser(int uid, String newName) {
        User u=em.find(User.class,uid);
        u.setName(newName);
        return u;
    }

    /**
     * 尝试使用merge()，以及find()2种方法分别实现
     * 更新指定地址为指定用户
     * @param aid
     * @param uid
     * @return
     */
    public Address updateAddress(int aid, int uid) {
        User u=em.find(User.class, uid);
        Address add=em.find(Address.class, aid);
        add.setUser(u);
        return add;
       /* User u1=new User();
        u1.setId(uid);
        User u2=em.merge(u1);
        em.refresh(u2);
        Address add1=new Address();
        add1.setId(aid);
        Address add2=em.merge(add1);
        em.refresh(add2);
        add2.setUser(u2);
        return add2;*/
    }

    /**
     * 返回指定用户的全部地址，没有返回空集合，而非null
     * @param uid
     * @return
     */
    public List<Address> listAddresses(int uid) {
        /*String jpql="SELECT u.address FROM User u WHERE u.id=?1";
        Query query=em.createQuery(jpql);
        query.setParameter(1, uid);
        List<Address> addresses=query.getResultList();*/
       // List.of(addresses);
        List<Address> list= em.find(User.class, uid).getAddresses();
        for(Address address:list){
            System.out.println("address:"+address.getDetial());
        }
        List.of(list);
        return list;
        //return addresses;
    }

    public void removeAddress(int aid) {
        Address a=em.find(Address.class, aid);
        em.remove(a);
    }

    /**
     * 删除用户，设置级联操作或手动删除相关地址
     * @param uid
     */
    public void remaveUser(int uid) {
        User u=em.find(User.class, uid);
        em.remove(u);
    }
}

