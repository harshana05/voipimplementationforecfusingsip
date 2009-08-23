/**
 * Aug 7, 2009
 * 10:29:34 PM
 * Administrator
 */
package org.eclipse.ecf.provider.sip.identity;

/**
 * @author Administrator
 *
 */
public class SipLocalParticipant {
	private SipUriID initiatorID;
	private String initiatorName;
	private String initiatorPassword;
	private String sipProxyServer;
	
	/**
	 * 
	 */
	public SipLocalParticipant(SipUriID initiatorID, String initiatorName,String initiatorPassword, String sipProxyServer) {
		this.initiatorID = initiatorID;
		this.initiatorName = initiatorName;
		this.initiatorPassword=initiatorPassword;
		this.sipProxyServer=sipProxyServer;
	}

	public SipUriID getInitiatorID() {
		return initiatorID;
	}

	public void setInitiatorID(SipUriID initiatorId) {
		initiatorID = initiatorId;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getInitiatorPassword() {
		return initiatorPassword;
	}

	public void setInitiatorPassword(String initiatorPassword) {
		this.initiatorPassword = initiatorPassword;
	}

	public String getSipProxyServer() {
		return sipProxyServer;
	}

	public void setSipProxyServer(String sipProxyServer) {
		this.sipProxyServer = sipProxyServer;
	}

	
	
}
