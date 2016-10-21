//make this extend Packet at some point

public class HandshakePacket extends Packet {
	private String key1,key2;
	private String destinationAddress;
	private String sourceAddress;

	public HandshakePacket (AccessPoint source, Client destination){
		super (source,destination);
		sourceAddress=source.getAddress();
		destinationAddress=destination.getAddress();
		key1=source.getKey();
		key2=destination.getKey();
		}

	public HandshakePacket (Client source, AccessPoint destination){
		super(source,destination);
		sourceAddress=source.getAddress();
		destinationAddress=destination.getAddress();
		key1=source.getKey();
		key2=destination.getKey();
		}

		public boolean checkKeys(){
			if(key1==key2) return true;
			return false;

		}

		public String getDestination(){
			return destinationAddress;
		}


	}