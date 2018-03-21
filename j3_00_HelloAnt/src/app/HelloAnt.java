package app;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class HelloAnt extends JFrame {

	private static final long serialVersionUID = 1L;

	public HelloAnt() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new HelloAnt();

	}

}
