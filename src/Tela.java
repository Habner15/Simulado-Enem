import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Enem {

	static JFrame fTela = new JFrame("Simulado Enem");

	static JPanel painelInicial = new JPanel();
	static JPanel painelMateria = new JPanel();
	static JPanel painelNivel = new JPanel();
	static JPanel painelQuantidade = new JPanel();
	static JPanel painelProva = new JPanel();

	static JLabel lLogo = new JLabel();
	static JLabel lEscritaMateria = new JLabel("Selecione as Provas Desejadas:");
	static JLabel lEscritaNivel = new JLabel("Selecione o Nível das Questões:");
	static JLabel lEscritaQuantidade = new JLabel("Selecione o Quantidade das Questões:");

	static ImageIcon imageIcone = new ImageIcon("Imagens\\iconeEnem.jpg");
	static ImageIcon imageLogo = new ImageIcon("Imagens\\logo.png");

	static JButton btnVoltarParaLogo = new JButton("Voltar");
	static JButton btnAvancarParaMateria = new JButton("Iniciar");
	static JButton btnVoltarParaMateria = new JButton("Voltar");
	static JButton btnAvancarParaNivel = new JButton("Avançar");
	static JButton btnVoltarParaNivel = new JButton("Voltar");
	static JButton btnAvancarParaQuantidade = new JButton("Avançar");
	static JButton btnVoltarParaQuantidade = new JButton("Voltar");
	static JButton btnAvancarParaProva = new JButton("Avançar");

	static JCheckBox checkBoxCienciasHumanas = new JCheckBox("Ciências Humanas e suas Tecnologias");
	static JCheckBox checkBoxCienciasDaNatureza = new JCheckBox("Ciências da Natureza e suas Tecnologias");
	static JCheckBox checkBoxLinguagensECodigos = new JCheckBox("Linguagens, Códigos e suas Tecnologias");
	static JCheckBox checkBoxMatematica = new JCheckBox("Matemática e suas Tecnologias");
	static JCheckBox checkBoxFacil = new JCheckBox("Fácil");
	static JCheckBox checkBoxMedio = new JCheckBox("Médio");
	static JCheckBox checkBoxDificil = new JCheckBox("Difícil");
	static JCheckBox checkBoxDez = new JCheckBox("10");
	static JCheckBox checkBoxVinte = new JCheckBox("20");
	static JCheckBox checkBoxTrinta = new JCheckBox("30");
	static JCheckBox checkBoxQuarenta = new JCheckBox("40");
	static JCheckBox checkBoxCinquenta = new JCheckBox("50");
	static JCheckBox checkBoxSessenta = new JCheckBox("60");
	static JCheckBox checkBoxSetenta = new JCheckBox("70");
	static JCheckBox checkBoxOitenta = new JCheckBox("80");
	static JCheckBox checkBoxNoventa = new JCheckBox("90");
	static JCheckBox checkBoxRespostaA = new JCheckBox();
	static JCheckBox checkBoxRespostaB = new JCheckBox();
	static JCheckBox checkBoxRespostaC = new JCheckBox();
	static JCheckBox checkBoxRespostaD = new JCheckBox();
	static JCheckBox checkBoxRespostaE = new JCheckBox();

	static JTextArea textProva = new JTextArea();

	static Toolkit kit;
	static Dimension tamanhoMonitor;
	static int minhaLargura;
	static int minhaAltura;
	static int botaoAltura;
	static int tamanho;
	static double botaoLargura;
	
	static ResultSet rSetPerguntas;
	static ResultSet rSetRespostas;

	public Enem() {

		// Configurações do Frame

		fTela.add(painelInicial);
		fTela.setIconImage(imageIcone.getImage());
		fTela.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		fTela.getContentPane().setBackground(Color.lightGray);
		fTela.setTitle("Simulado Enem");
		fTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fTela.setLayout(null);
		fTela.setVisible(true);

		// Configurações de resolução

		kit = Toolkit.getDefaultToolkit();
		tamanhoMonitor = kit.getScreenSize();
		minhaLargura = tamanhoMonitor.width;
		minhaAltura = tamanhoMonitor.height;
		botaoLargura = minhaLargura / 4;
		botaoAltura = minhaAltura / 15;
		tamanho = minhaLargura * minhaAltura / 60000;

		// Configurações do Painel Logo

		lLogo.setIcon(imageLogo);
		painelInicial.add(lLogo);
		painelInicial.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30), (int) (minhaLargura / 1.2),
				(int) (minhaAltura / 1.4));
		painelInicial.setBackground(Color.WHITE);
		painelInicial.setVisible(true);

		// Botões

		fTela.add(btnAvancarParaMateria).setBounds((int) (minhaLargura / 2.69), (int) (minhaAltura / 1.3),
				(int) botaoLargura, botaoAltura);

		Botoes();

	}

	public static void Materia() {

		// Label

		painelMateria.add(lEscritaMateria).setBounds((int) (minhaLargura / 4.1), minhaAltura / 990,
				(int) (botaoLargura * 3), (int) botaoAltura * 3);
		lEscritaMateria.setForeground(new Color(30, 50, 100));

		// CheckBox

		painelMateria.add(checkBoxCienciasHumanas).setBounds((int) (minhaLargura / 3.42), minhaAltura / 5,
				(int) ((int) botaoLargura / 1.01), (tamanho));
		painelMateria.add(checkBoxCienciasDaNatureza).setBounds((int) (minhaLargura / 3.42), (int) (minhaAltura / 3.75),
				(int) ((int) botaoLargura / 0.95), (tamanho));
		painelMateria.add(checkBoxLinguagensECodigos).setBounds((int) (minhaLargura / 3.42), minhaAltura / 3,
				(int) ((int) botaoLargura / 0.96), (tamanho));
		painelMateria.add(checkBoxMatematica).setBounds((int) (minhaLargura / 3.42), (int) (minhaAltura / 2.5),
				(int) ((int) botaoLargura / 1.2), (tamanho));

		// Botões

		painelMateria.add(btnVoltarParaLogo).setBounds(minhaLargura / 13, (int) (minhaAltura / 1.8), (int) botaoLargura,
				botaoAltura);
		painelMateria.add(btnAvancarParaNivel).setBounds(minhaLargura / 2, (int) (minhaAltura / 1.8),
				(int) botaoLargura, botaoAltura);

		// Configurações do Painel Materia

		painelMateria.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30), (int) (minhaLargura / 1.2),
				(int) (minhaAltura / 1.4));
		painelMateria.setBackground(Color.WHITE);
		painelMateria.setLayout(null);
		painelMateria.setVisible(true);

		// Configurações do Frame

		fTela.add(painelMateria);

	}

	public static void Nivel() {

		// Label

		painelNivel.add(lEscritaNivel).setBounds((int) (minhaLargura / 4.1), minhaAltura / 990,
				(int) (botaoLargura * 3), (int) botaoAltura * 3);
		lEscritaNivel.setForeground(new Color(30, 50, 100));

		// CheckBox

		painelNivel.add(checkBoxFacil).setBounds((int) (minhaLargura / 2.57), (int) (minhaAltura / 4.2),
				(int) ((int) botaoLargura / 5.5), (tamanho));
		painelNivel.add(checkBoxMedio).setBounds((int) (minhaLargura / 2.57), (int) (minhaAltura / 3.2),
				(int) ((int) botaoLargura / 4.5), (tamanho));
		painelNivel.add(checkBoxDificil).setBounds((int) (minhaLargura / 2.57), (int) (minhaAltura / 2.6),
				(int) ((int) botaoLargura / 4.5), (tamanho));

		// Botões

		painelNivel.add(btnVoltarParaMateria).setBounds(minhaLargura / 13, (int) (minhaAltura / 1.8),
				(int) botaoLargura, botaoAltura);
		painelNivel.add(btnAvancarParaQuantidade).setBounds(minhaLargura / 2, (int) (minhaAltura / 1.8),
				(int) botaoLargura, botaoAltura);

		// Configurações Painel Nivel

		painelNivel.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30), (int) (minhaLargura / 1.2),
				(int) (minhaAltura / 1.4));
		painelNivel.setBackground(Color.WHITE);
		painelNivel.setLayout(null);
		painelNivel.setVisible(true);

		// Configurações do Frame

		fTela.add(painelNivel);
	}

	public static void Quantidade() {

		// Label

		painelQuantidade.add(lEscritaQuantidade).setBounds((int) (minhaLargura / 4.68), minhaAltura / 990,
				(int) (botaoLargura * 3), (int) botaoAltura * 3);
		lEscritaQuantidade.setForeground(new Color(30, 50, 100));

		// CheckBox

		painelQuantidade.add(checkBoxDez).setBounds((int) (minhaLargura / 3.3), (int) (minhaAltura / 4.7),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxVinte).setBounds((int) (minhaLargura / 3.3), (int) (minhaAltura / 3.2),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxTrinta).setBounds((int) (minhaLargura / 3.3), (int) (minhaAltura / 2.44),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxQuarenta).setBounds((int) (minhaLargura / 2.49), (int) (minhaAltura / 4.7),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxCinquenta).setBounds((int) (minhaLargura / 2.49), (int) (minhaAltura / 3.2),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxSessenta).setBounds((int) (minhaLargura / 2.49), (int) (minhaAltura / 2.44),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxSetenta).setBounds((minhaLargura / 2), (int) (minhaAltura / 4.7),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxOitenta).setBounds((minhaLargura / 2), (int) (minhaAltura / 3.2),
				((int) botaoLargura / 8), (tamanho));
		painelQuantidade.add(checkBoxNoventa).setBounds((minhaLargura / 2), (int) (minhaAltura / 2.44),
				((int) botaoLargura / 8), (tamanho));

		// Botões

		painelQuantidade.add(btnVoltarParaNivel).setBounds(minhaLargura / 13, (int) (minhaAltura / 1.8),
				(int) botaoLargura, botaoAltura);
		painelQuantidade.add(btnAvancarParaProva).setBounds(minhaLargura / 2, (int) (minhaAltura / 1.8),
				(int) botaoLargura, botaoAltura);

		// Configurações Painel Quantidade

		painelQuantidade.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30), (int) (minhaLargura / 1.2),
				(int) (minhaAltura / 1.4));
		painelQuantidade.setBackground(Color.WHITE);
		painelQuantidade.setLayout(null);
		painelQuantidade.setVisible(true);

		// Configurações do Frame

		fTela.add(painelQuantidade);
	}

	public static void Prova() {

		// Label
		painelProva.add(textProva).setBounds((int) (minhaLargura / 4.68), minhaAltura / 990, (int) (botaoLargura * 3),
				(int) botaoAltura * 3);
		textProva.setEditable(false);

		// CheckBox

		painelProva.add(checkBoxRespostaA).setBounds(200, 150, 500, 50);
		painelProva.add(checkBoxRespostaB).setBounds(200, 250, 500, 50);
		painelProva.add(checkBoxRespostaC).setBounds(200, 350, 500, 50);
		painelProva.add(checkBoxRespostaD).setBounds(200, 450, 500, 50);
		painelProva.add(checkBoxRespostaE).setBounds(200, 550, 500, 50);

		// Botoes

		// Configurações da Prova

		painelProva.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30), (int) (minhaLargura / 1.2),
				(int) (minhaAltura / 1.4));
		painelProva.setBackground(Color.WHITE);
		painelProva.setLayout(null);
		painelProva.setVisible(true);

		// Configurações do Frame

		fTela.add(painelProva);
	}

	public static void Botoes() {

		btnVoltarParaLogo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelInicial.setVisible(true);
				painelMateria.setVisible(false);
				btnAvancarParaMateria.setVisible(true);
			}
		});

		btnAvancarParaMateria.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Materia();
				painelInicial.setVisible(false);
				btnAvancarParaMateria.setVisible(false);
			}
		});

		btnVoltarParaMateria.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Materia();
				painelNivel.setVisible(false);
			}
		});

		btnAvancarParaNivel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Nivel();
				painelMateria.setVisible(false);
			}
		});

		btnVoltarParaNivel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Nivel();
				painelQuantidade.setVisible(false);
			}
		});

		btnAvancarParaQuantidade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Quantidade();
				painelNivel.setVisible(false);
			}
		});

		btnVoltarParaQuantidade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Quantidade();
				painelProva.setVisible(false);
			}
		});

		btnAvancarParaProva.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				atualizarTelaProva();
				Prova();
				painelQuantidade.setVisible(false);
			}
		});
	}

	static void atualizarTelaProva() {
		try {
			Statement conexaoComOBanco = conectarComOBancoDeDados();
			
			if (rSetPerguntas == null) {
				rSetPerguntas = obterQuestoes(conexaoComOBanco);
			}
			if(rSetPerguntas.next()) {
				rSetRespostas = obterRespostas(rSetPerguntas.getString(1), conectarComOBancoDeDados());
				
				textProva.setText(rSetPerguntas.getString(2));
				
				JCheckBox[] alternativa = new JCheckBox[5];
				alternativa[0] = checkBoxRespostaA;
				alternativa[1] = checkBoxRespostaB;
				alternativa[2] = checkBoxRespostaC;
				alternativa[3] = checkBoxRespostaD;
				alternativa[4] = checkBoxRespostaE;
				
				for(int i = 0; i < alternativa.length; i++) {
					if(rSetRespostas.next()) {
						alternativa[i].setText(rSetRespostas.getString(1));
					} else {
						throw new RuntimeException("Numero de respostas recebidas menor que 5");
					}
				}
			} else {
				throw new RuntimeException("Numero insuficiente de questões recebidas do banco");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao carregar questões e respostas na tela. Erro: ", e);
		}
	}
	
	static Statement conectarComOBancoDeDados() {
		try {
			ConexaoBd conexaoComOBanco = new ConexaoBd();
			Statement st = conexaoComOBanco.obterConexao().createStatement();
			
			return st;
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao solicitar conexão com o banco de dados. Erro: ", e);
		}
		
	}
	
	static ResultSet obterQuestoes(Statement conexaoComOBanco) {
		try {
			String materiasEscolhidas = verificarMateriasEscolhidas(conexaoComOBanco);
			
			if(materiasEscolhidas.equals("")) {
				throw new RuntimeException("Nenhuma materia recebida");
			} else {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\", \"QUESTAO\" FROM \"QUESTOES\" WHERE " + materiasEscolhidas);
				return rs;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao carregar questão. Erro: ", e);
		}
	}
	
	static ResultSet obterRespostas(String idQuestao, Statement conexaoComOBanco) {
		try {
			ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"RESPOSTA\", \"CORRETA\" FROM \"RESPOSTAS\" WHERE \"QUESTAO\" = " + idQuestao);
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao carregar resposta. Erro: ", e);
		}
	}
	
	static String verificarMateriasEscolhidas(Statement conexaoComOBanco) {
		String materiasEscolhidas = "";

		try {
			if(checkBoxCienciasHumanas.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Humanas%'");
				if(rs.next()) {
					String id = rs.getString(1);
					materiasEscolhidas = "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxCienciasDaNatureza.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Natureza%'");
				if(rs.next()) {
					if(materiasEscolhidas.equals("") == false) {
						materiasEscolhidas = materiasEscolhidas + "OR ";
					}
					String id = rs.getString(1);
					materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxLinguagensECodigos.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Linguagens%'");
				if(rs.next()) {
					if(materiasEscolhidas.equals("") == false) {
						materiasEscolhidas = materiasEscolhidas + "OR ";
					}
					String id = rs.getString(1);
					materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxMatematica.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Matematica%'");
				if(rs.next()) {
					if(materiasEscolhidas.equals("") == false) {
						materiasEscolhidas = materiasEscolhidas + "OR ";
					}
					String id = rs.getString(1);
					materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao procurar materias. Verifique o banco de dados ", e);
		}
		return materiasEscolhidas;
	}
	public static void main(String[] args) {
		new Enem();
	}
}

class Questao {
	String textoQuestao;
	Resposta respostas;
	Questao anterior;
	Questao proxima;
	
	public void inserirQuestao(Questao head, String textoDaQuestao, String[] textoRespostas) {
		if(head == null) {
			throw new RuntimeException("Falha ao adicionar Questao. Head == null");
		} else {
			if(head.proxima == null) {
				head.proxima = new Questao();
				head.proxima.textoQuestao = textoDaQuestao;
				head.proxima.anterior = head;
				head.proxima.inserirRespostas(textoRespostas);
			} else {
				inserirQuestao(head.proxima, textoDaQuestao, textoRespostas);
			}
		}
	}
	
	private void inserirRespostas(String[] textoRespostas) {
		Resposta respostaAtual = null;
		for(int i = 0; i < textoRespostas.length; i++) {
			if(respostas == null) {
				respostas = new Resposta(textoRespostas[i], true);
				respostaAtual = respostas;
			} else {
				respostaAtual.proxima = new Resposta(textoRespostas[i], false);
				respostaAtual = respostaAtual.proxima;
			}
		}
	}
}

class Resposta {
	String textoResposta;
	Resposta proxima;
	boolean correta;
	
	public Resposta(String textoDaResposta, boolean respostaCerta) {
		textoResposta = textoDaResposta;
		correta = respostaCerta;
	}
}

class ConexaoBd {
	
	private static final String URL_BANCO_DE_DADOS = "jdbc:postgresql://localhost/Enem";
	private static final String LOGIN_BANCO_DE_DADOS = "postgres";
	private static final String SENHA_BANCO_DE_DADOS = "123";
	
	private Connection conexaoBd;
	
	public ConexaoBd() {
		try {
			conexaoBd = DriverManager.getConnection(URL_BANCO_DE_DADOS, LOGIN_BANCO_DE_DADOS, SENHA_BANCO_DE_DADOS);
		} catch (SQLException e){
			throw new RuntimeException("Falha ao conectar com o banco. Erro: ", e);
		}
	}
	
	public Connection obterConexao() {
		return conexaoBd;
	}
	
	public void fecharConexao() {
		try {
			conexaoBd.close();
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao desconectar o banco. Erro: ", e);
		}
	}

}
