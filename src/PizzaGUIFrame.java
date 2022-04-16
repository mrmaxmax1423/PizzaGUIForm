import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class PizzaGUIFrame extends JFrame{
    JPanel mainPanel;

    JPanel optionPanel;
    JRadioButton thinRB;
    JRadioButton regularRB;
    JRadioButton deepDishRB;
    JComboBox sizeSelectCB;

    JPanel toppingSelectPanel;
    JCheckBox pepperoniChB;
    JCheckBox sausageChB;
    JCheckBox baconChB;
    JCheckBox chickenChB;
    JCheckBox peppersChB;
    JCheckBox olivesChB;
    JCheckBox extraCheeseChB;
    JCheckBox anchoviesChB;

    JPanel orderDisplayPanel;
    JTextArea orderTextArea;

    JPanel controlPanel;
    JButton orderButton;
    JButton clearButton;
    JButton quitButton;

    boolean orderPlaced = false;
    public PizzaGUIFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createOptionPanel();
        mainPanel.add(optionPanel, BorderLayout.NORTH);

        createOrderDisplayPanel();
        mainPanel.add(orderDisplayPanel, BorderLayout.CENTER);

        createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createOptionPanel()
    {
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(3,4));

        Border titledBorder = BorderFactory.createTitledBorder("Options");
        optionPanel.setBorder(titledBorder);

        thinRB = new JRadioButton("Thin");
        regularRB = new JRadioButton("Regular");
        deepDishRB = new JRadioButton("Deep-Dish");

        optionPanel.add(thinRB);
        optionPanel.add(regularRB);
        optionPanel.add(deepDishRB);

        regularRB.setSelected(true);

        ButtonGroup crustChoicesGroup = new ButtonGroup();
        crustChoicesGroup.add(thinRB);
        crustChoicesGroup.add(regularRB);
        crustChoicesGroup.add(deepDishRB);

        sizeSelectCB = new JComboBox();
        sizeSelectCB.addItem("Small    ($8.00)");
        sizeSelectCB.addItem("Medium   ($12.00)");
        sizeSelectCB.addItem("Large    ($16.00)");
        sizeSelectCB.addItem("Super    ($20.00)");

        optionPanel.add(sizeSelectCB);

        pepperoniChB = new JCheckBox("Pepperoni");
        sausageChB = new JCheckBox("Sausage");
        baconChB = new JCheckBox("Bacon");
        chickenChB = new JCheckBox("Chicken");
        peppersChB = new JCheckBox("Peppers");
        olivesChB = new JCheckBox("Olives");
        extraCheeseChB = new JCheckBox("Extra Cheese");
        anchoviesChB = new JCheckBox("Anchovies");

        optionPanel.add(pepperoniChB);
        optionPanel.add(sausageChB);
        optionPanel.add(baconChB);
        optionPanel.add(chickenChB);
        optionPanel.add(peppersChB);
        optionPanel.add(olivesChB);
        optionPanel.add(extraCheeseChB);
        optionPanel.add(anchoviesChB);
    }
    
    private void createOrderDisplayPanel()
    {
        orderDisplayPanel = new JPanel();
        orderTextArea = new JTextArea(9, 30);

        Border titledBorder = BorderFactory.createTitledBorder("Order");

        orderDisplayPanel.setBorder(titledBorder);
        orderDisplayPanel.add(orderTextArea);
    }

    private void createControlPanel()
    {
        Border titledBorder = BorderFactory.createTitledBorder("Options");
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,4));

        orderButton = new JButton("Order");
        orderButton.addActionListener((ActionEvent ae) -> placeOrder());
        clearButton = new JButton("Clear");
        clearButton.addActionListener((ActionEvent ae) -> clearOrder());
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPanel.add(orderButton);
        controlPanel.add(clearButton);
        controlPanel.add(quitButton);
        controlPanel.setBorder(titledBorder);
    }


    private double subTotal;
    public void placeOrder()
    {
        if(!orderPlaced)
        {
            addSeparators();
            checkCrustAndSize();
            checkToppings();
            calculatePrice();
            addSeparators();
            orderPlaced = true;
        }
    }

    public void clearOrder()
    {
        pepperoniChB.setSelected(false);
        sausageChB.setSelected(false);
        baconChB.setSelected(false);
        chickenChB.setSelected(false);
        peppersChB.setSelected(false);
        olivesChB.setSelected(false);
        extraCheeseChB.setSelected(false);
        anchoviesChB.setSelected(false);
        regularRB.setSelected(true);
        sizeSelectCB.setSelectedIndex(0);

        orderTextArea.setText("");

        subTotal = 0;
        orderPlaced = false;
    }

    public void addSeparators()
    {
        orderTextArea.append("================================================================================ \n");
    }

    public void checkToppings()
    {
        ArrayList<JCheckBox> toppingButtons= new ArrayList<JCheckBox>(Arrays.asList(pepperoniChB,sausageChB,baconChB, chickenChB, peppersChB, olivesChB, extraCheeseChB, anchoviesChB));
        ArrayList<String> selectedToppings = new ArrayList<>();
        for(int i = 0; i < 8; i++)
        {
            if(toppingButtons.get(i).isSelected()) //check what buttons are selected and store selected ingredient names in an ArrayList
            {
                selectedToppings.add(toppingButtons.get(i).getText());
            }

        }
        orderTextArea.append("Toppings:  ");
        for(int l = 0 ; l < selectedToppings.size() ; l++)
        {
            subTotal += 1.0;
            if(l < selectedToppings.size() - 1)
                orderTextArea.append(selectedToppings.get(l) + ", ");
            else
                orderTextArea.append(selectedToppings.get(l));
        }
        orderTextArea.append("    $" + selectedToppings.size() + ".00 \n");

    }

    public void calculatePrice()
    {
        double tax = subTotal * .07;
        double totalPrice = subTotal + tax;

        orderTextArea.append("Sub Total:      $" + String.format("%.2f",subTotal) + "\n");
        orderTextArea.append("Tax:                 $" + String.format("%.2f",tax) +"\n");
        orderTextArea.append("-------------------------------------------------------------------------------------------------------------------------------------------- \n");
        orderTextArea.append("Total Price:    $" + String.format("%.2f",totalPrice) + "\n");
    }

    public void checkCrustAndSize()
    {
        String chosenCrust = "";
        if(thinRB.isSelected())
        {
            chosenCrust = "Thin";
        }
        if(regularRB.isSelected())
        {
            chosenCrust = "Regular";
        }
        if(deepDishRB.isSelected())
        {
            chosenCrust = "Deep-Dish";
        }

        String chosenSize = sizeSelectCB.getSelectedItem().toString();
        if(chosenSize == "Small    ($8.00)"){
            orderTextArea.append("Size, Style:   Small, "+ chosenCrust + "     $8.00 \n");
            subTotal += 8.0;
        }
        if(chosenSize == "Medium   ($12.00)")
        {
            orderTextArea.append("Size, Style:   Medium, "+ chosenCrust + "     $12.00 \n");
            subTotal += 12.0;
        }
        if(chosenSize == "Large    ($16.00)")
        {
            orderTextArea.append("Size, Style:   Large, " + chosenCrust + "     $16.00 \n");
            subTotal += 16.0;
        }
        if(chosenSize == "Super    ($20.00)")
        {
            orderTextArea.append("Size, Style:   Super, " + chosenCrust + "     $20.00 \n");
            subTotal += 20.0;
        }
    }
}
