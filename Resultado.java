public class Resultado
{

	private String ra;
	private int cod;
	private int nota;
	private double freq;

	public Resultado (String ra, int cod, int nota, double freq) throws Exception
	{
		this.setRa(ra);
		this.setCod(cod);
		this.setNota(nota);
		this.setFreq(freq);
	}

	public void setRa(String ra) throws Exception
	{
		if (ra == null || ra.equals(""))
			throw new Exception("Insira um ra válido!");

		this.ra = ra;
	}

	public void setCod(int cod) throws Exception
	{
		if (cod <= 0)
			throw new Exception("Insira um codigo válido!");

		this.cod = cod;
	}

	public void setNota(int nota) throws Exception
	{
		if (nota < 0 || nota > 10)
			throw new Exception("Insira uma nota válida!");

		this.nota = nota;
	}

	public void setFreq(double freq) throws Exception
	{
		if (nota < 0 || nota > 1)
			throw new Exception ("Insira uma frequencia válida!");

		this.freq = freq;
	}

	public String getRa()
	{
		return this.ra;
	}

	public int getCod()
	{
		return this.cod;
	}

	public int getNota()
	{
		return this.nota;
	}

	public double getFreq()
	{
		return this.freq;
	}

	public String toString ()
	{
		String ret="";

		ret +="Ra : " + this.ra+"\n";
		ret +="Codigo: : " + this.cod+"\n";
		ret += "Nota : " + this.nota+"\n";
		ret +="Freq : " + this.freq+"\n";

		return ret;
	}

	public int hashCode ()
	{
		int ret=666;

		ret = ret + new Integer(this.nota).hashCode();
		ret = ret + this.ra.hashCode();
		ret = ret + new Integer (this.cod).hashCode();
		ret = ret + new Double (this.freq).hashCode();

		return ret;
	}

	public Resultado (Resultado modelo) throws Exception
	{
		this.ra = modelo.ra; // nao clono, pq nao eh objeto
		this.cod = modelo.cod;
		this.nota = modelo.nota;
		this.freq = modelo.freq;
	}

	public Object clone ()
	{
		Resultado ret=null;

	    try
	    {
	    	ret = new Resultado (this);
	    }
	    catch (Exception erro)
	    {} // nao trato, pq this nunca é null e construtor de
	           // copia da excecao qdo seu parametro for null

	    return ret;
    }

}