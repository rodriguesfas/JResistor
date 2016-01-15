/**
 *  Este programa é software livre; você pode redistribuí-lo e/ou
 *  modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 *  publicada pela Free Software Foundation; tanto a versão 2 da
 *  Licença como (a seu critério) qualquer versão mais nova.
 *
 *  Este programa é distribuído na expectativa de ser útil, mas SEM
 *  QUALQUER GARANTIA; sem mesmo a garantia implícita de
 *  COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 *  PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 *  detalhes.
 *
 *  Você deve ter recebido uma cópia da Licença Pública Geral GNU
 *  junto com este programa; se não, escreva para a Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307, USA.
 *  
 *  http://www.gnu.org/licenses/gpl.html
 *  
 */
package br.com.jresistor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @Aplicação CalcRes
 * @version 1.0
 * @author Francisco de Assis de Souza Rodrigues
 * @Data 18 de junho de 2014
 * @Site http://www.clubedosgeeks.com.br
 *
 */

/**
 * 
 * Esse Aplicativo Calcular o valor de um resistor pelas cores.
 * 
 */
public class JResistor extends JFrame {
	/**/
	private static JResistor window;

	/**/
	private JFrame frmCalcres;

	/* Usado para selecionar a quantidade de faixas do resistor */
	private JComboBox ent_Faixas;

	/* Usado para configura cada faixa do resistor */
	private JComboBox ent_Faixa_1;
	private JComboBox ent_Faixa_2;
	private JComboBox ent_Faixa_3;
	private JComboBox ent_Mutiplicador;

	/* Usado para seleciona o Mutiplicador */
	private JComboBox ent_Tolerancia;

	/* Usado para exibir valor do Resistor */
	private JLabel saidaResultado;
	private JLabel saidaToleranca;
	private JLabel saidaGrandeza;

	/* Manipula a Imagem do Resistor */
	private JLabel imgResistor;

	/* Manipula as Faixas do Resistor */
	private JLabel imgFaixa1;
	private JLabel imgFaixa2;
	private JLabel imgFaixa3;
	private JLabel imgFaixa4;
	private JLabel imgTolerancia;

	/* Usados para realizar os calculos */
	private String auxFaixa1;
	private String auxFaixa2;
	private String auxFaixa3;
	private String concatenacao;
	private Double resultado;

	private Double auxResul;
	private Double auxMultiplicador;
	private Double auxConcatenacao;
	private String tolerancia;

	/**/
	private JMenu mnNewMenu;
	private JMenuItem mntmSobre;

