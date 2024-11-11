package operations;

import staff.Customer;

import java.time.LocalDateTime;

public abstract class Order {

    protected long id;
    protected LocalDateTime createTime;
    protected boolean complete;
    protected Customer customer;

    protected Order(long id) {
        this.id = id;
        createTime = LocalDateTime.now();
    }

    protected abstract void setComplete();
}
