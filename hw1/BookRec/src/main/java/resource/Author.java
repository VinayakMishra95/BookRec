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
 * Represents the data about an author.
 * 
 * @version 1.00
 * @since 1.00
 */
public class Author {

	/**
	 * The name (identifier) of the author
	 */
	private final String name;

	/**
	 * The biography of the author
	 */
	private final String biography;

	/**
	 * Creates a new author
	 * 
	 * @param name
	 *            the name of the author.
	 * @param biography
	 *            the biography of the author.
	 */
	public User(final String name, final String biography) {
		this.name = name;
		this.biography = biography;
	}

	/**
	 * Returns the name of the author.
	 * 
	 * @return the name of the author.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Returns the biography of the author.
	 * 
	 * @return the biography of the author.
	 */
	public final String getBiography() {
		return biography;
	}

}