	/**/
	private Tabela tabela;
	private Sobre sobre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					window = new JResistor();
					window.frmCalcres.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JResistor() {
		initialize();
		/* Inicia Aplicação Com Resistor de 4 Faixas */
		Resistor4Faixas();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalcres = new JFrame();
		/* Coloca a janela no centro da tela */
		frmCalcres.setLocationRelativeTo(null);
		frmCalcres.setIconImage(Toolkit.getDefaultToolkit().getImage(
				JResistor.class.getResource("/logo_app.png")));
		frmCalcres.setResizable(false);
		frmCalcres.setBackground(Color.WHITE);
		frmCalcres.getContentPane().setBackground(Color.WHITE);

		ent_Faixas = new JComboBox();
		ent_Faixas.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Faixas.setBounds(202, 6, 84, 25);
		ent_Faixas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Chama o método que controla a faixa dos resistores */
				ConfigFaixas();
			}
		});
		ent_Faixas.setToolTipText("Selecione o Tipo de Resistor");
		ent_Faixas.setBackground(Color.WHITE);
		ent_Faixas.setModel(new DefaultComboBoxModel(new String[] { "3 Faixas",
				"4 Faixas", "5 Faixas" }));

		ent_Faixa_1 = new JComboBox();
		ent_Faixa_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Faixa_1.setBounds(6, 241, 92, 25);
		ent_Faixa_1.setModel(new DefaultComboBoxModel(new String[] { "Preto",
				"Marrom", "Vermelho", "Laranja", "Amarelo", "Verde", "Azul",
				"Violeta", "Cinza", "Branco" }));
		ent_Faixa_1.setBackground(Color.WHITE);
		ent_Faixa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Faixa1();
			}
		});
		ent_Faixa_1.setToolTipText("Faixa 1");

		ent_Faixa_2 = new JComboBox();
		ent_Faixa_2.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Faixa_2.setBounds(104, 241, 92, 25);
		ent_Faixa_2.setModel(new DefaultComboBoxModel(new String[] { "Preto",
				"Marrom", "Vermelho", "Laranja", "Amarelo", "Verde", "Azul",
				"Violeta", "Cinza", "Branco" }));
		ent_Faixa_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Faixa2();
			}
		});
		ent_Faixa_2.setBackground(Color.WHITE);
		ent_Faixa_2.setToolTipText("Faixa 2");

		ent_Faixa_3 = new JComboBox();
		ent_Faixa_3.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Faixa_3.setBounds(202, 241, 92, 25);
		ent_Faixa_3.setModel(new DefaultComboBoxModel(new String[] { "Preto",
				"Marrom", "Vermelho", "Laranja", "Amarelo", "Verde", "Azul",
				"Violeta", "Cinza", "Branco" }));
		ent_Faixa_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Faixa3();
			}
		});
		ent_Faixa_3.setBackground(Color.WHITE);
		ent_Faixa_3.setToolTipText("Faixa 3");

		ent_Tolerancia = new JComboBox();
		ent_Tolerancia.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Tolerancia.setBounds(398, 241, 92, 25);
		ent_Tolerancia.setModel(new DefaultComboBoxModel(new String[] {
				"Marrom", "Vermelho", "Verde", "Azul", "Violeta", "Cinza",
				"Dourado", "Prateado", "Nenhum" }));
		ent_Tolerancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FaixaTolerancia();
			}
		});
		ent_Tolerancia.setBackground(Color.WHITE);
		ent_Tolerancia.setToolTipText("Faixa Tolerância");

		saidaResultado = new JLabel("R");
		saidaResultado.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		saidaResultado.setBounds(190, 176, 50, 15);

		imgResistor = new JLabel("");
		imgResistor.setBounds(43, 51, 400, 113);
		/* Define Imagem do Resistor */
		imgResistor.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/res1.png")));

		imgResistor.setHorizontalAlignment(SwingConstants.CENTER);

		ent_Mutiplicador = new JComboBox();
		ent_Mutiplicador.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		ent_Mutiplicador.setBounds(300, 241, 92, 25);
		ent_Mutiplicador.setToolTipText("Faixa Multiplicador");
		ent_Mutiplicador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FaixaMultiplicador();
			}
		});
		ent_Mutiplicador.setModel(new DefaultComboBoxModel(new String[] {
				"Preto", "Marrom", "Vermelho", "Laranja", "Amarelo", "Verde",
				"Azul", "Violeta", "Cinza", "Branco", "Dourado", "Prateado" }));
		ent_Mutiplicador.setBackground(Color.WHITE);
		frmCalcres.getContentPane().setLayout(null);
		frmCalcres.getContentPane().add(ent_Faixas);
		frmCalcres.getContentPane().add(ent_Faixa_1);
		frmCalcres.getContentPane().add(ent_Faixa_2);
		frmCalcres.getContentPane().add(ent_Faixa_3);
		frmCalcres.getContentPane().add(ent_Mutiplicador);
		frmCalcres.getContentPane().add(ent_Tolerancia);

		imgFaixa1 = new JLabel("");
		imgFaixa1.setBounds(190, 65, 10, 58);
		imgFaixa1.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/fxVerm.png")));
		frmCalcres.getContentPane().add(imgFaixa1);

		imgFaixa2 = new JLabel("");
		imgFaixa2.setBounds(210, 65, 10, 58);
		imgFaixa2.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/fxVerm.png")));
		frmCalcres.getContentPane().add(imgFaixa2);

		imgFaixa3 = new JLabel("");
		imgFaixa3.setBounds(230, 65, 10, 58);
		imgFaixa3.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/fxMarrom.png")));
		frmCalcres.getContentPane().add(imgFaixa3);

		imgFaixa4 = new JLabel("");
		imgFaixa4.setBounds(250, 65, 10, 58);
		imgFaixa4.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/fxPreto.png")));
		frmCalcres.getContentPane().add(imgFaixa4);

		imgTolerancia = new JLabel("");
		imgTolerancia.setBounds(286, 65, 10, 58);
		imgTolerancia.setIcon(new ImageIcon(JResistor.class
				.getResource("/br/com/jresistor/img/fxDourado.png")));
		frmCalcres.getContentPane().add(imgTolerancia);
		frmCalcres.getContentPane().add(imgResistor);
		frmCalcres.getContentPane().add(saidaResultado);

		saidaToleranca = new JLabel("T");
		saidaToleranca.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		saidaToleranca.setBounds(282, 176, 102, 15);
		frmCalcres.getContentPane().add(saidaToleranca);

		saidaGrandeza = new JLabel("G");
		saidaGrandeza.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		saidaGrandeza.setBounds(240, 176, 40, 15);
		frmCalcres.getContentPane().add(saidaGrandeza);
		frmCalcres.setTitle("JResistor");
		frmCalcres.setBounds(100, 100, 500, 320);
		frmCalcres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmCalcres.setJMenuBar(menuBar);

		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setFont(new Font("Ubuntu", Font.BOLD, 14));
		menuBar.add(mnInicio);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnInicio.add(mntmSair);

		mnNewMenu = new JMenu("Ajuda");
		mnNewMenu.setFont(new Font("Ubuntu", Font.BOLD, 14));
		menuBar.add(mnNewMenu);

		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setBackground(Color.WHITE);
		mntmSobre.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sobre == null) {
					sobre = new Sobre();
					sobre.setVisible(true);
				}
			}
		});

		JMenuItem mntmTabelaDeCores = new JMenuItem("Tabela de Cores");
		mntmTabelaDeCores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabela == null) {
					tabela = new Tabela();
					tabela.setVisible(true);
				}
			}
		});
		mntmTabelaDeCores.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		mnNewMenu.add(mntmTabelaDeCores);
		mnNewMenu.add(mntmSobre);

	}/* Fim método initialize */

	/**
	 * Define uma configuração Inical da aplicação.
	 */
	public void Resistor4Faixas() {
		/* Seleciona Quantidade de Faixas do resistor */
		ent_Faixas.setSelectedIndex(0);

		/* Chama o método que controla a faixa dos resistores */
		ConfigFaixas();

		/* Chamada do Metodo que Calcula Cor */
		CalcularCor();

	}/* Fim CanfInicial */

	/**
	 * ConfigFaixas. Controla a quantidade de faixas no Resitor.
	 */
	public void ConfigFaixas() {
		/**
		 * (3)- Resistor de Três Faixas
		 */
		if (ent_Faixas.getSelectedIndex() == 0) {
			/* Define posição do Botão Mutiplicador */
			ent_Mutiplicador.setBounds(202, 241, 92, 25);

			/* Define posição da faixa no Resistor */
			imgFaixa3.setBounds(230, 65, 10, 58);

			/* Define Imagem do Resistor */
			imgResistor.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/res1.png")));

			imgFaixa4.setVisible(false);/* Desativa Faixa4 */
			ent_Faixa_3.setVisible(false);/* Desativa Faixa_3 */

			/* Define a Cor das Faixas Inicais */
			ent_Faixa_1.setSelectedIndex(2);
			ent_Faixa_2.setSelectedIndex(2);
			ent_Mutiplicador.setSelectedIndex(1);
			ent_Tolerancia.setSelectedIndex(8);

			/* Chamadas dos Metodos Faixas e Tolerancia */
			Faixa1();
			Faixa2();
			FaixaMultiplicador();
			FaixaTolerancia();

			/* Chama o Método que Realiza o Calculo */
			CalcularCor();
		}/* Fim Resistor 4 Faixas */
		/**
		 * (4)- Resistor de Quatro Faixas
		 */
		if (ent_Faixas.getSelectedIndex() == 1) {
			/* Define posição do Botão Mutiplicador */
			ent_Mutiplicador.setBounds(202, 241, 92, 25);

			/* Define posição da faixa no Resistor */
			imgFaixa3.setBounds(230, 65, 10, 58);

			/* Define Imagem do Resistor */
			imgResistor.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/res1.png")));

			imgFaixa4.setVisible(false);/* Desativa Faixa4 */
			ent_Faixa_3.setVisible(false);/* Desativa Faixa_3 */

			/* Define a Cor das Faixas Inicais */
			ent_Faixa_1.setSelectedIndex(2);
			ent_Faixa_2.setSelectedIndex(2);
			ent_Mutiplicador.setSelectedIndex(1);
			ent_Tolerancia.setSelectedIndex(6);

			/* Chamadas dos Metodos Faixas e Tolerancia */
			Faixa1();
			Faixa2();
			FaixaMultiplicador();
			FaixaTolerancia();

			/* Chama o Método que Realiza o Calculo */
			CalcularCor();
		}/* Fim Resistor 4 Faixas */

		/**
		 * (5) - Resistor de 5 Faixas
		 */
		if (ent_Faixas.getSelectedIndex() == 2) {
			/* DeFine posição do Botão Mutiplicador */
			ent_Mutiplicador.setBounds(300, 241, 92, 25);
			ent_Faixa_3.setBounds(202, 241, 92, 25);

			/* Troca Posição no Resistor */
			imgFaixa3.setBounds(250, 65, 10, 58);
			imgFaixa4.setBounds(230, 65, 10, 58);

			/* Define Imagem do Resistor */
			imgResistor.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/res2.png")));

			imgFaixa4.setVisible(true);/* Ativa Faixa4 */
			ent_Faixa_3.setVisible(true);/* Ativa a Faixa 4 */

			/* Define a Cor das Faixas Inicais */
			ent_Faixa_1.setSelectedIndex(2);
			ent_Faixa_2.setSelectedIndex(2);
			ent_Faixa_3.setSelectedIndex(0);
			ent_Mutiplicador.setSelectedIndex(0);
			ent_Tolerancia.setSelectedItem(1);

			/* Chama os Métodos */
			Faixa1();
			Faixa2();
			Faixa3();
			FaixaMultiplicador();
			FaixaTolerancia();
		}/* Fim Resistor 5 Faixas */

	}/* Fim ConfigFaixa */

	/**
	 * CalcularCor - Esse Metodo Verifica as Faixas Selecionadas e Informa o
	 * Valor do Resistor.
	 */
	public void CalcularCor() {
		/**
		 * (3) - Calcula Cor do Resitor de Três Faixas.
		 */
		if (ent_Faixas.getSelectedIndex() == 0) {
			auxFaixa1 = String.valueOf(ent_Faixa_1.getSelectedIndex());
			auxFaixa2 = String.valueOf(ent_Faixa_2.getSelectedIndex());
			auxMultiplicador = Double.valueOf(ent_Mutiplicador
					.getSelectedIndex());

			/* Chama o método que Define a Tolerânça */
			DefineTolerancia();

			/* União dos dois valores */
			concatenacao = auxFaixa1 + auxFaixa2;
			/* Convete p/Double */
			auxConcatenacao = Double.valueOf(concatenacao);

			/* Verifica Mutiplicador */
			if (auxMultiplicador == 0) {/* PRETO */
				resultado = auxConcatenacao * 1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 1) {/* MARROM */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 10;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Chama o método que Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 2) {/* VERMELHO */
				resultado = auxConcatenacao * 100;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 3) {/* LARANJA */
				resultado = auxConcatenacao * 1000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 4) {/* AMARELO */
				resultado = auxConcatenacao * 10000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 5) {/* VERDE */
				resultado = auxConcatenacao * 100000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 6) {/* AZUL */
				resultado = auxConcatenacao * 1000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 7) {/* VIOLETA */
				resultado = auxConcatenacao * 10000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 8) {/* CINZA */
				resultado = auxConcatenacao * 100000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 9) {/* BRANCO */
				resultado = auxConcatenacao * 1000000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 10) {/* DOURADO */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 0.1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 11) {/* PRATEADO */
				resultado = auxConcatenacao * 0.01;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
		}/* FIM Resistor 3 */

		/**
		 * (4) - Calcula Cor do Resitor de Quatro Faixas.
		 */
		if (ent_Faixas.getSelectedIndex() == 1) {
			auxFaixa1 = String.valueOf(ent_Faixa_1.getSelectedIndex());
			auxFaixa2 = String.valueOf(ent_Faixa_2.getSelectedIndex());
			auxMultiplicador = Double.valueOf(ent_Mutiplicador
					.getSelectedIndex());

			/* Chama o método que Define a Tolerânça */
			DefineTolerancia();

			/* União dos dois valores */
			concatenacao = auxFaixa1 + auxFaixa2;
			/* Convete p/Double */
			auxConcatenacao = Double.valueOf(concatenacao);

			/* Verifica Mutiplicador */
			if (auxMultiplicador == 0) {/* PRETO */
				resultado = auxConcatenacao * 1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 1) {/* MARROM */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 10;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Chama o método que Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 2) {/* VERMELHO */
				resultado = auxConcatenacao * 100;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 3) {/* LARANJA */
				resultado = auxConcatenacao * 1000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 4) {/* AMARELO */
				resultado = auxConcatenacao * 10000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 5) {/* VERDE */
				resultado = auxConcatenacao * 100000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 6) {/* AZUL */
				resultado = auxConcatenacao * 1000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 7) {/* VIOLETA */
				resultado = auxConcatenacao * 10000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 8) {/* CINZA */
				resultado = auxConcatenacao * 100000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 9) {/* BRANCO */
				resultado = auxConcatenacao * 1000000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 10) {/* DOURADO */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 0.1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 11) {/* PRATEADO */
				resultado = auxConcatenacao * 0.01;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
		}/* FIM Resistor 4 */

		/**
		 * (5) - Calcula Cor do Resistor de Cinco Faixas.
		 */
		if (ent_Faixas.getSelectedIndex() == 2) {
			auxFaixa1 = String.valueOf(ent_Faixa_1.getSelectedIndex());
			auxFaixa2 = String.valueOf(ent_Faixa_2.getSelectedIndex());
			auxFaixa3 = String.valueOf(ent_Faixa_3.getSelectedIndex());
			auxMultiplicador = Double.valueOf(ent_Mutiplicador
					.getSelectedIndex());

			/* Chama o método que Define a Tolerânça */
			DefineTolerancia();

			/* União dos dois valores */
			concatenacao = auxFaixa1 + auxFaixa2 + auxFaixa3;
			/* Convete p/Double */
			auxConcatenacao = Double.valueOf(concatenacao);

			/* Verifica Mutiplicador */
			if (auxMultiplicador == 0) {/* PRETO */
				resultado = auxConcatenacao * 1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 1) {/* MARROM */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 10;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Chama o método que Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 2) {/* VERMELHO */
				resultado = auxConcatenacao * 100;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 3) {/* LARANJA */
				resultado = auxConcatenacao * 1000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado
						.setText(String.format("%.01f", resultado / 1000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 4) {/* AMARELO */
				resultado = auxConcatenacao * 10000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 5) {/* VERDE */
				resultado = auxConcatenacao * 100000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 6) {/* AZUL */
				resultado = auxConcatenacao * 1000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 7) {/* VIOLETA */
				resultado = auxConcatenacao * 10000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 8) {/* CINZA */
				resultado = auxConcatenacao * 100000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 9) {/* BRANCO */
				resultado = auxConcatenacao * 1000000000;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f",
						resultado / 1000000000));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 10) {/* DOURADO */
				/* Realiza Calculo */
				resultado = auxConcatenacao * 0.1;
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
			if (auxMultiplicador == 11) {/* PRATEADO */
				resultado = auxConcatenacao * 0.01;/* Realiza Calculo */
				/* Fomata as casas decimal e Exibe Resultado */
				saidaResultado.setText(String.format("%.01f", resultado));
				/* Define a Grandeza */
				DefineGrandeza();
			}
		}/* FIM Resistor 5 */

	}/* Fim Calcular */

	public void Faixa1() {
		/* Define Cor da Faixa_1 */
		if (ent_Faixa_1.getSelectedItem().equals("Preto")) {
			ent_Faixa_1.setBackground(new Color(0, 0, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPreto.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Marrom")) {
			ent_Faixa_1.setBackground(new Color(107, 66, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxMarrom.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Vermelho")) {
			ent_Faixa_1.setBackground(new Color(255, 0, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerm.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Laranja")) {
			ent_Faixa_1.setBackground(new Color(255, 140, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxLaranja.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Amarelo")) {
			ent_Faixa_1.setBackground(new Color(255, 255, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAmarelo.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Verde")) {
			ent_Faixa_1.setBackground(new Color(0, 255, 0));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerde.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Azul")) {
			ent_Faixa_1.setBackground(new Color(0, 0, 205));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAzul.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Violeta")) {
			ent_Faixa_1.setBackground(new Color(204, 50, 153));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVioleta.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Cinza")) {
			ent_Faixa_1.setBackground(new Color(108, 123, 139));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxCinza.png")));
		}
		if (ent_Faixa_1.getSelectedItem().equals("Branco")) {
			ent_Faixa_1.setBackground(new Color(255, 255, 255));
			imgFaixa1.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxBranco.png")));
		}
		/**/
		CalcularCor();
	}

	public void Faixa2() {
		/* Define Cor da Faixa_2 */
		if (ent_Faixa_2.getSelectedItem().equals("Preto")) {
			ent_Faixa_2.setBackground(new Color(0, 0, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPreto.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Marrom")) {
			ent_Faixa_2.setBackground(new Color(107, 66, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxMarrom.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Vermelho")) {
			ent_Faixa_2.setBackground(new Color(255, 0, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerm.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Laranja")) {
			ent_Faixa_2.setBackground(new Color(255, 140, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxLaranja.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Amarelo")) {
			ent_Faixa_2.setBackground(new Color(255, 255, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAmarelo.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Verde")) {
			ent_Faixa_2.setBackground(new Color(0, 255, 0));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerde.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Azul")) {
			ent_Faixa_2.setBackground(new Color(0, 0, 205));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAzul.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Violeta")) {
			ent_Faixa_2.setBackground(new Color(204, 50, 153));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVioleta.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Cinza")) {
			ent_Faixa_2.setBackground(new Color(108, 123, 139));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxCinza.png")));
		}
		if (ent_Faixa_2.getSelectedItem().equals("Branco")) {
			ent_Faixa_2.setBackground(new Color(255, 255, 255));
			imgFaixa2.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxBranco.png")));
		}
		/**/
		CalcularCor();
	}

	public void Faixa3() {
		/* Define Cor da Faixa_3 */
		if (ent_Faixa_3.getSelectedItem().equals("Preto")) {
			ent_Faixa_3.setBackground(new Color(0, 0, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPreto.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Marrom")) {
			ent_Faixa_3.setBackground(new Color(107, 66, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxMarrom.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Vermelho")) {
			ent_Faixa_3.setBackground(new Color(255, 0, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerm.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Laranja")) {
			ent_Faixa_3.setBackground(new Color(255, 140, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxLaranja.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Amarelo")) {
			ent_Faixa_3.setBackground(new Color(255, 255, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAmarelo.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Verde")) {
			ent_Faixa_3.setBackground(new Color(0, 255, 0));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerde.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Azul")) {
			ent_Faixa_3.setBackground(new Color(0, 0, 205));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAzul.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Violeta")) {
			ent_Faixa_3.setBackground(new Color(204, 50, 153));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVioleta.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Cinza")) {
			ent_Faixa_3.setBackground(new Color(108, 123, 139));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxCinza.png")));
		}
		if (ent_Faixa_3.getSelectedItem().equals("Branco")) {
			ent_Faixa_3.setBackground(new Color(255, 255, 255));
			imgFaixa4.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxBranco.png")));
		}
		/**/
		CalcularCor();
	}

	public void FaixaMultiplicador() {
		/* Define Cor da Faixa_4 */
		if (ent_Mutiplicador.getSelectedItem().equals("Preto")) {
			ent_Mutiplicador.setBackground(new Color(0, 0, 0));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPreto.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Marrom")) {
			ent_Mutiplicador.setBackground(new Color(107, 66, 0));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxMarrom.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Vermelho")) {
			ent_Mutiplicador.setBackground(new Color(255, 0, 0));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerm.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Laranja")) {
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxLaranja.png")));
			ent_Mutiplicador.setBackground(new Color(255, 140, 0));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Amarelo")) {
			ent_Mutiplicador.setBackground(new Color(255, 255, 0));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAmarelo.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Verde")) {
			ent_Mutiplicador.setBackground(new Color(0, 255, 0));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerde.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Azul")) {
			ent_Mutiplicador.setBackground(new Color(0, 0, 205));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAzul.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Violeta")) {
			ent_Mutiplicador.setBackground(new Color(0, 0, 205));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVioleta.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Cinza")) {
			ent_Mutiplicador.setBackground(new Color(108, 123, 139));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxCinza.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Branco")) {
			ent_Mutiplicador.setBackground(new Color(255, 255, 255));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxBranco.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Dourado")) {
			ent_Mutiplicador.setBackground(new Color(218, 165, 32));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxDourado.png")));
		}
		if (ent_Mutiplicador.getSelectedItem().equals("Prateado")) {
			ent_Mutiplicador.setBackground(new Color(108, 123, 139));
			imgFaixa3.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPrateado.png")));
		}
		/**/
		CalcularCor();
	}

	public void FaixaTolerancia() {
		/* Define Cor da Faixa de Tolerância */
		if (ent_Tolerancia.getSelectedItem().equals("Marrom")) {
			ent_Tolerancia.setBackground(new Color(107, 66, 0));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxMarrom.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Vermelho")) {
			ent_Tolerancia.setBackground(new Color(255, 0, 0));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerm.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Verde")) {
			ent_Tolerancia.setBackground(new Color(0, 255, 0));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVerde.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Azul")) {
			ent_Tolerancia.setBackground(new Color(0, 0, 205));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxAzul.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Violeta")) {
			ent_Tolerancia.setBackground(new Color(204, 50, 153));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxVioleta.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Cinza")) {
			ent_Tolerancia.setBackground(new Color(108, 123, 139));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxCinza.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Dourado")) {
			ent_Tolerancia.setBackground(new Color(218, 165, 32));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxDourado.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Prateado")) {
			ent_Tolerancia.setBackground(new Color(139, 134, 130));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxPrateado.png")));
		}
		if (ent_Tolerancia.getSelectedItem().equals("Nenhum")) {
			ent_Tolerancia.setBackground(new Color(255, 255, 255));
			imgTolerancia.setIcon(new ImageIcon(JResistor.class
					.getResource("/br/com/jresistor/img/fxNenhum.png")));
		}

		/**/
		CalcularCor();
	}

	/**
	 * DefineTolerancia.
	 */
	public void DefineTolerancia() {
		if (ent_Tolerancia.getSelectedIndex() == 0) {
			saidaToleranca.setText("+/- 1%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 1) {
			saidaToleranca.setText("+/- 2%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 2) {
			saidaToleranca.setText("+/- 0.5%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 3) {
			saidaToleranca.setText("+/- 0.25%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 4) {
			saidaToleranca.setText("+/- 0.1%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 5) {
			saidaToleranca.setText("+/- 0.05%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 6) {
			saidaToleranca.setText("+/- 5%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 7) {
			saidaToleranca.setText("+/- 10%");
		}
		if (ent_Tolerancia.getSelectedIndex() == 8) {
			saidaToleranca.setText("+/- 20%");
		}
	}/* Fim DefineTolerancia */

	/**
	 * DefineGrandeza.
	 */
	public void DefineGrandeza() {
		/* Dourado e Prateado */
		if (resultado < 1) {
			saidaGrandeza.setText("Ω");
		}
		/* Preto, Marrom e Vermelho */
		if (resultado >= 1 && resultado < 1000) {
			saidaGrandeza.setText("Ω");
		}
		/* Laranja, Amarelo e Verde */
		if (resultado >= 1000 && resultado < 1000000) {
			saidaGrandeza.setText("K Ω");
		}
		/* Azul, Violeta e Cinza */
		if (resultado >= 1000000 && resultado < 1000000000) {
			saidaGrandeza.setText("M Ω");
		}
		/* Branco */
		if (resultado >= 1000000000) {
			saidaGrandeza.setText("G Ω");
		}
	}
}/* Fim Class */
