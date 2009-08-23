package org.eclipse.ecf.provider.sip.identity;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.Namespace;

public class SipCallSessionNamespace extends Namespace{
	
	public static final String SCHEME = "Call-ID"; //$NON-NLS-1$
	public static final String NAME = "ecf.namespace.sip.call.session";

	/**
	 * 
	 */
	private static final long serialVersionUID = -2874309596864544598L;
	
	private String getInitFromExternalForm(Object[] args) {
		if (args == null || args.length < 1 || args[0] == null)
			return null;
		if (args[0] instanceof String) {
			final String arg = (String) args[0];
			if (arg.startsWith(getScheme() + Namespace.SCHEME_SEPARATOR)) {
				final int index = arg.indexOf(Namespace.SCHEME_SEPARATOR);
				if (index >= arg.length())
					return null;
				return arg.substring(index + 1);
			}
		}
		return null;
	}


	
	public ID createInstance(Object[] parameters) throws IDCreateException {
		try {
			final String init = getInitFromExternalForm(parameters);
			if (init != null)
				return new SipCallSessionID(this, init);
			return new SipCallSessionID(this, (String) parameters[0]);
		} catch (final Exception e) {
			throw new IDCreateException("Cannot Create CALL-ID");
		}
	}

	
	public String getScheme() {
		return SCHEME;
	}
	
	public String getName(){
		return NAME;
	}

}