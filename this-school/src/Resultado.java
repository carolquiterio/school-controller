public class Resultado
{
	private short ra;
	private int cod;
	private double nota;
	private double freq;

	public Resultado (short ra, int cod, double nota, double freq) throws Exception
	{
		this.setRa(ra);
		this.setCod(cod);
		this.setNota(nota);
		this.setFreq(freq);
	}

	public void setRa(short ra) throws Exception
	{
		if (ra <= 0)
			throw new Exception("Insira um ra valido!");

		this.ra = ra;
	}

	public void setCod(int cod) throws Exception
	{
		if (cod <= 0)
			throw new Exception("Insira um codigo valido!");

		this.cod = cod;
	}

	public void setNota(double nota) throws Exception
	{
		if (nota < 0 || nota > 10)
			throw new Exception("Insira uma nota valida!");

		this.nota = nota;
	}

	public void setFreq(double freq) throws Exception
	{
		if (freq < 0 || freq > 1)
			throw new Exception ("Insira uma frequencia valida!");

		this.freq = freq;
	}

	public short getRa()
	{
		return this.ra;
	}

	public int getCod()
	{
		return this.cod;
	}

	public double getNota()
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

		ret = ret + new Double(this.nota).hashCode();
		ret = ret + new Short (this.ra).hashCode();
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
	    {} // nao trato, pq this nunca � null e construtor de
	           // copia da excecao qdo seu parametro for null

	    return ret;
    }
}