package operations;

import staff.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Order {

    // we do not change ID
    protected final long ID;
    protected LocalDateTime createTime;
    protected boolean complete;
    protected Customer customer;

    protected Order(long id) {
        this.ID = id;
        createTime = LocalDateTime.now();
    }

    protected long getID() {
        return ID;
    }

    protected LocalDateTime getCreateTime() {
        return createTime;
    }

    protected Customer getCustomer() {
        return customer;
    }

    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }

    protected abstract void setComplete();

    protected abstract void setIncomplete();

    @Override
    public int hashCode() {
        return Objects.hash(ID, createTime);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return Objects.equals(ID, order.ID) &&
                Objects.equals(createTime, order.createTime);
    }
}
