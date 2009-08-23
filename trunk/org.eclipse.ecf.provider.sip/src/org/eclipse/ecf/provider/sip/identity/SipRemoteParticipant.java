/**
 * Aug 7, 2009
 * 10:33:38 PM
 * Administrator
 */
package org.eclipse.ecf.provider.sip.identity;

/**
 * @author Administrator
 *
 */
public class SipRemoteParticipant {
	private SipUriID receiverID;
	private String receiverName;
	
	/**
	 * 
	 */
	public SipRemoteParticipant(SipUriID receiverID, String receiverName) {
		this.receiverID = receiverID;
		this.receiverName = receiverName;
	}

	public SipUriID getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(SipUriID receiverId) {
		receiverID = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	
}
