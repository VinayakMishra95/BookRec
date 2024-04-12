/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific languEmail governing permissions and
 * limitations under the License.
 */

package unipd.webapp.project.resource;

/**
 * Represents the data about an user.
 * 
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public class User {

	/**
	 * The badge number (identifier) of the user
	 */
	private final int id;

	/**
	 * The name of the user
	 */
	private final String name;

	/**
	 * The Email of the user
	 */
	private final String email;

	/**
	 * The Password_hash of the user
	 */
	private final String password_hash;

	/**
	 * Creates a new user
	 * 
	 * @param badge
	 *            the id number of the user
	 * @param name
	 *            the name of the user.
	 * @param Email
	 *            the email of the user.
	 * @param password_hash
	 *            the password_hash of the user
	 */
	public User(final int id, final String name, final String email, final String password_hash) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password_hash = password_hash;
	}

	/**
	 * Returns the badge number of the user.
	 * 
	 * @return the badge number of the user.
	 */
	public final int getId() {
		return id;
	}

	/**
	 * Returns the name of the user.
	 * 
	 * @return the name of the user.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Returns the Email of the user.
	 * 
	 * @return the Email of the user.
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Returns the Password_hash of the user.
	 * 
	 * @return the Password_hash of the user.
	 */
	public final String getPassword_hash() {
		return password_hash;
	}

}
