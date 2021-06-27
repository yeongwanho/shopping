package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired OrderService orderService;

    @Autowired
    OrderRepository repository;

    @Test
    public void 상품주문() throws  Exception{
        Member member=new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        int orderCount=2;

        Long order = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder = repository.findOne(order);

        Assertions.assertEquals( OrderStatus.ORDER,getOrder.getStatus());
        Assertions.assertEquals(1,getOrder.getOrderItems().size());
        Assertions.assertEquals(10000*orderCount,getOrder.getTotalPrice());


    }

    @Test
    public void 주문취소() throws  Exception{

    }
    @Test
    public void 상품주문_재고수량초과() throws  Exception{

    }


}