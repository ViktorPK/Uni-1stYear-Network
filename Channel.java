import java.util.ArrayList;
public class Channel{
	private int number;
	private ArrayList<Client> clients;
	private ArrayList<AccessPoint> aps;
	private ArrayList<Packet> traffic;
	private Client client;
	private Packet p;
	private AccessPoint ap;
	private HandshakePacket h;
	private String ch2;

	public Channel(int number){
		aps = new ArrayList<AccessPoint>();
		clients = new ArrayList<Client>();
		traffic = new ArrayList<Packet>() ;
	this.number=number;
}

public void addClient(Client client){
this.client=client;
clients.add(client);
}

public void removeClient(Client client){
this.client=client;
clients.remove(client);
}

public int getTrafficSize(){
	return traffic.size();
}

public Packet getAt(int n){
	return traffic.get(n);
}

public Client getClient(int n){
	return clients.get(n);
}

public int howManyClients(){
	return clients.size();
}

public void clearChannel(){
	traffic.clear();
}
public AccessPoint getAp(){
	return ap;
}

public int getID(){
	return number;
}


public void addPacket(Packet p){
this.p=p;
traffic.add((Packet) p);
}

public void addHandshakePacket(HandshakePacket h){
this.h=h;
traffic.add((HandshakePacket) h);
}


public void addAP(AccessPoint ap){
this.ap=ap;
aps.add(ap);
}

public boolean checkAP(AccessPoint ap){
 if (aps.size()==0) return false;
 return true;
}

//Do I even need this shit?

public String getTraffic(){
	String ch2="  ";
	for (int i=0;i<traffic.size();i++){
	ch2+=traffic.get(i) + ",";
	}
	return ch2;
}
/*
public String printCH3(){
	String ch3="";
	for (AccessPoint ap:aps){
		ch3+=ap + ",";
	}
	return ch3;
}
}
*/
}