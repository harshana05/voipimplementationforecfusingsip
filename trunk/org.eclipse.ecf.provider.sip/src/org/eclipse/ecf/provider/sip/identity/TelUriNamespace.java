package org.eclipse.ecf.provider.sip.identity;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.Namespace;

public class TelUriNamespace extends Namespace{
	
	public static final String NAME = "ecf.namespace.tel.uri";
	public static final String SCHEME = "tel";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1704629492121702133L;
	
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
				return new TelUriID(this, init);
			return new TelUriID(this, (String) parameters[0]);
		} catch (final Exception e) {
			throw new IDCreateException("Cannot create Tel URI");
		}
	}

	public String getScheme() {
		return SCHEME;
	}

}
