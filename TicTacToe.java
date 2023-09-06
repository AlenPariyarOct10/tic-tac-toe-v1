package com.mycompany.tictactoe;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;

public class TicTacToe implements ActionListener {

    JFrame frame;
    JButton btns[];
    JLabel label;
    String currentPlayer, turn;
    String result[];
    boolean win;
    int steps;
    JButton btn;

    public TicTacToe() {

        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btns = new JButton[9];
        result = new String[9];
        btn = new JButton("restart");
        btn.setSize(20, 20);
        Arrays.fill(result, "");
        for (int i = 0; i < 9; i++) {
            btns[i] = new JButton();
            btns[i].setSize(20, 20);
            frame.add(btns[i]);

            btns[i].setActionCommand(String.valueOf(i));
            btns[i].addActionListener(this);
            result[i] = String.valueOf(i);
        }
        steps = 0;
        frame.setLayout(new GridLayout(4, 3, 5, 5));

        label = new JLabel("Let the game begin from `X`");
        frame.add(label);

        btn.addActionListener(this);

        frame.setVisible(true);
        currentPlayer = "O";

    }

    @Override
    public void actionPerformed(ActionEvent ex) {
        String ac = ex.getActionCommand();
        if (ac.equals("restart")) {

            frame.dispose();
            new TicTacToe();
        } else {
            int clickedBtn = Integer.parseInt(ac);
            steps++;

            if (!(btns[clickedBtn].getText().equals("X") || btns[clickedBtn].getText().equals("O")) && (!win)) {
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                    result[clickedBtn] = currentPlayer;
                    btns[clickedBtn].setBackground(Color.BLACK);
                    btns[clickedBtn].setForeground(Color.WHITE);
                    btns[clickedBtn].setText(currentPlayer);

                    turn = "X";
                } else if (currentPlayer.equals("O")) {

                    btns[clickedBtn].setBackground(Color.BLUE);
                    btns[clickedBtn].setForeground(Color.WHITE);
                    currentPlayer = "X";
                    result[clickedBtn] = currentPlayer;
                    btns[clickedBtn].setText(currentPlayer);

                    turn = "O";
                }

                if ((result[0].equals(result[1]) && result[1].equals(result[2]))
                        || (result[3].equals(result[4]) && result[4].equals(result[5]))
                        || (result[6].equals(result[7]) && result[7].equals(result[8]))
                        || (result[0].equals(result[3]) && result[3].equals(result[6]))
                        || (result[1].equals(result[4]) && result[4].equals(result[7]))
                        || (result[2].equals(result[5]) && result[5].equals(result[8]))
                        || (result[0].equals(result[4]) && result[4].equals(result[8]))
                        || (result[2].equals(result[4]) && result[4].equals(result[6]))) {
                    win = true;
                }
                label.setText("Turn " + turn);
                if (win) {
                    label.setText(currentPlayer + " Won the game !!!");
                    label.setFont(new Font("Arial", Font.BOLD, 20));
                    label.setForeground(Color.GREEN);
                    btn.setActionCommand("restart");
                    frame.add(btn);

                }
                if ((!win)&&steps == 9) {
                    label.setText(" Draw !!!");
                    label.setFont(new Font("Arial", Font.BOLD, 20));
                    label.setForeground(Color.GREEN);
                    btn.setActionCommand("restart");
                    frame.add(btn);

                }
            }
        }

    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
