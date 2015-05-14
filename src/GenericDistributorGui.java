
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.util.*;
import java.math.BigDecimal;

/**
 * Generic GUI for GenericDistributor
 *
 * @author Thomas Sarboni "max-k@post.com"
 */
@SuppressWarnings ("serial")
public class GenericDistributorGui <P extends Product, M extends Money>
    extends JFrame
{

    private JPanel container;
    private JPanel products_pane;
    private JPanel coins_pane;
    private JPanel admin_pane;
    private JPanel admin_prd_pane;
    private JPanel admin_cns_pane;
    private JButton giveMoneyBack_btn;
    private JButton[] products_btns = new JButton[20];
    private JButton[] coins_btns = new JButton[20];
    private TreeMap<String, JLabel> admin_prd_lbls;
    private TreeMap<String, JLabel> admin_cns_lbls;
    private JLabel alertLabel;
    private JLabel totalLabel;

    private GenericDistributor<P, M> distributor;

    public GenericDistributorGui(GenericDistributor<P, M> distributor)
    {
        this.distributor = distributor;

        // Main Window
        this.setTitle("Drink Distributor GUI");
        this.setSize(370,550);
        this.setLocationRelativeTo(null);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main containers
        container = new JPanel();
        products_pane = new JPanel();
        coins_pane = new JPanel();
        admin_pane = new JPanel();
        admin_prd_pane = new JPanel();
        admin_cns_pane = new JPanel();

        // Containers init
        initContainer();

        // Main container activation
        getContentPane().add(container);

    }

    public void initContainer()
    {
        // Sub-containers preffered sizes
        products_pane.setPreferredSize(new Dimension(216, 296));
        coins_pane.setPreferredSize(new Dimension(136, 296));
        admin_pane.setPreferredSize(new Dimension(358, 239));
        admin_prd_pane.setPreferredSize(new Dimension(212, 229));
        admin_cns_pane.setPreferredSize(new Dimension(133, 229));

        container.setBackground(Color.black);
        admin_pane.setBackground(Color.red);

        // Init products pane
        this.initProductsPane();

        // Init coins pane
        this.initCoinsPane();

        // Init admin pane
        this.initAdminPane(false);

        // Sub-containers layouts
        admin_pane.add(admin_prd_pane, BorderLayout.WEST);
        admin_pane.add(admin_cns_pane, BorderLayout.EAST);
        container.add(products_pane, BorderLayout.WEST);
        container.add(coins_pane, BorderLayout.EAST);
        container.add(admin_pane, BorderLayout.SOUTH);
    }

    public void initProductsPane()
    {
        int i = 0;

        Iterator<Map.Entry<String, P>> products;
        products = distributor.getProducts().entrySet().iterator();

        Dimension btnDimension = new Dimension(190, 15);

        while(products.hasNext())
        {
            Map.Entry<String, P> entry = products.next();
            products_btns[i] = new JButton(entry.getKey());
            products_btns[i].setPreferredSize(btnDimension);
            products_btns[i].addActionListener(new ProductsListener());
            products_pane.add(products_btns[i]);
            i++;
        }


        alertLabel = new JLabel();
        alertLabel.setText("Insert coin.");
        alertLabel.setPreferredSize(btnDimension);
        products_pane.add(alertLabel, BorderLayout.SOUTH);

        giveMoneyBack_btn = new JButton("Give Money Back");
        giveMoneyBack_btn.setPreferredSize(btnDimension);
        giveMoneyBack_btn.addActionListener(new MoneyBackListener());
        products_pane.add(giveMoneyBack_btn, BorderLayout.SOUTH);

    }

    public void initCoinsPane()
    {
        int i = 0;

        Iterator<Map.Entry<BigDecimal, String>> coins;
        coins = distributor.getFound().getMoney().getCoins();

        Dimension btnDimension = new Dimension(110, 15);

        while(coins.hasNext())
        {
            Map.Entry<BigDecimal, String> entry = coins.next();
            coins_btns[i] = new JButton(entry.getKey().toString());
            coins_btns[i].setPreferredSize(btnDimension);
            coins_btns[i].addActionListener(new CoinsListener());
            coins_pane.add(coins_btns[i]);
            i++;
        }

        totalLabel = new JLabel();
        String text = "Sum : " + distributor.getSum().getTotal();
        text += distributor.getSum().getMoney().getSymbol();
        totalLabel.setText(text);
        totalLabel.setPreferredSize(btnDimension);
        coins_pane.add(totalLabel, BorderLayout.SOUTH);

    }

    public void initAdminPane(Boolean refresh)
    {
        JLabel jlabel;
        int quantity;

        if(refresh == false)
        {
            admin_prd_lbls = new TreeMap<String,JLabel>();
            admin_cns_lbls = new TreeMap<String,JLabel>();
        }

        Iterator<Map.Entry<String, P>> products;
        products = distributor.getProducts().entrySet().iterator();

        Iterator<Map.Entry<BigDecimal, String>> coins;
        coins = distributor.getFound().getMoney().getCoins();

        Dimension lDimension = new Dimension(180, 15);
        Dimension rDimension = new Dimension(100, 15);

        while(products.hasNext())
        {
            Map.Entry<String, P> entry = products.next();
            quantity = distributor.getContainer().getQuantity(entry.getValue());
            String text = entry.getKey();
            text += " (" + entry.getValue().getPrice();
            text += distributor.getFound().getMoney().getSymbol() + ")";
            if(refresh == true)
            {
                jlabel = admin_prd_lbls.get(entry.getKey());
                jlabel.setText(text + " : " + quantity);
            }
            else
            {
                jlabel = new JLabel(text + " : " + quantity);
                jlabel.setPreferredSize(lDimension);
                admin_prd_lbls.put(entry.getKey(), jlabel);
                admin_prd_pane.add(admin_prd_lbls.get(entry.getKey()));
            }
        }

        while(coins.hasNext())
        {
            Map.Entry<BigDecimal, String> entry = coins.next();
            quantity = distributor.getFound().getQuantity(entry.getValue());
            if(refresh == true)
            {
                jlabel = admin_cns_lbls.get(entry.getValue());
                jlabel.setText(entry.getValue() + " : " + quantity);
            }
            else
            {
                jlabel = new JLabel(entry.getValue() + " : " + quantity);
                jlabel.setPreferredSize(rDimension);
                admin_cns_lbls.put(entry.getValue(), jlabel);
                admin_cns_pane.add(admin_cns_lbls.get(entry.getValue()));
            }
        }
    }

    public void refreshTotalLabel()
    {
        String text = "Sum : " + distributor.getSum().getTotal();
        text += distributor.getSum().getMoney().getSymbol();
        totalLabel.setText(text);
    }

    class ProductsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String choice = ((JButton)e.getSource()).getText();
            try
            {
                distributor.soldProduct(choice);
                initAdminPane(true);
                refreshTotalLabel();
                alertLabel.setText("Bought : " + choice);
            }
            catch(InvalidProductException exc)
            {
                alertLabel.setText(exc.getMessage());
            }
            catch(NotEnoughMoneyException exc)
            {
                alertLabel.setText(exc.getMessage());
            }
            catch(UnableToGiveMoneyBackException exc)
            {
                alertLabel.setText(exc.getMessage());
            }
    
        }
    
    }

    class CoinsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String choice = ((JButton)e.getSource()).getText();
            try
            {
                distributor.insertCoin(new BigDecimal(choice));
            }
            catch(InvalidCoinException exc)
            {
                alertLabel.setText(exc.getMessage());
            }
            refreshTotalLabel();
        }
    }

    class MoneyBackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            String choice = ((JButton)e.getSource()).getText();
            BigDecimal total = distributor.getSum().getTotal();
            try 
            {   
                distributor.giveMoneyBack(total);
            }   
            catch(UnableToGiveMoneyBackException exc)
            {   
                alertLabel.setText(exc.getMessage());
            }   
            refreshTotalLabel();
            alertLabel.setText("Brought back : " + total);
        }   
     
    }

}
