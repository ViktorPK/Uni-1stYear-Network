public class Main{
public static void main (String args[]) throws InterruptedException{
	Network n=new Network();

	n.createAccessPoint("c5:e0:bc:13:80:ff","123456");
	n.createAccessPoint("c6:d0:ks:17:99:qq","aloha");
	

	AccessPoint p=(n.extractAP(0));
	AccessPoint s=(n.extractAP(1));


	Client b=new Client("a5:b1:s4:g4:p0","123456");
    Client c=new Client("c3:f6:k3:j9:c0","aloha");
	n.start(n);

	n.handshake(b,p);
	n.handshake(c,s);
	n.start(n);
}
}