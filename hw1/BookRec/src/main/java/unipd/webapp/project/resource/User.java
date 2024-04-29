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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.fasterxml.jackson.core.*;

/**
 * Represents the data about an user.
 * 
 * @version 1.00
 * @since 1.00
 */
public class User extends AbstractResource {

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

	@Override
	protected final void writeJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("User");

		jg.writeStartObject();

		jg.writeStringField("name", name);

		jg.writeStringField("email", email);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	/**
	 * Creates a {@code User} from its JSON representation.
	 *
	 * @param in the input stream containing the JSON document.
	 *
	 * @return the {@code User} created from the JSON representation.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public static User fromJSON(final InputStream in) throws IOException  {

		// the fields read from JSON
		String name = null;
		String email = null;


		try {
			final JsonParser jp = JSON_FACTORY.createParser(in);

			// while we are not on the start of an element or the element is not
			// a token element, advance to the next element (if any)
			while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"user".equals(jp.getCurrentName())) {

				// there are no more events
				if (jp.nextToken() == null) {
					LOGGER.error("No User object found in the stream.");
					throw new EOFException("Unable to parse JSON: no User object found.");
				}
			}

			while (jp.nextToken() != JsonToken.END_OBJECT) {

				if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

					switch (jp.getCurrentName()) {
						case "name":
							jp.nextToken();
							name = jp.getText();
							break;
						case "email":
							jp.nextToken();
							email = jp.getText();
							break;

					}
				}
			}
		} catch(IOException e) {
			LOGGER.error("Unable to parse an User object from JSON.", e);
			throw e;
		}

		return new User(name,email,null);
	}

}
