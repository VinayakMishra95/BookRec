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

package dei.webapp.resource;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import com.fasterxml.jackson.core.*;

/**
 * Represents the data about an user.
 * 
 * @version 1.00
 * @since 1.00
 */
public class User {

	/**
	 * The name (identifier) of the user
	 */
	private final String name;

	/**
	 * The e-mail of the user
	 */
	private final String email;

	/**
	 * The password of the user
	 */
	private final String password;

	/**
	 * Creates a new user
	 * 
	 * @param name
	 *            the name of the user.
	 * @param email
	 *            the email of the user.
	 * @param password
	 *            the password of the user
	 */
	public User(final String name, final String email, final String password) {
		this.name = name;
		this.email = email;
		this.password = password;
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
	 * Returns the email of the user.
	 * 
	 * @return the email of the user.
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return the password of the user.
	 */
	public final String getPassword() {
		return password;
	}
}
