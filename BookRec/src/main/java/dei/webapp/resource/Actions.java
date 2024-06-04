/*
 * Copyright (c) 2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dei.webapp.resource;

/**
 * Contains constants for the actions performed by the application.
 *
 * @version 1.00
 * @since 1.00
 */
public final class Actions {

	/**
	 * The creation of an user
	 */
	public static final String CREATE_USER = "CREATE_USER";
	public static final String LIST_USER = "LIST_USER";
	public static final String READ_USER = "READ_USER";

	/**
	 * The search of users, books and authors
	 */
	public static final String SEARCH_USER = "SEARCH_USER";

    public static final String SEARCH_AUTHOR = "SEARCH_AUTHOR";

    public static final String SEARCH_BOOK = "SEARCH_BOOK";
    
    public static final String SEARCH_BAR = "SEARCH_BAR";
    
	/**
	 * Removing users
	 */
    
    public static final String DELETE_USER = "DELETE_USER";

	/**
	 * This class can be neither instantiated nor sub-classed.
	 */
	private Actions() {
		throw new AssertionError(String.format("No instances of %s allowed.", Actions.class.getName()));
	}
}
