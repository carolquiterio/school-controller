import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ClienteWS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class window extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ra;
	private JLabel lblNota;
	private JLabel lblFreq;
	private JTextField textField_cod;
	private JTextField textField_nota;
	private JTextField textField_freq;
	private JTextArea textArea_result = new JTextArea();
	
	Fila<Resultado> filaResultado = new Fila<Resultado>();
	
	public void trataBtnIncluir() throws Exception
	{
		try
		{           
			textArea_result.setText("");
			
			short ra =  getRa(textField_ra.getText());
			int cod = getCod(textField_cod.getText());
			double nota = getNota(textField_nota.getText());
			double freq = getFreq(textField_freq.getText());
			
			Resultado re = new Resultado(ra, cod, nota, freq);
			
			filaResultado.guardeUmItem(re);
		}
		catch(Exception err)
		{
			textArea_result.setText(err.getMessage());
		}
	}
	
	public void trataBtnListar() throws Exception
	{
		try
		{     
			String resultText = null;
		
			while (!filaResultado.isVazia())
			{
				Resultado resultadoDaFila = filaResultado.recupereUmItem();
					
				Logradouro log = (Logradouro) ClienteWS.postObjeto(resultadoDaFila, Logradouro.class, "http://localhost:3000/matricula");
				
				if(log.getLogradouro().equals("Sucesso na consulta!"))
				{
					resultText = resultText + " Sucesso: " + resultadoDaFila.getRa() + " na disciplina:" + resultadoDaFila.getCod() + "com nota: " + resultadoDaFila.getNota() + " e freq: " + resultadoDaFila.getFreq() + "\n";
					textArea_result.setText(resultText);
				}
				
				else
				{
					resultText = resultText + " Erro: " + resultadoDaFila.getRa() + " na disciplina: " + resultadoDaFila.getCod() + " - " + log.getLogradouro() + "\n";
					textArea_result.setText(resultText);
				}
				
				filaResultado.removaUmItem();
			}
		
		}
		catch(Exception err)
		{
			textArea_result.setText(err.getMessage());
		}
	}
	
	
	public void limpaCampos() throws Exception
	{
		try
		{
			textField_ra.setText("");
			textField_nota.setText("");
			textField_freq.setText("");
			textField_cod.setText("");
		}
		catch(Exception err)
		{
			textArea_result.setText(err.getMessage());
		}
		
	}
	
	 private short getRa(String raStr) throws Exception {
	        try {
	            short ret = Short.parseShort(raStr);
	            return ret;
	        } catch (Exception ex) {
	            textField_ra.setText("");
	            throw new Exception("Digite um RA valido!");
	        }
	    }

	    private int getCod(String codStr) throws Exception {
	        try {
	            int ret = Integer.parseInt(codStr);
	            return ret;
	        } catch (Exception ex) {
	            textField_cod.setText("");
	            throw new Exception("Digite um codigo válido!");
	        }
	    }

	    private double getNota(String notaStr) throws Exception {
	        try {
	            double ret = Integer.parseInt(notaStr);
	            return ret;
	        } catch (Exception ex) {
	            textField_nota.setText("");
	            throw new Exception("Digite uma nota válida!");
	        }
	    }

	    private double getFreq(String freqStr) throws Exception {
	        try {
	            double ret = Double.parseDouble(freqStr);
	            return ret;
	        } catch (Exception ex) {
	            textField_freq.setText("");
	            throw new Exception("Digite uma frequencia válida!");
	        }
	    }		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window frame = new window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_ra = new JTextField();
		textField_ra.setBounds(57, 11, 145, 27);
		contentPane.add(textField_ra);
		textField_ra.setColumns(10);
		
		JLabel lblRa = new JLabel("RA:");
		lblRa.setBounds(10, 17, 46, 14);
		contentPane.add(lblRa);
		
		JLabel lblCod = new JLabel("COD:");
		lblCod.setBounds(232, 16, 46, 14);
		contentPane.add(lblCod);
		
		lblNota = new JLabel("NOTA:");
		lblNota.setBounds(10, 55, 46, 14);
		contentPane.add(lblNota);
		
		lblFreq = new JLabel("FREQ:");
		lblFreq.setBounds(232, 56, 46, 14);
		contentPane.add(lblFreq);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(279, 87, 145, 23);
		contentPane.add(btnIncluir);
		
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					trataBtnIncluir();
					limpaCampos();
						
				}
				catch(Exception err)
				{}
			}
			
		});
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					trataBtnListar();
					limpaCampos();
						
				}
				catch(Exception err)
				{}
			}
		});
		btnListar.setBounds(57, 87, 145, 23);
		contentPane.add(btnListar);
		
		textField_cod = new JTextField();
		textField_cod.setColumns(10);
		textField_cod.setBounds(279, 11, 145, 27);
		contentPane.add(textField_cod);
		
		textField_nota = new JTextField();
		textField_nota.setColumns(10);
		textField_nota.setBounds(57, 49, 145, 27);
		contentPane.add(textField_nota);
		
		textField_freq = new JTextField();
		textField_freq.setColumns(10);
		textField_freq.setBounds(279, 49, 145, 27);
		contentPane.add(textField_freq);
		textArea_result.setLineWrap(true);
		
		
		textArea_result.setBounds(10, 121, 414, 129);
		contentPane.add(textArea_result);
	}
}
