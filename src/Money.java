
import java.util.*;
import java.math.BigDecimal;

public interface Money
{

    public char getSymbol();

    public String getCoin(BigDecimal value);

    public Iterator<Map.Entry<BigDecimal, String>> getCoins();

}

