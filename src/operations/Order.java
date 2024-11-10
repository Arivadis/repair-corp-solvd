package operations;
import java.time.LocalDateTime;

public abstract class Order {
    protected long id;
    protected LocalDateTime createTime;

    protected Order(long id) {
        this.id = id;
        createTime = LocalDateTime.now();
    }
}
