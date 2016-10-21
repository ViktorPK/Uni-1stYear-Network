
public class Packet{
	protected String destinationAddress;
	protected String sourceAddress;

	public Packet (AccessPoint source, Client destination){
		sourceAddress=source.getAddress();
		destinationAddress=destination.getAddress();
		}

	public Packet (Client source, AccessPoint destination){
		sourceAddress=source.getAddress();
		destinationAddress=destination.getAddress();
		}

		public String packetData(){
			return (destinationAddress +" "+sourceAddress);

		}
}