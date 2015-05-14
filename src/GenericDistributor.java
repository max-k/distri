
import java.util.*;
import java.math.BigDecimal;

/**
 * <b>Generic Distributor</b>
 * <p>
 * Can handle any kind product using any kind of money.
 * Two founds are used to store coins.
 * A container is used to store products.
 * </p>
 * @see Found
 * @see Container
 *
 * @author Thomas Sarboni "max-k@post.com"
 */
public class GenericDistributor<P extends Product, M extends Money>
{

    /**
     * Temporary found used store inserted coins
     */
    private Found<M> sum;

    /**
     * Main found used to store coins
     */
    private Found<M> found;

    /**
     * Main container used to store products
     */
    private Container<P> container;

    /**
     * Constructor of GenericDistributor
     *
     * @param found Main found
     * @param container Main container
     */
    protected GenericDistributor(Found<M> found, Container<P> container)
    {
        int i = 0;
        this.found = found;
        this.sum  = new Found<M>(found.getMoney());;
        this.container = container;
    }

    /**
     * Return temporary found
     * @return Temporary found
     */
    protected Found<M> getSum()
    {
        return this.sum;
    }

    /**
     * Return main found
     * @return Main found
     */
    protected Found<M> getFound()
    {
        return this.found;
    }

    /**
     * Return main container
     * @return Main container
     */
    protected Container<P> getContainer()
    {
        return this.container;
    }

    /**
     * Return a map of sold products sorted by names
     * @return A map of sold products sorted by names
     */
    public TreeMap<String, P> getProducts()
    {
        return this.getContainer().getProducts();
    }

    /**
     * Tries to insert a coin
     * @throws InvalidCoinException If no coin exists with this value
     * @param value Value of inserted coin
     */
    public void insertCoin(BigDecimal value)
        throws InvalidCoinException
    {
        String coin = this.found.getMoney().getCoin(value);
        if(coin == null) throw new InvalidCoinException();
        this.getSum().addCoin(coin);
    }

    /**
     * Tries to sold a product
     * @throws InvalidProductException If product is sold out or doesn't exists
     * @throws NotEnoughMoneyException If not enough money is inserted
     * @throws UnableToGiveMoneyBackException If give money back is impossible
     * @param choice Name of product to sold
     */
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

    /**
     * Tries to give money back
     * <p>
     * Coins from temporary found are eaten is money is given back
     * If toTake == 0 : Money is given back directly from temporary found
     * </p>
     * @throws UnableToGiveMoneyBackException If give money back is impossible
     * @param toTake Price of sold product
     */
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

