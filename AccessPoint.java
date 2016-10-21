import java.util.ArrayList;
public class AccessPoint extends NetworkDevice{
private  ArrayList<Client> authorisedClients;
private Client client;
private Channel ch;

public AccessPoint(String address, String key){
	super (address, key);
	authorisedClients = new ArrayList<Client>();
}
public void joinChannel(Channel ch){
	this.ch=ch;
	ch.addAP(this);
}

public AccessPoint getAP(Channel ch){
	this.ch=ch;
	return ch.getAp();

}

public Channel getChannel(){
	return ch;
}

public boolean readChannel(Channel ch){
	this.ch=ch;
	for (int i=0;i<ch.getTrafficSize();i++){
	Packet h=ch.getAt(i);
	HandshakePacket a= new HandshakePacket(ch.getClient(i),ch.getAp());
	if (a.getDestination()==address && a.checkKeys()==true) return true;
	}
	return false;
}

public void authorise(Client c){
	authorisedClients.add(c);
}

public boolean isAuthorised(Client c){
	if(authorisedClients.contains(c)==true) return true;
	return false;
}
public String getDevices(){
	String devices="";
	for (Client c:authorisedClients){
		devices+=c + ",";
	}
	return devices;
}

}

