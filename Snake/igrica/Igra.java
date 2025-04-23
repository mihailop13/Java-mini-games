package igrica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import igrica.Pozicija.Smer;

public class Igra extends Frame {

	private Label lenght = new Label("Duzina: 0");
	private Button stop = new Button("Stani");
	private Choice dificulty = new Choice();
	private TextField width = new TextField("20"), height = new TextField("20");
	private static final int W = 20;
	private static final int H = 20;
	private Tabla table = new Tabla(W, H);

	public Igra() {
		setBounds(700, 200, 400, 400);
		setTitle("Basic window");
		add(table, BorderLayout.CENTER);
		add(bottom(), BorderLayout.SOUTH);
		menu();
		setResizable(false);
		addListener();
		setVisible(true);
	}

	private void menu() {
		MenuBar bar = new MenuBar();
		setMenuBar(bar);
		Menu menu = new Menu("Akcija");
		bar.add(menu);
		MenuItem new_game = new MenuItem("Nova igra", new MenuShortcut('N'));
		menu.add(new_game);
		new_game.addActionListener(e -> {
			this.table.start_game(Integer.parseInt(this.width.getText()), Integer.parseInt(this.height.getText()));

		});
		menu.addSeparator();
		MenuItem close = new MenuItem("Zatvori!", new MenuShortcut('Z'));
		menu.add(close);
		close.addActionListener(e -> {
			dispose();
		});
	}

	private Panel bottom() {
		Panel panel = new Panel(new GridLayout(2, 2));
		lenght.setAlignment(Label.CENTER);
		lenght.setFont(new Font(null, Font.BOLD, 18));
		table.setLabel(lenght);
		table.setGame(this);
		dificulty.add("LAK");
		dificulty.add("SREDNJI");
		dificulty.add("TEZAK");
		stop.setEnabled(false);
		Panel xy = new Panel();
		xy.add(new Label("x, y: "));
		xy.add(width);
		xy.add(height);
		panel.add(dificulty);
		panel.add(stop);
		panel.add(lenght);
		panel.add(xy);
		return panel;
	}

	private void addListener() {
		this.table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					table.move(Smer.LEVO);
					break;
				case KeyEvent.VK_RIGHT:
					table.move(Smer.DESNO);
					break;
				case KeyEvent.VK_UP:
					table.move(Smer.GORE);
					break;
				case KeyEvent.VK_DOWN:
					table.move(Smer.DOLE);
					break;
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				table.end();
				dispose();
			}
		});

		dificulty.addItemListener(e -> {
			table.setDificulty(new int[] { 300, 500, 100 }[dificulty.getSelectedIndex()]);
		});

		stop.addActionListener(e -> {
			table.stop();
		});

	}

	public void update(boolean b) {
		this.dificulty.setEnabled(!b);
		this.stop.setEnabled(b);
		this.width.setEnabled(!b);
		this.height.setEnabled(!b);
	}
}
