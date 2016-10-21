public class Client extends NetworkDevice{
	private AccessPoint accessPoint;
	private Channel ch;

	public Client(String address, String key){
	super (address, key);
	}
	
	
	//NOT WORKING ATM ?!?!?!?!?
	public void joinChannel(Channel ch){
	this.ch=ch;
	ch.addClient(this);
	this.ch=ch;
}

public boolean readChannel(Channel ch){
	this.ch=ch;
	for (int i=0;i<ch.getTrafficSize();i++){
	Packet h=ch.getAt(i);
	HandshakePacket a= new HandshakePacket(ch.getAp(),ch.getClient(i));
	if (a.getDestination()==address && a.checkKeys()==true) return true;
	}
	return false;
}

public AccessPoint getAP(){
	return accessPoint;
}

public void setAP(AccessPoint b){
	this.accessPoint=b;
}
public Channel getChannel(){
	return ch;
}

}

	