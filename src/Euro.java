
import java.util.*;
import java.math.BigDecimal;

public class Euro implements Money
{

    private TreeMap<BigDecimal, String> coins;
    private char symbol;

    protected Euro()
    {
        this.symbol = '€';
        this.coins = new TreeMap<BigDecimal, String>();
        this.coins.put(new BigDecimal("0.01"), "1 cent");
        this.coins.put(new BigDecimal("0.02"), "2 cents");
        this.coins.put(new BigDecimal("0.05"), "5 cents");
        this.coins.put(new BigDecimal("0.10"), "10 cents");
        this.coins.put(new BigDecimal("0.20"), "20 cents");
        this.coins.put(new BigDecimal("0.50"), "50 cents");
        this.coins.put(new BigDecimal("1.00"), "1 euro");
        this.coins.put(new BigDecimal("2.00"), "2 euros");
    }

    public char getSymbol()
    {
        return this.symbol;
    }

    public String getCoin(BigDecimal value)
    {
        return this.coins.get(value);
    }

    public Iterator<Map.Entry<BigDecimal, String>> getCoins()
    {
        return this.coins.descendingMap().entrySet().iterator();
    }

}

