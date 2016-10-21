public class NetworkDevice{
protected String address;
protected String key;

public NetworkDevice (String address, String key){
	this.address=address;
	this.key=key;
}
public String getAddress(){
	return address;
}
public String getKey(){
	return key;
}
}
