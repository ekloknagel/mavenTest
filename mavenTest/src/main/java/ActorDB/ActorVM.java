package ActorDB;

import java.util.ArrayList;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Notification;

public class ActorVM {

	ActorController ac = new ActorController();
	ActorController cc = new ActorController();
	private ArrayList<Actor> al;
	private ArrayList<Customer> cl;




	public ArrayList<Customer> getCl() {
		return cl;
	}

	public void setCl(ArrayList<Customer> cl) {
		this.cl = cl;
	}

	private Actor selectedActor;



	public ActorController getAc() {
		return ac;
	}

	public void setAc(ActorController ac) {
		this.ac = ac;
	}

	public ArrayList<Actor> getAl() {
		return al;
	}

	public void setAl(ArrayList<Actor> al) {
		this.al = al;
	}

	public Actor getSelectedActor() {
		return selectedActor;
	}

	public void setSelectedActor(Actor selectedActor) {
		this.selectedActor = selectedActor;
	}

	@Init
	public void init() {
		al = ac.getallActor();
		cl = cc.getallCustomer();
	}

	@Command
	public void updateData() {
		Notification.show(ac.updateData(selectedActor));
	}

	@Command
	@NotifyChange("*")
	public void createNewActor() {
		Notification.show(ac.createNewActor());
		al = ac.getallActor();
	}
}
