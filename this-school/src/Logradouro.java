public class Logradouro
{
    private String logradouro;
    
    public Logradouro ( String logradouro) throws Exception
    {
    	this.setLogradouro  (logradouro);
    }
    
    public  String getLogradouro ()
    {
        return this.logradouro;
    }
    
    public void setLogradouro (String logradouro) throws Exception
    {
        if (logradouro==null || logradouro.length()==0)
            throw new Exception ("Logradouro ausente");

        this.logradouro = logradouro;
    }   

    // exigencia do mapeador de JSon
    public Logradouro () {}

    public String toString ()
    {
        return "Resultado: "+
               this.logradouro;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;        
        
        Logradouro objLog = (Logradouro) obj;
        
        if(!this.logradouro.equals(objLog.logradouro))
            return false;
 
        return true;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.logradouro .hashCode();

        return ret;
    }

    public Logradouro (Logradouro modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.logradouro  = modelo.logradouro;
    }

    public Object clone ()
    {
        Logradouro ret=null;

        try
        {
            ret = new Logradouro (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}