package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class SudokuGUI extends JFrame {
    private JTextField[][] sudokuFields;
    private JButton solveButton;
    private JButton clearButton;
    private Sudoku sudokuModel;
    private Scanner scan;

    public SudokuGUI() {
        super("Sudoku Solver");

        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Create the Sudoku grid panel
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));
        sudokuFields = new JTextField[9][9];

        // Initialize the Sudoku grid with text fields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j] = new JTextField(1);
                sudokuPanel.add(sudokuFields[i][j]);

                // Set orange background for every second 3x3 block
                if ((i / 3 + j / 3) % 2 == 1) {
                    sudokuFields[i][j].setBackground(Color.ORANGE);
                }
            }
        }

        // Create Solve and Clear buttons
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");

        // Add action listeners to buttons
        solveButton.addActionListener(new SolveButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        // Add components to the main frame
        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the frame
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        // Initialize the Sudoku model
        sudokuModel = new Sudoku();
        sudokuModel.setBoard(new int[9][9]);
    }

    private class SolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	int temp = 0;
            // Read values from text fields and update the Sudoku model
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String value = sudokuFields[i][j].getText();
                  
                    if (!value.isEmpty()) {
                     	
                            try {
                            	int num = Integer.parseInt(value);
                                if (sudokuModel.isLegal(j, i, num) && num >= 1 && num <= 9) {
                                    sudokuModel.set(i, j, num);
                                  
                                } 
                                else {
                                    showErrorDialog("Det gå ej att lösa");
                                    temp = 1;
                                    break;
                                }
                            } 
                            catch (NumberFormatException ex) {
                                showErrorDialog("Invalid input. Please enter a valid integer.");
                                break;
                                
                            }
                        
                    }

                }
                if(temp == 1)
                	break;
            }

            // Solve the Sudoku puzzle
            if(temp == 0)
            sudokuModel.solve();
            else {
            	clearButton.doClick();
            }

            // Update the GUI with the solution
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuFields[i][j].setText(Integer.toString(sudokuModel.get(i, j)));
                }
            }
        }
    }
    
    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear all text fields and reset the Sudoku model
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuFields[i][j].setText("");
                    sudokuModel.set(i, j, 0);
                }
            }
        }
    }
    
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}
