
import java.math.BigDecimal;

public class Drink extends Product
{
    private Integer capacity;

    protected Drink(String name, Integer size, BigDecimal price, Integer cap)
    {
        super(name, size, price);
        this.capacity = cap;
    }

    public Integer getCapacity()
    {
        return this.capacity;
    }

}

