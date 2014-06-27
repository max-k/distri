
import java.math.BigDecimal;

public class Product implements Comparable<Product>
{

    private Integer size;
    private String name;
    private BigDecimal price;

    protected Product(String name, Integer size, BigDecimal price)
    {
        this.size = size;
        this.name = name;
        this.price = price;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int compareTo(Product p)
        throws NullPointerException, ClassCastException
    {
        return this.getName().compareTo(p.getName());
    }

}

