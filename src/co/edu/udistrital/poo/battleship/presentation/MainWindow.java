package co.edu.udistrital.poo.battleship.presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class MainWindow extends JFrame {

	// Main Components
	
	JPanel contentPane;
	Canvas ownBoardCanvas;
	Canvas enemyBoardCanvas;
	
	// Controllers and Model Components
	
	OwnBoardCanvasController ownCanvasController;
	EnemyBoardCanvasController enemyCanvasController;
	MainWindowController mainController;
	Model model;

	// Menu Components
	
	JMenuBar menuBar;
	JMenu mnGame;
	JMenuItem mnCreate;
	JMenuItem mnJoin;
	JMenuItem mnExit;
	JMenu mnHelp;
	JMenuItem mnAbout;

	// Player Components
	
	JLabel lblGameTitle;
	JLabel lblPlayerNameText;
	JLabel lblOpponentsNameText;
	JLabel lblStatusText;
	
	// Game Components
	
	JLabel lblShotsQty;
	JLabel lblHitsQty;
	JLabel lblMissesQty;
	JLabel lblShipsQty;
	JLabel lblShipsAliveQty;
	JLabel lblShipsSunkQty;
	JLabel lblBattleshipQty;
	JLabel lblCruiserQty;
	JLabel lblSubmarineQty;
	JLabel lblDestroyerQty;
	JLabel lblLengthBattleship;
	JLabel lblLengthCruiser;
	JLabel lblLengthSubmarine;
	JLabel lblLengthDestroyer;
	JRadioButton rdBtnBattleship;
	JRadioButton rdBtnCruiser;
	JRadioButton rdBtnSubmarine;
	JRadioButton rdBtnDestroyer;
	
	JRadioButton rdbtnHorizontal;
	JRadioButton rdbtnVertical;

	// Get Fired Components

	JTextField txtXFire;
	JTextField txtYFire;
	
	
	/**
	 * Create the frame.
	 */
	public MainWindow(Model model) {

		setResizable(false);
		setTitle("Battleship GAME");

		this.model = model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 700);

		initComponents();
		captureEvents();

	}
	
	public void initComponents(){
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Setting MenuBar, Menus and MenuItems

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1280, 30);
		contentPane.add(menuBar);

		mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		mnCreate = new JMenuItem("Create");
		mnGame.add(mnCreate);

		mnJoin = new JMenuItem("Join");
		mnGame.add(mnJoin);

		mnGame.addSeparator();

		mnExit = new JMenuItem("Exit");
		mnGame.add(mnExit);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mnAbout = new JMenuItem("About");
		mnHelp.add(mnAbout);

		// Setting Player & Status Indicators

		lblGameTitle = new JLabel("BATTLESHIP GAME");
		lblGameTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblGameTitle.setBounds(31, 40, 244, 63);
		contentPane.add(lblGameTitle);

		JLabel lblPlayerName = new JLabel("Player Name: ");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlayerName.setBounds(22, 110, 112, 16);
		contentPane.add(lblPlayerName);

		lblPlayerNameText = new JLabel("");
		lblPlayerNameText.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerNameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlayerNameText.setBounds(163, 96, 112, 32);
		contentPane.add(lblPlayerNameText);
		
		JLabel lblOpponentsName = new JLabel("Opponents Name");
		lblOpponentsName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOpponentsName.setBounds(24, 139, 144, 23);
		contentPane.add(lblOpponentsName);
		
		lblOpponentsNameText = new JLabel("");
		lblOpponentsNameText.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponentsNameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOpponentsNameText.setBounds(163, 129, 112, 32);
		contentPane.add(lblOpponentsNameText);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(22, 175, 144, 16);
		contentPane.add(lblStatus);
		
		lblStatusText = new JLabel("");
		lblStatusText.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatusText.setBounds(162, 161, 112, 32);
		contentPane.add(lblStatusText);
		

		// Shots section

		JLabel lblShots = new JLabel("Shots:");
		lblShots.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShots.setBounds(25, 210, 112, 16);
		contentPane.add(lblShots);

		JLabel lblHits = new JLabel("Hits:");
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHits.setBounds(36, 239, 112, 16);
		contentPane.add(lblHits);

		JLabel lblMisses = new JLabel("Misses:");
		lblMisses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMisses.setBounds(36, 268, 112, 16);
		contentPane.add(lblMisses);

		lblShotsQty = new JLabel("");
		lblShotsQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShotsQty.setBounds(205, 211, 56, 16);
		contentPane.add(lblShotsQty);

		lblHitsQty = new JLabel("");
		lblHitsQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHitsQty.setBounds(205, 240, 56, 16);
		contentPane.add(lblHitsQty);

		lblMissesQty = new JLabel("");
		lblMissesQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMissesQty.setBounds(205, 269, 56, 16);
		contentPane.add(lblMissesQty);

		// Ships section

		JLabel lblShips = new JLabel("Ships");
		lblShips.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShips.setBounds(25, 294, 112, 16);
		contentPane.add(lblShips);

		JLabel lblAlive = new JLabel("Alive");
		lblAlive.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAlive.setBounds(36, 323, 112, 16);
		contentPane.add(lblAlive);

		JLabel lblSunk = new JLabel("Sunk");
		lblSunk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSunk.setBounds(36, 352, 112, 16);
		contentPane.add(lblSunk);

		lblShipsQty = new JLabel("");
		lblShipsQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShipsQty.setBounds(205, 297, 56, 16);
		contentPane.add(lblShipsQty);

		lblShipsAliveQty = new JLabel("");
		lblShipsAliveQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShipsAliveQty.setBounds(205, 324, 56, 16);
		contentPane.add(lblShipsAliveQty);

		lblShipsSunkQty = new JLabel("");
		lblShipsSunkQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShipsSunkQty.setBounds(205, 353, 56, 16);
		contentPane.add(lblShipsSunkQty);

		// Section Get Fired

		JLabel lblGetfired = new JLabel("Get Fired");
		lblGetfired.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGetfired.setBounds(405, 603, 112, 16);
		contentPane.add(lblGetfired);

		JLabel lblXFire = new JLabel("x:");
		lblXFire.setBounds(515, 604, 56, 16);
		contentPane.add(lblXFire);

		JLabel lblYFire = new JLabel("y:");
		lblYFire.setBounds(665, 604, 56, 16);
		contentPane.add(lblYFire);

		txtXFire = new JTextField();
		txtXFire.setColumns(10);
		txtXFire.setBounds(690, 603, 77, 22);
		contentPane.add(txtXFire);

		txtYFire = new JTextField();
		txtYFire.setColumns(10);
		txtYFire.setBounds(559, 602, 77, 22);
		contentPane.add(txtYFire);

		JButton btnGetFired = new JButton("Get Fired");
		btnGetFired.setBounds(806, 600, 112, 25);
		contentPane.add(btnGetFired);

		// Section Canvas

		ownBoardCanvas = new Canvas();
		ownBoardCanvas.setBackground(Color.BLUE);
		ownBoardCanvas.setBounds(350, 100, 400, 400);
		contentPane.add(ownBoardCanvas);

		enemyBoardCanvas = new Canvas();
		enemyBoardCanvas.setBackground(Color.RED);
		enemyBoardCanvas.setBounds(800, 100, 400, 400);
		contentPane.add(enemyBoardCanvas);

		// vertical labels own board

		JLabel lblA = new JLabel("A");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblA.setBounds(280, 120, 56, 16);
		contentPane.add(lblA);

		JLabel lblB = new JLabel("B");
		lblB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblB.setBounds(280, 160, 56, 16);
		contentPane.add(lblB);

		JLabel lblC = new JLabel("C");
		lblC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblC.setBounds(280, 200, 56, 16);
		contentPane.add(lblC);

		JLabel lblD = new JLabel("D");
		lblD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblD.setBounds(280, 240, 56, 16);
		contentPane.add(lblD);

		JLabel lblE = new JLabel("E");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblE.setBounds(280, 280, 56, 16);
		contentPane.add(lblE);

		JLabel lblF = new JLabel("F");
		lblF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblF.setBounds(280, 320, 56, 16);
		contentPane.add(lblF);

		JLabel lblG = new JLabel("G");
		lblG.setHorizontalAlignment(SwingConstants.RIGHT);
		lblG.setBounds(280, 360, 56, 16);
		contentPane.add(lblG);

		JLabel lblH = new JLabel("H");
		lblH.setHorizontalAlignment(SwingConstants.RIGHT);
		lblH.setBounds(280, 400, 56, 16);
		contentPane.add(lblH);

		JLabel lblI = new JLabel("I");
		lblI.setHorizontalAlignment(SwingConstants.RIGHT);
		lblI.setBounds(280, 440, 56, 16);
		contentPane.add(lblI);

		JLabel lblJ = new JLabel("J");
		lblJ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJ.setBounds(280, 480, 56, 16);
		contentPane.add(lblJ);

		// Horizontal labels Own Board

		JLabel label_1 = new JLabel("1");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(315, 68, 56, 16);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("2");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(355, 68, 56, 16);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("3");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(395, 68, 56, 16);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("4");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(435, 68, 56, 16);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("5");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(475, 68, 56, 16);
		contentPane.add(label_5);

		JLabel label_6= new JLabel("6");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(515, 68, 56, 16);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("7");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(555, 68, 56, 16);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("8");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(595, 68, 56, 16);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("9");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(635, 68, 56, 16);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("10");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(680, 68, 56, 16);
		contentPane.add(label_10);

		// Ship Types

		JLabel lblShipType = new JLabel("Ship Type");
		lblShipType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipType.setBounds(25, 389, 112, 16);
		contentPane.add(lblShipType);

		JLabel lblQty = new JLabel("Left");
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQty.setBounds(90, 388, 112, 16);
		contentPane.add(lblQty);

		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBattleship.setBounds(36, 417, 112, 16);
		contentPane.add(lblBattleship);

		JLabel lblCruiser = new JLabel("Cruiser");
		lblCruiser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCruiser.setBounds(36, 446, 112, 16);
		contentPane.add(lblCruiser);

		JLabel lblSubmarine = new JLabel("Submarine");
		lblSubmarine.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubmarine.setBounds(36, 475, 112, 16);
		contentPane.add(lblSubmarine);

		JLabel lblDestroyer = new JLabel("Destroyer");
		lblDestroyer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestroyer.setBounds(36, 504, 112, 16);
		contentPane.add(lblDestroyer);

		lblBattleshipQty = new JLabel("0");
		lblBattleshipQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBattleshipQty.setBounds(146, 417, 56, 16);
		contentPane.add(lblBattleshipQty);

		lblCruiserQty = new JLabel("0");
		lblCruiserQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCruiserQty.setBounds(147, 446, 56, 16);
		contentPane.add(lblCruiserQty);

		lblSubmarineQty = new JLabel("0");
		lblSubmarineQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubmarineQty.setBounds(147, 475, 56, 16);
		contentPane.add(lblSubmarineQty);

		lblDestroyerQty = new JLabel("0");
		lblDestroyerQty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDestroyerQty.setBounds(147, 504, 56, 16);
		contentPane.add(lblDestroyerQty);

		JLabel lblLength = new JLabel("Length");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLength.setBounds(195, 388, 112, 16);
		contentPane.add(lblLength);

		lblLengthBattleship = new JLabel("0");
		lblLengthBattleship.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLengthBattleship.setBounds(208, 417, 56, 16);
		contentPane.add(lblLengthBattleship);

		lblLengthCruiser = new JLabel("0");
		lblLengthCruiser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLengthCruiser.setBounds(208, 446, 56, 16);
		contentPane.add(lblLengthCruiser);

		lblLengthSubmarine = new JLabel("0");
		lblLengthSubmarine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLengthSubmarine.setBounds(208, 475, 56, 16);
		contentPane.add(lblLengthSubmarine);

		lblLengthDestroyer = new JLabel("0");
		lblLengthDestroyer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLengthDestroyer.setBounds(208, 504, 56, 16);
		contentPane.add(lblLengthDestroyer);

		// Radio Buttons for location of Ships

		rdbtnHorizontal = new JRadioButton("Horizontal");
		rdbtnHorizontal.setBounds(31, 558, 127, 25);
		rdbtnHorizontal.setSelected(true);
		contentPane.add(rdbtnHorizontal);

		rdbtnVertical = new JRadioButton("Vertical");
		rdbtnVertical.setBounds(163, 558, 127, 25);
		contentPane.add(rdbtnVertical);

		// Group the radio buttons.
		ButtonGroup btnGrpOrientation = new ButtonGroup();
		btnGrpOrientation.add(rdbtnHorizontal);
		btnGrpOrientation.add(rdbtnVertical);

		// Labels for enemy Board

		JLabel label_21 = new JLabel("A");
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setBounds(728, 115, 56, 16);
		contentPane.add(label_21);

		JLabel label_22 = new JLabel("B");
		label_22.setHorizontalAlignment(SwingConstants.RIGHT);
		label_22.setBounds(728, 155, 56, 16);
		contentPane.add(label_22);

		JLabel label_23 = new JLabel("C");
		label_23.setHorizontalAlignment(SwingConstants.RIGHT);
		label_23.setBounds(728, 195, 56, 16);
		contentPane.add(label_23);

		JLabel label_24 = new JLabel("D");
		label_24.setHorizontalAlignment(SwingConstants.RIGHT);
		label_24.setBounds(728, 235, 56, 16);
		contentPane.add(label_24);

		JLabel label_25 = new JLabel("E");
		label_25.setHorizontalAlignment(SwingConstants.RIGHT);
		label_25.setBounds(728, 275, 56, 16);
		contentPane.add(label_25);

		JLabel label_26 = new JLabel("F");
		label_26.setHorizontalAlignment(SwingConstants.RIGHT);
		label_26.setBounds(728, 315, 56, 16);
		contentPane.add(label_26);

		JLabel label_27 = new JLabel("G");
		label_27.setHorizontalAlignment(SwingConstants.RIGHT);
		label_27.setBounds(728, 355, 56, 16);
		contentPane.add(label_27);

		JLabel label_28 = new JLabel("H");
		label_28.setHorizontalAlignment(SwingConstants.RIGHT);
		label_28.setBounds(728, 395, 56, 16);
		contentPane.add(label_28);

		JLabel label_29 = new JLabel("I");
		label_29.setHorizontalAlignment(SwingConstants.RIGHT);
		label_29.setBounds(728, 435, 56, 16);
		contentPane.add(label_29);

		JLabel label_30 = new JLabel("J");
		label_30.setHorizontalAlignment(SwingConstants.RIGHT);
		label_30.setBounds(728, 475, 56, 16);
		contentPane.add(label_30);

		JLabel label_31 = new JLabel("1");
		label_31.setHorizontalAlignment(SwingConstants.RIGHT);
		label_31.setBounds(769, 68, 56, 16);
		contentPane.add(label_31);

		JLabel label_32 = new JLabel("2");
		label_32.setHorizontalAlignment(SwingConstants.RIGHT);
		label_32.setBounds(809, 68, 56, 16);
		contentPane.add(label_32);

		JLabel label_33 = new JLabel("3");
		label_33.setHorizontalAlignment(SwingConstants.RIGHT);
		label_33.setBounds(849, 68, 56, 16);
		contentPane.add(label_33);

		JLabel label_34 = new JLabel("4");
		label_34.setHorizontalAlignment(SwingConstants.RIGHT);
		label_34.setBounds(889, 68, 56, 16);
		contentPane.add(label_34);

		JLabel label_35 = new JLabel("5");
		label_35.setHorizontalAlignment(SwingConstants.RIGHT);
		label_35.setBounds(929, 68, 56, 16);
		contentPane.add(label_35);

		JLabel label_40 = new JLabel("6");
		label_40.setHorizontalAlignment(SwingConstants.RIGHT);
		label_40.setBounds(969, 68, 56, 16);
		contentPane.add(label_40);

		JLabel label_36 = new JLabel("7");
		label_36.setHorizontalAlignment(SwingConstants.RIGHT);
		label_36.setBounds(1009, 68, 56, 16);
		contentPane.add(label_36);

		JLabel label_37 = new JLabel("8");
		label_37.setHorizontalAlignment(SwingConstants.RIGHT);
		label_37.setBounds(1049, 68, 56, 16);
		contentPane.add(label_37);

		JLabel label_38 = new JLabel("9");
		label_38.setHorizontalAlignment(SwingConstants.RIGHT);
		label_38.setBounds(1089, 68, 56, 16);
		contentPane.add(label_38);

		JLabel label_39 = new JLabel("10");
		label_39.setHorizontalAlignment(SwingConstants.RIGHT);
		label_39.setBounds(1134, 68, 56, 16);
		contentPane.add(label_39);

		JLabel lblNewLabel = new JLabel("Hits: BLUE");
		lblNewLabel.setBounds(900, 527, 85, 16);
		contentPane.add(lblNewLabel);

		JLabel lblHitsMiss = new JLabel("Miss: WHITE");
		lblHitsMiss.setBounds(1060, 527, 85, 16);
		contentPane.add(lblHitsMiss);

		JLabel lblHitsRed = new JLabel("Hits: RED");
		lblHitsRed.setBounds(418, 527, 85, 16);
		contentPane.add(lblHitsRed);

		JLabel label_41 = new JLabel("Miss: WHITE");
		label_41.setBounds(578, 527, 85, 16);
		contentPane.add(label_41);

		rdBtnBattleship = new JRadioButton("");
		rdBtnBattleship.setBounds(250, 414, 26, 25);
		contentPane.add(rdBtnBattleship);

		rdBtnCruiser = new JRadioButton("");
		rdBtnCruiser.setBounds(250, 446, 26, 25);
		contentPane.add(rdBtnCruiser);

		rdBtnSubmarine = new JRadioButton("");
		rdBtnSubmarine.setBounds(250, 479, 26, 25);
		contentPane.add(rdBtnSubmarine);

		rdBtnDestroyer = new JRadioButton("");
		rdBtnDestroyer.setBounds(250, 506, 24, 25);
		rdBtnDestroyer.setSelected(true);
		contentPane.add(rdBtnDestroyer);

		// Group the radio buttons.
		ButtonGroup btnGroupShipTypes = new ButtonGroup();
		btnGroupShipTypes.add(rdBtnBattleship);
		btnGroupShipTypes.add(rdBtnCruiser);
		btnGroupShipTypes.add(rdBtnSubmarine);
		btnGroupShipTypes.add(rdBtnDestroyer);
		
		// Action buttons for actions
		
		JButton btnNewButton = new JButton("Place Ships");
		btnNewButton.setBounds(12, 614, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnBeginGame = new JButton("Begin Game");
		btnBeginGame.setBounds(121, 614, 112, 25);
		contentPane.add(btnBeginGame);
		
		JButton btnSurrender = new JButton("Surrender");
		btnSurrender.setBounds(250, 614, 97, 25);
		contentPane.add(btnSurrender);
	}

	// Add action listeners
	
	protected void captureEvents() {
		
		ownBoardCanvas.addMouseListener(getOwnBoardCanvasController());
		enemyBoardCanvas.addMouseListener(getEnemyBoardCanvasController());
		mnCreate.addActionListener(getMainController());
		mnJoin.addActionListener(getMainController());
	}
	
	
	// Methods to return controllers and Model
	

	public OwnBoardCanvasController getOwnBoardCanvasController() {
		if (ownCanvasController == null) {
			ownCanvasController = new OwnBoardCanvasController(this);
		}
		return ownCanvasController;
	}

	public EnemyBoardCanvasController getEnemyBoardCanvasController() {
		if (enemyCanvasController == null) {
			enemyCanvasController = new EnemyBoardCanvasController(this);
		}
		return enemyCanvasController;
	}

	public MainWindowController getMainController() {
		if (mainController == null) {
			mainController = new MainWindowController(this);
		}
		return mainController;
	}

	public Model getModel() {
		return model;
	}
	
	// Pop up for server player

	public void showPlayerDialog() {

		JTextField playerName = new JTextField("player 1");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("Player Name:"));
		panel.add(playerName);
		
		int result = JOptionPane.showConfirmDialog(this, panel, "Create Game", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		
		if (result == JOptionPane.OK_OPTION) {
			if(playerName.getText().isEmpty()){
				playerName.setText("player 1");
			}
			lblPlayerNameText.setText(playerName.getText());
			getModel().initServer(playerName.getText());
		} else {
			System.out.println("Cancelled");
		}
	}

	// Pop up window for client player
	
	public void showServerDialog() {

		JTextField playerName = new JTextField("player 2");
		JTextField serverAddress = new JTextField("127.0.0.1");
		JTextField serverPort = new JTextField("50000");

		JPanel panelPlayerTwo = new JPanel(new GridLayout(0, 1));
		panelPlayerTwo.add(new JLabel("Player Name:"));
		panelPlayerTwo.add(playerName);
		panelPlayerTwo.add(new JLabel("Server Address:"));
		panelPlayerTwo.add(serverAddress);
		panelPlayerTwo.add(new JLabel("Server Port:"));
		panelPlayerTwo.add(serverPort);

		int result = JOptionPane.showConfirmDialog(this, panelPlayerTwo, "Join Game", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		
		if (result == JOptionPane.OK_OPTION) {
			getModel().initClient(serverAddress.getText(), serverPort.getText(), playerName.getText());
			System.out.println("Player name: " + playerName.getText());
			System.out.println("Server Address: " + serverAddress.getText() + ":" + serverPort);
		} else {
			System.out.println("Cancelled");
		}
	}
}
