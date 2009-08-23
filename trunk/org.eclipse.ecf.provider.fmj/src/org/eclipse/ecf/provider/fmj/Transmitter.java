/**
 * Aug 14, 2009
 * 8:50:58 PM
 * Administrator
 */
package org.eclipse.ecf.provider.fmj;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.media.Processor;
import javax.media.protocol.DataSource;
import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;

/**
 * @author Administrator
 *
 */
public class Transmitter implements Runnable{

	
	DataSource dataOutput;
	InetAddress remoteAudioAddress;
	RTPManager manager;
	Processor processor;
	

	
	
	public void initTransmitter(String remoteAudioAddress,DataSource dataOutput,RTPManager manager) throws UnknownHostException{
		this.remoteAudioAddress=InetAddress.getByName(remoteAudioAddress);	
		this.dataOutput=dataOutput;
		this.manager=manager;
	}
	
	
	
	public Processor getProcessor() {
		return processor;
	}



	public void setProcessor(Processor processor) {
		this.processor = processor;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		try {
			
			
			SendStream stream=manager.createSendStream(dataOutput, 0);
			System.out.println("Processor = "+processor);
			System.out.println("Stream = "+stream);
			stream.start();
			
			processor.start();
			
			System.err.println("Transmitter Transmitting..");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	
	public DataSource getDataOutput() {
		return dataOutput;
	}

	public void setDataOutput(DataSource dataOutput) {
		this.dataOutput = dataOutput;
	}



	public InetAddress getRemoteAudioAddress() {
		return remoteAudioAddress;
	}



	public void setRemoteAudioAddress(InetAddress remoteAudioAddress) {
		this.remoteAudioAddress = remoteAudioAddress;
	}



	public RTPManager getManager() {
		return manager;
	}



	public void setManager(RTPManager manager) {
		this.manager = manager;
	}
	
	
	
    

}
