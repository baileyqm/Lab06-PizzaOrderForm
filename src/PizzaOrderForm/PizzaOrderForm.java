package PizzaOrderForm;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class PizzaOrderForm extends JFrame {

    JPanel mainPnl = new JPanel();
    JPanel crustPnl = new JPanel();
    JPanel sizePnl = new JPanel();
    JPanel crustSizeComboPnl = new JPanel();
    JPanel toppingsPnl = new JPanel();
    JPanel receiptPnl = new JPanel();
    JPanel controlPnl = new JPanel();

    ButtonGroup crustGroup;
    JRadioButton thinBtn, regularBtn, deepBtn;

    JCheckBox eyeballOlivesCB, tentacleCalamariCB, gummyWormsCB, blackGarlicCB, bloodyMarinaraCB,cobwebCheeseCB;
    JScrollPane scroller;
    JTextArea receiptArea;

    JButton quitBtn, orderBtn,clearBtn;
    
    JComboBox sizeComboBox;

    private double totalCost = 0.00;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public PizzaOrderForm(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setTitle("Monster's Pizzeria Order Form");
        add(mainPnl);

        mainPnl.setLayout(new GridLayout(4,1));


        createCrustPanel();
        createSizePanel();
        crustSizeComboPnl.setLayout(new GridLayout(1,2));
        crustSizeComboPnl.add(sizePnl);
        crustSizeComboPnl.add(crustPnl);
        mainPnl.add(crustSizeComboPnl);

        createToppingsPanel();
        mainPnl.add(toppingsPnl);

        createReceiptPanel();
        mainPnl.add(receiptPnl);

        createControlPanel();
        mainPnl.add(controlPnl);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(.5*screenSize.width),(int)(screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void createCrustPanel(){
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust"));
        crustPnl.setLayout(new GridLayout(1,5));
        crustPnl.add(new JLabel("\tCrust Style:"));
        crustGroup = new ButtonGroup();
        thinBtn = new JRadioButton("Thin");
        regularBtn = new JRadioButton("Regular");
        deepBtn = new JRadioButton("Deep-Dish");

        crustGroup.add(thinBtn);
        crustGroup.add(regularBtn);
        crustGroup.add(deepBtn);

        crustPnl.add(thinBtn);
        crustPnl.add(regularBtn);
        regularBtn.setSelected(true);
        crustPnl.add(deepBtn);
    }

    public void createSizePanel(){
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
        sizePnl.setLayout(new GridLayout(1,3));
        sizePnl.add(new JLabel("\tPizza Size:"));
        sizeComboBox = new JComboBox();
        sizeComboBox.addItem("$8.00 - Small");
        sizeComboBox.addItem("$12.00 - Medium");
        sizeComboBox.addItem("$16.00 - Large");
        sizeComboBox.addItem("$20.00 - Super");
        sizePnl.add(sizeComboBox);
    }

    public void createToppingsPanel(){
        toppingsPnl.setLayout(new GridLayout(2,3));
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Toppings - $1 Each"));

        eyeballOlivesCB = new JCheckBox("Eyeball Olives");
        tentacleCalamariCB = new JCheckBox("Tentacle Calamari");
        gummyWormsCB = new JCheckBox("Gummy Worms");
        blackGarlicCB = new JCheckBox("Black Garlic");
        bloodyMarinaraCB = new JCheckBox("Bloody Marinara Sauce");
        cobwebCheeseCB = new JCheckBox("Cobweb Cheese");

        toppingsPnl.add(eyeballOlivesCB);
        toppingsPnl.add(tentacleCalamariCB);
        toppingsPnl.add(gummyWormsCB);
        toppingsPnl.add(blackGarlicCB);
        toppingsPnl.add(bloodyMarinaraCB);
        toppingsPnl.add(cobwebCheeseCB);

    }

    public void createReceiptPanel(){
        receiptPnl.setBorder(new TitledBorder(new EtchedBorder(), "Receipt"));
        receiptArea = new JTextArea();
        scroller = new JScrollPane(receiptArea);
        receiptPnl.add(scroller);
        receiptArea.setEditable(false);
        receiptArea.setText(
                "Monster's Pizzeria \n"+
                "=============================================================\n" +
                "Type of Crust & Size\t\t\tPrice\n" +
                "Ingredient \t\t\t\tPrice\n" +
                "\n\n\n\n\n" +
                "Sub-total: \t\t\t\tAmount\n" +
                "Tax:\t\t\t\t\tAmount\n" +
                "-------------------------------------------------------------\n" +
                "Total: \t\t\t\t\tTotal\n" +
                "=============================================================\n"
        );
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 12));


    }

    public void createControlPanel(){
        controlPnl.setLayout(new GridLayout(1,3));

        orderBtn = new JButton("Place Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        controlPnl.add(orderBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);

        orderBtn.addActionListener(e ->{
            receiptArea.setText("Monster's Pizzeria \n"+ "=============================================================\n");
            if(sizeComboBox.getSelectedIndex() == 0){
                receiptArea.append("Small ");
                totalCost = 8.00;
            } else if (sizeComboBox.getSelectedIndex() == 1) {
                receiptArea.append("Medium ");
                totalCost = 12.00;
            }else if (sizeComboBox.getSelectedIndex() == 2) {
                receiptArea.append("Large ");
                totalCost = 16.00;
            } else if (sizeComboBox.getSelectedIndex() == 3) {
                receiptArea.append("Super ");
                totalCost = 20.00;
            }

            if(thinBtn.isSelected() == true) {
                receiptArea.append("Thin Crust");
                receiptArea.append("\t\t\t"+ df.format(totalCost)+"\n");
            }else if (deepBtn.isSelected() == true) {
                receiptArea.append("Deep Crust");
                receiptArea.append("\t\t\t" + df.format(totalCost)+"\n");
            } else{
                receiptArea.append("Regular Crust");
                receiptArea.append("\t\t\t" + df.format(totalCost)+"\n");
            }

            if (eyeballOlivesCB.isSelected() == true){
                receiptArea.append("Eyeball Olives" + "\t\t\t\t1.00\n" );
                totalCost +=1.00;
            }
            if (tentacleCalamariCB.isSelected() == true){
                receiptArea.append("Tentacle Calamari" + "\t\t\t1.00\n" );
                totalCost +=1.00;
            }
            if (gummyWormsCB.isSelected() == true){
                receiptArea.append("Gummy Worms" + "\t\t\t\t1.00\n" );
                totalCost +=1.00;
            }
            if (blackGarlicCB.isSelected() == true){
                receiptArea.append("Black Garlic" + "\t\t\t\t1.00\n" );
                totalCost +=1.00;
            }
            if (bloodyMarinaraCB.isSelected() == true){
                receiptArea.append("Bloody Marinara" + "\t\t\t\t1.00\n" );
                totalCost +=1.00;
            }
            if (cobwebCheeseCB.isSelected() == true){
                receiptArea.append("Cobweb Cheese" + "\t\t\t\t1.00\n" );
                totalCost +=1.00;
            }

            receiptArea.append("\nSub-total\t\t\t\t" + totalCost + "0");
            receiptArea.append("\nTax\t\t\t\t\t" + df.format(totalCost*0.07));
            receiptArea.append("\n-------------------------------------------------------------");
            receiptArea.append("\nTotal\t\t\t\t\t" + df.format(totalCost*0.07+totalCost));
            receiptArea.append("\n=============================================================");
        });

        clearBtn.addActionListener(e -> {
            regularBtn.setSelected(true);
            sizeComboBox.setSelectedIndex(0);

            eyeballOlivesCB.setSelected(false);
            tentacleCalamariCB.setSelected(false);
            gummyWormsCB.setSelected(false);
            blackGarlicCB.setSelected(false);
            bloodyMarinaraCB.setSelected(false);
            cobwebCheeseCB.setSelected(false);

            receiptArea.setText(
                    "Monster's Pizzeria \n"+
                    "=============================================================\n" +
                    "Type of Crust & Size\t\t\tPrice\n" +
                    "Ingredient \t\t\t\tPrice\n" +
                    "\n\n\n\n\n" +
                    "Sub-total: \t\t\t\tAmount\n" +
                    "Tax:\t\t\t\t\tAmount\n" +
                    "-------------------------------------------------------------\n" +
                    "Total: \t\t\t\t\tTotal\n" +
                    "=============================================================\n"
            );
        });

        quitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(quitBtn,"Are you sure you want to quit?","Quit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == 0){
                System.exit(0);
            }
        });
    }

}
