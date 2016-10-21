import java.util.ArrayList;
public class Network{
private int i;
private Client a;
private Channel ch;
private AccessPoint b;
private  ArrayList<Channel> channels;
private ArrayList<Channel> usedChannels;
private int connections=0;

public Network(){                   	//a constructor for a network
channels = new ArrayList<Channel>();
usedChannels=new ArrayList<Channel>();
}

public void createChannel(int i){      //a method that creates a channel
	this.i=i;
	Channel ch=new Channel(i);
	channels.add(ch);
}

public ArrayList getUsed(){     //a method that returns the channels in use in the given network
	return usedChannels;
}

public void createAccessPoint(String address, String key){      //a method that creates an AccessPoint and connects it to a Channel if there is one free or makes a new Channel if there isnt. Also lists the Channel as used.
AccessPoint ap= new AccessPoint(address,key);
int j=0;
System.out.println("Creating AccessPoint with address " + address + "  Key: " +key);
if (channels.size()==usedChannels.size()){
	for (j=0;j<channels.size();j++){
		if(j<channels.get(j).getID()) j=channels.get(j).getID();
	}
	Channel ch=new Channel(j);
	channels.add(ch);
for(int i=0;i<channels.size();i++){
	int initialised=0;
	if (channels.get(i).checkAP(ap)==false) {
		ap.joinChannel(channels.get(i)); 
		usedChannels.add(channels.get(i));
		initialised++;
}
if(initialised!=0) break;
}
}
else {
	for(int i=0;i<channels.size();i++){
	int initialised=0;
	if (channels.get(i).checkAP(ap)==false) {
		ap.joinChannel(channels.get(i)); 
		usedChannels.add(channels.get(i));
		initialised++;
}
if(initialised!=0) break;
}
}
}

public int extractIndex(Channel ch){
	return channels.indexOf(ch);
}

public String extractApAddress (Channel ch){
	return ch.getAp().getAddress();
}

public AccessPoint extractAP (int i){
	Channel ch=channels.get(i);
	return ch.getAp();
}
// HAAAAAAAAAAAAAAAAANDDDDDSHAAAAAAAAAAAAAAAAAAAAAAKE
 public void handshake (Client a,AccessPoint b) {
 	System.out.println("Client@" +a.getAddress()+" wants to connect to AccessPoint@"+ b.getAddress());
	if (a.getChannel()==null) {
		ch=b.getChannel();
		a.joinChannel(b.getChannel());
	HandshakePacket first=new HandshakePacket(a,b);
	ch.addHandshakePacket(first);
	System.out.println("Packet added to Channel " + b.getChannel().getID() +"  : HandshakePacket  Source:" + a.getAddress() + "  Destination:" +b.getAddress());
	if(b.readChannel(ch)==true) {
		b.authorise(a);
		HandshakePacket second=new HandshakePacket(b,a);
		ch.addHandshakePacket(second);	
		System.out.println("Packet added to Channel " + b.getChannel().getID() +"  : HandshakePacket  Source:" + b.getAddress() + "  Destination:" +a.getAddress());
	}
		if (a.readChannel(ch)==true) {
				if(usedChannels.contains(ch)==false) usedChannels.add(ch);
			a.setAP(b);
			connections++;
			System.out.println("Handshake successfull! Client@"+a.getAddress()+" is now connected to AccessPoint@"+ b.getAddress());
		}

		else {
			ch.removeClient(a);
			System.out.println("Handshake Failed!");
		}
		ch.clearChannel();
		System.out.println("There are "+connections+" connections");
}
}


public void communicate() {
	System.out.println("Activity burst:");
	for(int i=0;i<usedChannels.size();i++){
		Channel ch=usedChannels.get(i);
		ch.clearChannel();
		for(int j=0; j<ch.howManyClients(); j++){
			Packet p = new Packet(ch.getClient(j),ch.getAp());
			ch.addPacket(p);
			System.out.println("Packet added to Channel " + ch.getID() +"  Source:" + ch.getClient(j).getAddress() + "  Destination:" +ch.getAp().getAddress());
			if(ch.getAp().readChannel(ch)==true && ch.getAp().isAuthorised(ch.getClient(j))) {
			Packet q = new Packet(ch.getAp(),ch.getClient(j));
			ch.addPacket(q);
			System.out.println("Packet added to Channel " + ch.getID() +"  Source:" + ch.getAp().getAddress() + "  Destination:" +ch.getClient(j).getAddress());
			}
		}
	}
		}

public void start(Network n) throws InterruptedException{
	if (connections>0){
	for (int i=0;i<=5;i++){
		n.communicate();
		Thread.sleep(100);
	}
}
	else System.out.println("Network cannot start without any connected devices");
 }

}