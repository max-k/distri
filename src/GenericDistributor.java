
import java.util.*;
import java.math.BigDecimal;

public class GenericDistributor<P extends Product, M extends Money>
{

    private Found<M> sum;
    private Found<M> found;
    private Container<P> container;

    protected GenericDistributor(Found<M> found, Container<P> container)
    {
        int i = 0;
        this.found = found;
        this.sum  = new Found<M>(found.getMoney());;
        this.container = container;
    }

    protected Found<M> getSum()
    {
        return this.sum;
    }

    protected Found<M> getFound()
    {
        return this.found;
    }

    protected Container<P> getContainer()
    {
        return this.container;
    }

    public TreeMap<String, P> getProducts()
    {
        return this.getContainer().getProducts();
    }

    public void insertCoin(BigDecimal value)
        throws InvalidCoinException
    {
        String coin = this.found.getMoney().getCoin(value);
        if(coin == null) throw new InvalidCoinException();
        this.getSum().addCoin(coin);
    }

    public void soldProduct(String choice)
        throws InvalidProductException,
               NotEnoughMoneyException,
               UnableToGiveMoneyBackException
    {
        P product = this.getProducts().get(choice);

        if(this.container.getQuantity(product) == 0)
            throw new InvalidProductException();

        if(product.getPrice().compareTo(sum.getTotal()) == 1)
            throw new NotEnoughMoneyException();

        this.container.removeProduct(product);
        this.giveMoneyBack(product.getPrice());
    }

    public void giveMoneyBack(BigDecimal toTake)
        throws UnableToGiveMoneyBackException
    {
        if(toTake.compareTo(new BigDecimal("0")) > 0)
        {
            this.getFound().eatMoney(this.getSum());
            this.getFound().giveMoneyBack(toTake);
            return;
        }
        else
            this.getSum().giveMoneyBack(toTake);
    }

}

