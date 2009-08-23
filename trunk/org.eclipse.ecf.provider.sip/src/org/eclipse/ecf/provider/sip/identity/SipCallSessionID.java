package org.eclipse.ecf.provider.sip.identity;

import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.identity.StringID;

public class SipCallSessionID extends StringID {

	protected SipCallSessionID(Namespace n, String s) {
		super(n, s);
	}
	

	public SipCallSessionID(String s) {
		this(IDFactory.getDefault().getNamespaceByName(
				SipCallSessionNamespace.NAME), s);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198185266927833611L;

}
