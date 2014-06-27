
import java.util.*;
import java.math.BigDecimal;

public class EuroDrinkDistributor
    extends GenericDistributor<Drink, Euro>
{

    protected EuroDrinkDistributor(Found<Euro> found, Container<Drink> container)
    {
        super(found, container);

        Iterator<Map.Entry<BigDecimal, String>> coins;
        coins = this.getFound().getMoney().getCoins();

        while(coins.hasNext())
        {
            Map.Entry<BigDecimal, String> coin = coins.next();
            this.getFound().setQuantity(coin.getValue(), 50);
        }

        ArrayList<Drink> drinks = new ArrayList<Drink>();
        drinks.add(new Drink("COFFEE", 1, new BigDecimal("0.3"), 10));
        drinks.add(new Drink("COCA", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("PEPSI", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("FANTA", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("SPRITE", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("ORANGINA", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("ICE-TEA", 1, new BigDecimal("0.8"), 33));
        drinks.add(new Drink("CHERRY", 1, new BigDecimal("0.8"), 33));

        for(Drink drink : drinks)
        {
            try
            {
                this.getContainer().setQuantity(drink, 10);
            }
            catch(ExhaustedCapacityException e){}
        }

    }

}

