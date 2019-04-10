package pizzeria.exception;

public class Mysqlexception extends RuntimeException {
private static final long serialVersionUID = -8898974333236523950L;
	
	public Mysqlexception(String msg) 
	{
		super(msg);
	}
	
	public Mysqlexception(Exception e) 
	{
		super(e);
	}

	public Mysqlexception()
	{
		super();
	}

	public Mysqlexception(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public Mysqlexception(Throwable arg0)
	{
		super(arg0);
	}

}
