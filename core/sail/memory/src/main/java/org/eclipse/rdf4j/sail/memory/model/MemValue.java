/*******************************************************************************
 * Copyright (c) 2015 Eclipse RDF4J contributors, Aduna, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.sail.memory.model;

import org.eclipse.rdf4j.model.Value;

/**
 * A MemoryStore-specific extension of the Value interface, giving it node
 * properties.
 */
public interface MemValue extends Value {

	/*-----------*
	 * Constants *
	 *-----------*/

	/**
	 * A shared empty MemStatementList that is returned by MemURI and MemBNode to
	 * represent an empty list. The use of a shared list reduces memory usage.
	 */
	static final MemStatementList EMPTY_LIST = new MemStatementList(0);

	/*---------*
	 * Methods *
	 *---------*/

	/**
	 * Returns the object that created this MemValue. MemValues are only unique
	 * within a single repository, but an application could use several
	 * repositories at the same time, passing MemValues generated by one Sail to
	 * another Sail. In such situations, the MemValue of the first Sail cannot be
	 * used by the second Sail.
	 */
	public Object getCreator();

	/**
	 * Checks whether this MemValue has any statements. A MemValue object has
	 * statements if there is at least one statement where it is used as the
	 * subject, predicate, object or context value.
	 * 
	 * @return <tt>true</tt> if the MemValue has statements, <tt>false</tt>
	 *         otherwise.
	 */
	public boolean hasStatements();

	/**
	 * Gets the list of statements for which this MemValue is the object.
	 * 
	 * @return A MemStatementList containing the statements.
	 */
	public MemStatementList getObjectStatementList();

	/**
	 * Gets the number of statements for which this MemValue is the object.
	 * 
	 * @return An integer larger than or equal to 0.
	 */
	public int getObjectStatementCount();

	/**
	 * Adds a statement to this MemValue's list of statements for which it is the
	 * object.
	 */
	public void addObjectStatement(MemStatement st);

	/**
	 * Removes a statement from this MemValue's list of statements for which it
	 * is the object.
	 */
	public void removeObjectStatement(MemStatement st);

	/**
	 * Removes statements from old snapshots (those that have expired at or
	 * before the specified snapshot version) from this MemValue's list of
	 * statements for which it is the object.
	 * 
	 * @param currentSnapshot
	 *        The current snapshot version.
	 */
	public void cleanSnapshotsFromObjectStatements(int currentSnapshot);
}
