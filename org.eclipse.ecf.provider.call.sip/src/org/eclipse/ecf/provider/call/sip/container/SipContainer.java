/***********************************************************************************
 * Copyright (c) 2009 Harshana Eranga Martin and others. All rights reserved. This 
 * program and the accompanying materials are made available under the terms of 
 * the Eclipse Public License v1.0 which accompanies this distribution, and is 
 * available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Harshana Eranga Martin <harshana05@gmail.com> - initial API and implementation
************************************************************************************/
package org.eclipse.ecf.provider.call.sip.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerListener;
import org.eclipse.ecf.core.events.ContainerConnectingEvent;
import org.eclipse.ecf.core.events.ContainerDisposeEvent;
import org.eclipse.ecf.core.events.IContainerEvent;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.provider.call.sip.SipCallSessionContainerAdapter;
import org.eclipse.ecf.provider.call.sip.identity.SipLocalParticipant;
import org.eclipse.ecf.provider.call.sip.identity.SipUriID;
import org.eclipse.ecf.provider.call.sip.identity.SipUriNamespace;


public class SipContainer implements IContainer {

	
	private SipLocalParticipant sipUser;
	private SipUriID sipUserId;
	private String sipUserName;
	private String sipUserPassword;
	private SipCallSessionContainerAdapter callAdapter;
	private final List containerListeners = new ArrayList(5);
	
	public SipUriID getUserId(){
		return sipUserId;
	}
	
	/**
	 * 
	 */
	public SipContainer(SipLocalParticipant sipUser) {
		this.sipUser=sipUser;
		sipUserId=this.sipUser.getInitiatorID();
		sipUserName=this.sipUser.getInitiatorName();
		sipUserPassword=this.sipUser.getInitiatorPassword();
		try{
		callAdapter=new SipCallSessionContainerAdapter(this);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SipCallSessionContainerAdapter getCallAdapter(){
		return callAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#addListener(org.eclipse.ecf.core.IContainerListener)
	 */
	@Override

	public void addListener(IContainerListener l) {
		synchronized (containerListeners) {
			containerListeners.add(l);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#removeListener(org.eclipse.ecf.core.IContainerListener)
	 */
	@Override
	

	public void removeListener(IContainerListener l) {
		synchronized (containerListeners) {
			containerListeners.remove(l);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#dispose()
	 */
	@Override
	
	public void dispose() {
		fireContainerEvent(new ContainerDisposeEvent(getID()));
		synchronized (containerListeners) {
			containerListeners.clear();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#connect(org.eclipse.ecf.core.identity.ID, org.eclipse.ecf.core.security.IConnectContext)
	 */
	@Override
	public void connect(ID targetId, IConnectContext connectContext)
			throws ContainerConnectException {
		
		callAdapter.createVoiceConnection(targetId, connectContext);
		
		fireContainerEvent(new ContainerConnectingEvent(getID(), targetId));
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#disconnect()
	 */
	@Override
	public void disconnect() {
	
		callAdapter.disconnect();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class serviceType) {
		if(serviceType.equals(SipCallSessionContainerAdapter.class)){
			return callAdapter;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#getConnectNamespace()
	 */
	@Override
	public Namespace getConnectNamespace() {
		return new SipUriNamespace();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.IContainer#getConnectedID()
	 */
	@Override
	public ID getConnectedID() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.ecf.core.identity.IIdentifiable#getID()
	 */
	@Override
	public ID getID() {
		return sipUserId;
	}

	
	protected void fireContainerEvent(IContainerEvent event) {
		List toNotify = null;
		// Copy array
		synchronized (containerListeners) {
			toNotify = new ArrayList(containerListeners);
		}
		// Notify all in toNotify
		for (Iterator i = toNotify.iterator(); i.hasNext();) {
			IContainerListener l = (IContainerListener) i.next();
			l.handleEvent(event);
		}
	}
}
