
import java.util.*;
import java.util.Iterator;

public class Container<P extends Product>
{

    private TreeMap<String,P> products;
    private TreeMap<P,Integer> quantities;

    private Integer capacity = 20;

    protected Container()
    {
        this.products = new TreeMap<String,P>();
        this.quantities = new TreeMap<P,Integer>();
    }

    public void setQuantity(P product, Integer quantity)
        throws ExhaustedCapacityException
    {
        if(this.getQuantity(product) == 0)
        {

            if(this.capacity-product.getSize() < 0)
                throw new ExhaustedCapacityException();

            this.capacity -= product.getSize();
        }

        this.products.put(product.getName(), product);
        this.quantities.put(product, quantity);
    }

    public TreeMap<String,P> getProducts()
    {
        return this.products;
    }

    public Integer getQuantity(P product)
    {
        if(this.quantities.get(product) != null)
            return this.quantities.get(product);
        return 0;
    }

    public void addProduct(P product, Integer count)
    {
        this.products.put(product.getName(), product);
        this.quantities.put(product, this.getQuantity(product)+count);
    }

    public void removeProduct(P product)
    {
        this.quantities.put(product, this.getQuantity(product)-1);
    }

    public Iterator<Map.Entry<P, Integer>> getItems()
    {
        return this.quantities.entrySet().iterator();
    }

}

