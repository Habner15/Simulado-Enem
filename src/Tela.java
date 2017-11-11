import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class Tela {

	static JFrame fTela = new JFrame("Simulado Enem");
	static JPanel painelLogo = new JPanel();
	static JLabel lLogo = new JLabel();
	static ImageIcon imageIcone = new ImageIcon("Imagens\\iconeEnem.jpg");
	ImageIcon imageLogo = new ImageIcon("Imagens\\logo.png");
	static JButton btnComecar = new JButton("Começar");
	static JButton btnRecomecar = new JButton("Recomeçar");
	
	
	static JPanel painelMateria = new JPanel();
	static JLabel lEscritaMateria = new JLabel("Selecione as Provas Desejadas:");
	static JCheckBox checkBoxCienciasHumanas = new JCheckBox("Ciências Humanas e suas Tecnologias");
	static JCheckBox checkBoxCienciasDaNatureza = new JCheckBox("Ciências da Natureza e suas Tecnologias");
	static JCheckBox checkBoxLinguagensECodigos = new JCheckBox("Linguagens, Códigos e suas Tecnologias");
	static JCheckBox checkBoxMatematica = new JCheckBox("Matemática e suas Tecnologias");
	static JButton btnVoltarParaLogo = new JButton("Voltar");
	static JButton btnAvancarParaNivel = new JButton("Avançar");
	
	static JPanel painelNivel = new JPanel();
	static JLabel lEscritaNivel = new JLabel("Selecione o Nível das Questões:");
	static JCheckBox checkBoxFacil = new JCheckBox("Fácil");
	static JCheckBox checkBoxMedio = new JCheckBox("Médio");
	static JCheckBox checkBoxDificil = new JCheckBox("Difícil");
	static JButton btnVoltarParaMateria = new JButton("Voltar");
	static JButton btnAvancarParaQuantidadeQuestao = new JButton("Avançar");
	
	static JPanel painelQuantidade = new JPanel();
	static JLabel lEscritaQuantidade = new JLabel("Selecione o Quantidade das Questões:");
	static JCheckBox checkBoxDez = new JCheckBox("10");
	static JCheckBox checkBoxVinte = new JCheckBox("20");
	static JCheckBox checkBoxTrinta = new JCheckBox("30");
	static JCheckBox checkBoxQuarenta = new JCheckBox("40");
	static JCheckBox checkBoxCinquenta = new JCheckBox("50");
	static JCheckBox checkBoxSessenta = new JCheckBox("60");
	static JCheckBox checkBoxSetenta = new JCheckBox("70");
	static JCheckBox checkBoxOitenta = new JCheckBox("80");
	static JCheckBox checkBoxNoventa = new JCheckBox("90");
	
	static JPanel painelProva = new JPanel();
	static JTextArea textProva = new JTextArea();
	static JCheckBox checkBoxRespostaA = new JCheckBox();
	static JCheckBox checkBoxRespostaB = new JCheckBox();
	static JCheckBox checkBoxRespostaC = new JCheckBox();
	static JCheckBox checkBoxRespostaD = new JCheckBox();
	static JCheckBox checkBoxRespostaE = new JCheckBox();

	static JButton btnVoltarParaNivel = new JButton("Voltar");
	static JButton btnAvancarParaProva = new JButton("Avançar");

	static Toolkit kit;
	static Dimension tamanhoMonitor;
	static int minhaLargura;
	static int minhaAltura;
	static double botaoLargura;
	static int botaoAltura;
	static int tamanho;
	
	static ResultSet rSetPerguntas;
	static ResultSet rSetRespostas;

	public Tela() {	
		
		kit = Toolkit.getDefaultToolkit();
		tamanhoMonitor = kit.getScreenSize();
		minhaLargura = tamanhoMonitor.width;
		minhaAltura = tamanhoMonitor.height;
		botaoLargura = minhaLargura / 4;
		botaoAltura = minhaAltura / 15;
		tamanho = minhaLargura * minhaAltura / 60000;

		fTela.setIconImage(imageIcone.getImage());// icone da tela
		fTela.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		fTela.setLayout(null);

		fTela.add(btnComecar);
		btnComecar.setBounds((int) (minhaLargura / 2.69),
				(int) (minhaAltura / 1.3), (int) botaoLargura, botaoAltura);
		btnComecar.setFont(new Font("Cambria", Font.BOLD, tamanho));
		btnComecar.setBackground(Color.WHITE);
		btnComecar.setVisible(true);
		
		fTela.add(btnRecomecar);
		btnRecomecar.setBounds((int) (minhaLargura / 2.69),
				(int) (minhaAltura / 1.3), (int) botaoLargura, botaoAltura);
		btnRecomecar.setFont(new Font("Cambria", Font.BOLD, tamanho));
		btnRecomecar.setBackground(Color.WHITE);
		btnRecomecar.setVisible(false);
		
		
		
		fTela.add(painelLogo);
		painelLogo.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30),
				(int) (minhaLargura / 1.2), (int) (minhaAltura / 1.4));
		painelLogo.setBackground(Color.WHITE);
		painelLogo.setVisible(true);
		lLogo.setIcon(imageLogo);
		
		
		
		fTela.add(painelMateria);
		painelMateria.setLayout(null);
		painelMateria.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30),
				(int) (minhaLargura / 1.2), (int) (minhaAltura / 1.4));
		painelMateria.setBackground(Color.WHITE);
		painelMateria.setVisible(false);
		
		painelMateria.add(lEscritaMateria);
		lEscritaMateria.setBounds((int) (minhaLargura/4.1), minhaAltura/990,  
				(int) (botaoLargura * 3),(int) botaoAltura * 3);
		lEscritaMateria.setFont(new Font("Arial", Font.BOLD, (int) ((int) tamanho*1.5)));
		lEscritaMateria.setForeground(new Color(30, 50, 100));
		lEscritaMateria.setVisible(true);
		
		painelMateria.add(checkBoxCienciasHumanas);
		checkBoxCienciasHumanas.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxCienciasHumanas.setBounds((int) (minhaLargura/3.42),minhaAltura/5,(int)((int) botaoLargura/1.01), (tamanho));
		
		painelMateria.add(checkBoxCienciasDaNatureza);
		checkBoxCienciasDaNatureza.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxCienciasDaNatureza.setBounds((int) (minhaLargura/3.42),(int) (minhaAltura/3.75),(int)((int) botaoLargura/0.95), (tamanho));
		
		painelMateria.add(checkBoxLinguagensECodigos);
		checkBoxLinguagensECodigos.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxLinguagensECodigos.setBounds((int) (minhaLargura/3.42),minhaAltura/3,(int)((int) botaoLargura/0.96), (tamanho));

		painelMateria.add(checkBoxMatematica);
		checkBoxMatematica.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxMatematica.setBounds((int) (minhaLargura/3.42),(int) (minhaAltura/2.5),(int)((int) botaoLargura/1.2), (tamanho));

		painelMateria.add(btnVoltarParaLogo);
		btnVoltarParaLogo.setBounds(minhaLargura/13,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnVoltarParaLogo.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnVoltarParaLogo.setBackground(Color.WHITE);
		btnVoltarParaLogo.setVisible(true);
		
		painelMateria.add(btnAvancarParaNivel);
		btnAvancarParaNivel.setBounds(minhaLargura/2,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnAvancarParaNivel.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnAvancarParaNivel.setBackground(Color.WHITE);
		btnAvancarParaNivel.setVisible(true);
		
		
		fTela.add(painelNivel);
		painelNivel.setLayout(null);
		painelNivel.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30),
				(int) (minhaLargura / 1.2), (int) (minhaAltura / 1.4));
		painelNivel.setBackground(Color.WHITE);
		painelNivel.setVisible(false);
		
		painelNivel.add(lEscritaNivel);
		lEscritaNivel.setBounds((int) (minhaLargura/4.1), minhaAltura/990,  
				(int) (botaoLargura * 3),(int) botaoAltura * 3);
		lEscritaNivel.setFont(new Font("Arial", Font.BOLD, (int) ((int) tamanho*1.5)));
		lEscritaNivel.setForeground(new Color(30, 50, 100));
		lEscritaNivel.setVisible(true);
		
		painelNivel.add(checkBoxFacil);
		checkBoxFacil.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxFacil.setBounds((int) (minhaLargura/2.57),(int) (minhaAltura/4.2),(int)((int) botaoLargura/5.5), (tamanho));
		
		painelNivel.add(checkBoxMedio);
		checkBoxMedio.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxMedio.setBounds((int) (minhaLargura/2.57),(int) (minhaAltura/3.2),(int)((int) botaoLargura/4.5), (tamanho));

		painelNivel.add(checkBoxDificil);
		checkBoxDificil.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxDificil.setBounds((int) (minhaLargura/2.57),(int) (minhaAltura/2.6),(int)((int) botaoLargura/4.5), (tamanho));

		painelNivel.add(btnVoltarParaMateria);
		btnVoltarParaMateria.setBounds(minhaLargura/13,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnVoltarParaMateria.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnVoltarParaMateria.setBackground(Color.WHITE);
		btnVoltarParaMateria.setVisible(true);
		
		painelNivel.add(btnAvancarParaQuantidadeQuestao);
		btnAvancarParaQuantidadeQuestao.setBounds(minhaLargura/2,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnAvancarParaQuantidadeQuestao.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnAvancarParaQuantidadeQuestao.setBackground(Color.WHITE);
		btnAvancarParaQuantidadeQuestao.setVisible(true);
		
		
		
		
		fTela.add(painelQuantidade);
		painelQuantidade.setLayout(null);
		painelQuantidade.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30),
				(int) (minhaLargura / 1.2), (int) (minhaAltura / 1.4));
		painelQuantidade.setBackground(Color.WHITE);
		painelQuantidade.setVisible(false);
		
		painelQuantidade.add(lEscritaQuantidade);
		lEscritaQuantidade.setBounds((int) (minhaLargura/4.68), minhaAltura/990,  
				(int) (botaoLargura * 3),(int) botaoAltura * 3);
		lEscritaQuantidade.setFont(new Font("Arial", Font.BOLD, (int) ((int) tamanho*1.5)));
		lEscritaQuantidade.setForeground(new Color(30, 50, 100));
		lEscritaQuantidade.setVisible(true);
		
		painelQuantidade.add(checkBoxDez);
		checkBoxDez.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxDez.setBounds((int) (minhaLargura/3.3),(int) (minhaAltura/4.7),((int) botaoLargura/8), (tamanho));
		
		painelQuantidade.add(checkBoxVinte);
		checkBoxVinte.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxVinte.setBounds((int) (minhaLargura/3.3),(int) (minhaAltura/3.2),((int) botaoLargura/8), (tamanho));

		painelQuantidade.add(checkBoxTrinta);
		checkBoxTrinta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxTrinta.setBounds((int) (minhaLargura/3.3),(int) (minhaAltura/2.44),((int) botaoLargura/8), (tamanho));
		
		painelQuantidade.add(checkBoxQuarenta);
		checkBoxQuarenta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxQuarenta.setBounds((int) (minhaLargura/2.49),(int) (minhaAltura/4.7),((int) botaoLargura/8), (tamanho));
		
		painelQuantidade.add(checkBoxCinquenta);
		checkBoxCinquenta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxCinquenta.setBounds((int) (minhaLargura/2.49),(int) (minhaAltura/3.2),((int) botaoLargura/8), (tamanho));

		painelQuantidade.add(checkBoxSessenta);
		checkBoxSessenta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxSessenta.setBounds((int) (minhaLargura/2.49),(int) (minhaAltura/2.44),((int) botaoLargura/8), (tamanho));
		
		painelQuantidade.add(checkBoxSetenta);
		checkBoxSetenta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxSetenta.setBounds((minhaLargura/2),(int) (minhaAltura/4.7),((int) botaoLargura/8), (tamanho));
		
		painelQuantidade.add(checkBoxOitenta);
		checkBoxOitenta.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxOitenta.setBounds((minhaLargura/2),(int) (minhaAltura/3.2),((int) botaoLargura/8), (tamanho));

		painelQuantidade.add(checkBoxNoventa);
		checkBoxNoventa.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.1)));
		checkBoxNoventa.setBounds((minhaLargura/2),(int) (minhaAltura/2.44),((int) botaoLargura/8), (tamanho));

		painelQuantidade.add(btnVoltarParaNivel);
		btnVoltarParaNivel.setBounds(minhaLargura/13,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnVoltarParaNivel.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnVoltarParaNivel.setBackground(Color.WHITE);
		btnVoltarParaNivel.setVisible(true);
		
		painelQuantidade.add(btnAvancarParaProva);
		btnAvancarParaProva.setBounds(minhaLargura/2,(int) (minhaAltura/1.8),(int) botaoLargura,botaoAltura);
		btnAvancarParaProva.setFont(new Font("Cambria", Font.BOLD, (int) (tamanho/1.2)));
		btnAvancarParaProva.setBackground(Color.WHITE);
		btnAvancarParaProva.setVisible(true);
		
		
		
		painelProva.setLayout(null);
		painelProva.setBounds((int) (minhaLargura / 12), (int) (minhaAltura / 30),
				(int) (minhaLargura / 1.2), (int) (minhaAltura / 1.4));
		painelProva.setBackground(Color.WHITE);
		painelProva.setVisible(false);
		
		textProva.setBounds((int) (minhaLargura/4.68), minhaAltura/990,  
				(int) (botaoLargura * 3),(int) botaoAltura * 3);
		textProva.setEditable(false);
		textProva.setVisible(true);
		
		textProva.setText("TESTE");
		
		painelProva.add(textProva);
		fTela.add(painelProva);
		
		
		
		
		painelLogo.add(lLogo);
		fTela.setVisible(true);
		acaoComecar();
		acaoRecomecar();
		acaoVoltarParaLogo();
		acaoAvancarParaNivel();
		acaoVoltarParaMateria();
		acaoAvancarParaQuantidade();
		acaoVoltarParaNivel();
	}

	static void acaoComecar() {
		btnComecar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelLogo.setVisible(false);
				painelMateria.setVisible(true);
				btnComecar.setVisible(false);
				btnRecomecar.setVisible(true);
			}
		});
	}
	
	static void acaoRecomecar() {
		btnRecomecar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelLogo.setVisible(false);
				painelMateria.setVisible(true);
			}
		});
	}
	
	static void acaoVoltarParaLogo() {
		btnVoltarParaLogo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelMateria.setVisible(false);
				painelLogo.setVisible(true);

			}
		});
	}

	static void acaoAvancarParaNivel() {
		btnAvancarParaNivel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelMateria.setVisible(false);
				painelNivel.setVisible(true);

			}
		});
	}
	
	static void acaoVoltarParaMateria() {
		btnVoltarParaMateria.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelNivel.setVisible(false);
				painelMateria.setVisible(true);

			}
		});
	}
	
	static void acaoAvancarParaQuantidade() {
		btnAvancarParaQuantidadeQuestao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelNivel.setVisible(false);
				painelQuantidade.setVisible(true);

			}
		});
	}
	
	static void acaoVoltarParaNivel() {
		btnVoltarParaNivel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelQuantidade.setVisible(false);
				painelNivel.setVisible(true);

			}
		});
	}
	
	static void acaoAvancarParaProva() {
		btnAvancarParaProva.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				atualisarTelaProva();
				painelQuantidade.setVisible(false);
				painelNivel.setVisible(false);
				painelProva.setVisible(true);
			}
		});
	}
	
	static void atualisarTelaProva() {
		try {
			Statement conexaoComOBanco = conectarComOBancoDeDados();
			
			if (rSetPerguntas == null) {
				rSetPerguntas = obterQuestoes(conexaoComOBanco);
			}
			rSetRespostas = obterRespostas(rSetPerguntas.getString(1), conexaoComOBanco);
			
			textProva.setText(rSetPerguntas.getString(2));
			
			checkBoxRespostaA.setText(rSetRespostas.getString(1));
			rSetRespostas.next();
			checkBoxRespostaB.setText(rSetRespostas.getString(1));
			rSetRespostas.next();
			checkBoxRespostaC.setText(rSetRespostas.getString(1));
			rSetRespostas.next();
			checkBoxRespostaD.setText(rSetRespostas.getString(1));
			rSetRespostas.next();
			checkBoxRespostaE.setText(rSetRespostas.getString(1));
			rSetRespostas.next();			
			
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
			
			ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\", \"QUESTAO\" FROM \"QUESTOES\" WHERE " + materiasEscolhidas);
			
			return rs;
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
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%humanas% '");
				String id = rs.getString(1);
				materiasEscolhidas = "\"MATERIA\" = " + id + " ";
			}
			if(checkBoxCienciasDaNatureza.isSelected()) {
				if(materiasEscolhidas.equals("") == false) {
					materiasEscolhidas = materiasEscolhidas + "OR ";
				}
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%natureza% '");
				String id = rs.getString(1);
				materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
			}
			if(checkBoxLinguagensECodigos.isSelected()) {
				if(materiasEscolhidas.equals("") == false) {
					materiasEscolhidas = materiasEscolhidas + "OR ";
				}
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%linguagens% '");
				String id = rs.getString(1);
				materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
			}
			if(checkBoxMatematica.isSelected()) {
				if(materiasEscolhidas.equals("") == false) {
					materiasEscolhidas = materiasEscolhidas + "OR ";
				}
				ResultSet rs = conexaoComOBanco.executeQuery("SELECT \"ID\" FROM \"MATERIA\" m WHERE m.\"NOME\" LIKE '%matematica% '");
				String id = rs.getString(1);
				materiasEscolhidas = materiasEscolhidas + "\"MATERIA\" = " + id + " ";
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao procurar materias. Erro: ", e);
		}
		return materiasEscolhidas;
	}

	public static void main(String[] args) {
		new Tela();
	}

}

class ConexaoBd {
	private static final String LOGIN_BANCO_DE_DADOS = "login bd aqui";
	private static final String SENHA_BANCO_DE_DADOS = "senha aqui";
	private static final String URL_BANCO_DE_DADOS = "endereço do banco aqui";
	
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
