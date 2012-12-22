package core;

import java.util.Date;
import java.util.LinkedList;

public class Comm extends Thought {		//Classe commentaires herite de Thought
										//Donc possibilite dans le futur de Commentaires sur Commentaires.
	private Profile owner;
	
	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public Comm(String content, Date date, Profile owner)
	{
		super(content, date);
		this.owner = owner;
	}
	
	public Comm(String content, Profile owner)
	{
		super(content);
		this.owner= owner;
	}
	
	public String toString()
	{
		return ("["+this.date.toString()+""+this.owner.toString()+" : "+this.content);
	}
	

}
