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
import java.util.Random;

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
	
	static Pergunta listaDePerguntas;
	static Pergunta perguntaAtual;
	

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
		if(listaDePerguntas == null) {
			prepararPerguntas();
			perguntaAtual = listaDePerguntas;
		} else {
			perguntaAtual = perguntaAtual.proxima;
		}
		textProva.setText(perguntaAtual.textoQuestao);
		
		JCheckBox[] alternativa = new JCheckBox[5];
		alternativa[0] = checkBoxRespostaA;
		alternativa[1] = checkBoxRespostaB;
		alternativa[2] = checkBoxRespostaC;
		alternativa[3] = checkBoxRespostaD;
		alternativa[4] = checkBoxRespostaE;
		
		Resposta respostaAtual = perguntaAtual.respostas;
		
		for(int i = 0; i < alternativa.length; i++) {
			alternativa[i].setText(respostaAtual.textoResposta);
			respostaAtual = respostaAtual.proxima;
		}
	}
	
	static void prepararPerguntas() {
		try {
			Statement conexaoComOBanco = conectarComOBancoDeDados();			
			ResultSet rSetPerguntas = obterQuestoes(conexaoComOBanco);

			while(rSetPerguntas.next()) {
				ResultSet rSetRespostas = obterRespostas(rSetPerguntas.getString(1), conectarComOBancoDeDados());
				String[] respostas = new String[5];
				int count = 1;
				
				for(int i = 0; i < respostas.length; i++) {
					if(rSetRespostas.next()) {
						if(rSetRespostas.getBoolean(2)) {
							respostas[0] = rSetRespostas.getString(1);
						} else {
							respostas[count] = rSetRespostas.getString(1);
							count++;
						}						
					} else {
						throw new RuntimeException("Numero de respostas recebidas menor que 5");
					}
				}
				if(listaDePerguntas == null) {
					listaDePerguntas = new Pergunta();
					listaDePerguntas.textoQuestao = rSetPerguntas.getString(2);
					listaDePerguntas.inserirRespostas(respostas);
				} else {
					listaDePerguntas.inserirQuestao(listaDePerguntas, rSetPerguntas.getString(2), respostas);
				}				
			}
			listaDePerguntas = Pergunta.embaralharPerguntas(listaDePerguntas);
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao preparar perguntas e respostas", e);
		}
	}
	
	static Statement conectarComOBancoDeDados() {
		try {
			ConexaoBd conexaoComOBanco = new ConexaoBd();
			Statement st = conexaoComOBanco.obterConexao().createStatement();
			
			return st;
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao solicitar conexão com o banco de dados", e);
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
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Humanas%' OR m.\"NOME\" LIKE '%HUMANAS%'");
				if(rs.next()) {
					String id = rs.getString(1);
					materiasEscolhidas = "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxCienciasDaNatureza.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Natureza%' OR m.\"NOME\" LIKE '%NATUREZA%'");
				if(rs.next()) {
					if(materiasEscolhidas.equals("") == false) {
						materiasEscolhidas = materiasEscolhidas + "OR ";
					}
					String id = rs.getString(1);
					materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxLinguagensECodigos.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Linguagens%' OR m.\"NOME\" LIKE '%LINGUAGENS%'");
				if(rs.next()) {
					if(materiasEscolhidas.equals("") == false) {
						materiasEscolhidas = materiasEscolhidas + "OR ";
					}
					String id = rs.getString(1);
					materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
				}
			}
			if(checkBoxMatematica.isSelected()) {
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%Matematica%' OR m.\"NOME\" LIKE '%MATEMATICA%'");
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

class Pergunta {
	String textoQuestao;
	Resposta respostas;
	Pergunta anterior;
	Pergunta proxima;
	
	public Pergunta() {
		 //               //
		//  ( ⚆ _ ⚆ )   //
	}
	
	public Pergunta(String textoDaQuestao, String[] textoRespostas) {
		textoQuestao = textoDaQuestao;
		inserirRespostas(textoRespostas);
	}
	
	public void inserirQuestao(Pergunta head, String textoDaQuestao, String[] textoRespostas) {
		if(head == null) {
			throw new RuntimeException("Falha ao adicionar Questao. Head == null");
		} else {
			if(head.proxima == null) {
				head.proxima = new Pergunta();
				head.proxima.textoQuestao = textoDaQuestao;
				head.proxima.anterior = head;
				head.proxima.inserirRespostas(textoRespostas);
			} else {
				inserirQuestao(head.proxima, textoDaQuestao, textoRespostas);
			}
		}
	}
	
	public static Pergunta embaralharPerguntas(Pergunta head) {
		Pergunta misturadasHead = null;
		
		Pergunta[] arrayPerguntas = head.perguntasParaArray(head);
		boolean[] tabAux = new boolean[arrayPerguntas.length];
		
		for(int i = 0; i < arrayPerguntas.length; i++) {
			int numAleatorio = aleatorio(tabAux.length);
			while(true) {
				if(tabAux[numAleatorio]) {
					numAleatorio = funcaoQueEuNaoSeiComoChamar(tabAux.length, numAleatorio);
				} else {
					if(misturadasHead == null) {
						misturadasHead = new Pergunta();
						String[] textoRespostas = arrayPerguntas[numAleatorio].respostasParaArray();
						
						misturadasHead.textoQuestao = arrayPerguntas[numAleatorio].textoQuestao;
						misturadasHead.inserirRespostas(textoRespostas);
						misturadasHead.embaralharRespostas();
					} else {
						String[] textoRespostas = arrayPerguntas[numAleatorio].respostasParaArray();
						misturadasHead.inserirQuestao(misturadasHead, arrayPerguntas[numAleatorio].textoQuestao, textoRespostas);
					}
					tabAux[numAleatorio] = true;
					break;
				}
			}
		}		
		return misturadasHead;
	}
	
	public void embaralharRespostas() {		
		Resposta misturadasHead = null;
		int quantRespostas = contarRespostas();
		Resposta[] arrayResposta = new Resposta[quantRespostas];
		boolean[] tabAux = new boolean[quantRespostas];
		Resposta atual = respostas;

		for(int i = 0; i < arrayResposta.length; i++) {
			arrayResposta[i] = atual;
			atual = atual.proxima;
		}

		for(int i = 0; i < quantRespostas; i++) {
			int numAleatorio = aleatorio(quantRespostas);
			while(true) {
				if(tabAux[numAleatorio]) {
					numAleatorio = funcaoQueEuNaoSeiComoChamar(tabAux.length, numAleatorio);
				} else {
					if(misturadasHead == null) {
						misturadasHead = new Resposta(arrayResposta[numAleatorio].textoResposta, arrayResposta[numAleatorio].correta);
						atual = misturadasHead;
					} else {
						atual.proxima = new Resposta(arrayResposta[numAleatorio].textoResposta, arrayResposta[numAleatorio].correta);
						atual = atual.proxima;
					}
					tabAux[numAleatorio] = true;
					break;
				}
			}
		}
		respostas = misturadasHead;
	}
	
	public void inserirRespostas(String[] textoRespostas) {
		respostas = new Resposta(textoRespostas[0], true);
		Resposta respostaAtual = respostas;		
		
		for(int i = 1; i < textoRespostas.length; i++) {
			respostaAtual.proxima = new Resposta(textoRespostas[i], false);
			respostaAtual = respostaAtual.proxima;
		}
	}
	
	public int contarPerguntas(Pergunta head) {
		int count = 0;
		Pergunta atual = head;
		
		while(atual != null) {
			atual = atual.proxima;
			count++;
		}
		return count;
	}
	
	private Pergunta[] perguntasParaArray(Pergunta head) {
		int quantDePerguntas = contarPerguntas(head);
		Pergunta[] arrayPerguntas = new Pergunta[quantDePerguntas];
		Pergunta perguntaAtual = head;
		
		for(int i = 0; i < arrayPerguntas.length; i++) {
			arrayPerguntas[i] = perguntaAtual;
			perguntaAtual = perguntaAtual.proxima;
		}
		
		return arrayPerguntas;
	}
	
	private String[] respostasParaArray() {
		String[] arrayRespostas = new String[contarRespostas()];
		Resposta atual = respostas;
		int correta = 0;

		for(int i = 0; i < arrayRespostas.length; i++) {
			if(atual.correta) {
				correta = i;
			} 
			arrayRespostas[i] = atual.textoResposta;
			atual = atual.proxima;
		}
		if(correta != 0) {
			String tmp = arrayRespostas[0];
			arrayRespostas[0] = arrayRespostas[correta];
			arrayRespostas[correta] = tmp;
		}
		return arrayRespostas;
	}
	
	private int contarRespostas() {
		int count = 0;
		Resposta atual = respostas;
		
		while(atual != null) {
			atual = atual.proxima;
			count++;
		}
		return count;
	}
	
	private static int aleatorio(int limite) {
		Random aleatorio = new Random();
		int numAleatorio;
		
		if(limite == 1) {
			numAleatorio = aleatorio.nextInt(limite);
		} else {
			numAleatorio = aleatorio.nextInt(limite - 1);
		}
		return numAleatorio;
	}
	
	private static int funcaoQueEuNaoSeiComoChamar(int tamanho, int atual) {
		if(atual == tamanho - 1) {
			return 0;
		} else {
			return atual + 1;
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
