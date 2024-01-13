import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class
Hra implements ActionListener {
    JFrame puzzleFrame = new JFrame();
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel();
    JButton menu = new JButton();
    JButton reset = new JButton();
    JPanel buttonPanel = new JPanel();
    JButton[] buttons = new JButton[16];
    boolean player1Turn;

    /**
     * Tady se vytváří hrací pole s čísly
     */
    Hra() {

        topLabel.setText("Seřaď čísla od nejmenšího po největší");
        topLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        topLabel.setBounds(0, 0, 800, 100);

        reset.addActionListener(this);
        reset.setText("Reset");
        reset.setFont(new Font("Arial Black", Font.PLAIN, 25));
        reset.setBounds(550, 25, 150, 50);
        reset.setBackground(new Color(238, 4, 34));

        topPanel.setLayout(null);
        topPanel.setBackground(new Color(2, 116, 250));
        topPanel.setBounds(0, 0, 800, 100);
        topPanel.add(topLabel);
        topPanel.add(menu);
        topPanel.add(reset);

        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setBounds(0, 100, 800, 700);


        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            numbers.add(i + 1);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < 15; i++) {
            buttons[i] = new JButton(String.valueOf(numbers.get(i)));
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Monospaced", Font.PLAIN, 30));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }
        buttons[15] = new JButton();
        buttons[15].setFont(new Font("Monospaced", Font.PLAIN, 30));
        buttons[15].setFocusable(false);
        buttons[15].addActionListener(this);
        buttonPanel.add(buttons[15]);

        puzzleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        puzzleFrame.setTitle("Patnáctka - 15");
        puzzleFrame.setResizable(false);
        puzzleFrame.setSize(816, 839);
        puzzleFrame.setVisible(true);
        puzzleFrame.setLayout(null);
        puzzleFrame.add(topPanel);
        puzzleFrame.add(buttonPanel);

    }

    /**
     * @param i
     * @return
     */

    public String prepis(int i) {
        String buttonText = buttons[i].getText();

        if (buttonText == null) {
            return "";
        } else {
            return buttonText;
        }
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu) {
            puzzleFrame.dispose();
            Menu menu1 = new Menu();
        } else if (e.getSource() == reset) {
            puzzleFrame.dispose();
            Hra hra = new Hra();
        } else {
            JButton clickedButton = (JButton) e.getSource();
            String clickedButtonText = clickedButton.getText();

            for (int i = 0; i < 16; i++) {
                if (buttons[i].getText().equals("")) {

                    if (isAdjacent(i, Arrays.asList(buttons).indexOf(clickedButton))) {

                        clickedButton.setText("");
                        buttons[i].setText(clickedButtonText);
                        break;
                    }
                }
            }
        }
        if (isPuzzleSolved()) {
            JOptionPane.showMessageDialog(puzzleFrame, "You won!!!!!!", "Winner", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     * @return
     */
    private boolean isPuzzleSolved() {
        for (int i = 0; i < 15; i++) {
            String buttonText = buttons[i].getText();
            if (!buttonText.equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param index1
     * @param index2
     * @return
     */
    private boolean isAdjacent(int index1, int index2) {
        int row1 = index1 / 4;
        int col1 = index1 % 4;
        int row2 = index2 / 4;
        int col2 = index2 % 4;


        return (row1 == row2 && Math.abs(col1 - col2) == 1) || (col1 == col2 && Math.abs(row1 - row2) == 1);
    }
}



