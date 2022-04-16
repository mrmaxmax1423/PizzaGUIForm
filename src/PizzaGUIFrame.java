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

    JPanel orderDisplayPanel;
    JTextArea orderTextArea;

    JPanel controlPanel;
    JButton orderButton;
    JButton clearButton;
    JButton quitButton;

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
        optionPanel.setLayout(new GridLayout(4,3));

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

        optionPanel.add(pepperoniChB);
        optionPanel.add(sausageChB);
        optionPanel.add(baconChB);
        optionPanel.add(chickenChB);
        optionPanel.add(peppersChB);
        optionPanel.add(olivesChB);



    }

    private void createToppingSelectPanel()
    {

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
        quitButton = new JButton("Quit");

        controlPanel.add(orderButton);
        controlPanel.add(clearButton);
        controlPanel.add(quitButton);
        controlPanel.setBorder(titledBorder);
    }


    private double subTotal;
    public void placeOrder()
    {
        checkCrustAndSize();
        checkToppings();
        calculatePrice();
    }

    public void checkToppings()
    {
        ArrayList<JCheckBox> toppingButtons= new ArrayList<JCheckBox>(Arrays.asList(pepperoniChB,sausageChB,baconChB, chickenChB, peppersChB, olivesChB));
        ArrayList<String> selectedToppings = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            if(toppingButtons.get(i).isSelected()) //check what buttons are selected and store selected ingredient names in an ArrayList
            {
                selectedToppings.add(toppingButtons.get(i).getText());
            }

        }
        orderTextArea.append("Toppings:   ");
        for(int l = 0 ; l < selectedToppings.size() ; l++)
        {
            subTotal += 1.0;
            if(l < selectedToppings.size() - 1)
                orderTextArea.append(selectedToppings.get(l) + ", ");
            else
                orderTextArea.append(selectedToppings.get(l) + "\n");
        }

    }

    public void calculatePrice()
    {
        double tax = subTotal * .07;
        double totalPrice = subTotal + tax;

        orderTextArea.append("Sub Total: " + String.format("%.2f",subTotal) + "\n");
        orderTextArea.append("Tax:            " + String.format("%.2f",tax) +"\n");
        orderTextArea.append("Total Price:    " + String.format("%.2f",totalPrice) + "\n");
    }

    public void checkCrustAndSize()
    {
        String chosenSize = sizeSelectCB.getSelectedItem().toString();
        if(chosenSize == "Small    ($8.00)"){
            orderTextArea.append("Size:           Small ($8.00)\n");
            subTotal += 8.0;
        }
        if(chosenSize == "Medium   ($12.00)")
        {
            orderTextArea.append("Size:           Medium ($12.00)\n");
            subTotal += 12.0;
        }
        if(chosenSize == "Large    ($16.00)")
        {
            orderTextArea.append("Size:           Large ($16.00)\n");
            subTotal += 16.0;
        }
        if(chosenSize == "Super    ($20.00)")
        {
            orderTextArea.append("Size:           Super ($20.00)\n");
            subTotal += 20.0;
        }
    }
}
