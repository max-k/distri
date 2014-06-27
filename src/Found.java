
import java.util.*;
import java.math.BigDecimal;

public class Found<M extends Money> implements Cloneable
{

    private M money;

    private final HashMap<String, Integer> coins;

    protected Found(M money)
    {
        this.money = money;
        this.coins = new HashMap<String, Integer>();

        Iterator<Map.Entry<BigDecimal, String>> items = this.money.getCoins();

        while(items.hasNext())
        {
            Map.Entry<BigDecimal, String> entry = items.next();
            this.coins.put(entry.getValue(), 0);
        }
    }

    public M getMoney()
    {
        return this.money;
    }

    public void setQuantity(String coin, Integer quantity)
    {
        this.coins.put(coin, quantity);
    }

    public Integer getQuantity(String coin)
    {
        if(this.coins.get(coin) != null)
            return this.coins.get(coin);
        return 0;
    }

    public void addCoin(String coin)
    {
        this.setQuantity(coin, this.getQuantity(coin)+1);
    }

    public void addCoins(String coin, Integer quantity)
    {
        this.setQuantity(coin, this.getQuantity(coin)+quantity);
    }

    public void removeCoin(String coin)
    {
        this.setQuantity(coin, this.getQuantity(coin)-1);
    }

    public Iterator<Map.Entry<String, Integer>> getItems()
    {
        return this.coins.entrySet().iterator();
    }

    public BigDecimal getTotal()
    {
        BigDecimal quantity;
        BigDecimal value;
        BigDecimal total = new BigDecimal("0");

        Iterator<Map.Entry<BigDecimal, String>> items;
        items = this.money.getCoins();

        while(items.hasNext())
        {
            Map.Entry<BigDecimal, String> entry = items.next();
            value = entry.getKey();
            quantity = new BigDecimal(this.getQuantity(entry.getValue()));
            total = total.add(quantity.multiply(value));
        }
        return total;
    }

    public void eatMoney(Found<M> src)
    {
        Iterator<Map.Entry<BigDecimal, String>> coins;
        coins = this.getMoney().getCoins();

        while(coins.hasNext())
        {
            Map.Entry<BigDecimal, String> coin = coins.next();
            Integer srcQuantity = src.getQuantity(coin.getValue());
            Integer targetQuantity = this.getQuantity(coin.getValue());
            src.setQuantity(coin.getValue(), 0);
            this.setQuantity(coin.getValue(), srcQuantity + targetQuantity);
        }
    }

    public void giveMoneyBack(BigDecimal toTake)
        throws UnableToGiveMoneyBackException
    {
        Iterator<Map.Entry<BigDecimal, String>> coins;
        coins = this.getMoney().getCoins();

        while(coins.hasNext())

        {
            Map.Entry<BigDecimal, String> coin = coins.next();
            Integer quantity;

            while((quantity = this.getQuantity(coin.getValue())) > 0)
            {
                if(toTake.compareTo(new BigDecimal("0.00")) < 0) return;
                if(toTake.compareTo(coin.getKey()) < 0) break;
                toTake = toTake.subtract(coin.getKey());
                this.removeCoin(coin.getValue());
            }
            System.out.println(toTake.toString());
            if(toTake.compareTo(new BigDecimal("0")) > 0)
                throw new UnableToGiveMoneyBackException();
        }
    }

}

